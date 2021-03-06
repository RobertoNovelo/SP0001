package com.smartplace.bombasmejorada.tabs.hidros;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
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
    private TextWatcher longSaltxtwt;
    private TextWatcher presSalttxtwt;

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
        DataManager dataManager = ((TabsMainActivity)getActivity()).getDataManager();
        if((getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) &&(dataManager.screenSize == "large"||dataManager.screenSize=="xlarge"))
        {
            switch (item.getItemId()) {
                case R.id.btn_siguiente:

                    // Create new fragment and transaction
                    HidrosFragment4 newFragment = new HidrosFragment4();
                    HidrosFragment5 newFragment2 = new HidrosFragment5();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();

                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack
                    transaction.replace(android.R.id.tabcontent, newFragment,"hidros_fragment_4");
                    transaction.remove(fragmentManager.findFragmentByTag("hidros_fragment_2"));
                    transaction.remove(fragmentManager.findFragmentByTag("hidros_fragment_3"));
                    transaction.add(R.id.myfragment,newFragment2,"hidros_fragment_5");
                    transaction.addToBackStack(null);

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
                    HidrosFragment4 newFragment = new HidrosFragment4();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();

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
    }

    @Override
    public void onResume ()
    {
        super.onResume();

        final DataManager dataManager = ((TabsMainActivity)getActivity()).getDataManager();

        btnHidrosSum3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_long_salida.clearFocus();
                dataManager.hLongSalida = Integer.parseInt(txt_long_salida.getText().toString());
                dataManager.hLongSalida=dataManager.hLongSalida+5;
                txt_long_salida.setText(String.valueOf(dataManager.hLongSalida));
            }
        });
        btnHidrosRes3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_long_salida.clearFocus();
                dataManager.hLongSalida = Integer.parseInt(txt_long_salida.getText().toString());
                dataManager.hLongSalida=dataManager.hLongSalida-5;
                txt_long_salida.setText(String.valueOf(dataManager.hLongSalida));
            }
        });
        btnHidrosSum4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_presion_salida.clearFocus();
                dataManager.hPresionSalida = Integer.parseInt(txt_presion_salida.getText().toString());
                dataManager.hPresionSalida=dataManager.hPresionSalida+1;
                txt_presion_salida.setText(String.valueOf(dataManager.hPresionSalida));
            }
        });
        btnHidrosRes4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_presion_salida.clearFocus();
                dataManager.hPresionSalida = Integer.parseInt(txt_presion_salida.getText().toString());
                dataManager.hPresionSalida=dataManager.hPresionSalida-1;
                txt_presion_salida.setText(String.valueOf(dataManager.hPresionSalida));
            }
        });

        txt_long_salida.setText(String.valueOf(dataManager.hLongSalida));
        txt_presion_salida.setText(String.valueOf(dataManager.hPresionSalida));

        longSaltxtwt = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
            @Override
            public void afterTextChanged(Editable editable) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3)
            {
                DataManager dataManager = ((TabsMainActivity)getActivity()).getDataManager();

                txt_long_salida.removeTextChangedListener(longSaltxtwt);

                if (dataManager.hLongSalida < 1)
                {
                    dataManager.hLongSalida = (1);
                    txt_long_salida.setText(String.valueOf(dataManager.hLongSalida));
                }
                else if (dataManager.hLongSalida > 800)
                {
                    dataManager.hLongSalida = (800);
                    txt_long_salida.setText(String.valueOf(dataManager.hLongSalida));
                }
                if ((!charSequence.toString().matches(""))||(!txt_long_salida.getText().toString().matches("")))
                {
                    if (Integer.parseInt(charSequence.toString())<1)
                    {
                        if (txt_long_salida.hasFocus())
                        {
                            /*Do nothing*/
                        }
                        else
                        {
                            dataManager.hLongSalida = (1);
                            txt_long_salida.setText(String.valueOf(dataManager.hLongSalida));
                        }
                    }
                    else if (Integer.parseInt(charSequence.toString())>800)
                    {
                        dataManager.hLongSalida = (800);
                        txt_long_salida.setText(String.valueOf(dataManager.hLongSalida));
                    }
                }

                txt_long_salida.addTextChangedListener(longSaltxtwt);
            }
        };

        presSalttxtwt = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}

            @Override
            public void afterTextChanged(Editable editable) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                DataManager dataManager = ((TabsMainActivity)getActivity()).getDataManager();

                txt_presion_salida.removeTextChangedListener(presSalttxtwt);

                if (dataManager.hPresionSalida < 15)
                {
                    dataManager.hPresionSalida = (15);
                    txt_presion_salida.setText(String.valueOf(dataManager.hPresionSalida));
                }
                else if (dataManager.hPresionSalida > 30)
                {
                    dataManager.hPresionSalida = (30);
                    txt_presion_salida.setText(String.valueOf(dataManager.hPresionSalida));
                }
                if ((!charSequence.toString().matches(""))||(!txt_presion_salida.getText().toString().matches("")))
                {
                    if (Integer.parseInt(charSequence.toString())<15)
                    {
                        if (txt_presion_salida.hasFocus())
                        {
                            /*Do nothing*/
                        }
                        else
                        {
                            dataManager.hPresionSalida = (15);
                            txt_presion_salida.setText(String.valueOf(dataManager.hPresionSalida));
                        }
                    }
                    else if (Integer.parseInt(charSequence.toString())>30)
                    {
                        dataManager.hPresionSalida = (30);
                        txt_presion_salida.setText(String.valueOf(dataManager.hPresionSalida));
                    }
                }

                txt_presion_salida.addTextChangedListener(presSalttxtwt);
            }
        };


        txt_presion_salida.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b)
                {
                    if ((txt_presion_salida.getText().toString().matches(""))||(Integer.parseInt(txt_presion_salida.getText().toString())<10))
                    {
                        dataManager.hPresionSalida = (1);
                        txt_presion_salida.setText(String.valueOf(dataManager.hPresionSalida));
                    }
                }
            }
        });

        txt_long_salida.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b)
                {
                    if ((txt_long_salida.getText().toString().matches(""))||(Integer.parseInt(txt_long_salida.getText().toString())<5))
                    {
                        dataManager.hLongSalida = (15);
                        txt_long_salida.setText(String.valueOf(dataManager.hLongSalida));
                    }
                }
            }
        });

        txt_long_salida.addTextChangedListener(longSaltxtwt);
        txt_presion_salida.addTextChangedListener(presSalttxtwt);

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
