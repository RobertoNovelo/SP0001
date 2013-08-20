package com.smartplace.bombasmejorada.tabs.incendios;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.smartplace.bombasmejorada.R;
import com.smartplace.bombasmejorada.tabs.DataManager;
import com.smartplace.bombasmejorada.tabs.TabsMainActivity;

/**
 * Created by ROBERTO on 28/06/13.
 */
public class IncendiosFragment3 extends Fragment {


    private NumberPicker np1;
    private NumberPicker np2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View v = inflater.inflate(R.layout.tab_incendios_3, container, false);

        np1 = (NumberPicker)v.findViewById(R.id.incendiosPicker1);
        np2 = (NumberPicker)v.findViewById(R.id.incendiosPicker2);

        setHasOptionsMenu(true);

        return v;

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tab_incendios_2, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_accept:

                // Create new fragment and transaction
                IncendiosFragment4 newFragment = new IncendiosFragment4();
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

    @Override
    public void onResume ()
    {
        super.onResume();

        InitNumberPickers(np1, np2);

        DataManager dataManager = ((TabsMainActivity)getActivity()).getDataManager();
        np1.setValue((dataManager.hSalidas / 5) -2 );
        np2.setValue((dataManager.hDistVertical / 5) -2);


    }

    @Override
    public void onPause ()
    {
        super.onPause();

        DataManager dataManager =   ((TabsMainActivity)getActivity()).getDataManager();
        dataManager.iLongSalida =   (np1.getValue() +2 ) * 5 ;
        dataManager.iDesnivel =     (np2.getValue() +2 ) * 5;

    }

    private void InitNumberPickers(NumberPicker np1, NumberPicker np2)
    {
        //RoNo: Init stuff
        String[] np1nums = new String[2000];
        String[] np2nums = new String[100];

        for(int i=0; i<np1nums.length; i++)
        {
            np1nums[i] = Integer.toString((i+1)*5);
        }

        for(int i=0; i<np2nums.length; i++)
        {
            np2nums[i] = Integer.toString((i+1)*5);
        }

        np1.setMaxValue(np1nums.length-1);
        np1.setMinValue(0);
        np1.setWrapSelectorWheel(true);
        np1.setDisplayedValues(np1nums);

        np2.setMaxValue(np2nums.length-1);
        np2.setMinValue(0);
        np2.setWrapSelectorWheel(true);
        np2.setDisplayedValues(np2nums);
    }


}
