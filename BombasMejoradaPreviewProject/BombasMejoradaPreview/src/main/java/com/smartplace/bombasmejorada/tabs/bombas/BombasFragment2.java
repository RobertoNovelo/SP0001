package com.smartplace.bombasmejorada.tabs.bombas;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.NumberPicker;

import com.smartplace.bombasmejorada.R;
import com.smartplace.bombasmejorada.tabs.DataManager;
import com.smartplace.bombasmejorada.tabs.TabsMainActivity;

/**
 * Created by ROBERTO on 17/06/13.
 */
public class BombasFragment2 extends Fragment {

    //Interface instance used to pass data on to the holder activity
    onSaveData mCallback;
    private Bundle savedState = null;
    private NumberPicker np1;
    private NumberPicker np2;

    public interface onSaveData
    {
        public void saveData(TabsMainActivity.Identifiers TabIdentifier, DataManager fragmentDataManager);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.tab_bombas_2, container, false);
        np1 = (NumberPicker)v.findViewById(R.id.numberPicker);
        np2 = (NumberPicker)v.findViewById(R.id.numberPicker2);
        setHasOptionsMenu(true);

//        if(savedInstanceState != null && savedState == null)
//        {
//            savedState = savedInstanceState.getBundle("bundle");
//            if(savedState != null)
//            {
//                np1.setValue(savedState.getInt("np1"));
//                np2.setValue(savedState.getInt("np2"));
//            }
//        }
//        savedState = null;



        return v;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (onSaveData) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        savedState = saveState(); /* vstup defined here for sure */
//        np1 = null;
//        np2 = null;
//    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tab_bombas_2, menu);
    }
//    private Bundle saveState() { /* called either from onDestroyView() or onSaveInstanceState() */
//        Bundle state = new Bundle();
//        state.putInt("np1",np1.getValue());
//        state.putInt("np2",np2.getValue());
//        return state;
//    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putBundle("bundle", savedState != null ? savedState : saveState());
//
//    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_siguiente:

                // Create new fragment and transaction
                BombasFragment3 newFragment = new BombasFragment3();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

//                Bundle args = new Bundle();
//                args.putString("EnergySource", tempDataManager.EnergySource);
//                newFragment.setArguments(args);

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
        np1.setValue((dataManager.lpm / 10) +9);
        np2.setValue((dataManager.psi / 5) +4);

    }

    @Override
    public void onPause ()
    {
        super.onPause();

        DataManager dataManager = ((TabsMainActivity)getActivity()).getDataManager();
        dataManager.lpm = (np1.getValue() - 9 ) * 10 ;
        dataManager.psi = (np2.getValue() - 4 ) * 5;

    }

    private void InitNumberPickers(NumberPicker np1, NumberPicker np2)
    {
        //RoNo: Init stuff
        String[] np1nums = new String[510];
        for(int i=0; i<np1nums.length; i++)
        {
            np1nums[i] = Integer.toString((i+1)*10);
        }

        String[] np2nums = new String[41];
        for(int i=0; i<np2nums.length; i++)
        {
            np2nums[i] = Integer.toString((i+1)*5);
        }

        np1.setMaxValue(np1nums.length-1);
        np1.setMinValue(10);
        np1.setWrapSelectorWheel(true);
        np1.setDisplayedValues(np1nums);

        np2.setMaxValue(np2nums.length-1);
        np2.setMinValue(5);
        np2.setWrapSelectorWheel(true);
        np2.setDisplayedValues(np2nums);
    }
}
