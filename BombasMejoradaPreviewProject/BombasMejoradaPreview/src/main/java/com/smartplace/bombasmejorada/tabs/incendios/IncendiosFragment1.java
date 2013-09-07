package com.smartplace.bombasmejorada.tabs.incendios;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.graphics.Color;
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
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v=inflater.inflate(R.layout.tab_incendios_1, container, false);
        dataManager = ((TabsMainActivity)getActivity()).getDataManager();
        if((getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) &&(dataManager.screenSize == "large" || dataManager.screenSize == "xlarge"))
        {
            setPressed();
           /*Do nothing it shall just select the value*/
        }
        else
        {
            /*Do nothing*/
        }
        return v;
    }

    @Override
    public void onResume ()
    {
        super.onResume();

        int RelLayoutCount=0;

        RelativeLayout relLayout;


        RelLayoutCount = ((LinearLayout) getActivity().findViewById(R.id.riesgoLigero)).getChildCount();

        for (int i =0 ;i < RelLayoutCount;i++)
        {

            relLayout = (RelativeLayout)((LinearLayout) getActivity().findViewById(R.id.riesgoLigero)).getChildAt(i);
            relLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


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


                    dataManager.Uso =  ((TextView)((RelativeLayout) view).getChildAt(0)).getText().toString();
                    dataManager.Grupo = "Riesgo Extra 2";
                    dataManager.iPresion = 100;

                    switchFragment();


                }
            });

        }
    }


    private void switchFragment ()
    {
        if((getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) &&(dataManager.screenSize == "large" || dataManager.screenSize == "xlarge"))
        {
            setPressed();
           /*Do nothing it shall just select the value*/
        }
        else
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
    private void setPressed ()
    {
        int RelLayoutCount = ((LinearLayout) v.findViewById(R.id.riesgoLigero)).getChildCount();
        TextView txt;
        for (int i =0 ;i < RelLayoutCount;i++)
        {

            RelativeLayout relLayout = (RelativeLayout)((LinearLayout) v.findViewById(R.id.riesgoLigero)).getChildAt(i);
             txt = (TextView)(relLayout.getChildAt(0));
            if( txt.getText().toString().equalsIgnoreCase(dataManager.Uso))
            {
                relLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                txt.setTextColor(Color.parseColor("#c20f14"));
            }
            else
            {
                relLayout.setBackgroundColor(Color.parseColor("#c20f14"));
                txt.setTextColor(Color.parseColor("#FFFFFF"));
            }
        }
        RelLayoutCount = ((LinearLayout) v.findViewById(R.id.riesgoOrdinario1)).getChildCount();

        for (int i =0 ;i < RelLayoutCount;i++)
        {
            RelativeLayout relLayout = (RelativeLayout)((LinearLayout) v.findViewById(R.id.riesgoOrdinario1)).getChildAt(i);
             txt = (TextView)(relLayout.getChildAt(0));
            if( txt.getText().toString().equalsIgnoreCase(dataManager.Uso))
            {
                relLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                txt.setTextColor(Color.parseColor("#c20f14"));
            }
            else
            {
                relLayout.setBackgroundColor(Color.parseColor("#c20f14"));
                txt.setTextColor(Color.parseColor("#FFFFFF"));
            }
        }
        RelLayoutCount = ((LinearLayout) v.findViewById(R.id.riesgoOrdinario2)).getChildCount();

        for (int i =0 ;i < RelLayoutCount;i++)
        {
            RelativeLayout relLayout = (RelativeLayout)((LinearLayout) v.findViewById(R.id.riesgoOrdinario2)).getChildAt(i);
             txt = (TextView)(relLayout.getChildAt(0));
            if( txt.getText().toString().equalsIgnoreCase(dataManager.Uso))
            {
                relLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                txt.setTextColor(Color.parseColor("#c20f14"));
            }
            else
            {
                relLayout.setBackgroundColor(Color.parseColor("#c20f14"));
                txt.setTextColor(Color.parseColor("#FFFFFF"));
            }
        }
        RelLayoutCount = ((LinearLayout)v.findViewById(R.id.riesgoExtra1)).getChildCount();

        for (int i =0 ;i < RelLayoutCount;i++)
        {
            RelativeLayout relLayout = (RelativeLayout)((LinearLayout) v.findViewById(R.id.riesgoExtra1)).getChildAt(i);
             txt = (TextView)(relLayout.getChildAt(0));
            if( txt.getText().toString().equalsIgnoreCase(dataManager.Uso))
            {
                relLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                txt.setTextColor(Color.parseColor("#c20f14"));
            }
            else
            {
                relLayout.setBackgroundColor(Color.parseColor("#c20f14"));
                txt.setTextColor(Color.parseColor("#FFFFFF"));
            }
        }
        RelLayoutCount = ((LinearLayout) v.findViewById(R.id.riesgoExtra2)).getChildCount();

        for (int i =0 ;i < RelLayoutCount;i++)
        {
            RelativeLayout relLayout = (RelativeLayout)((LinearLayout) v.findViewById(R.id.riesgoExtra2)).getChildAt(i);
             txt = (TextView)(relLayout.getChildAt(0));
            if( txt.getText().toString().equalsIgnoreCase(dataManager.Uso))
            {
                relLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                txt.setTextColor(Color.parseColor("#c20f14"));
            }
            else
            {
                relLayout.setBackgroundColor(Color.parseColor("#c20f14"));
                txt.setTextColor(Color.parseColor("#FFFFFF"));
            }
        }


/*
        if(dataManager.Edificio == "Club")
        {
            relLayout1.setBackgroundColor(Color.parseColor("#FFFFFF"));
            txt1.setTextColor(Color.parseColor("#1212B1"));
        }
        else if(dataManager.Edificio == "Edificio Oficinas")
        {
            relLayout2.setBackgroundColor(Color.parseColor("#FFFFFF"));
            txt2.setTextColor(Color.parseColor("#1212B1"));
        }
        else if(dataManager.Edificio == "Escuela")
        {
            relLayout3.setBackgroundColor(Color.parseColor("#FFFFFF"));
            txt3.setTextColor(Color.parseColor("#1212B1"));
        }
        else if(dataManager.Edificio == "Hospital")
        {
            relLayout4.setBackgroundColor(Color.parseColor("#FFFFFF"));
            txt4.setTextColor(Color.parseColor("#1212B1"));
        }
        else if(dataManager.Edificio == "Hotel o Motel")
        {
            relLayout5.setBackgroundColor(Color.parseColor("#FFFFFF"));
            txt5.setTextColor(Color.parseColor("#1212B1"));
        }
        else if(dataManager.Edificio == "Tiendas de\nAutoservicio")
        {
            relLayout6.setBackgroundColor(Color.parseColor("#FFFFFF"));
            txt6.setTextColor(Color.parseColor("#1212B1"));
        }
        else if(dataManager.Edificio == "Plaza Comercial")
        {
            relLayout7.setBackgroundColor(Color.parseColor("#FFFFFF"));
            txt7.setTextColor(Color.parseColor("#1212B1"));
        }
        else if(dataManager.Edificio == "Vivienda")
        {
            relLayout8.setBackgroundColor(Color.parseColor("#FFFFFF"));
            txt8.setTextColor(Color.parseColor("#1212B1"));
        }
        else
        {
            *//*Do nothing*//*
        }*/
    }

}
