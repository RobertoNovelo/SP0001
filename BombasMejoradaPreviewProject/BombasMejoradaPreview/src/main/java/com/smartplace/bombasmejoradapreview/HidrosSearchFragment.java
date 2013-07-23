package com.smartplace.bombasmejoradapreview;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

/**
 * Created by BEBETO on 28/06/13.
 */
public class HidrosSearchFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


        return inflater.inflate(R.layout.search_hidros_fragment1, container, false);

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tab_bombas_2, menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_siguiente:

                // Create new fragment and transaction
                HidrosSearchFragment2 newFragment = new HidrosSearchFragment2();
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

        String[] nums = new String[21];

        for(int i=0; i<nums.length; i++)
            nums[i] = Integer.toString(i*5);

        NumberPicker numberPicker = (NumberPicker) getView().findViewById (R.id.numberPicker);
        NumberPicker numberPicker2 =  (NumberPicker) getView().findViewById(R.id.numberPicker2);

        numberPicker.setMaxValue(nums.length-1);
        numberPicker2.setMaxValue(nums.length-1);

        numberPicker.setMinValue(0);
        numberPicker2.setMinValue(0);

        numberPicker.setWrapSelectorWheel(false);
        numberPicker2.setWrapSelectorWheel(false);

        numberPicker.setDisplayedValues(nums);
        numberPicker2.setDisplayedValues(nums);

    }


}
