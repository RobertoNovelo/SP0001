package com.smartplace.bombasmejoradapreview;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class TabsMainActivity extends Activity {

    /* Tab identifiers */


    static String TAB_A = "Bombas";
    static String TAB_B = "Hidros";
    static String TAB_C = "Incendios";
    static String TAB_D = "Convertidor";
    static String TAB_E = "Otros";
    TabHost mTabHost;

    TabBombasFragment tab_bombas;
    TabHidrosFragment tab_hidros;
    TabIncendiosFragment tab_incendios;
    TabCalculadoraFragment tab_calculadora;
    TabOtrosFragment tab_otros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);

        tab_bombas = new TabBombasFragment();
        tab_hidros = new TabHidrosFragment();
        tab_incendios = new TabIncendiosFragment();
        tab_calculadora = new TabCalculadoraFragment();
        tab_otros = new TabOtrosFragment();

        mTabHost = (TabHost)findViewById(android.R.id.tabhost);
        mTabHost.setOnTabChangedListener(listener);
        mTabHost.setup();

        initializeTab();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.tabs_main, menu);
        return true;
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
