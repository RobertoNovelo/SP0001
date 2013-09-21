package com.smartplace.bombasmejorada.tabs.otros;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
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
    DataManager dataManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ActionBar abar = getActivity().getActionBar();
        abar.setTitle("Servicio");
        abar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1A535A")));

        View view = inflater.inflate(R.layout.tab_otros_service_2, container, false);
        dataManager = ((TabsMainActivity)getActivity()).getDataManager();
        txt_falla = (EditText)view.findViewById(R.id.txt_service_3);
        setHasOptionsMenu(true);
       return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tab_otros_servicio_2, menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if((getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) &&(dataManager.screenSize == "large" || dataManager.screenSize == "xlarge"))
        {
            switch(item.getItemId())
            {
                case R.id.btn_next:
                    ServiceFragment3 newFragment = new ServiceFragment3();
                    ServiceFragment4 newFragment4 = new ServiceFragment4();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack
                    transaction.replace(android.R.id.tabcontent, newFragment,"service_fragment_3");
                    transaction.remove(fragmentManager.findFragmentByTag("service_fragment_2"));
                    transaction.add(R.id.myfragment, newFragment4, "service_fragment_4");
                    transaction.addToBackStack(null);

                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                    // Commit the transaction
                    transaction.commit();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }
        else
        {
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
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }

    }
    @Override
    public void onResume ()
    {
        super.onResume();

        txt_falla.setText(dataManager.Falla);

    }

    @Override
    public void onPause ()
    {
        super.onPause();

        dataManager.Falla = txt_falla.getText().toString();

    }

}