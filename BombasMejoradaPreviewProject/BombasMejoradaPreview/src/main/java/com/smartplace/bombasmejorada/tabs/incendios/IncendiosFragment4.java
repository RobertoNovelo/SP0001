package com.smartplace.bombasmejorada.tabs.incendios;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
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
public class IncendiosFragment4 extends Fragment {

    private TextView    txt_instalacion,
                        txt_proteccion,
                        txt_long_max,
                        txt_desnivel_max,
                        txt_presion,
                        txt_perdidas_presion,
                        txt_gasto,
                        txt_carga_dinamica,
                        txt_diametro_principal,
                        txt_diametro_ciruito;

    DataManager dataManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.tab_incendios_4, container, false);

        txt_instalacion = (TextView)view.findViewById(R.id.iInstalacion);
        txt_proteccion = (TextView)view.findViewById(R.id.iProteccion);
        txt_long_max = (TextView)view.findViewById(R.id.iLongitudMax);
        txt_desnivel_max = (TextView)view.findViewById(R.id.iDesnivelMax);
        txt_presion = (TextView)view.findViewById(R.id.iPresion);
        txt_perdidas_presion = (TextView)view.findViewById(R.id.iPerdidas);
        txt_carga_dinamica = (TextView)view.findViewById(R.id.iCargaDinamica);
        txt_gasto = (TextView)view.findViewById(R.id.iGasto);
        txt_diametro_principal = (TextView)view.findViewById(R.id.iDiametroPrincipal);
        txt_diametro_ciruito = (TextView)view.findViewById(R.id.iDiametroCircuito);

        return view;

    }

    @Override
    public void onResume ()
    {
        super.onResume();

        Button btnEnviar = (Button) getActivity().findViewById(R.id.enviarbutton);
        dataManager =  ((TabsMainActivity)getActivity()).getDataManager();

        calcIncendiosValues();

        txt_instalacion.setText(dataManager.Grupo);
        txt_proteccion.setText(dataManager.iProteccion);
        txt_long_max.setText(String.valueOf(dataManager.iLongSalida)+" mts");
        txt_desnivel_max.setText(String.valueOf(dataManager.iDesnivel)+" mts");
        txt_presion.setText(String.valueOf(dataManager.iPresion)+" psi");
        txt_perdidas_presion.setText(String.valueOf(String.format("%.2f", dataManager.iPerdidas))+" %");
        txt_gasto.setText(String.valueOf(String.format("%.2f", dataManager.iGastoPico))+" gpm");
        txt_carga_dinamica.setText(String.valueOf(String.format("%.2f", dataManager.iCargaDinamica))+" psi");
        txt_diametro_principal.setText(String.valueOf(String.format("%.2f", dataManager.iDiamPrincipal))+" "+String.valueOf('"'));
        txt_diametro_ciruito.setText(String.valueOf(String.format("%.2f", dataManager.iDiamCircuito))+" "+String.valueOf('"'));

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"enlinea@bombasmejorada.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, Html.fromHtml("Solicito Informaci&oacute;n v&iacute;a Android").toString());
                i.putExtra(Intent.EXTRA_TEXT,
                        "Bombas - Hoja de cálculo: " + Html.fromHtml("<br/><br/>") +
                                "Instalación: " + Html.fromHtml("<br/>") + dataManager.Grupo + Html.fromHtml("<br/><br/>") +
                                "Protección con: " + Html.fromHtml("<br/>") + dataManager.iProteccion + Html.fromHtml("<br/><br/>") +
                                "Longitud de tubería máxima: " + Html.fromHtml("<br/>") + dataManager.iLongSalida + " mts" + Html.fromHtml("<br/><br/>") +
                                "Desnivel total máximo: " + Html.fromHtml("<br/>") + dataManager.iDesnivel + " mts" + Html.fromHtml("<br/><br/>") +
                                "Presión de trabajo: " + Html.fromHtml("<br/>") + dataManager.iPresion + " psi" + Html.fromHtml("<br/><br/>") +
                                "Pérdidas de presión por fricción: " + Html.fromHtml("<br/>") + String.valueOf(String.format("%.2f", dataManager.iPerdidas)) + " %" + Html.fromHtml("<br/><br/>") +
                                "Gasto total: " + Html.fromHtml("<br/>") + String.valueOf(String.format("%.2f", dataManager.iGastoPico)) + " gpm" + Html.fromHtml("<br/><br/>") +
                                "Carga dinámica total: " + Html.fromHtml("<br/>") + String.valueOf(String.format("%.2f", dataManager.iCargaDinamica)) + " psi" + Html.fromHtml("<br/><br/>") +
                                "Diámetro de tubo recomendado en la linea principal: " + Html.fromHtml("<br/>") + String.valueOf(String.format("%.2f", dataManager.iDiamPrincipal)) + " \"" + Html.fromHtml("<br/><br/>") +
                                "Diámetro de tubo con circuito: " + Html.fromHtml("<br/>") + String.valueOf(String.format("%.2f", dataManager.iDiamCircuito)) + " \"" + Html.fromHtml("<br/><br/>") +
                                "Enviado desde mi Android");

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

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_buscar:

                // Create new fragment and transaction
                IncendiosFragment5 newFragment = new IncendiosFragment5();
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

    private void calcIncendiosValues ()
    {

        if (dataManager.iLongSalida < 200)
        {
            dataManager.iPerdidas = 5;

            if (dataManager.iGastoPico <= 170)
            {
                dataManager.iDiamPrincipal = 2;
            }
            else if ((dataManager.iGastoPico > 170) && (dataManager.iGastoPico <= 375))
            {
                dataManager.iDiamPrincipal = 3;
            }
            else if ((dataManager.iGastoPico > 375) && (dataManager.iGastoPico <= 500))
            {
                dataManager.iDiamPrincipal = 3;
            }
            else if ((dataManager.iGastoPico > 500) && (dataManager.iGastoPico <= 960))
            {
                dataManager.iDiamPrincipal = 4;
            }
            else if ((dataManager.iGastoPico > 960) && (dataManager.iGastoPico <= 1500))
            {
                dataManager.iDiamPrincipal = 5;
            }
            else if ((dataManager.iGastoPico > 1500) && (dataManager.iGastoPico <= 2160))
            {
                dataManager.iDiamPrincipal = 6;
            }
            else if (dataManager.iGastoPico > 2160)
            {
                dataManager.iDiamPrincipal = 8;
            }

            //Diametro de tubo con circuito cerrado
            if (dataManager.iGastoPico/2 <= 170)
            {
                dataManager.iDiamCircuito = 2;
            }
            else if ((dataManager.iGastoPico/2 > 170) && (dataManager.iGastoPico/2 <= 375))
            {
                dataManager.iDiamCircuito = 3;
            }
            else if ((dataManager.iGastoPico/2 > 375) && (dataManager.iGastoPico/2 <= 500))
            {
                dataManager.iDiamCircuito = 3;
            }
            else if ((dataManager.iGastoPico/2 > 500) && (dataManager.iGastoPico/2 <= 960))
            {
                dataManager.iDiamCircuito = 4;
            }
            else if ((dataManager.iGastoPico/2 > 960) && (dataManager.iGastoPico/2 <= 1500))
            {
                dataManager.iDiamCircuito = 5;
            }
            else if ((dataManager.iGastoPico/2 > 1500) && (dataManager.iGastoPico/2 <= 2160))
            {
                dataManager.iDiamCircuito = 6;
            }
            else if (dataManager.iGastoPico/2 > 2160)
            {
                dataManager.iDiamCircuito = 8;
            }
        }

        else if ((dataManager.iLongSalida >= 200) && (dataManager.iLongSalida < 300))
        {
            dataManager.iPerdidas = 4;

            if (dataManager.iGastoPico <= 170)
            {
                dataManager.iDiamPrincipal = 2.5;
            }
            else if ((dataManager.iGastoPico > 170) && (dataManager.iGastoPico <= 375))
            {
                dataManager.iDiamPrincipal = 3;
            }
            else if ((dataManager.iGastoPico > 375) && (dataManager.iGastoPico <= 500))
            {
                dataManager.iDiamPrincipal = 4;
            }
            else if ((dataManager.iGastoPico > 500) && (dataManager.iGastoPico <= 960))
            {
                dataManager.iDiamPrincipal = 5;
            }
            else if ((dataManager.iGastoPico > 960) && (dataManager.iGastoPico <= 1500))
            {
                dataManager.iDiamPrincipal = 6;
            }
            else if ((dataManager.iGastoPico > 1500) && (dataManager.iGastoPico <= 2160))
            {
                dataManager.iDiamPrincipal = 6;
            }
            else if (dataManager.iGastoPico > 2160)
            {
                dataManager.iDiamPrincipal = 8;
            }

            //Diametro de tubo con circuito cerrado
            if (dataManager.iGastoPico/2 <= 170)
            {
                dataManager.iDiamCircuito = 2.5;
            }
            else if ((dataManager.iGastoPico/2 > 170) && (dataManager.iGastoPico/2 <= 375))
            {
                dataManager.iDiamCircuito = 3;
            }
            else if ((dataManager.iGastoPico/2 > 375) && (dataManager.iGastoPico/2 <= 500))
            {
                dataManager.iDiamCircuito = 4;
            }
            else if ((dataManager.iGastoPico/2 > 500) && (dataManager.iGastoPico/2 <= 960))
            {
                dataManager.iDiamCircuito = 5;
            }
            else if ((dataManager.iGastoPico/2 > 960) && (dataManager.iGastoPico/2 <= 1500))
            {
                dataManager.iDiamCircuito = 6;
            }
            else if ((dataManager.iGastoPico/2 > 1500) && (dataManager.iGastoPico/2 <= 2160))
            {
                dataManager.iDiamCircuito = 6;
            }
            else if (dataManager.iGastoPico/2 > 2160)
            {
                dataManager.iDiamCircuito = 8;
            }

        }
        else if ((dataManager.iLongSalida >= 300) && (dataManager.iLongSalida < 450))
        {
            dataManager.iPerdidas = 2.5;

            if (dataManager.iGastoPico <= 170)
            {
                dataManager.iDiamPrincipal = 2.5;
            }
            else if ((dataManager.iGastoPico > 170) && (dataManager.iGastoPico <= 375))
            {
                dataManager.iDiamPrincipal = 3;
            }
            else if ((dataManager.iGastoPico > 375) && (dataManager.iGastoPico <= 500))
            {
                dataManager.iDiamPrincipal = 4;
            }
            else if ((dataManager.iGastoPico > 500) && (dataManager.iGastoPico <= 960))
            {
                dataManager.iDiamPrincipal = 5;
            }
            else if ((dataManager.iGastoPico > 960) && (dataManager.iGastoPico <= 1500))
            {
                dataManager.iDiamPrincipal = 6;
            }
            else if ((dataManager.iGastoPico > 1500) && (dataManager.iGastoPico <= 2160))
            {
                dataManager.iDiamPrincipal = 6;
            }
            else if (dataManager.iGastoPico > 2160)
            {
                dataManager.iDiamPrincipal = 8;
            }

            //Diametro de tubo con circuito cerrado
            if (dataManager.iGastoPico/2 <= 170)
            {
                dataManager.iDiamCircuito = 2.5;
            }
            else if ((dataManager.iGastoPico/2 > 170) && (dataManager.iGastoPico/2 <= 375))
            {
                dataManager.iDiamCircuito = 3;
            }
            else if ((dataManager.iGastoPico/2 > 375) && (dataManager.iGastoPico/2 <= 500))
            {
                dataManager.iDiamCircuito = 4;
            }
            else if ((dataManager.iGastoPico/2 > 500) && (dataManager.iGastoPico/2 <= 960))
            {
                dataManager.iDiamCircuito = 5;
            }
            else if ((dataManager.iGastoPico/2 > 960) && (dataManager.iGastoPico/2 <= 1500))
            {
                dataManager.iDiamCircuito = 6;
            }
            else if ((dataManager.iGastoPico/2 > 1500) && (dataManager.iGastoPico/2 <= 2160))
            {
                dataManager.iDiamCircuito = 6;
            }
            else if (dataManager.iGastoPico/2 > 2160)
            {
                dataManager.iDiamCircuito = 8;
            }
        }

        else if ((dataManager.iLongSalida >= 450) && (dataManager.iLongSalida < 550))
        {
            dataManager.iPerdidas = 2;

            if (dataManager.iGastoPico <= 170)
            {
                dataManager.iDiamPrincipal = 3;
            }
            else if ((dataManager.iGastoPico > 170) && (dataManager.iGastoPico <= 375))
            {
                dataManager.iDiamPrincipal = 4;
            }
            else if ((dataManager.iGastoPico > 375) && (dataManager.iGastoPico <= 500))
            {
                dataManager.iDiamPrincipal = 4;
            }
            else if ((dataManager.iGastoPico > 500) && (dataManager.iGastoPico <= 960))
            {
                dataManager.iDiamPrincipal = 6;
            }
            else if ((dataManager.iGastoPico > 960) && (dataManager.iGastoPico <= 1500))
            {
                dataManager.iDiamPrincipal = 6;
            }
            else if ((dataManager.iGastoPico > 1500) && (dataManager.iGastoPico <= 2160))
            {
                dataManager.iDiamPrincipal = 8;
            }
            else if (dataManager.iGastoPico > 2160)
            {
                dataManager.iDiamPrincipal = 8;
            }

            //Diametro de tubo con circuito cerrado
            if (dataManager.iGastoPico/2 <= 170)
            {
                dataManager.iDiamCircuito = 3;
            }
            else if ((dataManager.iGastoPico/2 > 170) && (dataManager.iGastoPico/2 <= 375))
            {
                dataManager.iDiamCircuito = 4;
            }
            else if ((dataManager.iGastoPico/2 > 375) && (dataManager.iGastoPico/2 <= 500))
            {
                dataManager.iDiamCircuito = 4;
            }
            else if ((dataManager.iGastoPico/2 > 500) && (dataManager.iGastoPico/2 <= 960))
            {
                dataManager.iDiamCircuito = 6;
            }
            else if ((dataManager.iGastoPico/2 > 960) && (dataManager.iGastoPico/2 <= 1500))
            {
                dataManager.iDiamCircuito = 6;
            }
            else if ((dataManager.iGastoPico/2 > 1500) && (dataManager.iGastoPico/2 <= 2160))
            {
                dataManager.iDiamCircuito = 8;
            }
            else if (dataManager.iGastoPico/2 > 2160)
            {
                dataManager.iDiamCircuito = 8;
            }
        }

        else if (dataManager.iLongSalida >= 550)
        {
            dataManager.iPerdidas = 1.5;

            if (dataManager.iGastoPico <= 170)
            {
                dataManager.iDiamPrincipal = 3;
            }
            else if ((dataManager.iGastoPico > 170) && (dataManager.iGastoPico <= 375))
            {
                dataManager.iDiamPrincipal = 4;
            }
            else if ((dataManager.iGastoPico > 375) && (dataManager.iGastoPico <= 500))
            {
                dataManager.iDiamPrincipal = 4;
            }
            else if ((dataManager.iGastoPico > 500) && (dataManager.iGastoPico <= 960))
            {
                dataManager.iDiamPrincipal = 6;
            }
            else if ((dataManager.iGastoPico > 960) && (dataManager.iGastoPico <= 1500))
            {
                dataManager.iDiamPrincipal = 8;
            }
            else if ((dataManager.iGastoPico > 1500) && (dataManager.iGastoPico <= 2160))
            {
                dataManager.iDiamPrincipal = 8;
            }
            else if (dataManager.iGastoPico > 2160)
            {
                dataManager.iDiamPrincipal = 10;
            }

            //Diametro de tubo con circuito cerrado
            if (dataManager.iGastoPico/2 <= 170)
            {
                dataManager.iDiamCircuito = 3;
            }
            else if ((dataManager.iGastoPico/2 > 170) && (dataManager.iGastoPico/2 <= 375))
            {
                dataManager.iDiamCircuito = 4;
            }
            else if ((dataManager.iGastoPico/2 > 375) && (dataManager.iGastoPico/2 <= 500))
            {
                dataManager.iDiamCircuito = 4;
            }
            else if ((dataManager.iGastoPico/2 > 500) && (dataManager.iGastoPico/2 <= 960))
            {
                dataManager.iDiamCircuito = 6;
            }
            else if ((dataManager.iGastoPico/2 > 960) && (dataManager.iGastoPico/2 <= 1500))
            {
                dataManager.iDiamCircuito = 8;
            }
            else if ((dataManager.iGastoPico/2 > 1500) && (dataManager.iGastoPico/2 <= 2160))
            {
                dataManager.iDiamCircuito = 8;
            }
            else if (dataManager.iGastoPico/2 > 2160)
            {
                dataManager.iDiamCircuito = 10;
            }
        }

        dataManager.iCargaDinamica = dataManager.iDesnivel/0.7031 + dataManager.iLongSalida*dataManager.iPerdidas*.01/0.7031 + dataManager.iPresion;
    }//end calcHidrosValues



}
