package com.smartplace.bombasmejorada.tabs.otros;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.smartplace.bombasmejorada.R;
import com.smartplace.bombasmejorada.tabs.bombas.SearchFragment;

import java.util.ArrayList;
import java.util.List;

public class TabOtrosFragment extends Fragment {

    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    List<String> listItems=new ArrayList<String>();

    //DEFINING STRING ADAPTER WHICH WILL HANDLE DATA OF LISTVIEW
    ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        ActionBar abar = getActivity().getActionBar();
        abar.setTitle(R.string.tab_otros_title);
        abar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#195259")));
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.tab_otros_fragment, container, false);
        return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tab_otros, menu);
    }

    @Override
    public void onResume ()
    {
        super.onResume();

        listItems.add("Datos de Contacto");
        listItems.add("Servicio de Garantia al Producto");
        listItems.add("Ayuda");
        listItems.add("Acerca de la App");

        adapter=new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                listItems);

        ListView productList= (ListView) getActivity().findViewById(R.id.list);

        productList.setAdapter(adapter);

        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String item = ((TextView)view).getText().toString();

                if(item == "Datos de Contacto")
                {
                    // Create new fragment and transaction
                    ContactInfoFragment newFragment = new ContactInfoFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack
                    transaction.replace(android.R.id.tabcontent, newFragment);
                    transaction.addToBackStack(null);

                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                    // Commit the transaction
                    transaction.commit();
                }
                else if(item== "Servicio de Garantia al Producto")
                {
                    ContactInfoFragment newFragment = new ContactInfoFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack
                    transaction.replace(android.R.id.tabcontent, newFragment);
                    transaction.addToBackStack(null);

                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                    // Commit the transaction
                    transaction.commit();
                }
                else if(item == "Ayuda")
                {
                    HelpFragment newFragment = new HelpFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack
                    transaction.replace(android.R.id.tabcontent, newFragment);
                    transaction.addToBackStack(null);

                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                    // Commit the transaction
                    transaction.commit();
                }
                else if(item == "Acerca de la App")
                {
                    AboutFragment newFragment = new AboutFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack
                    transaction.replace(android.R.id.tabcontent, newFragment);
                    transaction.addToBackStack(null);

                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                    // Commit the transaction
                    transaction.commit();
                }
                else
                {
                    /*Do nothing*/
                }



            }
        });

    }

    @Override

    public  void onPause ()
    {
        super.onPause();

        listItems.clear();

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_accept:
                Toast.makeText(getActivity().getBaseContext(), "Aceptar de tab de otros presionado", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


