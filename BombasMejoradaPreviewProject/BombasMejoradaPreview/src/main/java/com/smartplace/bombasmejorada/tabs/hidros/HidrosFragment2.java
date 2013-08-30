package com.smartplace.bombasmejorada.tabs.hidros;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
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
 * Created by ROBERTO on 28/06/13.
 */
public class HidrosFragment2 extends Fragment {

    private Button btnHidrosSum1;
    private EditText txt_salidas;
    private Button btnHidrosRes1;
    private Button btnHidrosSum2;
    private EditText txt_dist_vertical;
    private Button btnHidrosRes2;
    private TextWatcher saltxtwt;
    private TextWatcher distverttxtwt;


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
                txt_salidas.clearFocus();
                dataManager.hSalidas = Integer.parseInt(txt_salidas.getText().toString());
                dataManager.hSalidas=dataManager.hSalidas+10;
                txt_salidas.setText(String.valueOf(dataManager.hSalidas));
            }
        });
        btnHidrosRes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_salidas.clearFocus();
                dataManager.hSalidas = Integer.parseInt(txt_salidas.getText().toString());
                dataManager.hSalidas=dataManager.hSalidas-10;
                txt_salidas.setText(String.valueOf(dataManager.hSalidas));
            }
        });
        btnHidrosSum2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_dist_vertical.clearFocus();
                dataManager.hDistVertical = Integer.parseInt(txt_dist_vertical.getText().toString());
                dataManager.hDistVertical=dataManager.hDistVertical+5;
                txt_dist_vertical.setText(String.valueOf(dataManager.hDistVertical));
            }
        });
        btnHidrosRes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_dist_vertical.clearFocus();
                dataManager.hDistVertical = Integer.parseInt(txt_dist_vertical.getText().toString());
                dataManager.hDistVertical=dataManager.hDistVertical-5;
                txt_dist_vertical.setText(String.valueOf(dataManager.hDistVertical));
            }
        });

        txt_salidas.setText(String.valueOf(dataManager.hSalidas));
        txt_dist_vertical.setText(String.valueOf(dataManager.hDistVertical));

        saltxtwt = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
            @Override
            public void afterTextChanged(Editable editable) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3)
            {
                DataManager dataManager = ((TabsMainActivity)getActivity()).getDataManager();

                txt_salidas.removeTextChangedListener(saltxtwt);

                if (dataManager.hSalidas < 1)
                {
                    dataManager.hSalidas = (1);
                    txt_salidas.setText(String.valueOf(dataManager.hSalidas));
                }
                else if (dataManager.hSalidas > 9999)
                {
                    dataManager.hSalidas = (9999);
                    txt_salidas.setText(String.valueOf(dataManager.hSalidas));
                }
                if ((!charSequence.toString().matches(""))||(!txt_salidas.getText().toString().matches("")))
                {
                    if (Integer.parseInt(charSequence.toString())<1)
                    {
                        if (txt_salidas.hasFocus())
                        {
                            /*Do nothing*/
                        }
                        else
                        {
                            dataManager.hSalidas = (1);
                            txt_salidas.setText(String.valueOf(dataManager.hSalidas));
                        }
                    }
                    else if (Integer.parseInt(charSequence.toString())>9999)
                    {
                        dataManager.hSalidas = (9999);
                        txt_salidas.setText(String.valueOf(dataManager.hSalidas));
                    }
                }

                txt_salidas.addTextChangedListener(saltxtwt);
            }
        };

        distverttxtwt = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}

            @Override
            public void afterTextChanged(Editable editable) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                DataManager dataManager = ((TabsMainActivity)getActivity()).getDataManager();

                txt_dist_vertical.removeTextChangedListener(distverttxtwt);

                if (dataManager.hDistVertical < 1)
                {
                    dataManager.hDistVertical = (1);
                    txt_dist_vertical.setText(String.valueOf(dataManager.hDistVertical));
                }
                else if (dataManager.hDistVertical > 9999)
                {
                    dataManager.hDistVertical = (9999);
                    txt_dist_vertical.setText(String.valueOf(dataManager.hDistVertical));
                }
                if ((!charSequence.toString().matches(""))||(!txt_dist_vertical.getText().toString().matches("")))
                {
                    if (Integer.parseInt(charSequence.toString())<1)
                    {
                        if (txt_dist_vertical.hasFocus())
                        {
                            /*Do nothing*/
                        }
                        else
                        {
                            dataManager.hDistVertical = (1);
                            txt_dist_vertical.setText(String.valueOf(dataManager.hDistVertical));
                        }
                    }
                    else if (Integer.parseInt(charSequence.toString())>9999)
                    {
                        dataManager.psi = (9999);
                        txt_dist_vertical.setText(String.valueOf(dataManager.hDistVertical));
                    }
                }

                txt_dist_vertical.addTextChangedListener(distverttxtwt);
            }
        };


        txt_salidas.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b)
                {
                    if ((txt_salidas.getText().toString().matches(""))||(Integer.parseInt(txt_salidas.getText().toString())<10))
                    {
                        dataManager.hSalidas = (10);
                        txt_salidas.setText(String.valueOf(dataManager.hSalidas));
                    }
                }
            }
        });

        txt_dist_vertical.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b)
                {
                    if ((txt_dist_vertical.getText().toString().matches(""))||(Integer.parseInt(txt_dist_vertical.getText().toString())<5))
                    {
                        dataManager.hDistVertical = (5);
                        txt_dist_vertical.setText(String.valueOf(dataManager.hDistVertical));
                    }
                }
            }
        });

        txt_salidas.addTextChangedListener(saltxtwt);
        txt_dist_vertical.addTextChangedListener(distverttxtwt);


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
