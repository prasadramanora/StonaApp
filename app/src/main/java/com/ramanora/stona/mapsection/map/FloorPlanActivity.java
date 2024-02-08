package com.ramanora.stona.mapsection.map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ramanora.stona.R;
import com.ramanora.stona.mapsection.hallsection.HallSectionActivity;
import com.ramanora.stona.mapsection.outdoorsection.OutdoorSectionActivity;

public class FloorPlanActivity extends AppCompatActivity {

    Button mReachExhibitor, mHallsection, mOutdoorsection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_plan);

        mReachExhibitor = (Button) findViewById(R.id.reachexhibitor);
        mHallsection = (Button) findViewById(R.id.hallsection);
        mOutdoorsection = (Button) findViewById(R.id.outdoorsection);

        mReachExhibitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FloorPlanActivity.this,CompanyListOneActivity.class);//FloorePlanActivityListMap
                startActivity(intent);
                FloorPlanActivity.this.finish();

            }
        });

        mHallsection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FloorPlanActivity.this,HallSectionActivity.class);//FloorePlanActivityListMap
                startActivity(intent);
            }
        });

        mOutdoorsection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FloorPlanActivity.this,OutdoorSectionActivity.class);//FloorePlanActivityListMap
                startActivity(intent);
            }
        });


    }
}
