package com.smartplace.bombasmejorada.tabs.otros;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
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
public class ServiceFragment1 extends Fragment {

    EditText txt_equipo_modelo;
    EditText txt_no_de_serie;
    DataManager dataManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ActionBar abar = getActivity().getActionBar();
        abar.setTitle("Servicio");
        abar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1A535A")));

        View view = inflater.inflate(R.layout.tab_otros_service_1, container, false);
        dataManager = ((TabsMainActivity)getActivity()).getDataManager();
        txt_equipo_modelo = (EditText)view.findViewById(R.id.txt_service_1);
        txt_no_de_serie = (EditText)view.findViewById(R.id.txt_service_2);
        setHasOptionsMenu(true);
       return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);

        if((getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) &&(dataManager.screenSize == "large" || dataManager.screenSize == "xlarge"))
        {
            /*Do nothing*/
        }
        else
        {
            inflater.inflate(R.menu.tab_otros_servicio_1, menu);
        }
    }
    public boolean onOptionsItemSelected(MenuItem item) {


        if((getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) &&(dataManager.screenSize.equalsIgnoreCase("large") || dataManager.screenSize.equalsIgnoreCase("xlarge")))
        {
            return super.onOptionsItemSelected(item);
            /*Do nothing*/
        }
        else
        {
            switch(item.getItemId())
            {
                case R.id.btn_next:
                    ServiceFragment2 newFragment = new ServiceFragment2();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    //getFragmentManager().saveFragmentInstanceState(this);
                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack
                    transaction.replace(android.R.id.tabcontent, newFragment);
                    transaction.addToBackStack(null);

                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                    // Commit the transaction
                    transaction.commit();

                    return  true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }

    }
    @Override
    public void onResume ()
    {
        super.onResume();

        txt_equipo_modelo.setText(dataManager.EquipoModelo);
        txt_no_de_serie.setText(dataManager.NoDeSerie);

    }

    @Override
    public void onPause ()
    {
        super.onPause();

        dataManager.EquipoModelo = txt_equipo_modelo.getText().toString();
        dataManager.NoDeSerie = txt_no_de_serie.getText().toString();

    }

}