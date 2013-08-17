package com.smartplace.bombasmejorada.tabs.incendios;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import com.smartplace.bombasmejorada.R;
import com.smartplace.bombasmejorada.tabs.TabsMainActivity;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by ROBERTO on 15/06/13.
 */

public class IncendiosFragment1 extends Fragment {

    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    List<String> listItems=new ArrayList<String>();

    //Test
    int test=1;

    //DEFINING STRING ADAPTER WHICH WILL HANDLE DATA OF LISTVIEW
    ArrayAdapter<String> adapter;

    //Interface instance used to pass data on to the holder activity
    onBombasFragment1Change mCallback;

    public interface onBombasFragment1Change
    {
        public void onFragmentChange( TabsMainActivity.Identifiers TabIdentifier,String Param1,int Param2, int Param3);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.tab_incendios_1, container, false);
        return view;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
//        try {
//            mCallback = (onBombasFragment1Change) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnHeadlineSelectedListener");
//        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tab_incendios, menu);
    }
    @Override
    public void onResume ()
    {
        super.onResume();
//
//        RelativeLayout relLayout = (RelativeLayout) getActivity().findViewById(R.id.asilo);
//
//        relLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setPressed(view);
//                mCallback.onFragmentChange(TabsMainActivity.Identifiers.BombasFragment1, "Monofasica 110 V", 1, 1);
//                switchFragment();
//
//            }
//        });
//
//        relLayout = (RelativeLayout) getActivity().findViewById(R.id.auditorio);
//
//        relLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setPressed(view);
//                mCallback.onFragmentChange(TabsMainActivity.Identifiers.BombasFragment1, "Monofasica 220 V", 1, 1);
//                switchFragment();
//
//            }
//        });
//
//        relLayout = (RelativeLayout) getActivity().findViewById(R.id.carcel);
//
//        relLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setPressed(view);
//                mCallback.onFragmentChange(TabsMainActivity.Identifiers.BombasFragment1, "Trifasica 220 V", 1, 1);
//                switchFragment();
//
//            }
//        });
//
//        relLayout = (RelativeLayout) getActivity().findViewById(R.id.club);
//
//        relLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setPressed(view);
//                mCallback.onFragmentChange(TabsMainActivity.Identifiers.BombasFragment1,"Trifasica 440 V", 1, 1);
//                switchFragment();
//
//            }
//        });
//
//        relLayout = (RelativeLayout) getActivity().findViewById(R.id.escuela);
//
//        relLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setPressed(view);
//                mCallback.onFragmentChange(TabsMainActivity.Identifiers.BombasFragment1,"Gasolina", 1, 1);
//                switchFragment();
//
//            }
//        });
//
//        relLayout = (RelativeLayout) getActivity().findViewById(R.id.hospital);
//
//        relLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setPressed(view);
//                mCallback.onFragmentChange(TabsMainActivity.Identifiers.BombasFragment1,"Diesel", 1, 1);
//                switchFragment();
//
//            }
//        });
//


    }


    private void setPressed(View view)
    {
        ViewGroup viewGroup = (ViewGroup) view;
        for (int i = 0; i < viewGroup .getChildCount(); i++) {

            View viewChild = viewGroup .getChildAt(i);
            viewChild.setPressed(true);

        }

    }

    private void switchFragment ()
    {
//        // Create new fragment and transaction
//        BombasFragment2 newFragment = new BombasFragment2();
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//
//        // Replace whatever is in the fragment_container view with this fragment,
//        // and add the transaction to the back stack
//        transaction.replace(android.R.id.tabcontent, newFragment);
//        transaction.addToBackStack(null);
//
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//
//        // Commit the transaction
//        transaction.commit();
        Toast.makeText(getActivity(),"success", Toast.LENGTH_SHORT).show();

    }

}
