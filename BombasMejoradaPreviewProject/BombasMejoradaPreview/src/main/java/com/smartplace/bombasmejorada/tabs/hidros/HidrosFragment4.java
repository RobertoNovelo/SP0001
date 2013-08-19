package com.smartplace.bombasmejorada.tabs.hidros;

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
public class HidrosFragment4 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.tab_hidros_4, container, false);

    }

    @Override
    public void onResume ()
    {
        super.onResume();

        Button btnEnviar = (Button) getActivity().findViewById(R.id.enviarbutton);

        DataManager dataManager =  ((TabsMainActivity)getActivity()).getDataManager();


        Toast.makeText(getActivity(), dataManager.Edificio, Toast.LENGTH_SHORT).show();

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"robertod.novelo@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, Html.fromHtml("Solicito Informaci&oacute;n v&iacute;a Android").toString());
                i.putExtra(Intent.EXTRA_TEXT, Html.fromHtml("Bombas - Hoja de c&aacute;lculo:<br/>" +
                        "<br/>" +
                        "1.Fuente de energ&iacute;a: <br/>" +
                        "2.Gasto pico m&aacute;ximo: <br/>" +
                        "3.Presi&oacute;n:           <br/>" +
                        "<br/><br/>" +
                        "Enviado desde mi Android").toString());
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
        inflater.inflate(R.menu.tab_hidros, menu);
    }

}
