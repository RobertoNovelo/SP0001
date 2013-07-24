
package com.smartplace.bombasmejorada.tabs.bombas;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.smartplace.bombasmejorada.R;


public class TabBombasFragment extends Fragment {

    ViewPager vp;
    private vpAdapter myAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        ActionBar abar = getActivity().getActionBar();
        abar.setTitle(R.string.bombas_centrifugas_title);
        abar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0F4A51")));
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.tab_bombas_fragment, container, false);
        vp = (ViewPager)view.findViewById(R.id.pager);
        myAdapter = new vpAdapter();
        int novelo =1;
        vp.setAdapter(myAdapter);
        return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tab_bombas, menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_accept:
                // Create new fragment and transaction
                BombasListFragment newFragment = new BombasListFragment();
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
            return 4;
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
                    v= inflater.inflate(R.layout.page_bomba_1,null);
                    break;
                case 1:
                    v= inflater.inflate(R.layout.page_bomba_2,null);
                    break;
                case 2:
                    v= inflater.inflate(R.layout.page_bomba_3,null);
                    break;
                case 3:
                    v= inflater.inflate(R.layout.page_bomba_4,null);
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

