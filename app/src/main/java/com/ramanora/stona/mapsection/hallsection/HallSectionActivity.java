package com.ramanora.stona.mapsection.hallsection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.ramanora.stona.R;

import java.util.ArrayList;

public class HallSectionActivity extends AppCompatActivity {

    private final int[] imageId = {

            R.drawable.hall1,
            R.drawable.hall2,
            R.drawable.hall3,
            R.drawable.hall4,
            R.drawable.hall5,
            R.drawable.hall6
           /* R.drawable.mapupdate,
            R.drawable.newsandupdatesupdate,
            R.drawable.socialmediaupdate,
            R.drawable.visitingcardupdate,
            R.drawable.myprofileupdate,
            R.drawable.virtualmediaupdate*/

    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_section);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvNumbers);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        //ArrayList<PojoHallSection> mArrayList = prepareData();
        ArrayList<PojoHallSection> mArrayList = new ArrayList<>();
       // ArrayList<PojoHallSection>data = new ArrayList<PojoHallSection>();
        for (int i = 0; i < imageId.length; i++) {
            PojoHallSection pojoHallSection=new PojoHallSection();
            pojoHallSection.setmImg(imageId[i]);
            mArrayList.add(pojoHallSection);
        }

        HallSectionAdapter adapter = new HallSectionAdapter(mArrayList, getApplicationContext());
        recyclerView.setAdapter(adapter);


    }
    private ArrayList<PojoHallSection> prepareData(){

        ArrayList<PojoHallSection> mArrayList= new ArrayList<>();
        for(int i=0;i<imageId.length;i++){
            PojoHallSection pojo = new PojoHallSection();
            //pojo.setMtxt(data[i]);
            pojo.setmImg(imageId[i]);
            mArrayList.add(pojo);
        }
        return mArrayList;

    }
    }

