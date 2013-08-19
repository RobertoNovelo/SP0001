package com.smartplace.bombasmejorada.tabs.bombas;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.smartplace.bombasmejorada.R;
import com.smartplace.bombasmejorada.tabs.DataManager;
import com.smartplace.bombasmejorada.tabs.TabsMainActivity;

/**
 * Created by ROBERTO on 15/06/13.
 */
public class BombasFragment1 extends Fragment {

    //Interface instance used to pass data on to the holder activity
    onSaveData mCallback;
    // Used to reference current state data to the activity.
    DataManager dataManager = new DataManager();

    public interface onSaveData
    {
        public void saveData(  TabsMainActivity.Identifiers TabIdentifier, DataManager fragmentDataManager);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.tab_bombas_1, container, false);
        return view;

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

    @Override
    public void onResume ()
    {
        super.onResume();

        RelativeLayout relLayout = (RelativeLayout) getActivity().findViewById(R.id.monofasica110);


        relLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPressed(view);
                /* Pass current Fragment Arguments to next Fragment */

                dataManager.EnergySource = "Monofasica 110 V";
                mCallback.saveData(TabsMainActivity.Identifiers.BombasFragment1, dataManager);
                switchFragment();

            }
        });

        relLayout = (RelativeLayout) getActivity().findViewById(R.id.monofasica220);

        relLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPressed(view);
                /* Pass current Fragment Arguments to next Fragment */
                dataManager.EnergySource = "Monofasica 220 V";
                mCallback.saveData(TabsMainActivity.Identifiers.BombasFragment1, dataManager);
                switchFragment();

            }
        });

        relLayout = (RelativeLayout) getActivity().findViewById(R.id.trifasica220);

        relLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPressed(view);
                /* Pass current Fragment Arguments to next Fragment */
                dataManager.EnergySource = "Trifasica 220 V";
                mCallback.saveData(TabsMainActivity.Identifiers.BombasFragment1, dataManager);
                switchFragment();

            }
        });

        relLayout = (RelativeLayout) getActivity().findViewById(R.id.trifasica440);

        relLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPressed(view);
                /* Pass current Fragment Arguments to next Fragment */
                dataManager.EnergySource = "Trifasica 440 V";
                mCallback.saveData(TabsMainActivity.Identifiers.BombasFragment1, dataManager);
                switchFragment();

            }
        });

        relLayout = (RelativeLayout) getActivity().findViewById(R.id.gasolina);

        relLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPressed(view);
                /* Pass current Fragment Arguments to next Fragment */
                dataManager.EnergySource = "Gasolina";
                mCallback.saveData(TabsMainActivity.Identifiers.BombasFragment1, dataManager);
                switchFragment();

            }
        });

        relLayout = (RelativeLayout) getActivity().findViewById(R.id.diesel);

        relLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPressed(view);
                /* Pass current Fragment Arguments to next Fragment */
                dataManager.EnergySource = "Diesel";
                mCallback.saveData(TabsMainActivity.Identifiers.BombasFragment1, dataManager);
                switchFragment();

            }
        });



    }


    private void setPressed(View view)
    {
        ViewGroup viewGroup = (ViewGroup) view;
        for (int i = 0; i < viewGroup .getChildCount(); i++) {

            View viewChild = viewGroup .getChildAt(i);
            viewChild.setPressed(true);

        }

    }

    private void switchFragment ()
    {
        // Create new fragment and transaction
        BombasFragment2 newFragment = new BombasFragment2();
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
