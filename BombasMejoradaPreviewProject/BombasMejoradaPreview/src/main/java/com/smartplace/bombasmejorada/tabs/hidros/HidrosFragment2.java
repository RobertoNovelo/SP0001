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
import android.widget.Toast;

import com.smartplace.bombasmejorada.R;
import com.smartplace.bombasmejorada.tabs.DataManager;
import com.smartplace.bombasmejorada.tabs.TabsMainActivity;

/**
 * Created by ROBERTO on 28/06/13.
 */
public class HidrosFragment2 extends Fragment {

    private Button btnHidrosSum1;
    private EditText txt_salidas;
    private Button btnHidrosRes1;
    private Button btnHidrosSum2;
    private EditText txt_dist_vertical;
    private Button btnHidrosRes2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View v = inflater.inflate(R.layout.tab_hidros_2, container, false);

        btnHidrosSum1 = (Button)v.findViewById(R.id.btnHidrosSum1);
        txt_salidas = (EditText)v.findViewById(R.id.tvHidros1);
        btnHidrosRes1 = (Button)v.findViewById(R.id.btnHidrosRes1);
        btnHidrosSum2 = (Button)v.findViewById(R.id.btnHidrosSum2);
        txt_dist_vertical = (EditText)v.findViewById(R.id.tvHidros2);
        btnHidrosRes2 = (Button)v.findViewById(R.id.btnHidrosRes2);
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
                HidrosFragment3 newFragment = new HidrosFragment3();
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

        btnHidrosSum1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataManager.hSalidas = Integer.parseInt(txt_salidas.getText().toString());
                dataManager.hSalidas=dataManager.hSalidas+10;
                txt_salidas.setText(String.valueOf(dataManager.hSalidas));
            }
        });
        btnHidrosRes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataManager.hSalidas = Integer.parseInt(txt_salidas.getText().toString());
                dataManager.hSalidas=dataManager.hSalidas-10;
                txt_salidas.setText(String.valueOf(dataManager.hSalidas));
            }
        });
        btnHidrosSum2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataManager.hDistVertical = Integer.parseInt(txt_dist_vertical.getText().toString());
                dataManager.hDistVertical=dataManager.hDistVertical+5;
                txt_dist_vertical.setText(String.valueOf(dataManager.hDistVertical));
            }
        });
        btnHidrosRes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataManager.hDistVertical = Integer.parseInt(txt_dist_vertical.getText().toString());
                dataManager.hDistVertical=dataManager.hDistVertical-5;
                txt_dist_vertical.setText(String.valueOf(dataManager.hDistVertical));
            }
        });

        txt_salidas.setText(String.valueOf(dataManager.hSalidas));
        txt_dist_vertical.setText(String.valueOf(dataManager.hDistVertical));
    }

    @Override
    public void onPause ()
    {
        super.onPause();

        DataManager dataManager = ((TabsMainActivity)getActivity()).getDataManager();
        dataManager.hDistVertical = Integer.parseInt(txt_dist_vertical.getText().toString());
        dataManager.hSalidas = Integer.parseInt(txt_salidas.getText().toString());
    }


}
