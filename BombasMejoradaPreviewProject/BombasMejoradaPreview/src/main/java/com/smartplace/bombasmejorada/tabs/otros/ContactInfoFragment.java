package com.smartplace.bombasmejorada.tabs.otros;

import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

/**
 * Created by Roberto on 12/07/13.
 */
import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.smartplace.bombasmejorada.R;


/**
 * Created by Roberto on 11/07/13.
 */

/*
*   DON´T FORGET TO ADD PERMISSION INTO ANDROID MANIFEST
*   <uses-permission android:name="android.permission.CALL_PHONE"></uses-permission>
*
* */
public class ContactInfoFragment extends Fragment {

    Button btnViewMap = null;
    Button btnLocalTel = null;
    Button btnCountryTel = null;
    Button btnSendMail = null;
    Button btnWebBombas = null;
    Button btnWebSystems = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        ActionBar abar = getActivity().getActionBar();
        abar.setTitle("Contacto");
        abar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#195259")));
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.tab_otros_contact_info, container, false);
        btnViewMap = (Button) view.findViewById(R.id.btnViewMap);
        btnLocalTel = (Button) view.findViewById(R.id.btnLocalTel);
        btnCountryTel = (Button) view.findViewById(R.id.btnCountryTel);
        btnSendMail = (Button) view.findViewById(R.id.btnSendMail);
        btnWebBombas = (Button) view.findViewById(R.id.btnWebBombas);
        btnWebSystems = (Button) view.findViewById(R.id.btnWebSistemas);


        btnViewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        //Uri.parse("geo:0,0?q=20.64439,-103.36689 (Bombas Mejorada)"));
                        Uri.parse("geo:0,0?q=4+2368,+44940+Guadalajara,+Jalisco (Bombas Mejorada)"));
                startActivity(intent); }
        });
        btnLocalTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:3338118517"));
                    getActivity().startActivity(callIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getActivity().getBaseContext(), "No se pudo concretar la llamada", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnCountryTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:018002266236"));
                    getActivity().startActivity(callIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getActivity().getBaseContext(), "No se pudo concretar la llamada", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"enlinea@bombasmejorada.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, Html.fromHtml("Solicito Informaci&oacute;n v&iacute;a Android").toString());
                i.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(
                        "<br/><br/>" +
                        "Enviado desde mi Android").toString());
                try {
                    startActivity(Intent.createChooser(i, "Enviar correo..."));
                } catch (ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "No hay clientes de correo disponibles", Toast.LENGTH_SHORT).show();
                } }
        });
        btnWebBombas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.bombasmejorada.com"));
                startActivity(browserIntent);
            }
        });
        btnWebSystems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sistemasdebombeo.com"));
                startActivity(browserIntent);}
        });

        return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tab_otros, menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        return  true;

    }

}