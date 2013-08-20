package com.smartplace.bombasmejorada.tabs.hidros;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
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

import com.smartplace.bombasmejorada.R;
import com.smartplace.bombasmejorada.tabs.bombas.BombasFragment1;

public class TabHidrosFragment extends Fragment {
    ViewPager vp;
    private vpAdapter myAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        ActionBar abar = getActivity().getActionBar();
        abar.setTitle(R.string.hidros_bar_title);
        abar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#141443")));
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.tab_hidros_main, container, false);
        vp = (ViewPager)view.findViewById(R.id.pager);
        myAdapter = new vpAdapter();
        vp.setAdapter(myAdapter);
        return view;
    }

    final GestureDetector gestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
        public boolean onDoubleTap(MotionEvent e) {

            // Create new fragment and transaction
            HidrosFragment1 newFragment = new HidrosFragment1();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            transaction.replace(android.R.id.tabcontent, newFragment);
            transaction.addToBackStack(null);

            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

            // Commit the transaction
            transaction.commit();

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
        inflater.inflate(R.menu.tab_hidros, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_accept:
                // Create new fragment and transaction
                HidrosFragment1 newFragment = new HidrosFragment1();
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
    private class vpAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return 7;
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
                    v= inflater.inflate(R.layout.page_hidros_1,null);
                    break;
                case 1:
                    v= inflater.inflate(R.layout.page_hidros_2,null);
                    break;
                case 2:
                    v= inflater.inflate(R.layout.page_hidros_3,null);
                    break;
                case 3:
                    v= inflater.inflate(R.layout.page_hidros_4,null);
                    break;
                case 4:
                    v= inflater.inflate(R.layout.page_hidros_5,null);
                    break;
                case 5:
                    v= inflater.inflate(R.layout.page_hidros_6,null);
                    break;
                case 6:
                    v= inflater.inflate(R.layout.page_hidros_7,null);
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