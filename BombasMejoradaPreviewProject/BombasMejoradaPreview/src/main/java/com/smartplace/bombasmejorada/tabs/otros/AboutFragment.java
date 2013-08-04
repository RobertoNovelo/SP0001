package com.smartplace.bombasmejorada.tabs.otros;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.smartplace.bombasmejorada.R;

/**
 * Created by Roberto on 12/07/13.
 */


/**
 * Created by Roberto on 11/07/13.
 */

/*
*   INTEGRATION HINTS:
*
*   layout: tab_otros_about.xmll
*   menu: menu_about_fragment.xml
*   values: about.xml
*
* */
public class AboutFragment extends Fragment {

    WebView aboutWebView;
    Button btnWebSmartPlace;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        ActionBar abar = getActivity().getActionBar();
        abar.setTitle("SmartPlace");
        abar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#195259")));
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.tab_otros_about, container, false);
        aboutWebView = (WebView) view.findViewById(R.id.about_webView);
        aboutWebView.setVerticalScrollBarEnabled(false);
        aboutWebView.loadData(getString(R.string.about_text), "text/html", "utf-8");
        btnWebSmartPlace = (Button) view.findViewById(R.id.btn_web_smartplace);
        btnWebSmartPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.smartplace.mx"));
                getActivity().startActivity(browserIntent);
            }
        });
       return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_about_fragment, menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.action_send:
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"ayuda.android@smartplace.mx"});
                i.putExtra(Intent.EXTRA_SUBJECT, Html.fromHtml("Acerca de App BM").toString());
                i.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(
                        "<br/><br/>" +
                                "Enviado desde mi Android").toString());
                try {
                    startActivity(Intent.createChooser(i, "Enviar correo..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "No hay clientes de correo disponibles", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                Toast.makeText(getActivity().getBaseContext(),"No ID identificado",Toast.LENGTH_SHORT).show();
        }
        return  true;

    }

}