package com.ramanora.stona.mapsection.map;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ramanora.stona.R;


/**
 * Created by admin on 10/8/2017.
 */

public class ActivityMap extends AppCompatActivity {

    ImageView mimggoogleMap, mimgOla, mimgUber;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity_map);

     //   mimgOla = (ImageView) findViewById(R.id.imgola);
     //   mimgUber = (ImageView) findViewById(R.id.imguber);
        mimggoogleMap = (ImageView) findViewById(R.id.imggooglemap);
        linearLayout= (LinearLayout) findViewById(R.id.LLreachexhibitors);


      /*  mimgOla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.olacabs.customer");
                if (launchIntent != null) {
                    startActivity(launchIntent);//null pointer check in case package name was not found
                } else {
                    Uri uri = Uri.parse("market://details?id=com.olacabs.customer");
                    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);

                    goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                            Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                            Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    try {
                        startActivity(goToMarket);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://play.google.com/store/apps/details?id=com.olacabs.customer")));
                    }
                }
            }
        });


        mimgUber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.ubercab");
                if (launchIntent != null) {
                    startActivity(launchIntent);//null pointer check in case package name was not found
                } else {
                    Uri uri = Uri.parse("uber://?action=setPickup&pickup=my_location");
                    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);

                    goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                            Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                            Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    try {
                        startActivity(goToMarket);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://play.google.com/store/apps/details?id=com.ubercab")));
                    }
                }
            }
        });
*/
        mimggoogleMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                float lat = 13.0622611f;
                float lng = 77.47408960000007f;
                String strUri = "http://maps.google.com/maps?q=loc:" + lat + "," + lng + " (" + "10th Mile, Tumkur Road, Madavara Post, Dasanapura Hobli, Bengaluru, Karnataka 562123" + ")";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(strUri));

                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");

                startActivity(intent);

            /*    String maplLabel = "Raghukul Society, Plot No.13, Opp Girija Shankar Vihar, Raghukul Society, Ganesh Nagar, Karve Nagar, Pune, Maharashtra 411052" ;
                final Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("geo:0,0?q="+lat+","+lng+"&z=16(" + maplLabel + ")"));
                startActivity(intent);*/


            }
        });


        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityMap.this, FloorPlanActivity.class);
                startActivity(intent);


               /* Intent intent=new Intent(ActivityMap.this,CompanyListOneActivity.class);//FloorePlanActivityListMap
                startActivity(intent);
                ActivityMap.this.finish();*/
            }
        });
    }
}