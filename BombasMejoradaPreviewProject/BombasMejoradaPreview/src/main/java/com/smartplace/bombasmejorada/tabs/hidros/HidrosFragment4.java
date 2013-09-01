package com.smartplace.bombasmejorada.tabs.hidros;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    DataManager dataManager;



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
        dataManager =  ((TabsMainActivity)getActivity()).getDataManager();

        calcHidrosValues();

        txt_instalacion.setText(dataManager.Edificio);
        txt_salidas.setText(String.valueOf(dataManager.hSalidas)+" ");
        txt_factor_salida.setText(String.valueOf(dataManager.hFactorPorSalida)+" lpm/salida");
        txt_desnivel.setText(String.valueOf(dataManager.hDistVertical)+" mts");
        txt_max_long.setText(String.valueOf(dataManager.hLongSalida)+" mts");
        txt_presion.setText(String.valueOf(dataManager.hPresionSalida)+" psi");
        txt_porcentaje.setText(String.valueOf(String.format("%.2f",dataManager.hPorcentajePerdidas))+" %");
        txt_gasto.setText(String.valueOf(String.format("%.2f",dataManager.hGastoPico))+ " lpm");
        txt_carga.setText(String.valueOf(String.format("%.2f",dataManager.hCargaDinamica))+ " psi");
        txt_diametro.setText(String.valueOf(String.format("%.2f",dataManager.hDiametroTubo))+ " " + String.valueOf('"'));

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
                                "Factor por salida: " + Html.fromHtml("<br/>") + dataManager.hFactorPorSalida + " lpm/salida" + Html.fromHtml("<br/><br/>") +
                                "Desnivel total máximo: " + Html.fromHtml("<br/>") + dataManager.hDistVertical + " mts" + Html.fromHtml("<br/><br/>") +
                                "Longitud de tubería máxima: " + Html.fromHtml("<br/>") + dataManager.hLongSalida + " mts" + Html.fromHtml("<br/><br/>") +
                                "Presión en Salida: " + Html.fromHtml("<br/>") + dataManager.hPresionSalida + " psi" + Html.fromHtml("<br/><br/>") +
                                "Porcentaje máximo de de pérdidas en la conducción: " + Html.fromHtml("<br/>") + String.valueOf(String.format("%.2f",dataManager.hPorcentajePerdidas)) + "%" + Html.fromHtml("<br/><br/>") +
                                "Gasto pico máximo: " + Html.fromHtml("<br/>") + String.valueOf(String.format("%.2f",dataManager.hGastoPico)) + " lpm" + Html.fromHtml("<br/><br/>") +
                                "Carga dinámica total: " + Html.fromHtml("<br/>") + String.valueOf(String.format("%.2f",dataManager.hCargaDinamica)) + " psi" + Html.fromHtml("<br/><br/>") +
                                "Diámetro de tubo recomendado en la linea principal: " + Html.fromHtml("<br/>") + String.valueOf(String.format("%.2f",dataManager.hDiametroTubo)) + '"' + Html.fromHtml("<br/><br/>") +
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

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_buscar:

                // Create new fragment and transaction
                HidrosFragment5 newFragment = new HidrosFragment5();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(android.R.id.tabcontent, newFragment);
                transaction.addToBackStack(null);

                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                // Commit the transaction
                transaction.commit();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void calcHidrosValues ()
    {

        if ((dataManager.Edificio.equals("Club")) || (dataManager.Edificio.equals("Escuela")))
        {
            if (dataManager.hSalidas <= 25)
            {
                dataManager.hFactorPorSalida = 4.55;
            }
            else if ((dataManager.hSalidas > 25) && (dataManager.hSalidas <= 50))
            {
                dataManager.hFactorPorSalida = 3.21;
            }
            else if ((dataManager.hSalidas > 50) && (dataManager.hSalidas <= 100))
            {
                dataManager.hFactorPorSalida = 2.46;
            }
            else if ((dataManager.hSalidas > 100) && (dataManager.hSalidas <= 200))
            {
                dataManager.hFactorPorSalida = 2.27;
            }
            else if ((dataManager.hSalidas > 200) && (dataManager.hSalidas <= 400))
            {
                dataManager.hFactorPorSalida = 2.08;
            }
            else if ((dataManager.hSalidas > 400) && (dataManager.hSalidas <= 600))
            {
                dataManager.hFactorPorSalida = 1.70;
            }
            else if ((dataManager.hSalidas > 600) && (dataManager.hSalidas <= 900))
            {
                dataManager.hFactorPorSalida = 1.60;
            }
            else if ((dataManager.hSalidas > 900) && (dataManager.hSalidas <= 3000))
            {
                dataManager.hFactorPorSalida = 1.36;
            }
            else if (dataManager.hSalidas > 3000)
            {
                dataManager.hFactorPorSalida = 1.02;
            }
        }

        else if (dataManager.Edificio.equals("Edificio Oficinas"))
        {
            if (dataManager.hSalidas <= 25)
            {
                dataManager.hFactorPorSalida = 4.55;
            }
            else if ((dataManager.hSalidas > 25) && (dataManager.hSalidas <= 50))
            {
                dataManager.hFactorPorSalida = 3.40;
            }
            else if ((dataManager.hSalidas > 50) && (dataManager.hSalidas <= 100))
            {
                dataManager.hFactorPorSalida = 2.72;
            }
            else if ((dataManager.hSalidas > 100) && (dataManager.hSalidas <= 200))
            {
                dataManager.hFactorPorSalida = 2.46;
            }
            else if ((dataManager.hSalidas > 200) && (dataManager.hSalidas <= 400))
            {
                dataManager.hFactorPorSalida = 1.90;
            }
            else if ((dataManager.hSalidas > 400) && (dataManager.hSalidas <= 600))
            {
                dataManager.hFactorPorSalida = 1.51;
            }
            else if ((dataManager.hSalidas > 600) && (dataManager.hSalidas <= 900))
            {
                dataManager.hFactorPorSalida = 1.32;
            }
            else if ((dataManager.hSalidas > 900) && (dataManager.hSalidas <= 3000))
            {
                dataManager.hFactorPorSalida = 1.12;
            }
            else if (dataManager.hSalidas > 3000)
            {
                dataManager.hFactorPorSalida = 0.84;
            }
        }

        else if (dataManager.Edificio.equals("Hospital"))
        {
            if (dataManager.hSalidas <= 25)
            {
                dataManager.hFactorPorSalida = 3.78;
            }
            else if ((dataManager.hSalidas > 25) && (dataManager.hSalidas <= 50))
            {
                dataManager.hFactorPorSalida = 3.78;
            }
            else if ((dataManager.hSalidas > 50) && (dataManager.hSalidas <= 100))
            {
                dataManager.hFactorPorSalida = 3.03;
            }
            else if ((dataManager.hSalidas > 100) && (dataManager.hSalidas <= 200))
            {
                dataManager.hFactorPorSalida = 2.27;
            }
            else if ((dataManager.hSalidas > 200) && (dataManager.hSalidas <= 400))
            {
                dataManager.hFactorPorSalida = 1.90;
            }
            else if ((dataManager.hSalidas > 400) && (dataManager.hSalidas <= 600))
            {
                dataManager.hFactorPorSalida = 1.70;
            }
            else if ((dataManager.hSalidas > 600) && (dataManager.hSalidas <= 900))
            {
                dataManager.hFactorPorSalida = 1.51;
            }
            else if ((dataManager.hSalidas > 900) && (dataManager.hSalidas <= 3000))
            {
                dataManager.hFactorPorSalida = 1.28;
            }
            else if (dataManager.hSalidas > 3000)
            {
                dataManager.hFactorPorSalida = 0.96;
            }
        }

        else if (dataManager.Edificio.equals("Hotel o Motel"))
        {
            if (dataManager.hSalidas <= 25)
            {
                dataManager.hFactorPorSalida = 3.03;
            }
            else if ((dataManager.hSalidas > 25) && (dataManager.hSalidas <= 50))
            {
                dataManager.hFactorPorSalida = 2.46;
            }
            else if ((dataManager.hSalidas > 50) && (dataManager.hSalidas <= 100))
            {
                dataManager.hFactorPorSalida = 2.08;
            }
            else if ((dataManager.hSalidas > 100) && (dataManager.hSalidas <= 200))
            {
                dataManager.hFactorPorSalida = 1.70;
            }
            else if ((dataManager.hSalidas > 200) && (dataManager.hSalidas <= 400))
            {
                dataManager.hFactorPorSalida = 1.51;
            }
            else if ((dataManager.hSalidas > 400) && (dataManager.hSalidas <= 600))
            {
                dataManager.hFactorPorSalida = 1.32;
            }
            else if ((dataManager.hSalidas > 600) && (dataManager.hSalidas <= 900))
            {
                dataManager.hFactorPorSalida = 1.24;
            }
            else if ((dataManager.hSalidas > 900) && (dataManager.hSalidas <= 3000))
            {
                dataManager.hFactorPorSalida = 1.05;
            }
            else if (dataManager.hSalidas > 3000)
            {
                dataManager.hFactorPorSalida = 0.79;
            }
        }

        else if (dataManager.Edificio.equals("Tiendas de Autoservicio"))
        {
            if (dataManager.hSalidas <= 25)
            {
                dataManager.hFactorPorSalida = 4.92;
            }
            else if ((dataManager.hSalidas > 25) && (dataManager.hSalidas <= 50))
            {
                dataManager.hFactorPorSalida = 3.78;
            }
            else if ((dataManager.hSalidas > 50) && (dataManager.hSalidas <= 100))
            {
                dataManager.hFactorPorSalida = 3.03;
            }
            else if ((dataManager.hSalidas > 100) && (dataManager.hSalidas <= 200))
            {
                dataManager.hFactorPorSalida = 2.68;
            }
            else if ((dataManager.hSalidas > 200) && (dataManager.hSalidas <= 400))
            {
                dataManager.hFactorPorSalida = 2.27;
            }
            else if ((dataManager.hSalidas > 400) && (dataManager.hSalidas <= 600))
            {
                dataManager.hFactorPorSalida = 2.05;
            }
            else if ((dataManager.hSalidas > 600) && (dataManager.hSalidas <= 900))
            {
                dataManager.hFactorPorSalida = 1.81;
            }
            else if ((dataManager.hSalidas > 900) && (dataManager.hSalidas <= 3000))
            {
                dataManager.hFactorPorSalida = 1.54;
            }
            else if (dataManager.hSalidas > 3000)
            {
                dataManager.hFactorPorSalida = 1.16;
            }
        }

        else if (dataManager.Edificio.equals("Plaza Comercial"))
        {
            if (dataManager.hSalidas <= 25)
            {
                dataManager.hFactorPorSalida = 4.92;
            }
            else if ((dataManager.hSalidas > 25) && (dataManager.hSalidas <= 50))
            {
                dataManager.hFactorPorSalida = 3.78;
            }
            else if ((dataManager.hSalidas > 50) && (dataManager.hSalidas <= 100))
            {
                dataManager.hFactorPorSalida = 3.03;
            }
            else if ((dataManager.hSalidas > 100) && (dataManager.hSalidas <= 200))
            {
                dataManager.hFactorPorSalida = 2.68;
            }
            else if ((dataManager.hSalidas > 200) && (dataManager.hSalidas <= 400))
            {
                dataManager.hFactorPorSalida = 2.27;
            }
            else if ((dataManager.hSalidas > 400) && (dataManager.hSalidas <= 600))
            {
                dataManager.hFactorPorSalida = 2.05;
            }
            else if ((dataManager.hSalidas > 600) && (dataManager.hSalidas <= 900))
            {
                dataManager.hFactorPorSalida = 1.81;
            }
            else if ((dataManager.hSalidas > 900) && (dataManager.hSalidas <= 3000))
            {
                dataManager.hFactorPorSalida = 1.54;
            }
            else if (dataManager.hSalidas > 3000)
            {
                dataManager.hFactorPorSalida = 1.16;
            }
        }

        else if (dataManager.Edificio.equals("Vivienda"))
        {
            if (dataManager.hSalidas <= 25)
            {
                dataManager.hFactorPorSalida = 2.27;
            }
            else if ((dataManager.hSalidas > 25) && (dataManager.hSalidas <= 50))
            {
                dataManager.hFactorPorSalida = 1.90;
            }
            else if ((dataManager.hSalidas > 50) && (dataManager.hSalidas <= 100))
            {
                dataManager.hFactorPorSalida = 1.40;
            }
            else if ((dataManager.hSalidas > 100) && (dataManager.hSalidas <= 200))
            {
                dataManager.hFactorPorSalida = 1.13;
            }
            else if ((dataManager.hSalidas > 200) && (dataManager.hSalidas <= 400))
            {
                dataManager.hFactorPorSalida = 1.05;
            }
            else if ((dataManager.hSalidas > 400) && (dataManager.hSalidas <= 600))
            {
                dataManager.hFactorPorSalida = 0.95;
            }
            else if ((dataManager.hSalidas > 600) && (dataManager.hSalidas <= 900))
            {
                dataManager.hFactorPorSalida = 0.90;
            }
            else if ((dataManager.hSalidas > 900) && (dataManager.hSalidas <= 3000))
            {
                dataManager.hFactorPorSalida = 0.70;
            }
            else if (dataManager.hSalidas > 3000)
            {
                dataManager.hFactorPorSalida = 0.53;
            }
        }

        if (dataManager.hLongSalida < 150)
        {
            dataManager.hPorcentajePerdidas = 7;
        }
        else if ((dataManager.hLongSalida >= 150) && (dataManager.hLongSalida < 200))
        {
            dataManager.hPorcentajePerdidas = 5;
        }
        else if ((dataManager.hLongSalida >= 200) && (dataManager.hLongSalida < 350))
        {
            dataManager.hPorcentajePerdidas = 5;
        }
        else if ((dataManager.hLongSalida >= 350) && (dataManager.hLongSalida < 500))
        {
            dataManager.hPorcentajePerdidas = 5;
        }
        else if (dataManager.hLongSalida >= 500)
        {
            dataManager.hPorcentajePerdidas = 1.5;
        }

        dataManager.hGastoPico = dataManager.hSalidas * dataManager.hFactorPorSalida;
        //mca
        dataManager.hCargaDinamica = (dataManager.hDistVertical/0.7031 + (dataManager.hLongSalida*dataManager.hPorcentajePerdidas/70.31) + dataManager.hPresionSalida);

        if (dataManager.hGastoPico < 170)
        {
            if (dataManager.hPorcentajePerdidas <= 1.50)
            {
                dataManager.hDiametroTubo = 4;
            }
            else if ((dataManager.hPorcentajePerdidas > 1.50) && (dataManager.hPorcentajePerdidas <= 2))
            {
                dataManager.hDiametroTubo = 3;
            }
            else if ((dataManager.hPorcentajePerdidas > 2) && (dataManager.hPorcentajePerdidas <= 3))
            {
                dataManager.hDiametroTubo = 3;
            }
            else if ((dataManager.hPorcentajePerdidas > 3) && (dataManager.hPorcentajePerdidas <= 5))
            {
                dataManager.hDiametroTubo = 2.5;
            }
            else if ((dataManager.hPorcentajePerdidas > 5) && (dataManager.hPorcentajePerdidas <= 7))
            {
                dataManager.hDiametroTubo = 2;
            }
        }
        else if ((dataManager.hGastoPico >= 170) && (dataManager.hGastoPico < 375))
        {
            if (dataManager.hPorcentajePerdidas <= 1.50)
            {
                dataManager.hDiametroTubo = 4;
            }
            else if ((dataManager.hPorcentajePerdidas > 1.50) && (dataManager.hPorcentajePerdidas <= 2))
            {
                dataManager.hDiametroTubo = 4;
            }
            else if ((dataManager.hPorcentajePerdidas > 2) && (dataManager.hPorcentajePerdidas <= 3))
            {
                dataManager.hDiametroTubo = 3;
            }
            else if ((dataManager.hPorcentajePerdidas > 3) && (dataManager.hPorcentajePerdidas <= 5))
            {
                dataManager.hDiametroTubo = 3;
            }
            else if ((dataManager.hPorcentajePerdidas > 5) && (dataManager.hPorcentajePerdidas <= 7))
            {
                dataManager.hDiametroTubo = 3;
            }
        }
        else if ((dataManager.hGastoPico >= 375) && (dataManager.hGastoPico < 460))
        {
            if (dataManager.hPorcentajePerdidas <= 1.50)
            {
                dataManager.hDiametroTubo = 4;
            }
            else if ((dataManager.hPorcentajePerdidas > 1.50) && (dataManager.hPorcentajePerdidas <= 2))
            {
                dataManager.hDiametroTubo = 4;
            }
            else if ((dataManager.hPorcentajePerdidas > 2) && (dataManager.hPorcentajePerdidas <= 3))
            {
                dataManager.hDiametroTubo = 4;
            }
            else if ((dataManager.hPorcentajePerdidas > 3) && (dataManager.hPorcentajePerdidas <= 5))
            {
                dataManager.hDiametroTubo = 4;
            }
            else if ((dataManager.hPorcentajePerdidas > 5) && (dataManager.hPorcentajePerdidas <= 7))
            {
                dataManager.hDiametroTubo = 3;
            }
        }
        else if ((dataManager.hGastoPico >= 460) && (dataManager.hGastoPico < 960))
        {
            if (dataManager.hPorcentajePerdidas <= 1.50)
            {
                dataManager.hDiametroTubo = 6;
            }
            else if ((dataManager.hPorcentajePerdidas > 1.50) && (dataManager.hPorcentajePerdidas <= 2))
            {
                dataManager.hDiametroTubo = 5;
            }
            else if ((dataManager.hPorcentajePerdidas > 2) && (dataManager.hPorcentajePerdidas <= 3))
            {
                dataManager.hDiametroTubo = 5;
            }
            else if ((dataManager.hPorcentajePerdidas > 3) && (dataManager.hPorcentajePerdidas <= 5))
            {
                dataManager.hDiametroTubo = 5;
            }
            else if ((dataManager.hPorcentajePerdidas > 5) && (dataManager.hPorcentajePerdidas <= 7))
            {
                dataManager.hDiametroTubo = 4;
            }
        }
        else if ((dataManager.hGastoPico >= 960) && (dataManager.hGastoPico < 1500))
        {
            if (dataManager.hPorcentajePerdidas <= 1.50)
            {
                dataManager.hDiametroTubo = 6;
            }
            else if ((dataManager.hPorcentajePerdidas > 1.50) && (dataManager.hPorcentajePerdidas <= 2))
            {
                dataManager.hDiametroTubo = 6;
            }
            else if ((dataManager.hPorcentajePerdidas > 2) && (dataManager.hPorcentajePerdidas <= 3))
            {
                dataManager.hDiametroTubo = 5;
            }
            else if ((dataManager.hPorcentajePerdidas > 3) && (dataManager.hPorcentajePerdidas <= 5))
            {
                dataManager.hDiametroTubo = 5;
            }
            else if ((dataManager.hPorcentajePerdidas > 5) && (dataManager.hPorcentajePerdidas <= 7))
            {
                dataManager.hDiametroTubo = 5;
            }
        }
        else if ((dataManager.hGastoPico >= 1500) && (dataManager.hGastoPico < 2160))
        {
            if (dataManager.hPorcentajePerdidas <= 1.50)
            {
                dataManager.hDiametroTubo = 8;
            }
            else if ((dataManager.hPorcentajePerdidas > 1.50) && (dataManager.hPorcentajePerdidas <= 2))
            {
                dataManager.hDiametroTubo = 8;
            }
            else if ((dataManager.hPorcentajePerdidas > 2) && (dataManager.hPorcentajePerdidas <= 3))
            {
                dataManager.hDiametroTubo = 8;
            }
            else if ((dataManager.hPorcentajePerdidas > 3) && (dataManager.hPorcentajePerdidas <= 5))
            {
                dataManager.hDiametroTubo = 6;
            }
            else if ((dataManager.hPorcentajePerdidas > 5) && (dataManager.hPorcentajePerdidas <= 7))
            {
                dataManager.hDiametroTubo = 5;
            }
        }
        else if (dataManager.hGastoPico >= 2160)
        {
            if (dataManager.hPorcentajePerdidas <= 1.50)
            {
                dataManager.hDiametroTubo = 10;
            }
            else if ((dataManager.hPorcentajePerdidas > 1.50) && (dataManager.hPorcentajePerdidas <= 2))
            {
                dataManager.hDiametroTubo = 10;
            }
            else if ((dataManager.hPorcentajePerdidas > 2) && (dataManager.hPorcentajePerdidas <= 3))
            {
                dataManager.hDiametroTubo = 8;
            }
            else if ((dataManager.hPorcentajePerdidas > 3) && (dataManager.hPorcentajePerdidas <= 5))
            {
                dataManager.hDiametroTubo = 8;
            }
            else if ((dataManager.hPorcentajePerdidas > 5) && (dataManager.hPorcentajePerdidas <= 7))
            {
                dataManager.hDiametroTubo = 8;
            }
        }




    }//end calcValues

}
