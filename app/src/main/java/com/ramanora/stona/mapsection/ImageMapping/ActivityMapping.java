package com.ramanora.stona.mapsection.ImageMapping;

import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ramanora.stona.R;
import com.ramanora.stona.bean.AZExhibitorListPojo;
import com.ramanora.stona.database.DatabaseAccess;
import com.ramanora.stona.mapsection.map.CompanyListTwoActivity;

import java.util.ArrayList;

public class ActivityMapping extends AppCompatActivity {


    ArrayList<String> arrayList;
    Button button_next;

    PinView imageView;
    String campanyname,hall,stallno;
    private ArrayList<AZExhibitorListPojo> mArrayListCompany;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapping);
        Intent intent = getIntent();
        campanyname = intent.getStringExtra("companyname");
        hall= intent.getStringExtra("hall");

        stallno= intent.getStringExtra("stallno");
        Log.d("test", "onCreate: stallno : "+stallno);
        Log.d("test", "onCreate: campanyname : "+campanyname);
        Log.d("test", "onCreate: hall : "+hall);

        button_next= (Button) findViewById(R.id.button_next);
        imageView = (PinView) findViewById(R.id.imageView);
        imageView.setImage(ImageSource.asset("stonamap2020.png"));
        imageView.setTag(R.drawable.destination);


        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();


        mArrayListCompany = databaseAccess.getCompanyCordinates(campanyname,hall,stallno);

        Log.d("test", "onCreate: "+mArrayListCompany.size());
        databaseAccess.close();

        if (mArrayListCompany.size() > 0) {

            if(mArrayListCompany.get(0).getX().equalsIgnoreCase("")  && mArrayListCompany.get(0).getY().equalsIgnoreCase("")){
                Log.d("test", "onCreate: null");
            }else{
                float x = Float.parseFloat(mArrayListCompany.get(0).getX()+"f");
                float y = Float.parseFloat(mArrayListCompany.get(0).getY()+"f");
                imageView.setDestination(new PointF(x, y));
            }


        } else {

        }

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ActivityMapping.this, CompanyListTwoActivity.class);
                intent.putExtra("companyname", campanyname);
                intent.putExtra("hall", hall);
                intent.putExtra("stallno",stallno);
                startActivity(intent);
               // ActivityMapping.this.finish();
            }
        });
    }
}

