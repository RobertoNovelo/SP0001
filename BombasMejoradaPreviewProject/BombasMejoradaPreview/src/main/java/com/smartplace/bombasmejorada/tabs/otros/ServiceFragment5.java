package com.smartplace.bombasmejorada.tabs.otros;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

import com.smartplace.bombasmejorada.R;
import com.smartplace.bombasmejorada.tabs.DataManager;
import com.smartplace.bombasmejorada.tabs.TabsMainActivity;

/**
 * Created by Roberto on 12/07/13.
 */


/**
 * Created by Roberto on 11/07/13.
 */

/*
*   INTEGRATION HINTS:
*
*   layout: tab_otros_aboutt.xml
*   menu: menu_about_fragment.xml
*   values: about.xml
*
* */
public class ServiceFragment5 extends Fragment {
    EditText txt_nombre;
    EditText txt_empresa;
    EditText txt_puesto;
    EditText txt_telefono;
    EditText txt_celular;
    EditText txt_correo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ActionBar abar = getActivity().getActionBar();
        abar.setTitle("Servicio");
        abar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1A535A")));
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.tab_otros_service_5, container, false);
        txt_nombre = (EditText)view.findViewById(R.id.txt_service_7);
        txt_empresa = (EditText)view.findViewById(R.id.txt_service_8);
        txt_puesto = (EditText)view.findViewById(R.id.txt_service_9);
        txt_telefono = (EditText)view.findViewById(R.id.txt_service_10);
        txt_celular = (EditText)view.findViewById(R.id.txt_service_11);
        txt_correo = (EditText)view.findViewById(R.id.txt_service_12);
       return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tab_otros_servicio_5, menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.btn_service_accept:
                DataManager dataManager = ((TabsMainActivity)getActivity()).getDataManager();
                dataManager.Nombre = txt_nombre.getText().toString();
                dataManager.Empresa = txt_empresa.getText().toString();
                dataManager.Puesto = txt_puesto.getText().toString();
                dataManager.Telefono = txt_telefono.getText().toString();
                dataManager.Celular = txt_celular.getText().toString();
                dataManager.Correo = txt_correo.getText().toString();
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create(); //Read Update
                alertDialog.setTitle("Servicios y Garantías");
                alertDialog.setMessage("Una vez recibida su solicitud, nos comunicaremos con usted para agendar la fecha de visita.\nHe leído y estoy conforme con las condiciones de servicio");
                alertDialog.setButton("Acepto", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                        DataManager dataManager = ((TabsMainActivity)getActivity()).getDataManager();
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("message/rfc822");
                        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"servicio@bombasmejorada.com"});
                        i.putExtra(Intent.EXTRA_SUBJECT, Html.fromHtml("Solicitud de Servicio v&iacute;a Android").toString());
                        i.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(
                                "Por medio de la presente solicito que se haga una visita para la revision del siguiente producto:" +
                                        "<br/><br/>" +
                                        "1.Equipo Modelo:" + dataManager.EquipoModelo +
                                        "<br/><br/>" +
                                        "2.No.de Serie:" + dataManager.NoDeSerie +
                                        "<br/><br/>" +
                                        "3.Descripcion de la falla:" + dataManager.Falla +
                                        "<br/><br/>" +
                                        "4.Direccion donde esta instalado:" + dataManager.Domicilio +
                                        "<br/><br/>" +
                                        "5.Observaciones:" + dataManager.Observaciones +
                                        "<br/><br/>" +
                                        "7.Nombre:" + dataManager.Nombre +
                                        "<br/><br/>" +
                                        "8.Empresa:" + dataManager.Empresa +
                                        "<br/><br/>" +
                                        "9.Puesto:" + dataManager.Puesto +
                                        "<br/><br/>" +
                                        "10.Telefono:" + dataManager.Telefono +
                                        "<br/><br/>" +
                                        "11.Celular:" + dataManager.Celular +
                                        "<br/><br/>" +
                                        "12.Correo:" + dataManager.Correo +
                                        "<br/><br/>" +
                                        "<br/><br/>" +
                                        "Enviado desde mi Android").toString());

                        try {
                            startActivity(Intent.createChooser(i, "Enviar mail..."));
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                alertDialog.setButton2("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // here you can add functions

                    }
                });

                alertDialog.show();  //<-- See This!

                break;
            default:
                Toast.makeText(getActivity().getBaseContext(),"No ID identificado",Toast.LENGTH_SHORT).show();
        }
        return  true;

    }
    @Override
    public void onResume ()
    {
        super.onResume();

        DataManager dataManager = ((TabsMainActivity)getActivity()).getDataManager();
        txt_nombre.setText(dataManager.Nombre);
        txt_empresa.setText(dataManager.Empresa);
        txt_puesto.setText(dataManager.Puesto);
        txt_telefono.setText(dataManager.Telefono);
        txt_celular.setText(dataManager.Celular);
        txt_correo.setText(dataManager.Correo);

    }

    @Override
    public void onPause ()
    {
        super.onPause();

        DataManager dataManager = ((TabsMainActivity)getActivity()).getDataManager();
        dataManager.Nombre = txt_nombre.getText().toString();
        dataManager.Empresa = txt_empresa.getText().toString();
        dataManager.Puesto = txt_puesto.getText().toString();
        dataManager.Telefono = txt_telefono.getText().toString();
        dataManager.Celular = txt_celular.getText().toString();
        dataManager.Correo = txt_correo.getText().toString();

    }
}