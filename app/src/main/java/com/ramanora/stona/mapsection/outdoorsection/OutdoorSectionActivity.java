package com.ramanora.stona.mapsection.outdoorsection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ramanora.stona.R;

import java.util.ArrayList;

public class OutdoorSectionActivity extends AppCompatActivity {

    private final int[] imageId = {

            R.drawable.hall6


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outdoor_section);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvNumbers);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<PojoOutdoorSection> mArrayList = prepareData();
        OutdoorSectionAdapter adapter = new OutdoorSectionAdapter(mArrayList, getApplicationContext());
        recyclerView.setAdapter(adapter);


    }
    private ArrayList<PojoOutdoorSection> prepareData(){

        ArrayList<PojoOutdoorSection> mArrayList= new ArrayList<>();
        for(int i=0;i<imageId.length;i++){
            PojoOutdoorSection pojo = new PojoOutdoorSection();
            //pojo.setMtxt(data[i]);
            pojo.setmImg(imageId[i]);
            mArrayList.add(pojo);
        }
        return mArrayList;

    }
    }

