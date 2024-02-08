package com.ramanora.stona.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.widget.VideoView;

import com.ramanora.stona.R;


/**
 * Created by hrushikesh on 22/05/2017.
 */

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
public class ActivitySplash extends Activity {

    private int timeoutMillis = 4000;
    private long startTimeMillis = 0;
    private static final int PERMISSIONS_REQUEST = 1234;
    public static String MyPREFERENCES = "myprefe";
    public int getTimeoutMillis() {
        return timeoutMillis;
    }
    SharedPreferences sharedpreferences;
    public static String str_login_test;
    public static SharedPreferences sh;
    public static SharedPreferences.Editor editor;
    VideoView videoHolder;

    public String[] getRequiredPermissions() {
        String[] permissions = null;
        try {
            permissions = getPackageManager().getPackageInfo(getPackageName(),
                    PackageManager.GET_PERMISSIONS).requestedPermissions;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (permissions == null) {
            return new String[0];
        } else {
            return permissions.clone();
        }
    }

    @TargetApi(23)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);




        sh = getSharedPreferences(MyPREFERENCES, 0);
        editor = sh.edit();

        str_login_test = sh.getString("loginTest1", null);




        new Handler().postDelayed(new Runnable() {

// Using handler with postDelayed called runnable run method

            @Override

            public void run() {

                Intent intent;
                if (str_login_test != null
                        && !str_login_test.toString().trim().equals("")) {

                    intent = new Intent(ActivitySplash.this, ActivityMainHomePage.class);

                } else {

                    intent = new Intent(ActivitySplash.this, ActivityRegister.class);

                }

                startActivity(intent);
                finish();

            }

        }, 5*1000); // wait for 5 seconds
    }








}


































