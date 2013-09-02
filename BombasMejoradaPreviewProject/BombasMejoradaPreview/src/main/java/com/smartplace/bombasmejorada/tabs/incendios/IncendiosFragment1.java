package com.smartplace.bombasmejorada.tabs.incendios;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.smartplace.bombasmejorada.R;
import com.smartplace.bombasmejorada.tabs.DataManager;
import com.smartplace.bombasmejorada.tabs.TabsMainActivity;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by ROBERTO on 15/06/13.
 */

public class IncendiosFragment1 extends Fragment {

    // Used to reference current state data to the activity.
    DataManager dataManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        return inflater.inflate(R.layout.tab_incendios_1, container, false);
    }

    @Override
    public void onResume ()
    {
        super.onResume();

        int RelLayoutCount=0;

        RelativeLayout relLayout;

        dataManager = ((TabsMainActivity)getActivity()).getDataManager();

        RelLayoutCount = ((LinearLayout) getActivity().findViewById(R.id.riesgoLigero)).getChildCount();

        for (int i =0 ;i < RelLayoutCount;i++)
        {

            relLayout = (RelativeLayout)((LinearLayout) getActivity().findViewById(R.id.riesgoLigero)).getChildAt(i);
            relLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    setPressed(view);

                    dataManager.Uso =  ((TextView)((RelativeLayout) view).getChildAt(0)).getText().toString();
                    dataManager.Grupo = "Riesgo Ligero";
                    dataManager.iPresion = 65;

                    switchFragment();


                }
            });

        }


        RelLayoutCount = ((LinearLayout) getActivity().findViewById(R.id.riesgoOrdinario1)).getChildCount();

        for (int i =0 ;i < RelLayoutCount;i++)
        {

            relLayout = (RelativeLayout)((LinearLayout) getActivity().findViewById(R.id.riesgoOrdinario1)).getChildAt(i);
            relLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    setPressed(view);

                    dataManager.Uso =  ((TextView)((RelativeLayout) view).getChildAt(0)).getText().toString();
                    dataManager.Grupo = "Riesgo Ordinario 1";
                    dataManager.iPresion = 100;

                    switchFragment();


                }
            });

        }

        RelLayoutCount = ((LinearLayout) getActivity().findViewById(R.id.riesgoOrdinario2)).getChildCount();

        for (int i =0 ;i < RelLayoutCount;i++)
        {

            relLayout = (RelativeLayout)((LinearLayout) getActivity().findViewById(R.id.riesgoOrdinario2)).getChildAt(i);
            relLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    setPressed(view);

                    dataManager.Uso =  ((TextView)((RelativeLayout) view).getChildAt(0)).getText().toString();
                    dataManager.Grupo = "Riesgo Ordinario 2";
                    dataManager.iPresion = 100;

                    switchFragment();


                }
            });

        }

        RelLayoutCount = ((LinearLayout) getActivity().findViewById(R.id.riesgoExtra1)).getChildCount();

        for (int i =0 ;i < RelLayoutCount;i++)
        {

            relLayout = (RelativeLayout)((LinearLayout) getActivity().findViewById(R.id.riesgoExtra1)).getChildAt(i);
            relLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    setPressed(view);

                    dataManager.Uso =  ((TextView)((RelativeLayout) view).getChildAt(0)).getText().toString();
                    dataManager.Grupo = "Riesgo Extra 1";
                    dataManager.iPresion = 100;

                    switchFragment();


                }
            });

        }

        RelLayoutCount = ((LinearLayout) getActivity().findViewById(R.id.riesgoExtra2)).getChildCount();

        for (int i =0 ;i < RelLayoutCount;i++)
        {

            relLayout = (RelativeLayout)((LinearLayout) getActivity().findViewById(R.id.riesgoExtra2)).getChildAt(i);
            relLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    setPressed(view);

                    dataManager.Uso =  ((TextView)((RelativeLayout) view).getChildAt(0)).getText().toString();
                    dataManager.Grupo = "Riesgo Extra 2";
                    dataManager.iPresion = 100;

                    switchFragment();


                }
            });

        }

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
        IncendiosFragment2 newFragment = new IncendiosFragment2();
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
