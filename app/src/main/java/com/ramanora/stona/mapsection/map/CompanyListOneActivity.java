package com.ramanora.stona.mapsection.map;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.ramanora.stona.R;
import com.ramanora.stona.adapter.AZExhibitorlistAdapter;
import com.ramanora.stona.bean.AZExhibitorListPojo;
import com.ramanora.stona.database.DatabaseAccess;

import java.util.ArrayList;

public class CompanyListOneActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    LinearLayoutManager mLinearLayoutManager;
    private ArrayList<AZExhibitorListPojo> mArrayListCompany;
    CompanylistAdapter companyAdapter;
    RequestQueue mRequestQueue;
    SearchView editTextsearch;
    Button button_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_list);

        //button_next=findViewById(R.id.button_next);
        mRecyclerView= (RecyclerView) findViewById(R.id.recycler_exhibitor_company);
        mLinearLayoutManager=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mArrayListCompany = new ArrayList<>();


        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();


        mArrayListCompany= databaseAccess.getExhibitorlist();

        databaseAccess.close();


        System.out.println("mArrayListCompany country :"+mArrayListCompany.size());




        companyAdapter=new CompanylistAdapter(CompanyListOneActivity.this,mArrayListCompany);
        mRecyclerView.setAdapter(companyAdapter);

        mRequestQueue= Volley.newRequestQueue(this);



        editTextsearch = (SearchView) findViewById(R.id.editTextsearch);
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


    }

}

