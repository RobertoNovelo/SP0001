package com.smartplace.bombasmejorada.tabs.otros;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
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
*   layout: tab_otros_aboutt.xml
*   menu: menu_about_fragment.xml
*   values: about.xml
*
* */
public class ServiceFragment1 extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ActionBar abar = getActivity().getActionBar();
        abar.setTitle("Servicio");
        abar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1A535A")));
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.tab_otros_service_1, container, false);

       return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tab_otros_servicio_1, menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
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
                break;
            default:
                Toast.makeText(getActivity().getBaseContext(),"No ID identificado",Toast.LENGTH_SHORT).show();
        }
        return  true;

    }

}