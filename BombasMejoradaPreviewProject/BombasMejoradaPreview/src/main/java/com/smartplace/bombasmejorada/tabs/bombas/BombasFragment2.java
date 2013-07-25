package com.smartplace.bombasmejorada.tabs.bombas;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import com.smartplace.bombasmejorada.R;
import com.smartplace.bombasmejorada.tabs.TabsMainActivity;

/**
 * Created by ROBERTO on 17/06/13.
 */
public class BombasFragment2 extends Fragment {

    //Interface instance used to pass data on to the holder activity
    onBombasFragment2Change mCallback;

    public interface onBombasFragment2Change
    {
        public void onFragmentChange( TabsMainActivity.Identifiers TabIdentifier,String Param1,int Param2, int Param3);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);


        return inflater.inflate(R.layout.tab_bombas_2, container, false);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (onBombasFragment2Change) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
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

                mCallback.onFragmentChange(TabsMainActivity.Identifiers.BombasFragment2,"Test2",1,1);

                // Create new fragment and transaction
                BombasFragment3 newFragment = new BombasFragment3();
                FragmentManager manager         =   getActivity().getFragmentManager();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                BombasFragmentDataManager tempDataManager = ((TabsMainActivity)getActivity()).getBFDataManager();

                Bundle args = new Bundle();
                args.putString("EnergySource", tempDataManager.EnergySource);
                newFragment.setArguments(args);

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
