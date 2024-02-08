package com.ramanora.stona.activities;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ramanora.stona.R;
import com.ramanora.stona.utils.UrlConstants;

public class ActivitySocialMedia extends Activity {


    LinearLayout llTwitter,llFacebook,llyoytube,lllinkedin,lgoogleplus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.social_media_activity);

        llTwitter= (LinearLayout) findViewById(R.id.llTwitter);
        lllinkedin= (LinearLayout) findViewById(R.id.lllinkedin);
        llFacebook= (LinearLayout) findViewById(R.id.llfacebook);
        llyoytube= (LinearLayout) findViewById(R.id.llyoutube);
        lgoogleplus = findViewById(R.id.llgoogleplus);






        llFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                  try {
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(UrlConstants.URL_SOCIAL_FACEBOOK));
                    startActivity(myIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(ActivitySocialMedia.this, "No application can handle this request."
                            + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }


            }
        });


        llTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(UrlConstants.URL_SOCIAL_TWITTER));
                    startActivity(myIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(ActivitySocialMedia.this, "No application can handle this request."
                            + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });


        llyoytube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(UrlConstants.URL_SOCIAL_YOUTUBE));
                    startActivity(myIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(ActivitySocialMedia.this, "No application can handle this request."
                            + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

       /* lgoogleplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(UrlConstants.URL_SOCIAL_Google));
                    startActivity(myIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(ActivitySocialMedia.this, "No application can handle this request."
                            + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });*/


        lllinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(UrlConstants.URL_SOCIAL_LINKEDIN));
                    startActivity(myIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(ActivitySocialMedia.this, "No application can handle this request."
                            + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });








    }
}
