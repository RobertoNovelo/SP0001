package com.smartplace.bombasmejorada.tabs.hidros;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.smartplace.bombasmejorada.R;
import com.smartplace.bombasmejorada.tabs.DataManager;
import com.smartplace.bombasmejorada.tabs.TabsMainActivity;

/**
 * Created by ROBERTO on 28/06/13.
 */
public class HidrosFragment4 extends Fragment {

    private TextView txt_instalacion,
                        txt_salidas,
                        txt_factor_salida,
                        txt_desnivel,
                        txt_max_long,
                        txt_presion,
                        txt_porcentaje,
                        txt_gasto,
                        txt_carga,
                        txt_diametro;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.tab_hidros_4, container, false);

        txt_instalacion = (TextView)view.findViewById(R.id.instalacionHidros);
        txt_salidas = (TextView)view.findViewById(R.id.salidasHidros);
        txt_factor_salida = (TextView)view.findViewById(R.id.factorSalida);
        txt_desnivel = (TextView)view.findViewById(R.id.desnivelMax);
        txt_max_long = (TextView)view.findViewById(R.id.longitudTuberia);
        txt_presion = (TextView)view.findViewById(R.id.presionSalida);
        txt_porcentaje = (TextView)view.findViewById(R.id.porcentajePerdidas);
        txt_gasto = (TextView)view.findViewById(R.id.gastoPico);
        txt_carga = (TextView)view.findViewById(R.id.cargaDinamica);
        txt_diametro = (TextView)view.findViewById(R.id.diametroTubo);

return view;

    }

    @Override
    public void onResume ()
    {
        super.onResume();

        Button btnEnviar = (Button) getActivity().findViewById(R.id.enviarbutton);
        DataManager dataManager =  ((TabsMainActivity)getActivity()).getDataManager();

        txt_instalacion.setText(dataManager.Edificio);
        txt_salidas.setText(String.valueOf(dataManager.hSalidas)+" ");
        txt_factor_salida.setText(String.valueOf(dataManager.hPresionSalida)+" lpm/salida");
        txt_desnivel.setText(String.valueOf(dataManager.hDistVertical)+" mts");
        txt_max_long.setText(String.valueOf(dataManager.hLongSalida)+" mts");
        txt_presion.setText(String.valueOf(dataManager.hPresionSalida)+" psi");
        txt_porcentaje.setText(String.valueOf("no se"+"%"));
        txt_gasto.setText(String.valueOf("no se"+" lpm"));
        txt_carga.setText(String.valueOf("no se"+" psi"));
        txt_diametro.setText(String.valueOf("no se"+'"'));

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DataManager dataManager = ((TabsMainActivity)getActivity()).getDataManager();
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"enlinea@bombasmejorada.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, Html.fromHtml("Solicito Informaci&oacute;n v&iacute;a Android").toString());
                i.putExtra(Intent.EXTRA_TEXT,
                        "Bombas - Hoja de cálculo: " + Html.fromHtml("<br/><br/>") +
                                "Instalación: " + Html.fromHtml("<br/>") + dataManager.Edificio + Html.fromHtml("<br/><br/>") +
                                "Salidas: " + Html.fromHtml("<br/>") + dataManager.hSalidas + Html.fromHtml("<br/><br/>") +
                                "Factor por salida: " + Html.fromHtml("<br/>") + dataManager.Edificio + " lpm/salida" + Html.fromHtml("<br/><br/>") +
                                "Desnivel total máximo: " + Html.fromHtml("<br/>") + dataManager.hDistVertical + " mts" + Html.fromHtml("<br/><br/>") +
                                "Longitud de tubería máxima: " + Html.fromHtml("<br/>") + dataManager.hLongSalida + " mts" + Html.fromHtml("<br/><br/>") +
                                "Presión en Salida: " + Html.fromHtml("<br/>") + dataManager.hPresionSalida + " psi" + Html.fromHtml("<br/><br/>") +
                                "Porcentaje máximo de de pérdidas en la conducción: " + Html.fromHtml("<br/>") + dataManager.Edificio + "%" + Html.fromHtml("<br/><br/>") +
                                "Gasto pico máximo: " + Html.fromHtml("<br/>") + dataManager.Edificio + " lpm" + Html.fromHtml("<br/><br/>") +
                                "Carga dinámica total: " + Html.fromHtml("<br/>") + dataManager.Edificio + " psi" + Html.fromHtml("<br/><br/>") +
                                "Diámetro de tubo recomendado en la linea principal: " + Html.fromHtml("<br/>") + dataManager.Edificio + '"' + Html.fromHtml("<br/><br/>") +
                                "Enviado desde mi Android" );

                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tab_hidros_3, menu);
    }

}
