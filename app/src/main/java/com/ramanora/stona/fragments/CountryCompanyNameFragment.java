package com.ramanora.stona.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.ramanora.stona.R;
import com.ramanora.stona.adapter.CountryCompanyAdapter;
import com.ramanora.stona.bean.AZExhibitorListPojo;
import com.ramanora.stona.database.DatabaseAccess;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CountryCompanyNameFragment extends Fragment {


    RecyclerView mRecyclerView;
    LinearLayoutManager mLinearLayoutManager;
    private ArrayList<AZExhibitorListPojo> mArrayListCompany;
    CountryCompanyAdapter companyAdapter;
    RequestQueue mRequestQueue;
    SearchView editTextsearch;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.country_fragment_company_name,null);

        mRecyclerView= (RecyclerView) view.findViewById(R.id.recycler_exhibitor_company);
        mLinearLayoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mArrayListCompany = new ArrayList<>();

        Bundle bundle=getArguments();

        String country = bundle.getString("key");

        System.out.println(" country name :"+country);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());
        databaseAccess.open();

//hall comp
        mArrayListCompany= databaseAccess.getCountryAlllist(country);

        databaseAccess.close();


        System.out.println("mArrayListCompany country :"+mArrayListCompany.size());


//c
        companyAdapter=new CountryCompanyAdapter(getContext(),mArrayListCompany);
        mRecyclerView.setAdapter(companyAdapter);

        mRequestQueue= Volley.newRequestQueue(getContext());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        editTextsearch = (SearchView) view.findViewById(R.id.editTextsearch);
        editTextsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextsearch.setIconified(false);
            }
        });

        editTextsearch.setQueryHint("Search...");

        editTextsearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText){
                if (companyAdapter != null) companyAdapter.getFilter().filter(newText);
                return true;
            }});



        return view;


    }

}
