package com.ramanora.stona.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ramanora.stona.R;
import com.ramanora.stona.adapter.CountryMainAdapter;
import com.ramanora.stona.bean.AZExhibitorListPojo;
import com.ramanora.stona.database.DatabaseAccess;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CountryMainFragment extends Fragment {

    SearchView editTextsearch;
    RecyclerView recyclerView;
    LinearLayoutManager manager;
    CountryMainAdapter countryExhbitorlistAdapter;

    public static ArrayList<AZExhibitorListPojo> mExhibitorList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.country_exhibitorlist_lay_country_exhibitor, null);

        editTextsearch = (SearchView) view.findViewById(R.id.editTextsearch);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        editTextsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextsearch.setIconified(false);
            }
        });

        editTextsearch.setQueryHint("Search...");

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_exhibitor_country);
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        getDatafromDB();


        editTextsearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (countryExhbitorlistAdapter != null)
                    countryExhbitorlistAdapter.getFilter().filter(newText);
                return true;
            }
        });


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }

    private void getDatafromDB() {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());
        databaseAccess.open();

        mExhibitorList = databaseAccess.getCountrylist();

        countryExhbitorlistAdapter = new CountryMainAdapter(getActivity(), mExhibitorList);
        recyclerView.setAdapter(countryExhbitorlistAdapter);
        countryExhbitorlistAdapter.notifyDataSetChanged();
        databaseAccess.close();

    }


}
