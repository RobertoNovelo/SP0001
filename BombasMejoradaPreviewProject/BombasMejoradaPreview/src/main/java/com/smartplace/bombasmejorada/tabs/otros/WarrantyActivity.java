package com.smartplace.bombasmejorada.tabs.otros;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.smartplace.bombasmejorada.R;

/**
 * Created by Roberto on 29/07/13.
 */
public class WarrantyActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //add below line if you want to remove  title for the activity dialog
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.tab_otros_warranty);

        final Button buttonYes = (Button) findViewById(R.id.button_ok);
        buttonYes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"servicio@bombasmejorada.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, Html.fromHtml("Solicitud de Servicio v&iacute;a Android").toString());
                i.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(
                        "Por medio de la presente solicito que se haga una visita para la revision del siguiente producto:" +
                        "<br/><br/>" +
                        "1.Equipo Modelo:" +
                                "<br/><br/>" +
                        "2.No.de Serie:" +
                                "<br/><br/>" +
                        "3.Descripcion de la falla:" +
                                "<br/><br/>" +
                        "4.Direccion donde esta instalado:" +
                                "<br/><br/>" +
                        "5.Observaciones:" +
                                "<br/><br/>" +
                        "7.Nombre" +
                                "<br/><br/>" +
                        "8.Empresa:" +
                                "<br/><br/>" +
                        "9.Puesto:" +
                                "<br/><br/>" +
                        "10.Telefono:" +
                                "<br/><br/>" +
                        "11.Celular:" +
                                "<br/><br/>" +
                        "12.Correo:" +
                                "<br/><br/>" +
                                "<br/><br/>" +
                                "Enviado desde mi Android").toString());
                try {
                    startActivity(Intent.createChooser(i, "Enviar correo..."));
                } catch (ActivityNotFoundException ex) {
                    Toast.makeText(getBaseContext(), "No hay clientes de correo disponibles", Toast.LENGTH_SHORT).show();
                }

            }
        });

        final Button buttonNo = (Button) findViewById(R.id.button_cancel);
        buttonNo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

    }
}