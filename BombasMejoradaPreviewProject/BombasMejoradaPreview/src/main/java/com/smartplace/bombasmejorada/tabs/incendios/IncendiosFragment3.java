package com.smartplace.bombasmejorada.tabs.incendios;

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
public class IncendiosFragment3 extends Fragment {

    private Button btnIncendiosSum1;
    private EditText txt_long_salida;
    private Button btnIncendiosRes1;
    private Button btnIncendiosSum2;
    private EditText txt_desnivel;
    private Button btnIncendiosRes2;
    private TextWatcher longSaltxtwt;
    private TextWatcher desniveltxtwt;


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
                txt_long_salida.clearFocus();
                dataManager.iLongSalida = Integer.parseInt(txt_long_salida.getText().toString());
                dataManager.iLongSalida=dataManager.iLongSalida+5;
                txt_long_salida.setText(String.valueOf(dataManager.iLongSalida));
            }
        });
        btnIncendiosRes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_long_salida.clearFocus();
                dataManager.iLongSalida = Integer.parseInt(txt_long_salida.getText().toString());
                dataManager.iLongSalida=dataManager.iLongSalida-5;
                txt_long_salida.setText(String.valueOf(dataManager.iLongSalida));
            }
        });
        btnIncendiosSum2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_desnivel.clearFocus();
                dataManager.iDesnivel = Integer.parseInt(txt_desnivel.getText().toString());
                dataManager.iDesnivel=dataManager.iDesnivel+5;
                txt_desnivel.setText(String.valueOf(dataManager.iDesnivel));
            }
        });
        btnIncendiosRes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_desnivel.clearFocus();
                dataManager.iDesnivel = Integer.parseInt(txt_desnivel.getText().toString());
                dataManager.iDesnivel=dataManager.iDesnivel-5;
                txt_desnivel.setText(String.valueOf(dataManager.iDesnivel));
            }
        });

        txt_long_salida.setText(String.valueOf(dataManager.iLongSalida));
        txt_desnivel.setText(String.valueOf(dataManager.iDesnivel));

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

                if (dataManager.iLongSalida < 1)
                {
                    dataManager.iLongSalida = (1);
                    txt_long_salida.setText(String.valueOf(dataManager.iLongSalida));
                }
                else if (dataManager.iLongSalida > 9999)
                {
                    dataManager.iLongSalida = (9999);
                    txt_long_salida.setText(String.valueOf(dataManager.iLongSalida));
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
                            dataManager.iLongSalida = (1);
                            txt_long_salida.setText(String.valueOf(dataManager.iLongSalida));
                        }
                    }
                    else if (Integer.parseInt(charSequence.toString())>9999)
                    {
                        dataManager.iLongSalida = (9999);
                        txt_long_salida.setText(String.valueOf(dataManager.iLongSalida));
                    }
                }

                txt_long_salida.addTextChangedListener(longSaltxtwt);
            }
        };

        desniveltxtwt = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}

            @Override
            public void afterTextChanged(Editable editable) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                DataManager dataManager = ((TabsMainActivity)getActivity()).getDataManager();

                txt_desnivel.removeTextChangedListener(desniveltxtwt);

                if (dataManager.iDesnivel < 1)
                {
                    dataManager.iDesnivel = (1);
                    txt_desnivel.setText(String.valueOf(dataManager.iDesnivel));
                }
                else if (dataManager.iDesnivel > 500)
                {
                    dataManager.iDesnivel = (500);
                    txt_desnivel.setText(String.valueOf(dataManager.iDesnivel));
                }
                if ((!charSequence.toString().matches(""))||(!txt_desnivel.getText().toString().matches("")))
                {
                    if (Integer.parseInt(charSequence.toString())<1)
                    {
                        if (txt_desnivel.hasFocus())
                        {
                            /*Do nothing*/
                        }
                        else
                        {
                            dataManager.iDesnivel = (1);
                            txt_desnivel.setText(String.valueOf(dataManager.iDesnivel));
                        }
                    }
                    else if (Integer.parseInt(charSequence.toString())>500)
                    {
                        dataManager.iDesnivel = (500);
                        txt_desnivel.setText(String.valueOf(dataManager.iDesnivel));
                    }
                }

                txt_desnivel.addTextChangedListener(desniveltxtwt);
            }
        };


        txt_desnivel.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b)
                {
                    if ((txt_desnivel.getText().toString().matches(""))||(Integer.parseInt(txt_desnivel.getText().toString())<1))
                    {
                        dataManager.iLongSalida = (1);
                        txt_desnivel.setText(String.valueOf(dataManager.hPresionSalida));
                    }
                }
            }
        });

        txt_long_salida.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b)
                {
                    if ((txt_long_salida.getText().toString().matches(""))||(Integer.parseInt(txt_long_salida.getText().toString())<1))
                    {
                        dataManager.iDesnivel = (1);
                        txt_long_salida.setText(String.valueOf(dataManager.hLongSalida));
                    }
                }
            }
        });

        txt_long_salida.addTextChangedListener(longSaltxtwt);
        txt_desnivel.addTextChangedListener(desniveltxtwt);


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
