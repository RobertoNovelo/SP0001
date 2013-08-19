package com.smartplace.bombasmejorada.tabs.hidros;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smartplace.bombasmejorada.R;
import com.smartplace.bombasmejorada.tabs.DataManager;
import com.smartplace.bombasmejorada.tabs.TabsMainActivity;

/**
 * Created by ROBERTO on 28/06/13.
 */
public class HidrosFragment1 extends Fragment {

    // Used to reference current state data to the activity.
    DataManager dataManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        return inflater.inflate(R.layout.tab_hidros_1, container, false);
    }



    @Override
    public void onResume ()
    {
        super.onResume();

        RelativeLayout relLayout = (RelativeLayout) getActivity().findViewById(R.id.club);

        dataManager = ((TabsMainActivity)getActivity()).getDataManager();

        relLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPressed(view);

                /* Pass current Fragment Arguments to next Fragment */
                dataManager.Edificio =((TextView)((RelativeLayout) view).getChildAt(0)).getText().toString();;
                switchFragment();

            }
        });

        relLayout = (RelativeLayout) getActivity().findViewById(R.id.edificio_oficinas);

        relLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPressed(view);
                /* Pass current Fragment Arguments to next Fragment */
                dataManager.Edificio =((TextView)((RelativeLayout) view).getChildAt(0)).getText().toString();;
                switchFragment();

            }
        });

        relLayout = (RelativeLayout) getActivity().findViewById(R.id.escuela);

        relLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPressed(view);
                /* Pass current Fragment Arguments to next Fragment */
                dataManager.Edificio =((TextView)((RelativeLayout) view).getChildAt(0)).getText().toString();;
                switchFragment();

            }
        });

        relLayout = (RelativeLayout) getActivity().findViewById(R.id.hospital);

        relLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPressed(view);
                /* Pass current Fragment Arguments to next Fragment */
                dataManager.Edificio =((TextView)((RelativeLayout) view).getChildAt(0)).getText().toString();;
                switchFragment();

            }
        });

        relLayout = (RelativeLayout) getActivity().findViewById(R.id.hotelomotel);

        relLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPressed(view);
                /* Pass current Fragment Arguments to next Fragment */
                dataManager.Edificio =((TextView)((RelativeLayout) view).getChildAt(0)).getText().toString();;
                switchFragment();

            }
        });

        relLayout = (RelativeLayout) getActivity().findViewById(R.id.tiendasautoservicio);

        relLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPressed(view);
                /* Pass current Fragment Arguments to next Fragment */
                dataManager.Edificio =((TextView)((RelativeLayout) view).getChildAt(0)).getText().toString();;
                switchFragment();

            }
        });
        relLayout = (RelativeLayout) getActivity().findViewById(R.id.plazacomercial);

        relLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPressed(view);
                /* Pass current Fragment Arguments to next Fragment */
                dataManager.Edificio =((TextView)((RelativeLayout) view).getChildAt(0)).getText().toString();;
                switchFragment();

            }
        });



    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tab_bombas_2, menu);
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
        HidrosFragment2 newFragment = new HidrosFragment2();
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
