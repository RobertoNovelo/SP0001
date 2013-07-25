package com.smartplace.bombasmejorada.tabs.bombas;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.smartplace.bombasmejorada.R;
import com.smartplace.bombasmejorada.tabs.TabsMainActivity;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by ROBERTO on 15/06/13.
 */
public class BombasFragment1 extends Fragment {

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

        View view = inflater.inflate(R.layout.tab_bombas_1, container, false);
        return view;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (onBombasFragment1Change) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tab_bombas_2, menu);
    }

    @Override
    public void onResume ()
    {
        super.onResume();

        listItems.add("Monofasica 110 V");
        listItems.add("Monofasica 220 V");
        listItems.add("Trifasica 220 V");
        listItems.add("Trifasica 440 V");
        listItems.add("Gasolina");
        listItems.add("Diesel");

        adapter=new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                listItems);

        ListView productList= (ListView) getActivity().findViewById(R.id.list);

        productList.setAdapter(adapter);

        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String item = ((TextView)view).getText().toString();

//                Toast.makeText(getActivity(),item,Toast.LENGTH_LONG).show();

                mCallback.onFragmentChange(TabsMainActivity.Identifiers.BombasFragment1, (String) ((TextView) view).getText(), 1, 1);

                // Create new fragment and transaction
                BombasFragment2 newFragment = new BombasFragment2();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(android.R.id.tabcontent, newFragment);
                transaction.addToBackStack(null);

                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                // Commit the transaction
                transaction.commit();

            }
        });

    }

    @Override

    public  void onPause ()
    {
        super.onPause();

        listItems.clear();

    }

   /* @Override

    public void onClick(View view)
    {

        super.onClick(view);

        String item = ((TextView)view).getText().toString();

        Toast.makeText(getActivity(),item,Toast.LENGTH_LONG).show();

    }*/


}
