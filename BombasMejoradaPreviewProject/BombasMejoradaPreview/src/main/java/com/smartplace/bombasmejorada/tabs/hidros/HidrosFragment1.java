package com.smartplace.bombasmejorada.tabs.hidros;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.graphics.Color;
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
    RelativeLayout relLayout1;
    RelativeLayout relLayout2;
    RelativeLayout relLayout3;
    RelativeLayout relLayout4;
    RelativeLayout relLayout5;
    RelativeLayout relLayout6;
    RelativeLayout relLayout7;
    RelativeLayout relLayout8;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataManager = ((TabsMainActivity)getActivity()).getDataManager();

        View view= inflater.inflate(R.layout.tab_hidros_1, container, false);
        relLayout1 = (RelativeLayout) view.findViewById(R.id.club);
        relLayout2 = (RelativeLayout) view.findViewById(R.id.edificio_oficinas);
        relLayout3 = (RelativeLayout) view.findViewById(R.id.escuela);
        relLayout4 = (RelativeLayout) view.findViewById(R.id.hospital);
        relLayout5 = (RelativeLayout) view.findViewById(R.id.hotelomotel);
        relLayout6 = (RelativeLayout) view.findViewById(R.id.tiendasautoservicio);
        relLayout7 = (RelativeLayout) view.findViewById(R.id.plazacomercial);
        relLayout8 = (RelativeLayout) view.findViewById(R.id.vivienda);
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
                dataManager.Edificio = "Club";//((TextView)((RelativeLayout) view).getChildAt(0)).getText().toString();;
                switchFragment();

            }
        });

        relLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Pass current Fragment Arguments to next Fragment */
                dataManager.Edificio ="Edificio Oficinas";//((TextView)((RelativeLayout) view).getChildAt(0)).getText().toString();;
                switchFragment();

            }
        });

        relLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Pass current Fragment Arguments to next Fragment */
                dataManager.Edificio = "Escuela";//((TextView)((RelativeLayout) view).getChildAt(0)).getText().toString();;
                switchFragment();

            }
        });
        relLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Pass current Fragment Arguments to next Fragment */
                dataManager.Edificio ="Hospital";//((TextView)((RelativeLayout) view).getChildAt(0)).getText().toString();;
                switchFragment();

            }
        });
        relLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Pass current Fragment Arguments to next Fragment */
                dataManager.Edificio = "Hotel o Motel";//((TextView)((RelativeLayout) view).getChildAt(0)).getText().toString();;
                switchFragment();

            }
        });
        relLayout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Pass current Fragment Arguments to next Fragment */
                dataManager.Edificio ="Tiendas de\nAutoservicio";//((TextView)((RelativeLayout) view).getChildAt(0)).getText().toString();;
                switchFragment();

            }
        });
        relLayout7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Pass current Fragment Arguments to next Fragment */
                dataManager.Edificio ="Plaza Comercial";//((TextView)((RelativeLayout) view).getChildAt(0)).getText().toString();;
                switchFragment();

            }
        });
        relLayout8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Pass current Fragment Arguments to next Fragment */
                dataManager.Edificio = "Vivienda";//((TextView)((RelativeLayout) view).getChildAt(0)).getText().toString();;
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

    private void setPressed ()
    {
        relLayout1.setBackgroundColor(Color.parseColor("#1212B1"));
        relLayout2.setBackgroundColor(Color.parseColor("#1212B1"));
        relLayout3.setBackgroundColor(Color.parseColor("#1212B1"));
        relLayout4.setBackgroundColor(Color.parseColor("#1212B1"));
        relLayout5.setBackgroundColor(Color.parseColor("#1212B1"));
        relLayout6.setBackgroundColor(Color.parseColor("#1212B1"));
        relLayout7.setBackgroundColor(Color.parseColor("#1212B1"));
        relLayout8.setBackgroundColor(Color.parseColor("#1212B1"));
        TextView txt1 = (TextView)(relLayout1.getChildAt(0));
        txt1.setTextColor(Color.parseColor("#FFFFFF"));
        TextView txt2 = (TextView)(relLayout2.getChildAt(0));
        txt2.setTextColor(Color.parseColor("#FFFFFF"));
        TextView txt3 = (TextView)(relLayout3.getChildAt(0));
        txt3.setTextColor(Color.parseColor("#FFFFFF"));
        TextView txt4 = (TextView)(relLayout4.getChildAt(0));
        txt4.setTextColor(Color.parseColor("#FFFFFF"));
        TextView txt5 = (TextView)(relLayout5.getChildAt(0));
        txt5.setTextColor(Color.parseColor("#FFFFFF"));
        TextView txt6 = (TextView)(relLayout6.getChildAt(0));
        txt6.setTextColor(Color.parseColor("#FFFFFF"));
        TextView txt7 = (TextView)(relLayout7.getChildAt(0));
        txt7.setTextColor(Color.parseColor("#FFFFFF"));
        TextView txt8 = (TextView)(relLayout8.getChildAt(0));
        txt8.setTextColor(Color.parseColor("#FFFFFF"));

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
            /*Do nothing*/
        }
    }

}
