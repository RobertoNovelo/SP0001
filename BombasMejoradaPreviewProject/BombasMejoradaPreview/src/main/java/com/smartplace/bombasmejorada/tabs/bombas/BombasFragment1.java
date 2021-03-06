package com.smartplace.bombasmejorada.tabs.bombas;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
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

    private DataManager dataManager;
    RelativeLayout relLayout1;
    RelativeLayout relLayout2;
    RelativeLayout relLayout3;
    RelativeLayout relLayout4;
    RelativeLayout relLayout5;
    RelativeLayout relLayout6;
    // Used to reference current state data to the activity.

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.tab_bombas_1, container, false);
        dataManager = ((TabsMainActivity)getActivity()).getDataManager();
        relLayout1 = (RelativeLayout) view.findViewById(R.id.monofasica110);
        relLayout2 = (RelativeLayout) view.findViewById(R.id.monofasica220);
        relLayout3 = (RelativeLayout) view.findViewById(R.id.trifasica220);
        relLayout4 = (RelativeLayout) view.findViewById(R.id.trifasica440);
        relLayout5 = (RelativeLayout) view.findViewById(R.id.gasolina);
        relLayout6 = (RelativeLayout) view.findViewById(R.id.diesel);
        if((getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) &&(dataManager.screenSize == "large" || dataManager.screenSize == "xlarge"))
        {
            setPressed();
           /*Do nothing it shall just select the value*/
        }
        else
        {
            /*Do nothing*/
        }
        return view;
    }

    @Override
    public void onResume ()
    {
        super.onResume();

        relLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Pass current Fragment Arguments to next Fragment */
                dataManager.EnergySource = "Monofasica 110 V";
                switchFragment();

            }
        });

        relLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Pass current Fragment Arguments to next Fragment */
                dataManager.EnergySource = "Monofasica 220 V";
                switchFragment();
            }
        });
        relLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // setPressed(view);
                /* Pass current Fragment Arguments to next Fragment */
                dataManager.EnergySource = "Trifasica 220 V";
                switchFragment();
            }
        });
        relLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // setPressed(view);
                /* Pass current Fragment Arguments to next Fragment */
                dataManager.EnergySource = "Trifasica 440 V";
                switchFragment();
            }
        });
        relLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  setPressed(view);
                /* Pass current Fragment Arguments to next Fragment */
                dataManager.EnergySource = "Gasolina";
                switchFragment();
            }
        });
        relLayout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // setPressed(view);
                /* Pass current Fragment Arguments to next Fragment */
                dataManager.EnergySource = "Diesel";
                switchFragment();
            }
        });
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
            BombasFragment2 newFragment = new BombasFragment2();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            transaction.replace(android.R.id.tabcontent, newFragment,"bombas_fragment_2");
            transaction.addToBackStack(null);

            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

            // Commit the transaction
            transaction.commit();
        }

    }
    private void setPressed ()
    {
        relLayout1.setBackgroundColor(Color.parseColor("#26AFB1"));
        relLayout2.setBackgroundColor(Color.parseColor("#26AFB1"));
        relLayout3.setBackgroundColor(Color.parseColor("#26AFB1"));
        relLayout4.setBackgroundColor(Color.parseColor("#26AFB1"));
        relLayout5.setBackgroundColor(Color.parseColor("#26AFB1"));
        relLayout6.setBackgroundColor(Color.parseColor("#26AFB1"));
        if(dataManager.EnergySource == "Monofasica 110 V")
        {
            relLayout1.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else if(dataManager.EnergySource == "Monofasica 220 V")
        {
            relLayout2.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else if(dataManager.EnergySource == "Trifasica 220 V")
        {
            relLayout3.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else if(dataManager.EnergySource == "Trifasica 440 V")
        {
            relLayout4.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else if(dataManager.EnergySource == "Gasolina")
        {
            relLayout5.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else if(dataManager.EnergySource == "Diesel")
        {
            relLayout6.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else
        {
            /*Do nothing*/
        }
    }

}
