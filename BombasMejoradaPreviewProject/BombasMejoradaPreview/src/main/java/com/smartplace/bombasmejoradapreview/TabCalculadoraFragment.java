package com.smartplace.bombasmejoradapreview;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.smartplace.kankan.wheel.widget.OnWheelChangedListener;
import com.smartplace.kankan.wheel.widget.OnWheelScrollListener;
import com.smartplace.kankan.wheel.widget.WheelView;
import com.smartplace.kankan.wheel.widget.adapters.ArrayWheelAdapter;

public class TabCalculadoraFragment extends Fragment {

    // TODO: Externalize string-array
    String wheelMenu[] = new String[]{
            "gpm a lpm",
            "gpm a lps",
            "gpm a m3/s",
            "lpm a gpm",
            "lpm a lps",
            "lpm a m3/s",
            "lps a gpm",
            "lps a lpm",
            "lps a m3/s",
            "kgf/cm2 a kPa",
            "kgf/cm2 a mca",
            "kgf/cm2 a psi",
            "kPa a kgf/cm2",
            "kPa a mca",
            "kPa a psi",
            "mca a kgf/cm2",
            "mca a kPa",
            "mca a psi",
            "m3/s a gpm",
            "m3/s a lpm",
            "m3/s a lps",
            "psi a kgf/cm2",
            "psi a kPa",
            "psi a mca"};

    // Wheel scrolled flag
    private boolean wheelScrolled = false;

    private TextView conversion_unit1,conversion_unit2;
    private View view;
    private Button btn_Convert;
    private EditText txt_valueToConvert;
    private EditText txt_valueConverted;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        /*Load calculadora fragment*/
        view = inflater.inflate(R.layout.tab_calculadora_fragment, container, false);

        /*Change action bar title and color according to requirement*/
        ActionBar abar = getActivity().getActionBar();
        abar.setTitle(R.string.calculadora_tab_title);
        abar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#683629")));

        /*Delete menu from previous fragment*/
        setHasOptionsMenu(true);

        /*Initialize wheelview with converter strings*/
        initWheel(R.id.p1,wheelMenu);

