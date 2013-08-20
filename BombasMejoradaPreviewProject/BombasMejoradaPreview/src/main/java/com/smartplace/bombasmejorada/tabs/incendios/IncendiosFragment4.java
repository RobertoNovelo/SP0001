package com.smartplace.bombasmejorada.tabs.incendios;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.smartplace.bombasmejorada.R;
import com.smartplace.bombasmejorada.tabs.DataManager;
import com.smartplace.bombasmejorada.tabs.TabsMainActivity;

/**
 * Created by ROBERTO on 28/06/13.
 */
public class IncendiosFragment4 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.tab_incendios_4, container, false);

    }

    @Override
    public void onResume ()
    {
        super.onResume();

        Button btnEnviar = (Button) getActivity().findViewById(R.id.enviarbutton);


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
                                "1.Instalación: " + Html.fromHtml("<br/>") + dataManager.Edificio + Html.fromHtml("<br/><br/>") +
                                "2.Salidas: " + Html.fromHtml("<br/>") + dataManager.hSalidas + " lpm" + Html.fromHtml("<br/><br/>") +
                                "4.Desnivel total máximo: " + Html.fromHtml("<br/>") + dataManager.hDistVertical + " psi" + Html.fromHtml("<br/><br/>") +
                                "5.Longitud de tubería máxima: " + Html.fromHtml("<br/>") + dataManager.hLongSalida + " psi" + Html.fromHtml("<br/><br/>") +
                                "6.Presión en Salida: " + Html.fromHtml("<br/>") + dataManager.hPresionSalida + " psi" + Html.fromHtml("<br/><br/>") +
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
        inflater.inflate(R.menu.tab_incendios_3, menu);
    }

}
