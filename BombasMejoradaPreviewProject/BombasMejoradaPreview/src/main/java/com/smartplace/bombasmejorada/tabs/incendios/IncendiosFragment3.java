package com.smartplace.bombasmejorada.tabs.incendios;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.smartplace.bombasmejorada.R;
import com.smartplace.bombasmejorada.tabs.DataManager;
import com.smartplace.bombasmejorada.tabs.TabsMainActivity;

/**
 * Created by ROBERTO on 28/06/13.
 */
public class IncendiosFragment3 extends Fragment {

    private Button btnIncendiosSum1;
    private EditText txt_long_salida;
    private Button btnIncendiosRes1;
    private Button btnIncendiosSum2;
    private EditText txt_desnivel;
    private Button btnIncendiosRes2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View v = inflater.inflate(R.layout.tab_incendios_3, container, false);

        btnIncendiosSum1 = (Button)v.findViewById(R.id.btnIncendiosSum1);
        txt_long_salida = (EditText)v.findViewById(R.id.tvIncendios1);
        btnIncendiosRes1 = (Button)v.findViewById(R.id.btnIncendiosRes1);
        btnIncendiosSum2 = (Button)v.findViewById(R.id.btnIncendiosSum2);
        txt_desnivel = (EditText)v.findViewById(R.id.tvIncendios2);
        btnIncendiosRes2 = (Button)v.findViewById(R.id.btnIncendiosRes2);
        setHasOptionsMenu(true);
        return v;

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tab_incendios_2, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_accept:

                // Create new fragment and transaction
                IncendiosFragment4 newFragment = new IncendiosFragment4();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

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

    public void onResume ()
    {
        super.onResume();
        final DataManager dataManager = ((TabsMainActivity)getActivity()).getDataManager();

        btnIncendiosSum1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataManager.iLongSalida = Integer.parseInt(txt_long_salida.getText().toString());
                dataManager.iLongSalida=dataManager.iLongSalida+5;
                txt_long_salida.setText(String.valueOf(dataManager.iLongSalida));
            }
        });
        btnIncendiosRes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataManager.iLongSalida = Integer.parseInt(txt_long_salida.getText().toString());
                dataManager.iLongSalida=dataManager.iLongSalida-5;
                txt_long_salida.setText(String.valueOf(dataManager.iLongSalida));
            }
        });
        btnIncendiosSum2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataManager.iDesnivel = Integer.parseInt(txt_desnivel.getText().toString());
                dataManager.iDesnivel=dataManager.iDesnivel+5;
                txt_desnivel.setText(String.valueOf(dataManager.iDesnivel));
            }
        });
        btnIncendiosRes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataManager.iDesnivel = Integer.parseInt(txt_desnivel.getText().toString());
                dataManager.iDesnivel=dataManager.iDesnivel-5;
                txt_desnivel.setText(String.valueOf(dataManager.iDesnivel));
            }
        });

        txt_long_salida.setText(String.valueOf(dataManager.iLongSalida));
        txt_desnivel.setText(String.valueOf(dataManager.iDesnivel));
    }

    @Override
    public void onPause ()
    {
        super.onPause();

        DataManager dataManager =   ((TabsMainActivity)getActivity()).getDataManager();
        dataManager.iLongSalida = Integer.parseInt(txt_long_salida.getText().toString());
        dataManager.iDesnivel = Integer.parseInt(txt_desnivel.getText().toString());

    }


}
