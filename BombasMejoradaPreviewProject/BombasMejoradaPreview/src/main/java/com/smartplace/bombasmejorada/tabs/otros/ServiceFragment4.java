package com.smartplace.bombasmejorada.tabs.otros;

import android.app.ActionBar;
import android.app.Fragment;
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
public class ServiceFragment4 extends Fragment {

    WebView serviceWebView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ActionBar abar = getActivity().getActionBar();
        abar.setTitle("Servicio");
        abar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1A535A")));
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.tab_otros_service_4, container, false);
        serviceWebView = (WebView)view.findViewById(R.id.service_webview);
        serviceWebView.setVerticalScrollBarEnabled(false);
        serviceWebView.loadData(getString(R.string.terms_text), "text/html", "utf-8");
       return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
            inflater.inflate(R.menu.tab_otros_servicio_4, menu);

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        DataManager dataManager = ((TabsMainActivity)getActivity()).getDataManager();
        if((getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) &&(dataManager.screenSize == "large" || dataManager.screenSize == "xlarge"))
        {
            switch(item.getItemId())
            {
                case R.id.btn_next:
                    ServiceFragment5 newFragment = new ServiceFragment5();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack
                    transaction.replace(android.R.id.tabcontent, newFragment);
                    transaction.remove(getFragmentManager().findFragmentByTag("service_fragment_4"));
                    transaction.addToBackStack(null);

                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                    // Commit the transaction
                    transaction.commit();
                    return  true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }
        else
        {
            switch(item.getItemId())
            {
                case R.id.btn_next:
                    ServiceFragment5 newFragment = new ServiceFragment5();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
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

}