package com.smartplace.bombasmejorada.tabs.bombas;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentTransaction;
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
 * Created by ROBERTO on 17/06/13.
 */
public class BombasFragment2 extends Fragment {

    private Button btnBombasSum1;
    private EditText txt_lpm;
    private Button btnBombasRes1;
    private Button btnBombasSum2;
    private EditText txt_psi;
    private Button btnBombasRes2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View v = inflater.inflate(R.layout.tab_bombas_2, container, false);

        btnBombasSum1 = (Button)v.findViewById(R.id.btnBombasSum1);
        txt_lpm = (EditText)v.findViewById(R.id.tvBombas1);
        btnBombasRes1 = (Button)v.findViewById(R.id.btnBombasRes1);
        btnBombasSum2 = (Button)v.findViewById(R.id.btnBombasSum2);
        txt_psi = (EditText)v.findViewById(R.id.tvBombas2);
        btnBombasRes2 = (Button)v.findViewById(R.id.btnBombasRes2);

        setHasOptionsMenu(true);

        return v;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.tab_bombas_2, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_siguiente:

                // Create new fragment and transaction
                BombasFragment3 newFragment = new BombasFragment3();
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

        btnBombasSum1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataManager.lpm = Integer.parseInt(txt_lpm.getText().toString());
                dataManager.lpm=dataManager.lpm+10;
                txt_lpm.setText(String.valueOf(dataManager.lpm));
            }
        });
        btnBombasRes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataManager.lpm = Integer.parseInt(txt_lpm.getText().toString());
                dataManager.lpm=dataManager.lpm-10;
                txt_lpm.setText(String.valueOf(dataManager.lpm));
            }
        });
        btnBombasSum2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataManager.psi = Integer.parseInt(txt_psi.getText().toString());
                dataManager.psi=dataManager.psi+5;
                txt_psi.setText(String.valueOf(dataManager.psi));
            }
        });
        btnBombasRes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataManager.psi = Integer.parseInt(txt_psi.getText().toString());
                dataManager.psi=dataManager.psi-5;
                txt_psi.setText(String.valueOf(dataManager.psi));
            }
        });

        txt_lpm.setText(String.valueOf(dataManager.lpm));
        txt_psi.setText(String.valueOf(dataManager.psi));

    }

    @Override
    public void onPause ()
    {
        super.onPause();

        DataManager dataManager = ((TabsMainActivity)getActivity()).getDataManager();
        dataManager.lpm = Integer.parseInt(txt_lpm.getText().toString());
        dataManager.psi = Integer.parseInt(txt_psi.getText().toString());

    }

}
