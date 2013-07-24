package com.smartplace.bombasmejorada.tabs.bombas;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.app.Fragment;

import com.smartplace.bombasmejorada.R;

/**
 * Created by ROBERTO on 26/06/13.
 */
public class ResultsFound extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        return inflater.inflate(R.layout.results_found_fragment, container, false);

    }

    @Override
    public void onResume ()
    {
        super.onResume();

        RelativeLayout modelo3 = (RelativeLayout) getActivity().findViewById(R.id.modelo3);


        Log.d("NOVELO", "TEST");

        modelo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                RelativeLayout modelo3 = (RelativeLayout) getActivity().findViewById(R.id.modelo3);

                modelo3.setPressed(true);

                Toast.makeText(getActivity(),"modelo 3", Toast.LENGTH_SHORT).show();

                Log.d("NOVELO", "TEST");

                if ( getActivity().getResources().getDrawable(R.drawable.custom_button_green)== modelo3.getBackground())
                {

                    modelo3.setBackgroundResource(R.drawable.custom_button_dark_green);
                    modelo3.bringToFront();

                    Toast.makeText(getActivity(),"darkgreen set", Toast.LENGTH_SHORT).show();

                }
                else
                {

                    modelo3.setBackground( getActivity().getResources().getDrawable(R.drawable.custom_button_green));

                }

            }
        });
        RelativeLayout modelo4 = (RelativeLayout) getActivity().findViewById(R.id.modelo4);

        modelo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RelativeLayout modelo4 = (RelativeLayout) getActivity().findViewById(R.id.modelo4);

                if ( getActivity().getResources().getDrawable(R.drawable.custom_button_green)== modelo4.getBackground())
                {

                    modelo4.setBackground( getActivity().getResources().getDrawable(R.drawable.custom_button_dark_green));

                }
                else
                {

                    modelo4.setBackground( getActivity().getResources().getDrawable(R.drawable.custom_button_green));

                }
            }
        });

        Button btnEnviar = (Button) getActivity().findViewById(R.id.enviarbutton);

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


}
