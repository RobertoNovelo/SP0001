package com.smartplace.bombasmejorada.tabs.hidros;

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

import com.smartplace.bombasmejorada.R;
import com.smartplace.bombasmejorada.tabs.DataManager;
import com.smartplace.bombasmejorada.tabs.TabsMainActivity;

/**
 * Created by ROBERTO on 28/06/13.
 */
public class HidrosFragment3 extends Fragment {

    private Button btnHidrosSum3;
    private EditText txt_long_salida;
    private Button btnHidrosRes3;
    private Button btnHidrosSum4;
    private EditText txt_presion_salida;
    private Button btnHidrosRes4;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View v = inflater.inflate(R.layout.tab_hidros_3, container, false);

        btnHidrosSum3 = (Button)v.findViewById(R.id.btnHidrosSum3);
        txt_long_salida = (EditText)v.findViewById(R.id.tvHidros3);
        btnHidrosRes3 = (Button)v.findViewById(R.id.btnHidrosRes3);
        btnHidrosSum4 = (Button)v.findViewById(R.id.btnHidrosSum4);
        txt_presion_salida = (EditText)v.findViewById(R.id.tvHidros4);
        btnHidrosRes4 = (Button)v.findViewById(R.id.btnHidrosRes4);
        setHasOptionsMenu(true);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tab_hidros_2, menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_siguiente:

                // Create new fragment and transaction
                HidrosFragment4 newFragment = new HidrosFragment4();
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

    @Override
    public void onResume ()
    {
        super.onResume();

        final DataManager dataManager = ((TabsMainActivity)getActivity()).getDataManager();

        btnHidrosSum3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataManager.hLongSalida = Integer.parseInt(txt_long_salida.getText().toString());
                dataManager.hLongSalida=dataManager.hLongSalida+5;
                txt_long_salida.setText(String.valueOf(dataManager.hLongSalida));
            }
        });
        btnHidrosRes3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataManager.hLongSalida = Integer.parseInt(txt_long_salida.getText().toString());
                dataManager.hLongSalida=dataManager.hLongSalida-5;
                txt_long_salida.setText(String.valueOf(dataManager.hLongSalida));
            }
        });
        btnHidrosSum4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataManager.hPresionSalida = Integer.parseInt(txt_presion_salida.getText().toString());
                dataManager.hPresionSalida=dataManager.hPresionSalida+15;
                txt_presion_salida.setText(String.valueOf(dataManager.hPresionSalida));
            }
        });
        btnHidrosRes4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataManager.hPresionSalida = Integer.parseInt(txt_presion_salida.getText().toString());
                dataManager.hPresionSalida=dataManager.hPresionSalida-15;
                txt_presion_salida.setText(String.valueOf(dataManager.hPresionSalida));
            }
        });

        txt_long_salida.setText(String.valueOf(dataManager.hLongSalida));
        txt_presion_salida.setText(String.valueOf(dataManager.hPresionSalida));
    }

    @Override
    public void onPause ()
    {
        super.onPause();

        DataManager dataManager = ((TabsMainActivity)getActivity()).getDataManager();
        dataManager.hLongSalida = Integer.parseInt(txt_long_salida.getText().toString());
        dataManager.hPresionSalida = Integer.parseInt(txt_presion_salida.getText().toString());
    }

}
