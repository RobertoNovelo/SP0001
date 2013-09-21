package com.smartplace.bombasmejorada.tabs.otros;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.smartplace.bombasmejorada.R;
import com.smartplace.bombasmejorada.tabs.DataManager;
import com.smartplace.bombasmejorada.tabs.TabsMainActivity;

public class TabOtrosFragment extends Fragment {

    DataManager dataManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        ActionBar abar = getActivity().getActionBar();
        abar.setTitle(R.string.tab_otros_title);
        abar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#195259")));

        View view = inflater.inflate(R.layout.tab_otros_main, container, false);
        dataManager = ((TabsMainActivity)getActivity()).getDataManager();
        setHasOptionsMenu(true);
        return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tab_otros, menu);
    }



    @Override
    public void onResume ()
    {
        super.onResume();

        RelativeLayout relLayout = (RelativeLayout) getActivity().findViewById(R.id.contactinfo);

        relLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Create new fragment and transaction
                ContactInfoFragment newFragment = new ContactInfoFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(android.R.id.tabcontent, newFragment);
                transaction.addToBackStack(null);

                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                // Commit the transaction
                transaction.commit();

            }
        });

        relLayout = (RelativeLayout) getActivity().findViewById(R.id.service);

        relLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if((getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) &&(dataManager.screenSize == "large" || dataManager.screenSize == "xlarge"))
                {
                    // Create new fragment and transaction
                    ServiceFragment1 newFragment = new ServiceFragment1();
                    ServiceFragment2 newFragment2 = new ServiceFragment2();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack
                    transaction.replace(android.R.id.tabcontent, newFragment,"service_fragment_1");
                    transaction.add(R.id.myfragment,newFragment2,"service_fragment_2");
                    transaction.addToBackStack(null);

                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                    // Commit the transaction
                    transaction.commit();
                }
                else
                {
                    // Create new fragment and transaction
                    ServiceFragment1 newFragment = new ServiceFragment1();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack
                    transaction.replace(android.R.id.tabcontent, newFragment);
                    transaction.addToBackStack(null);

                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                    // Commit the transaction
                    transaction.commit();
                }
            }
        });

        relLayout = (RelativeLayout) getActivity().findViewById(R.id.help);

        relLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Create new fragment and transaction
                HelpFragment newFragment = new HelpFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(android.R.id.tabcontent, newFragment);
                transaction.addToBackStack(null);

                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                // Commit the transaction
                transaction.commit();

            }
        });

        relLayout = (RelativeLayout) getActivity().findViewById(R.id.about);

        relLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Create new fragment and transaction
                AboutFragment newFragment = new AboutFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(android.R.id.tabcontent, newFragment);
                transaction.addToBackStack(null);

                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                // Commit the transaction
                transaction.commit();

            }
        });


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_accept:
                Toast.makeText(getActivity().getBaseContext(), "Aceptar de tab de otros presionado", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


