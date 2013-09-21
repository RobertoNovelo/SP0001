package com.smartplace.bombasmejorada.tabs.incendios;
import android.app.ActionBar;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.smartplace.bombasmejorada.R;
import com.smartplace.bombasmejorada.tabs.DataManager;
import com.smartplace.bombasmejorada.tabs.TabsMainActivity;
import com.smartplace.bombasmejorada.tabs.hidros.HidrosFragment1;
import com.smartplace.bombasmejorada.tabs.hidros.HidrosFragment2;
import com.smartplace.bombasmejorada.tabs.hidros.HidrosFragment3;

public class TabIncendiosFragment extends Fragment {

    ViewPager vp;
    private vpAdapter myAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        ActionBar abar = getActivity().getActionBar();
        abar.setTitle(R.string.incendios_bar_title);
        abar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#591819")));
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.tab_incendios_main, container, false);
        vp = (ViewPager)view.findViewById(R.id.pager);
        myAdapter = new vpAdapter();
        vp.setAdapter(myAdapter);
        return view;
    }

    final GestureDetector gestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
        public boolean onDoubleTap(MotionEvent e) {

            DataManager dataManager = ((TabsMainActivity)getActivity()).getDataManager();

            if((getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) &&(dataManager.screenSize == "large" || dataManager.screenSize == "xlarge"))
            {
                // Create new fragment and transaction
                IncendiosFragment1 newFragment = new IncendiosFragment1();
                IncendiosFragment2 newFragment2 = new IncendiosFragment2();
                IncendiosFragment3 newFragment3 = new IncendiosFragment3();
                FragmentTransaction transaction =getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(android.R.id.tabcontent, newFragment,"incendios_fragment_1");
                transaction.add(R.id.myfragment,newFragment3,"incendios_fragment_3");
                transaction.add(R.id.myfragment,newFragment2,"incendios_fragment_2");
                transaction.addToBackStack(null);

                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                // Commit the transaction
                transaction.commit();
            }
            else
            {
                // Create new fragment and transaction
                IncendiosFragment1 newFragment = new IncendiosFragment1();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(android.R.id.tabcontent, newFragment);
                transaction.addToBackStack(null);

                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                // Commit the transaction
                transaction.commit();
            }
                return true;
        }
    });


    @Override
    public void onResume ()
    {
        super.onResume();
        vp.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                return gestureDetector.onTouchEvent(motionEvent);

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tab_incendios, menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_accept:

                DataManager dataManager = ((TabsMainActivity)getActivity()).getDataManager();

                if((getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) &&(dataManager.screenSize == "large" || dataManager.screenSize == "xlarge"))
                {
                    // Create new fragment and transaction
                    IncendiosFragment1 newFragment = new IncendiosFragment1();
                    IncendiosFragment2 newFragment2 = new IncendiosFragment2();
                    IncendiosFragment3 newFragment3 = new IncendiosFragment3();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack
                    transaction.replace(android.R.id.tabcontent, newFragment,"incendios_fragment_1");
                    transaction.add(R.id.myfragment,newFragment3,"incendios_fragment_3");
                    transaction.add(R.id.myfragment,newFragment2,"incendios_fragment_2");
                    transaction.addToBackStack(null);

                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                    // Commit the transaction
                    transaction.commit();
                }
                else
                {
                    // Create new fragment and transaction
                    IncendiosFragment1 newFragment = new IncendiosFragment1();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack
                    transaction.replace(android.R.id.tabcontent, newFragment);
                    transaction.addToBackStack(null);

                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                    // Commit the transaction
                    transaction.commit();
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private class vpAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == ((LinearLayout)o);
        }
        public void finishUpdate (ViewGroup container)
        {

        }
        public void startUpdate(ViewGroup container) {
         /* compiled code */
        }
        public Object instantiateItem(ViewGroup container, int position) {
            LayoutInflater inflater = (LayoutInflater)container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v =null;
            switch (position)            {

                case 0:
                    v= inflater.inflate(R.layout.page_incendios_1,null);
                    break;
                case 1:
                    v= inflater.inflate(R.layout.page_incendios_2,null);
                    break;
                case 2:
                    v= inflater.inflate(R.layout.page_incendios_3,null);
                    break;

            }
            ((ViewPager)container).addView(v,0);
            return v;
        }

        public android.os.Parcelable saveState() {
            return null;
        }
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager)container).removeView((LinearLayout)object);
        }
    }
}