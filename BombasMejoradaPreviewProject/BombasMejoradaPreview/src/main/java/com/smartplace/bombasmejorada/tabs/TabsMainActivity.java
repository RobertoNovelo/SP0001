package com.smartplace.bombasmejorada.tabs;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.smartplace.assets.AssetsHandler;
import com.smartplace.bombasmejorada.R;
import com.smartplace.bombasmejorada.tabs.bombas.TabBombasFragment;
import com.smartplace.bombasmejorada.tabs.convertidor.TabCalculadoraFragment;
import com.smartplace.bombasmejorada.tabs.hidros.TabHidrosFragment;
import com.smartplace.bombasmejorada.tabs.incendios.TabIncendiosFragment;
import com.smartplace.bombasmejorada.tabs.otros.TabOtrosFragment;

import static com.smartplace.assets.AssetsHandler.Operations.fileExistsInSD;

public class TabsMainActivity extends Activity{



    /* Tab identifiers */
    static String TAB_A = "Bombas";
    static String TAB_B = "Hidros";
    static String TAB_C = "Incendio";
    static String TAB_D = "Convertidor";
    static String TAB_E = "Otros";
    TabHost mTabHost;

    TabBombasFragment           tab_bombas;
    TabHidrosFragment           tab_hidros;
    TabIncendiosFragment        tab_incendios;
    TabCalculadoraFragment      tab_calculadora;
    TabOtrosFragment            tab_otros;


    public DataManager dataManager = new DataManager();


    public DataManager getDataManager(){
        return (dataManager);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tabhost);
        if(savedInstanceState == null) {
        /* First launch, add fragments */
            tab_bombas = new TabBombasFragment();
            tab_hidros = new TabHidrosFragment();
            tab_incendios = new TabIncendiosFragment();
            tab_calculadora = new TabCalculadoraFragment();
            tab_otros = new TabOtrosFragment();
            mTabHost = (TabHost)findViewById(android.R.id.tabhost);
            mTabHost.setOnTabChangedListener(listener);
            mTabHost.setup();
            dataManager.screenSize = getScreenSize();
            initializeTab(0);

        } else {
        /*
           Activity restored, don't add new fragments or in your case,
           don't make the first tab selected.
        */
            tab_bombas = new TabBombasFragment();
            tab_hidros = new TabHidrosFragment();
            tab_incendios = new TabIncendiosFragment();
            tab_calculadora = new TabCalculadoraFragment();
            tab_otros = new TabOtrosFragment();
            mTabHost = (TabHost)findViewById(android.R.id.tabhost);
            mTabHost.setOnTabChangedListener(listener);
            mTabHost.setup();
            int currentTab = savedInstanceState.getInt("CurrentTab");
            dataManager = savedInstanceState.getParcelable("DataManager");
//            Toast.makeText(getBaseContext(),"Current Tab:" + String.valueOf(currentTab),Toast.LENGTH_LONG).show();
            dataManager.screenSize = getScreenSize();
            initializeTab(currentTab);
        }




    }

    @Override
    public void onResume ()
    {
        super.onResume();
        if (!fileExistsInSD("Modelo1P.pdf")) {
            AssetsHandler.Operations.CopyAssetsToPhone(getBaseContext(),Environment.getExternalStorageDirectory() + "/Android/data/com.smartplace.bombasmejorada/","PDFs");
        }
    }




    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        //savedInstanceState.putBoolean("MyBoolean", true);
        //savedInstanceState.putDouble("myDouble", 1.9);
        savedInstanceState.putInt("CurrentTab", mTabHost.getCurrentTab());
       savedInstanceState.putParcelable("DataManager",dataManager);

        // etc.
    }
    public void initializeTab(int i) {
        /*
         * Initialize the tabs and set views and identifiers for the tabs
         */
        TabHost.TabSpec spec    =   mTabHost.newTabSpec(TAB_A);
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
        mTabHost.setCurrentTab(i);
    }
    public void pushFragments(String tag, Fragment fragment){
        /*
         * adds the fragment to the FrameLayout
         */
        FragmentManager manager         =   getFragmentManager();
        manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction ft            =   manager.beginTransaction();

        ft.replace(android.R.id.tabcontent, fragment);
        ft.commit();
    }
    private View createTabView(final String text, final int id) {
        /*
         * returns the tab view i.e. the tab icon and text
         */
        View view = LayoutInflater.from(this).inflate(R.layout.tabs_icon, null);
        ImageView imageView =   (ImageView) view.findViewById(R.id.tab_icon);
        imageView.setImageDrawable(getResources().getDrawable(id));
        ((TextView) view.findViewById(R.id.tab_text)).setText(text);
        return view;
    }
    private String getScreenSize(){

        String screenSize;
        //Determine screen size
        if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE)
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
            screenSize = "xlarge";
//            Toast.makeText(this, "X-Large screen", Toast.LENGTH_LONG).show();
        }
        else if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE)
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
            screenSize = "large";
//            Toast.makeText(this, "Large screen", Toast.LENGTH_LONG).show();

        }
        else if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL)
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            screenSize = "normal";
//            Toast.makeText(this, "Normal sized screen" , Toast.LENGTH_LONG).show();
        }
        else if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL)
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            screenSize = "small";
//            Toast.makeText(this, "Small sized screen" , Toast.LENGTH_LONG).show();
        }
        else
        {
            screenSize = "not defined";
//            Toast.makeText(this, "Screen size is neither x-large, large, normal or small" , Toast.LENGTH_LONG).show();
        }
        return screenSize;
    }

    TabHost.OnTabChangeListener listener    =   new TabHost.OnTabChangeListener() {
        public void onTabChanged(String tabId) {
            /*Set current tab..*/
            /*
             * TabChangeListener for changing the tab when one of the tabs is pressed
             */
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



}
