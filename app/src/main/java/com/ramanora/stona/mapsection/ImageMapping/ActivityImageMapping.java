package com.ramanora.stona.mapsection.ImageMapping;

import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.ramanora.stona.R;
import com.ramanora.stona.bean.AZExhibitorListPojo;
import com.ramanora.stona.database.DatabaseAccess;

import java.util.ArrayList;

public class ActivityImageMapping extends AppCompatActivity {

    private ArrayList<AZExhibitorListPojo> mArrayListCompany1;
    private ArrayList<AZExhibitorListPojo> mArrayListCompany2;
    ArrayList<String> arrayList;
    Button btn_FindLocation;
    EditText edt_MyPlotNo;
    EditText edt_DestinationPlotNo;
    PinView imageView;
    String companyname1, companyname2, hall1, hall2, stallno1, stallno2;
    // TextView txt_CompanyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extension_pin_fragment);

        //  btn_FindLocation=(Button) findViewById(R.id.btn_FindLocation);
        //  edt_MyPlotNo=(EditText) findViewById(R.id.edt_MyPlotNo);
        //  edt_DestinationPlotNo=(EditText) findViewById(R.id.edt_DestinationPlotNo);
/*        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { ((ExtensionActivity) ExtensionPinFragment.this.getActivity()).next(); }
        });*/

        Intent intent = getIntent();
        companyname1 = intent.getStringExtra("companyname1");
        companyname2 = intent.getStringExtra("companyname2");
        hall1 = intent.getStringExtra("hall1");
        hall2 = intent.getStringExtra("hall2");
        stallno1 = intent.getStringExtra("stallno1");

        stallno2 = intent.getStringExtra("stallno2");

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();


        mArrayListCompany1 = databaseAccess.getCompanyCordinates(companyname1, hall1,stallno1);
        mArrayListCompany2 = databaseAccess.getCompanyCordinates(companyname2, hall2,stallno2);

        databaseAccess.close();


        imageView = (PinView) findViewById(R.id.imageView);

        imageView.setImage(ImageSource.asset("stonamap2020.png"));
        imageView.setTag(R.drawable.pushpin_blue);
        imageView.setTag(R.drawable.destination);
        //bitmpa = BitmapFactory.decodeResource(this.getResources(), R.drawable.destination);

        //imageView.setDestination(new PointF(500, 500));

        if (mArrayListCompany1.size() > 0) {
            if (mArrayListCompany1.get(0).getX().equalsIgnoreCase("") && mArrayListCompany1.get(0).getY().equalsIgnoreCase("")) {
                Log.d("test", "onCreate: null");
            } else {
                float x = Float.parseFloat(mArrayListCompany1.get(0).getX() + "f");
                float y = Float.parseFloat(mArrayListCompany1.get(0).getY() + "f");
                String FromCompanyName = mArrayListCompany1.get(0).getY();
                String ToCompanyName = mArrayListCompany1.get(0).getY();
                imageView.setDestination(new PointF(x, y));

                //2960,1800


                // imageView.setPin(new PointF(x, y));

            }
        } else {

        }
        if (mArrayListCompany2.size() > 0) {
            if (mArrayListCompany2.get(0).getX().equalsIgnoreCase("") && mArrayListCompany2.get(0).getY().equalsIgnoreCase("")) {
                Log.d("test", "onCreate: null");
            } else {
                float x = Float.parseFloat(mArrayListCompany2.get(0).getX() + "f");
                float y = Float.parseFloat(mArrayListCompany2.get(0).getY() + "f");
                imageView.setPin(new PointF(x, y));
            }
        } else {

        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       /* Intent intent=new Intent(ActivityImageMapping.this, ActivityMainHomePage.class);
        startActivity(intent);
        finish();*/
    }
}
