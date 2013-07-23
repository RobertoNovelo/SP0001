package com.smartplace.bombasmejoradapreview;
import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

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
        View view = inflater.inflate(R.layout.tab_incendios_fragment, container, false);
        vp = (ViewPager)view.findViewById(R.id.pager);
        myAdapter = new vpAdapter();
        vp.setAdapter(myAdapter);
        return view;
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
                Toast.makeText(getActivity().getBaseContext(),"Aceptar de tab de incendios presionado",Toast.LENGTH_SHORT).show();
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