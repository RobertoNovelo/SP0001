package com.smartplace.bombasmejorada.tabs;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.smartplace.assets.AssetsHandler;
import com.smartplace.bombasmejorada.R;
import com.smartplace.bombasmejorada.tabs.bombas.BombasFragment1;
import com.smartplace.bombasmejorada.tabs.bombas.BombasFragment2;
import com.smartplace.bombasmejorada.tabs.bombas.TabBombasFragment;
import com.smartplace.bombasmejorada.tabs.convertidor.TabCalculadoraFragment;
import com.smartplace.bombasmejorada.tabs.hidros.TabHidrosFragment;
import com.smartplace.bombasmejorada.tabs.incendios.TabIncendiosFragment;
import com.smartplace.bombasmejorada.tabs.otros.ServiceFragment1;
import com.smartplace.bombasmejorada.tabs.otros.ServiceFragment2;
import com.smartplace.bombasmejorada.tabs.otros.ServiceFragment3;
import com.smartplace.bombasmejorada.tabs.otros.ServiceFragment5;
import com.smartplace.bombasmejorada.tabs.otros.TabOtrosFragment;

public class TabsMainActivity extends Activity implements
        BombasFragment1.onSaveData,
        BombasFragment2.onSaveData{



    /* Tab identifiers */
    static String TAB_A = "Bombas";
    static String TAB_B = "Hidros";
    static String TAB_C = "Incendios";
    static String TAB_D = "Convertidor";
    static String TAB_E = "Otros";
    TabHost mTabHost;

    TabBombasFragment           tab_bombas;
    TabHidrosFragment           tab_hidros;
    TabIncendiosFragment        tab_incendios;
    TabCalculadoraFragment      tab_calculadora;
    TabOtrosFragment            tab_otros;

    public enum Identifiers
    {
        BombasFragment1,
        BombasFragment2,
        BombasFragment3,
        HidrosFragment1,
        HidrosFragment2,
        HidrosFragment3,
        HidrosFragment4,
        IncendiosFragment1,
        IncendiosFragment2,
        IncendiosFragment3,
        IncendiosFragment4,
        ServicioFragment1,
        ServicioFragment2,
        ServicioFragment3,
        ServicioFragment5
    }

    public DataManager DataManager = new DataManager();

    public void saveData( Identifiers TabIdentifier,DataManager fragmentDataManager)
    {
        switch (TabIdentifier)
        {
            case BombasFragment1:
                DataManager.EnergySource = fragmentDataManager.EnergySource;
                break;
            case BombasFragment2:
                DataManager.lpm = fragmentDataManager.lpm;
                DataManager.psi = fragmentDataManager.psi;
                break;
            case HidrosFragment1:
                DataManager.EnergySource = fragmentDataManager.EnergySource;
                break;
            case HidrosFragment2:
                DataManager.EnergySource = fragmentDataManager.EnergySource;
                break;
            case HidrosFragment3:
                DataManager.EnergySource = fragmentDataManager.EnergySource;
                break;
            case ServicioFragment1:
                DataManager.EquipoModelo = fragmentDataManager.EquipoModelo;
                DataManager.NoDeSerie = fragmentDataManager.NoDeSerie;
                break;
            case ServicioFragment2:
                DataManager.Falla = fragmentDataManager.Falla;
                break;
            case ServicioFragment3:
                DataManager.Domicilio = fragmentDataManager.Domicilio;
                DataManager.Observaciones = fragmentDataManager.Observaciones;
                break;
            case ServicioFragment5:
                DataManager.Nombre = fragmentDataManager.Nombre;
                DataManager.Empresa = fragmentDataManager.Empresa;
                DataManager.Puesto = fragmentDataManager.Puesto;
                DataManager.Telefono = fragmentDataManager.Telefono;
                DataManager.Celular = fragmentDataManager.Celular;
                DataManager.Correo = fragmentDataManager.Correo;
                break;
        }
    }

    public DataManager getDataManager()
    {
        return (DataManager);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tabhost);

        tab_bombas = new TabBombasFragment();
        tab_hidros = new TabHidrosFragment();
        tab_incendios = new TabIncendiosFragment();
        tab_calculadora = new TabCalculadoraFragment();
        tab_otros = new TabOtrosFragment();

        mTabHost = (TabHost)findViewById(android.R.id.tabhost);
        mTabHost.setOnTabChangedListener(listener);
        mTabHost.setup();

        initializeTab();
        AssetsHandler.Operations.CopyAssetsToPhone(getBaseContext(),Environment.getExternalStorageDirectory() + "/BombasMejorada/","PDFs");
    }



    /*
     * Initialize the tabs and set views and identifiers for the tabs
     */
    public void initializeTab() {

        TabHost.TabSpec spec    =   mTabHost.newTabSpec(TAB_A);
        mTabHost.setCurrentTab(-3);
        spec.setContent(new TabHost.TabContentFactory() {
            public View createTabContent(String tag) {
                return findViewById(android.R.id.tabcontent);
            }
        });
        spec.setIndicator(createTabView(TAB_A, R.drawable.tab_bomba_selector));
        mTabHost.addTab(spec);


        spec                    =   mTabHost.newTabSpec(TAB_B);
        spec.setContent(new TabHost.TabContentFactory() {
            public View createTabContent(String tag) {
                return findViewById(android.R.id.tabcontent);
            }
        });
        spec.setIndicator(createTabView(TAB_B, R.drawable.tab_hidros_selector));
        mTabHost.addTab(spec);


        spec                    =   mTabHost.newTabSpec(TAB_C);
        spec.setContent(new TabHost.TabContentFactory() {
            public View createTabContent(String tag) {
                return findViewById(android.R.id.tabcontent);
            }
        });
        spec.setIndicator(createTabView(TAB_C, R.drawable.tab_incendios_selector));
        mTabHost.addTab(spec);

        spec                    =   mTabHost.newTabSpec(TAB_D);
        spec.setContent(new TabHost.TabContentFactory() {
            public View createTabContent(String tag) {
                return findViewById(android.R.id.tabcontent);
            }
        });
        spec.setIndicator(createTabView(TAB_D,R.drawable.tab_convertidor_selector));
        mTabHost.addTab(spec);
        spec                    =   mTabHost.newTabSpec(TAB_E);
        spec.setContent(new TabHost.TabContentFactory() {
            public View createTabContent(String tag) {
                return findViewById(android.R.id.tabcontent);
            }
        });
        spec.setIndicator(createTabView(TAB_E, R.drawable.tab_otros_selector));
        mTabHost.addTab(spec);
    }

    /*
     * TabChangeListener for changing the tab when one of the tabs is pressed
     */
    TabHost.OnTabChangeListener listener    =   new TabHost.OnTabChangeListener() {
        public void onTabChanged(String tabId) {
            /*Set current tab..*/

            if(tabId.equals(TAB_A)){
                pushFragments(tabId, tab_bombas);
            }else if(tabId.equals(TAB_B)){
                pushFragments(tabId, tab_hidros);
            }else if(tabId.equals(TAB_C)){
                pushFragments(tabId, tab_incendios);
            }else if(tabId.equals(TAB_D)){
                pushFragments(tabId, tab_calculadora);
            }else if(tabId.equals(TAB_E)){
                pushFragments(tabId, tab_otros);
            }
        }
    };

    /*
     * adds the fragment to the FrameLayout
     */
    public void pushFragments(String tag, Fragment fragment){

        FragmentManager manager         =   getFragmentManager();
        manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction ft            =   manager.beginTransaction();

        ft.replace(android.R.id.tabcontent, fragment);
        ft.commit();
    }

    /*
     * returns the tab view i.e. the tab icon and text
     */
    private View createTabView(final String text, final int id) {
        View view = LayoutInflater.from(this).inflate(R.layout.tabs_icon, null);
        ImageView imageView =   (ImageView) view.findViewById(R.id.tab_icon);
        imageView.setImageDrawable(getResources().getDrawable(id));
        ((TextView) view.findViewById(R.id.tab_text)).setText(text);
        return view;
    }
}
