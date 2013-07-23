package com.smartplace.bombasmejoradapreview;

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



import java.util.ArrayList;
import java.util.List;



/**
 * Created by ROBERTO on 15/06/13.
 */
public class BombasListFragment extends Fragment {

    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    List<String> listItems=new ArrayList<String>();

    //DEFINING STRING ADAPTER WHICH WILL HANDLE DATA OF LISTVIEW
    ArrayAdapter<String> adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.list_fragment_layout, container, false);
        return view;

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

                Toast.makeText(getActivity(),item,Toast.LENGTH_LONG).show();

                // Create new fragment and transaction
                SearchFragment newFragment = new SearchFragment();
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
