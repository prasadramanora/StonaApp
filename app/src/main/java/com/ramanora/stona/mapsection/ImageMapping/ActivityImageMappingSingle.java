package com.ramanora.stona.mapsection.ImageMapping;

import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

import com.ramanora.stona.R;
import com.ramanora.stona.bean.AZExhibitorListPojo;
import com.ramanora.stona.database.DatabaseAccess;

import java.util.ArrayList;

/**
 * Created by amolrokade on 17/11/17.
 */

public class ActivityImageMappingSingle extends AppCompatActivity {


    ArrayList<String> arrayList;
    Button btn_FindLocation;
    EditText edt_MyPlotNo;
    EditText edt_DestinationPlotNo;
    PinView imageView;
    String campanyname;
    String hall;
    String stallno;

    private ArrayList<AZExhibitorListPojo> mArrayListCompany=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleimage_mapping);
        Intent intent = getIntent();
        campanyname = intent.getStringExtra("Companyname");
        hall = intent.getStringExtra("hallname");
        stallno = intent.getStringExtra("stallno");

        Log.d("test1", "onCreate: hall : " + hall);


        imageView = (PinView) findViewById(R.id.imageView);
        imageView.setImage(ImageSource.asset("plastfocusmap.png"));
        imageView.setTag(R.drawable.destination);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_top);

        mArrayListCompany = databaseAccess.getCompanyCordinates(campanyname, hall, stallno);

        Log.d("test", "onCreate: " + mArrayListCompany.size());
        databaseAccess.close();
        //imageView.setDestination(new PointF(500, 500));

        try {
            if (mArrayListCompany.size() > 0) {

                if (mArrayListCompany.get(0).getX().equalsIgnoreCase("") && mArrayListCompany.get(0).getY().equalsIgnoreCase("")) {
                    Log.d("test", "onCreate: null");
                } else {
                    float x = Float.parseFloat(mArrayListCompany.get(0).getX() + "f");
                    float y = Float.parseFloat(mArrayListCompany.get(0).getY() + "f");


                    imageView.setDestination(new PointF(914







                            , 1260





                    ));


                }


            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}

