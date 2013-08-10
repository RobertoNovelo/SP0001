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
    private Bundle savedState = null;
    private NumberPicker np1;
    private NumberPicker np2;
    private NumberPicker np3;

    public interface onBombasFragment2Change
    {
        public void onFragmentChange( TabsMainActivity.Identifiers TabIdentifier,String Param1,int Param2, int Param3);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.tab_bombas_2, container, false);
        np1 = (NumberPicker)v.findViewById(R.id.numberPicker);
        np2 = (NumberPicker)v.findViewById(R.id.numberPicker2);
        setHasOptionsMenu(true);
        InitNumberPickers(np1,np2);
        if(savedInstanceState != null && savedState == null)
            savedState = savedInstanceState.getBundle("bundle");
        if(savedState != null)
        {
            np1.setValue(savedState.getInt("np1"));
            np2.setValue(savedState.getInt("np2"));
        }
        savedState = null;

        return v;

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
    public void onDestroyView() {
        super.onDestroyView();
        savedState = saveState(); /* vstup defined here for sure */
        np1 = null;
        np2 = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tab_bombas_2, menu);
    }
    private Bundle saveState() { /* called either from onDestroyView() or onSaveInstanceState() */
        Bundle state = new Bundle();
        state.putInt("np1",np1.getValue());
        state.putInt("np2",np2.getValue());
        return state;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle("bundle", savedState != null ? savedState : saveState());

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


    }
private void InitNumberPickers(NumberPicker np1, NumberPicker np2)
{
    String[] nums = new String[21];

    for(int i=0; i<nums.length; i++)
        nums[i] = Integer.toString(i*5);



    np1.setMaxValue(nums.length-1);
    np2.setMaxValue(nums.length-1);

    np1.setMinValue(0);
    np2.setMinValue(0);

    np1.setWrapSelectorWheel(false);
    np2.setWrapSelectorWheel(false);

    np1.setDisplayedValues(nums);
    np2.setDisplayedValues(nums);

}
}