        /*Get graphical objects in order to interact with them*/
        conversion_unit1 = (TextView) view.findViewById(R.id.conversion_units1);
        conversion_unit2 = (TextView) view.findViewById(R.id.conversion_units2);
        txt_valueToConvert = (EditText) view.findViewById(R.id.conversion_value1);
        txt_valueConverted = (EditText) view.findViewById(R.id.conversion_value2);
        btn_Convert = (Button) view.findViewById(R.id.btn_convert);
        btn_Convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_convert_click();
            }
        }
        );
        /*return the View*/
        return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);

        /*Set fragment Menu*/
        inflater.inflate(R.menu.tab_calculadora, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        /*Act according to Menu Option selected*/
        switch (item.getItemId()) {
            case R.id.btn_clean:
                Toast.makeText(getActivity().getBaseContext(), "Limpiar del tab calculadora ha sido presionado", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    // Wheel scrolled listener
    // Wheel scrolled listener
    OnWheelScrollListener scrolledListener = new OnWheelScrollListener() {
        public void onScrollingStarted(WheelView wheel) {
            wheelScrolled = true;
        }

        public void onScrollingFinished(WheelView wheel) {
            wheelScrolled = false;
            updateStatus();
        }
    };

    // Wheel changed listener
    private OnWheelChangedListener changedListener = new OnWheelChangedListener() {
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            if (!wheelScrolled) {
                // updateStatus();
            }
        }
    };

    private void updateStatus()
    {
            if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="gpm a lpm")
            {
                conversion_unit1.setText("gpm");
                conversion_unit2.setText("lpm");

            }
            else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="gpm a lps")
            {
                conversion_unit1.setText("gpm");
                conversion_unit2.setText("lps");

            }
            else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="gpm a m3/s")
            {
                conversion_unit1.setText("gpm");
                conversion_unit2.setText("m3/s");

            }
            else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="lpm a gpm")
            {
                conversion_unit1.setText("lpm");
                conversion_unit2.setText("gpm");

            }
            else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="lpm a lps")
            {
                conversion_unit1.setText("lpm");
                conversion_unit2.setText("lps");

            }
            else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="lpm a m3/s")
            {
                conversion_unit1.setText("lpm");
                conversion_unit2.setText("m3/s");

            }
            else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="lps a gpm")
            {
                conversion_unit1.setText("lps");
                conversion_unit2.setText("gpm");

            }
            else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="lps a lpm")
            {
                conversion_unit1.setText("lps");
                conversion_unit2.setText("lpm");

            }
            else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="lps a m3/s")
            {
                conversion_unit1.setText("lps");
                conversion_unit2.setText("m3/s");

            }
            else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="kgf/cm2 a kPa")
            {
                conversion_unit1.setText("kgf/cm2");
                conversion_unit2.setText("kPa");

            }
            else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="kgf/cm2 a mca")
            {
                conversion_unit1.setText("kgf/cm2");
                conversion_unit2.setText("mca");

            }
            else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="kgf/cm2 a psi")
            {
                conversion_unit1.setText("kgf/cm2");
                conversion_unit2.setText("psi");

            }
            else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="kPa a kgf/cm2")
            {
                conversion_unit1.setText("kPa");
                conversion_unit2.setText("kgf/cm2");

            }
            else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="kPa a mca")
            {
                conversion_unit1.setText("kPa");
                conversion_unit2.setText("mca");

            }
            else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="kPa a psi")
            {
                conversion_unit1.setText("kPa");
                conversion_unit2.setText("psi");

            }
            else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="mca a kgf/cm2")
            {
                conversion_unit1.setText("mca");
                conversion_unit2.setText("kgf/cm2");

            }
            else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="mca a kPa")
            {
                conversion_unit1.setText("mca");
                conversion_unit2.setText("kPa");

            }
            else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="mca a psi")
            {
                conversion_unit1.setText("mca");
                conversion_unit2.setText("psi");

            }
            else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="m3/s a gpm")
            {
                conversion_unit1.setText("m3/s");
                conversion_unit2.setText("gpm");

            }
            else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="m3/s a lpm")
            {
                conversion_unit1.setText("m3/s");
                conversion_unit2.setText("lpm");

            }
            else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="m3/s a lps")
            {
                conversion_unit1.setText("m3/s");
                conversion_unit2.setText("lps");

            }
            else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="psi a kgf/cm2")
            {
                conversion_unit1.setText("psi");
                conversion_unit2.setText("kgf/cm2");

            }
            else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="psi a kPa")
            {
                conversion_unit1.setText("psi");
                conversion_unit2.setText("kPa");

            }
            else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="psi a mca")
            {
                conversion_unit1.setText("psi");
                conversion_unit2.setText("mca");

            }
    }
    private void initWheel(int id, String wheelMenu[]) {

        ArrayWheelAdapter<String> adapter = new ArrayWheelAdapter<String>(getActivity().getBaseContext(),
                wheelMenu);

        WheelView wheel = getWheel(id);
        wheel.setViewAdapter(adapter);
        wheel.setCurrentItem(0);
        wheel.setVisibleItems(5);

        wheel.addChangingListener(changedListener);
        wheel.addScrollingListener(scrolledListener);

        wheel.setEnabled(true);
    }


    private WheelView getWheel(int id)
    {
        return (WheelView)  view.findViewById(id);
    }


    private void btn_convert_click ()
    {
        try
        {
                if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="gpm a lpm")
                {
                    float temp_value=Float.valueOf(txt_valueToConvert.getText().toString());
                    txt_valueConverted.setText(String.format("%.3f", temp_value*3.785));

                }
                else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="gpm a lps")
                {
                    float temp_value=Float.valueOf(txt_valueToConvert.getText().toString());
                    txt_valueConverted.setText(String.format("%.5f", temp_value*0.06309));

                }
                else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="gpm a m3/s")
                {
                    float temp_value=Float.valueOf(txt_valueToConvert.getText().toString());
                    txt_valueConverted.setText(String.format("%.8f", temp_value*0.00006309));

                }
                else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="lpm a gpm")
                {
                    float temp_value=Float.valueOf(txt_valueToConvert.getText().toString());
                    txt_valueConverted.setText(String.format("%.4f", temp_value*0.2642));

                }
                else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="lpm a lps")
                {
                    float temp_value=Float.valueOf(txt_valueToConvert.getText().toString());
                    txt_valueConverted.setText(String.format("%.5f", temp_value*0.01667));

                }
                else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="lpm a m3/s")
                {
                    float temp_value=Float.valueOf(txt_valueToConvert.getText().toString());
                    txt_valueConverted.setText(String.format("%.8f", temp_value*0.00001667));

                }
                else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="lps a gpm")
                {
                    float temp_value=Float.valueOf(txt_valueToConvert.getText().toString());
                    txt_valueConverted.setText(String.format("%.2f", temp_value*15.85));

                }
                else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="lps a lpm")
                {
                    float temp_value=Float.valueOf(txt_valueToConvert.getText().toString());
                    txt_valueConverted.setText(String.format("%.2f", temp_value*60));

                }
                else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="lps a m3/s")
                {
                    float temp_value=Float.valueOf(txt_valueToConvert.getText().toString());
                    txt_valueConverted.setText(String.format("%.3f", temp_value*0.001));

                }
                else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="kgf/cm2 a kPa")
                {
                    float temp_value=Float.valueOf(txt_valueToConvert.getText().toString());
                    txt_valueConverted.setText(String.format("%.2f", temp_value*98.07));

                }
                else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="kgf/cm2 a mca")
                {
                    float temp_value=Float.valueOf(txt_valueToConvert.getText().toString());
                    txt_valueConverted.setText(String.format("%.2f", temp_value*10));

                }
                else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="kgf/cm2 a psi")
                {
                    float temp_value=Float.valueOf(txt_valueToConvert.getText().toString());
                    txt_valueConverted.setText(String.format("%.2f", temp_value*14.22));

                }
                else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="kPa a kgf/cm2")
                {
                    float temp_value=Float.valueOf(txt_valueToConvert.getText().toString());
                    txt_valueConverted.setText(String.format("%.4f", temp_value*0.0102));

                }
                else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="kPa a mca")
                {
                    float temp_value=Float.valueOf(txt_valueToConvert.getText().toString());
                    txt_valueConverted.setText(String.format("%.3f", temp_value*0.102));

                }
                else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="kPa a psi")
                {
                    float temp_value=Float.valueOf(txt_valueToConvert.getText().toString());
                    txt_valueConverted.setText(String.format("%.3f", temp_value*0.145));

                }
                else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="mca a kgf/cm2")
                {
                    float temp_value=Float.valueOf(txt_valueToConvert.getText().toString());
                    txt_valueConverted.setText(String.format("%.1f", temp_value*0.1));

                }
                else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="mca a kPa")
                {
                    float temp_value=Float.valueOf(txt_valueToConvert.getText().toString());
                    txt_valueConverted.setText(String.format("%.3f", temp_value*9.807));

                }
                else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="mca a psi")
                {
                    float temp_value=Float.valueOf(txt_valueToConvert.getText().toString());
                    txt_valueConverted.setText(String.format("%.3f", temp_value*1.422));

                }
                else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="m3/s a gpm")
                {
                    float temp_value=Float.valueOf(txt_valueToConvert.getText().toString());
                    txt_valueConverted.setText(String.format("%.0f", temp_value*15850));

                }
                else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="m3/s a lpm")
                {
                    float temp_value=Float.valueOf(txt_valueToConvert.getText().toString());
                    txt_valueConverted.setText(String.format("%.0f", temp_value*60000));

                }
                else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="m3/s a lps")
                {
                    float temp_value=Float.valueOf(txt_valueToConvert.getText().toString());
                    txt_valueConverted.setText(String.format("%.0f", temp_value*1000));

                }
                else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="psi a kgf/cm2")
                {
                    float temp_value=Float.valueOf(txt_valueToConvert.getText().toString());
                    txt_valueConverted.setText(String.format("%.5f", temp_value*0.07031));

                }
                else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="psi a kPa")
                {
                    float temp_value=Float.valueOf(txt_valueToConvert.getText().toString());
                    txt_valueConverted.setText(String.format("%.6f", temp_value*6.895));

                }
                else if (wheelMenu[getWheel(R.id.p1).getCurrentItem()]=="psi a mca")
                {
                    float temp_value=Float.valueOf(txt_valueToConvert.getText().toString());
                    txt_valueConverted.setText(String.format("%.4f", temp_value*0.7031));

                }
        }catch(Exception e)
        {
            /*Do nothing*/
            Toast.makeText(getActivity().getBaseContext(), "Favor de escribir valor a convertir", Toast.LENGTH_SHORT).show();

        }
    }

}
