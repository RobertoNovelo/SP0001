package com.smartplace.bombasmejorada.tabs.incendios;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.smartplace.bombasmejorada.R;
import com.smartplace.bombasmejorada.tabs.DataManager;
import com.smartplace.bombasmejorada.tabs.TabsMainActivity;
import com.smartplace.bombasmejorada.tabs.hidros.HidrosFragment3;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by ROBERTO on 15/06/13.
 */

public class IncendiosFragment2 extends Fragment {

    // Used to reference current state data to the activity.
    DataManager dataManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.tab_incendios_2, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        if((getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) &&(dataManager.screenSize == "large" || dataManager.screenSize == "xlarge"))
        {

           /*Do nothing it shall just select the value*/
        }
        else
        {
            inflater.inflate(R.menu.tab_incendios_2, menu);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if((getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) &&(dataManager.screenSize == "large" || dataManager.screenSize == "xlarge"))
        {
            return super.onOptionsItemSelected(item);
           /*Do nothing it shall just select the value*/
        }
        else
        {
            switch (item.getItemId()) {
                case R.id.btn_accept:

                   switchFragment();

                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    public void onPrepareOptionsMenu (Menu menu) {
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
            // Do something
        } else{

            if (dataManager.iproteccionCorrecta)
            {
                menu.getItem(0).setEnabled(true);
            }
            else
            {
                menu.getItem(0).setEnabled(false);
            }
        }

    }

    @Override
    public void onResume ()
    {
        super.onResume();

        dataManager = ((TabsMainActivity)getActivity()).getDataManager();


        CheckBox chkBox;

        chkBox = (CheckBox)getActivity().findViewById(R.id.checkBox1);
        chkBox.setChecked(dataManager.iCheckBox1);
        chkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCheckboxClicked(view);
            }
        });
        chkBox = (CheckBox)getActivity().findViewById(R.id.checkBox2);
        chkBox.setChecked(dataManager.iCheckBox2);
        chkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCheckboxClicked(view);
            }
        });
        chkBox = (CheckBox)getActivity().findViewById(R.id.checkBox3);
        chkBox.setChecked(dataManager.iCheckBox3);
        chkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCheckboxClicked(view);
            }
        });
        chkBox = (CheckBox)getActivity().findViewById(R.id.checkBox4);
        chkBox.setChecked(dataManager.iCheckBox4);
        chkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCheckboxClicked(view);
            }
        });

    }

    private void onCheckboxClicked(View view) {

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBox1:
                    dataManager.iCheckBox1 = ((CheckBox) view).isChecked();
            break;
            case R.id.checkBox2:
                    dataManager.iCheckBox2 = ((CheckBox) view).isChecked();
            break;
            case R.id.checkBox3:
                    dataManager.iCheckBox3 = ((CheckBox) view).isChecked();
            break;
            case R.id.checkBox4:
                    dataManager.iCheckBox4 = ((CheckBox) view).isChecked();
            break;

        }

        validateCheckBoxStatus();

    }

    private void validateCheckBoxStatus()
    {


        if ((((CheckBox)getActivity().findViewById(R.id.checkBox1)).isChecked() == true) && (((CheckBox)getActivity().findViewById(R.id.checkBox2)).isChecked() == false) && (((CheckBox)getActivity().findViewById(R.id.checkBox3)).isChecked() == false) && (((CheckBox)getActivity().findViewById(R.id.checkBox4)).isChecked() == false))
        {
            dataManager.iProteccion = "Mangueras de 1.5\"";

            if (dataManager.Grupo == "Riesgo Ligero")
            {
                dataManager.iGastoPico = 100;
            }
            else if (dataManager.Grupo == "Riesgo Ordinario Grupo 1")
            {
                dataManager.iGastoPico = 250;
            }
            else
            {
                dataManager.iGastoPico = 500;
            }

            dataManager.iproteccionCorrecta = true;
        }

        else if ((((CheckBox)getActivity().findViewById(R.id.checkBox1)).isChecked() == true) && (((CheckBox)getActivity().findViewById(R.id.checkBox2)).isChecked() == true) && (((CheckBox)getActivity().findViewById(R.id.checkBox3)).isChecked() == false) && (((CheckBox)getActivity().findViewById(R.id.checkBox4)).isChecked() == false))
        {
            dataManager.iProteccion = "Mangueras de\n1.5\" y 2.5\"";

            if (dataManager.Grupo == "Riesgo Ligero")
            {
                dataManager.iGastoPico = 100;
            }
            else if (dataManager.Grupo == "Riesgo Ordinario Grupo 1")
            {
                dataManager.iGastoPico = 250;
            }
            else
            {
                dataManager.iGastoPico = 500;
            }

            dataManager.iproteccionCorrecta = true;
        }

        else if ((((CheckBox)getActivity().findViewById(R.id.checkBox1)).isChecked() == true) && (((CheckBox)getActivity().findViewById(R.id.checkBox2)).isChecked() == false) && (((CheckBox)getActivity().findViewById(R.id.checkBox3)).isChecked() == true) && (((CheckBox)getActivity().findViewById(R.id.checkBox4)).isChecked() == false))
        {
            dataManager.iProteccion = "Mangueras de\n1.5\" y Rociadores";

            if (dataManager.Grupo == "Riesgo Ligero")
            {
                dataManager.iGastoPico = 250;
            }
            else if (dataManager.Grupo == "Riesgo Ordinario Grupo 1")
            {
                dataManager.iGastoPico = 350;
            }
            else if (dataManager.Grupo == "Riesgo Extra Grupo 2")
            {
                dataManager.iGastoPico = 1000;
            }
            else
            {
                dataManager.iGastoPico = 750;
            }

            dataManager.iproteccionCorrecta = true;
        }

        else if ((((CheckBox)getActivity().findViewById(R.id.checkBox1)).isChecked() == true) && (((CheckBox)getActivity().findViewById(R.id.checkBox2)).isChecked() == false) && (((CheckBox)getActivity().findViewById(R.id.checkBox3)).isChecked() == false) && (((CheckBox)getActivity().findViewById(R.id.checkBox4)).isChecked() == true))
        {
            dataManager.iProteccion = "Mangueras de\n1.5\" y Cañones";

            if (dataManager.Grupo == "Riesgo Ligero")
            {
                dataManager.iGastoPico = 100;
            }
            else if (dataManager.Grupo == "Riesgo Ordinario Grupo 1")
            {
                dataManager.iGastoPico = 600;
            }
            else if (dataManager.Grupo == "Riesgo Extra Grupo 2")
            {
                dataManager.iGastoPico = 850;
            }
            else
            {
                dataManager.iGastoPico = 1000;
            }

            dataManager.iproteccionCorrecta = true;
        }

        else if ((((CheckBox)getActivity().findViewById(R.id.checkBox1)).isChecked() == true) && (((CheckBox)getActivity().findViewById(R.id.checkBox2)).isChecked() == true) && (((CheckBox)getActivity().findViewById(R.id.checkBox3)).isChecked() == true) && (((CheckBox)getActivity().findViewById(R.id.checkBox4)).isChecked() == false))
        {
            dataManager.iProteccion = "Mangueras de\n1.5\" y 2.5\"\ny rociadores";

            if (dataManager.Grupo == "Riesgo Ligero")
            {
                dataManager.iGastoPico = 250;
            }
            else if (dataManager.Grupo == "Riesgo Ordinario Grupo 1")
            {
                dataManager.iGastoPico = 350;
            }
            else if (dataManager.Grupo == "Riesgo Ordinario Grupo 2")
            {
                dataManager.iGastoPico = 750;
            }
            else if (dataManager.Grupo == "Riesgo Extra Grupo 1")
            {
                dataManager.iGastoPico = 750;
            }
            else
            {
                dataManager.iGastoPico = 1000;
            }

            dataManager.iproteccionCorrecta = true;
        }


        else if ((((CheckBox)getActivity().findViewById(R.id.checkBox1)).isChecked() == true) && (((CheckBox)getActivity().findViewById(R.id.checkBox2)).isChecked() == true) && (((CheckBox)getActivity().findViewById(R.id.checkBox3)).isChecked() == true) && (((CheckBox)getActivity().findViewById(R.id.checkBox4)).isChecked() == true))
        {
            dataManager.iProteccion = "Mangueras de\n1.5\" y 2.5\",\nrociadores\ny cañones";

            if (dataManager.Grupo == "Riesgo Ligero")
            {
                dataManager.iGastoPico = 250;
            }
            else if (dataManager.Grupo == "Riesgo Ordinario Grupo 1")
            {
                dataManager.iGastoPico = 700;
            }
            else if (dataManager.Grupo == "Riesgo Ordinario Grupo 2")
            {
                dataManager.iGastoPico = 1100;
            }
            else if (dataManager.Grupo == "Riesgo Extra Grupo 1")
            {
                dataManager.iGastoPico = 1250;
            }
            else
            {
                dataManager.iGastoPico = 1500;
            }

            dataManager.iproteccionCorrecta = true;
        }

        else if ((((CheckBox)getActivity().findViewById(R.id.checkBox1)).isChecked() == false) && (((CheckBox)getActivity().findViewById(R.id.checkBox2)).isChecked() == true) && (((CheckBox)getActivity().findViewById(R.id.checkBox3)).isChecked() == false) && (((CheckBox)getActivity().findViewById(R.id.checkBox4)).isChecked() == false))
        {
            dataManager.iProteccion = "Mangueras de 2.5\"";

            if (dataManager.Grupo == "Riesgo Ligero")
            {
                dataManager.iGastoPico = 100;
            }
            else if (dataManager.Grupo == "Riesgo Ordinario Grupo 1")
            {
                dataManager.iGastoPico = 250;
            }
            else
            {
                dataManager.iGastoPico = 500;
            }

            dataManager.iproteccionCorrecta = true;
        }

        else if ((((CheckBox)getActivity().findViewById(R.id.checkBox1)).isChecked() == false) && (((CheckBox)getActivity().findViewById(R.id.checkBox2)).isChecked() == true) && (((CheckBox)getActivity().findViewById(R.id.checkBox3)).isChecked() == true) && (((CheckBox)getActivity().findViewById(R.id.checkBox4)).isChecked() == false))
        {
            dataManager.iProteccion = "Mangueras de 2.5\"\ny rociadores";

            if (dataManager.Grupo == "Riesgo Ligero")
            {
                dataManager.iGastoPico = 250;
            }
            else if (dataManager.Grupo == "Riesgo Ordinario Grupo 1")
            {
                dataManager.iGastoPico = 350;
            }
            else if (dataManager.Grupo == "Riesgo Ordinario Grupo 2")
            {
                dataManager.iGastoPico = 750;
            }
            else if (dataManager.Grupo == "Riesgo Extra Grupo 1")
            {
                dataManager.iGastoPico = 750;
            }
            else
            {
                dataManager.iGastoPico = 1000;
            }

            dataManager.iproteccionCorrecta = true;
        }

        else if ((((CheckBox)getActivity().findViewById(R.id.checkBox1)).isChecked() == false) && (((CheckBox)getActivity().findViewById(R.id.checkBox2)).isChecked() == true) && (((CheckBox)getActivity().findViewById(R.id.checkBox3)).isChecked() == true) && (((CheckBox)getActivity().findViewById(R.id.checkBox4)).isChecked() == true))
        {
            dataManager.iProteccion = "Mangueras de 2.5\",\nrociadores y cañones";

            if (dataManager.Grupo == "Riesgo Ligero")
            {
                dataManager.iGastoPico = 250;
            }
            else if (dataManager.Grupo == "Riesgo Ordinario Grupo 1")
            {
                dataManager.iGastoPico = 700;
            }
            else if (dataManager.Grupo == "Riesgo Ordinario Grupo 2")
            {
                dataManager.iGastoPico = 1100;
            }
            else if (dataManager.Grupo == "Riesgo Extra Grupo 1")
            {
                dataManager.iGastoPico = 1250;
            }
            else
            {
                dataManager.iGastoPico = 1500;
            }

            dataManager.iproteccionCorrecta = true;
        }

        else if ((((CheckBox)getActivity().findViewById(R.id.checkBox1)).isChecked() == false) && (((CheckBox)getActivity().findViewById(R.id.checkBox2)).isChecked() == false) && (((CheckBox)getActivity().findViewById(R.id.checkBox3)).isChecked() == true) && (((CheckBox)getActivity().findViewById(R.id.checkBox4)).isChecked() == false))
        {
            dataManager.iProteccion = "Rociadores automáticos";

            if (dataManager.Grupo == "Riesgo Ligero")
            {
                dataManager.iGastoPico = 150;
            }
            else if (dataManager.Grupo == "Riesgo Ordinario Grupo 1")
            {
                dataManager.iGastoPico = 100;
            }
            else if (dataManager.Grupo == "Riesgo Ordinario Grupo 2")
            {
                dataManager.iGastoPico = 250;
            }
            else if (dataManager.Grupo == "Riesgo Extra Grupo 1")
            {
                dataManager.iGastoPico = 250;
            }
            else
            {
                dataManager.iGastoPico = 500;
            }

            dataManager.iproteccionCorrecta = true;
        }

        else if ((((CheckBox)getActivity().findViewById(R.id.checkBox1)).isChecked() == false) && (((CheckBox)getActivity().findViewById(R.id.checkBox2)).isChecked() == false) && (((CheckBox)getActivity().findViewById(R.id.checkBox3)).isChecked() == true) && (((CheckBox)getActivity().findViewById(R.id.checkBox4)).isChecked() == true))
        {
            dataManager.iProteccion = "Rociadores\ny cañones";

            if (dataManager.Grupo == "Riesgo Ligero")
            {
                dataManager.iGastoPico = 150;
            }
            else if (dataManager.Grupo == "Riesgo Ordinario Grupo 1")
            {
                dataManager.iGastoPico = 450;
            }
            else if (dataManager.Grupo == "Riesgo Ordinario Grupo 2")
            {
                dataManager.iGastoPico = 600;
            }
            else if (dataManager.Grupo == "Riesgo Extra Grupo 1")
            {
                dataManager.iGastoPico = 750;
            }
            else
            {
                dataManager.iGastoPico = 1000;
            }

            dataManager.iproteccionCorrecta = true;
        }

        else if ((((CheckBox)getActivity().findViewById(R.id.checkBox1)).isChecked() == false) && (((CheckBox)getActivity().findViewById(R.id.checkBox2)).isChecked() == false) && (((CheckBox)getActivity().findViewById(R.id.checkBox3)).isChecked() == false) && (((CheckBox)getActivity().findViewById(R.id.checkBox4)).isChecked() == true))
        {
            dataManager.iProteccion = "Cañones automáticos";

            if (dataManager.Grupo == "Riesgo Ligero")
            {
                dataManager.iGastoPico = 1;
            }
            else if (dataManager.Grupo == "Riesgo Ordinario Grupo 1")
            {
                dataManager.iGastoPico = 350;
            }
            else if (dataManager.Grupo == "Riesgo Ordinario Grupo 2")
            {
                dataManager.iGastoPico = 350;
            }
            else if (dataManager.Grupo == "Riesgo Extra Grupo 1")
            {
                dataManager.iGastoPico = 500;
            }
            else
            {
                dataManager.iGastoPico = 500;
            }

            dataManager.iproteccionCorrecta = true;
        }

        else
        {
            dataManager.iProteccion = "";
            dataManager.iproteccionCorrecta = false;
            dataManager.iGastoPico = 0;
        }

        dataManager.iGastoPico = dataManager.iGastoPico*3.785;

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
            // Do something
        } else{

            getActivity().invalidateOptionsMenu();
        }

    }


    @Override
    public void onPause ()
    {
        super.onPause();

        dataManager = ((TabsMainActivity)getActivity()).getDataManager();

    }


    private void switchFragment ()
    {
        // Create new fragment and transaction
        IncendiosFragment3 newFragment = new IncendiosFragment3();
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
