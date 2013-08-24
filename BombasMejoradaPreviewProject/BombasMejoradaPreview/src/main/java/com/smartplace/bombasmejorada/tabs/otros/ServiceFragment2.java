package com.smartplace.bombasmejorada.tabs.otros;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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
public class ServiceFragment2 extends Fragment {

    EditText txt_falla;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ActionBar abar = getActivity().getActionBar();
        abar.setTitle("Servicio");
        abar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1A535A")));
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.tab_otros_service_2, container, false);
        txt_falla = (EditText)view.findViewById(R.id.txt_service_3);
       return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tab_otros_servicio_2, menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.btn_next:
                ServiceFragment3 newFragment = new ServiceFragment3();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
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
    @Override
    public void onResume ()
    {
        super.onResume();

        DataManager dataManager = ((TabsMainActivity)getActivity()).getDataManager();
        txt_falla.setText(dataManager.Falla);

    }

    @Override
    public void onPause ()
    {
        super.onPause();

        DataManager dataManager = ((TabsMainActivity)getActivity()).getDataManager();
        dataManager.Falla = txt_falla.getText().toString();

    }

}