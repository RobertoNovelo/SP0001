package com.smartplace.bombasmejorada.tabs.bombas;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
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
 * Created by ROBERTO on 17/06/13.
 */
public class BombasFragment2 extends Fragment {

    private Button btnBombasSum1;
    private EditText txt_lpm;
    private Button btnBombasRes1;
    private Button btnBombasSum2;
    private EditText txt_psi;
    private Button btnBombasRes2;
    private TextWatcher lpmtxtwt;
    private TextWatcher psitxtwt;
    private DataManager dataManager;

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
        dataManager = ((TabsMainActivity)getActivity()).getDataManager();
        setHasOptionsMenu(true);

        return v;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        if((getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) &&dataManager.screenSize == "xlarge")
        {
           /*Do nothing*/
        }
        else
        {
            inflater.inflate(R.menu.tab_bombas_2, menu);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if((getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) &&dataManager.screenSize == "large")
        {
            switch (item.getItemId()) {
                case R.id.btn_siguiente:

                    // Create new fragment and transaction
                    BombasFragment3 newFragment = new BombasFragment3();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();

                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack
                    transaction.replace(android.R.id.tabcontent, newFragment,"bombas_fragment_3");
                    transaction.addToBackStack(null);
                    transaction.remove(fragmentManager.findFragmentByTag("bombas_fragment_2"));
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                    // Commit the transaction
                    transaction.commit();

                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }
        else
        {
            switch (item.getItemId()) {
                case R.id.btn_siguiente:

                    // Create new fragment and transaction
                    BombasFragment3 newFragment = new BombasFragment3();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack
                    transaction.replace(android.R.id.tabcontent, newFragment,"bombas_fragment_3");
                    transaction.addToBackStack(null);

                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                    // Commit the transaction
                    transaction.commit();

                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    public void onResume ()
    {
        super.onResume();

        btnBombasSum1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_lpm.clearFocus();
                dataManager.lpm = Integer.parseInt(txt_lpm.getText().toString());
                dataManager.lpm=dataManager.lpm+10;
                txt_lpm.setText(String.valueOf(dataManager.lpm));
            }
        });
        btnBombasRes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_lpm.clearFocus();
                dataManager.lpm = Integer.parseInt(txt_lpm.getText().toString());
                dataManager.lpm=dataManager.lpm-10;
                txt_lpm.setText(String.valueOf(dataManager.lpm));
            }
        });
        btnBombasSum2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_psi.clearFocus();
                dataManager.psi = Integer.parseInt(txt_psi.getText().toString());
                dataManager.psi=dataManager.psi+5;
                txt_psi.setText(String.valueOf(dataManager.psi));
            }
        });
        btnBombasRes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_psi.clearFocus();
                dataManager.psi = Integer.parseInt(txt_psi.getText().toString());
                dataManager.psi=dataManager.psi-5;
                txt_psi.setText(String.valueOf(dataManager.psi));
            }
        });

        txt_lpm.setText(String.valueOf(dataManager.lpm));
        txt_psi.setText(String.valueOf(dataManager.psi));

        lpmtxtwt = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}

            @Override
            public void afterTextChanged(Editable editable) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                DataManager dataManager = ((TabsMainActivity)getActivity()).getDataManager();

                txt_lpm.removeTextChangedListener(lpmtxtwt);

                if (dataManager.lpm < 10)
                {
                    dataManager.lpm = (10);
                    txt_lpm.setText(String.valueOf(dataManager.lpm));
                }
                else if (dataManager.lpm > 5000)
                {
                    dataManager.lpm = (5000);
                    txt_lpm.setText(String.valueOf(dataManager.lpm));
                }
                if ((!charSequence.toString().matches(""))||(!txt_lpm.getText().toString().matches("")))
                {
                    if (Integer.parseInt(charSequence.toString())<10)
                    {
                        if (txt_lpm.hasFocus())
                        {
                            /*Do nothing*/
                        }
                        else
                        {
                            dataManager.lpm = (10);
                            txt_lpm.setText(String.valueOf(dataManager.lpm));
                        }
                    }
                    else if (Integer.parseInt(charSequence.toString())>5000)
                    {
                        dataManager.lpm = (5000);
                        txt_lpm.setText(String.valueOf(dataManager.lpm));
                    }
                }

                txt_lpm.addTextChangedListener(lpmtxtwt);
            }
        };

        psitxtwt = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}

            @Override
            public void afterTextChanged(Editable editable) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                DataManager dataManager = ((TabsMainActivity)getActivity()).getDataManager();

                txt_psi.removeTextChangedListener(psitxtwt);

                if (dataManager.psi < 5)
                {
                    dataManager.psi = (5);
                    txt_psi.setText(String.valueOf(dataManager.psi));
                }
                else if (dataManager.psi > 180)
                {
                    dataManager.psi = (180);
                    txt_psi.setText(String.valueOf(dataManager.psi));
                }
                if ((!charSequence.toString().matches(""))||(!txt_psi.getText().toString().matches("")))
                {
                    if (Integer.parseInt(charSequence.toString())<5)
                    {
                        if (txt_psi.hasFocus())
                        {
                            /*Do nothing*/
                        }
                        else
                        {
                            dataManager.psi = (5);
                            txt_psi.setText(String.valueOf(dataManager.psi));
                        }
                    }
                    else if (Integer.parseInt(charSequence.toString())>180)
                    {
                        dataManager.psi = (180);
                        txt_psi.setText(String.valueOf(dataManager.psi));
                    }
                }

                txt_psi.addTextChangedListener(psitxtwt);
            }
        };

        txt_lpm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b)
                {
                    if ((txt_lpm.getText().toString().matches(""))||(Integer.parseInt(txt_lpm.getText().toString())<10))
                    {
                        dataManager.lpm = (10);
                        txt_lpm.setText(String.valueOf(dataManager.lpm));
                    }
                }
            }
        });

        txt_psi.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b)
                {
                    if ((txt_psi.getText().toString().matches(""))||(Integer.parseInt(txt_psi.getText().toString())<5))
                    {
                        dataManager.psi = (5);
                        txt_psi.setText(String.valueOf(dataManager.psi));
                    }
                }
            }
        });

        txt_lpm.addTextChangedListener(lpmtxtwt);
        txt_psi.addTextChangedListener(psitxtwt);
    }





        @Override
    public void onPause ()
    {
        super.onPause();

        txt_psi.clearFocus();
        txt_lpm.clearFocus();

        DataManager dataManager = ((TabsMainActivity)getActivity()).getDataManager();

        if ((txt_lpm.getText().toString().matches(""))||(Integer.parseInt(txt_lpm.getText().toString())<10))
        {
            dataManager.lpm = (10);
            txt_lpm.setText(String.valueOf(dataManager.lpm));
        }


        dataManager.lpm = Integer.parseInt(txt_lpm.getText().toString());
        dataManager.psi = Integer.parseInt(txt_psi.getText().toString());

    }

}
