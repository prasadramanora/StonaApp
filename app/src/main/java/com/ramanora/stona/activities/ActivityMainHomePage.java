package com.ramanora.stona.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.messaging.FirebaseMessaging;
import com.ramanora.stona.R;
import com.ramanora.stona.adapter.HomePageAdapter;
import com.ramanora.stona.bean.Item;
import com.ramanora.stona.bean.PojoHomePojo;
import com.ramanora.stona.database.DataBaseHandler;
import com.ramanora.stona.services.Config1;
import com.ramanora.stona.services.NotificationUtils;
import com.ramanora.stona.tiker.CircleIndicator;
import com.ramanora.stona.tiker.ImagePagerAdapter;
import com.ramanora.stona.tiker.PagerScheduleProxy;
import com.ramanora.stona.utils.AppStatus;
import com.ramanora.stona.utils.Config;
import com.ramanora.stona.utils.UrlConstants;
import com.ramanora.stona.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 9/12/2017.
 */

public class ActivityMainHomePage extends AppCompatActivity {
    private final int PERMISSION_REQUEST_CODE = 1;
    PagerScheduleProxy pagerScheduleProxy;
    static int mNotifCount = 0;


    public static String str_login_test;
    String firebasetoken, isUploaded;
    public static String MyPREFERENCES = "myprefe";
    public static SharedPreferences sh;
    public static SharedPreferences.Editor editor;


    private BroadcastReceiver mRegistrationBroadcastReceiver;
    DataBaseHandler dataBaseHandler;
    String android_id, fcmtoken;
    private RequestQueue mRequestQueue;

/*
    private final int[] imageId = {
            R.drawable.stonafigsi,
            R.drawable.exhibitorliststona,
            R.drawable.seminars,
            R.drawable.newsstona,
            R.drawable.mapstona,
            // R.drawable.b2b, nnhhhhhhhhhhhhhhhhhhhhhhhhhhhh   hhhhh  hh  hhhhhhh h j
            R.drawable.socialmediastona,
            R.drawable.visitingcardstona,
            R.drawable.myprofilestona,
            R.drawable.feedbackstona
            // R.drawable.myprofileupdate,
            //R.drawable.fairfacilities,
            //  R.drawable.feedbackstona,
    };
*/


    private final int[] imageId = {
            R.drawable.stonafigsi,
            R.drawable.exhibitorliststona,
            R.drawable.seminars,
            R.drawable.newsstona,
            R.drawable.mapstona,
            // R.drawable.b2b, nnhhhhhhhhhhhhhhhhhhhhhhhhhhhh   hhhhh  hh  hhhhhhh h j
            R.drawable.socialmediastona,
            R.drawable.visitingcardstona,
            R.drawable.myprofilestona,
            R.drawable.feedbackstona,
            R.drawable.logout
            // R.drawable.myprofileupdate,
            //R.drawable.fairfacilities,
            //  R.drawable.feedbackstona,
    };


    RecyclerView recyclerView;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    String message="";
    public static String emailid;
    String MessageBody="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticker_activity);


        dataBaseHandler = new DataBaseHandler(getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.rvNumbers);
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                 message = intent.getStringExtra("message");
                 MessageBody = intent.getStringExtra("MessageBody");

                Log.d("Message", "onReceive: message" + MessageBody);

                Log.d("Message", "onReceive: message" + message);
                if(MessageBody.equals("News"))
                {
                    try {
                        String News = intent.getStringExtra("News");
                        Log.e("CheckType",News);

                        final Dialog dialog = new Dialog(ActivityMainHomePage.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setCancelable(false);
                        dialog.setContentView(R.layout.diloagnewsandseminars);
                        dialog.show();
                        TextView tv_message=dialog.findViewById(R.id.tv_message);
                        tv_message.setText(message+"");
                        Button btn_ok=dialog.findViewById(R.id.btn_ok);
                        btn_ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                MessageBody="";
                                message="";

                                Intent i=new Intent(getApplicationContext(),ActivityNewsAndEventMainPage.class);
                                startActivity(i);
                                dialog.dismiss();
                            }
                        });
                        // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }else {
                    try {
                        String Seminars = intent.getStringExtra("Seminars");
                        Log.e("CheckType",Seminars);
                        final Dialog dialog = new Dialog(ActivityMainHomePage.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setCancelable(false);
                        dialog.setContentView(R.layout.diloagnewsandseminars);
                        dialog.show();
                        TextView tv_message=dialog.findViewById(R.id.tv_message);
                        tv_message.setText(message+"");

                        Button btn_ok=dialog.findViewById(R.id.btn_ok);
                        btn_ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                MessageBody="";
                                message="";
                               // Toast.makeText(ActivityMainHomePage.this, "Comming Soon....", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(getApplicationContext(),ActivitySeminar.class);
                                startActivity(i);
                                dialog.dismiss();
                            }
                        });

                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }

               // String Seminars = intent.getStringExtra("Seminars");

                recyclerView.setHasFixedSize(true);
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 3);

                recyclerView.setLayoutManager(layoutManager);
                ArrayList<PojoHomePojo> mArrayList = prepareData();
                HomePageAdapter adapter = new HomePageAdapter(mArrayList, getApplicationContext());

                recyclerView.setAdapter(adapter);

                adapter.notifyDataSetChanged();

            }
        };


        mRequestQueue = Volley.newRequestQueue(getBaseContext());

        sh = getSharedPreferences(MyPREFERENCES, 0);
        editor = sh.edit();
        str_login_test = sh.getString("loginTest1", null);
        isUploaded = sh.getString("isUploaded", null);
        emailid= sh.getString("email", null);
        Log.d("test", "Register isUploaded : " + str_login_test);

        firebasetoken = sh.getString("fcmtoken", null);

        Log.d("Token", "onCreate: " + firebasetoken);

        android_id = Settings.Secure.getString(ActivityMainHomePage.this.getContentResolver(),
                Settings.Secure.ANDROID_ID);


        SharedPreferences sharedpreferences = getSharedPreferences(ActivityMainHomePage.MyPREFERENCES,
                Context.MODE_PRIVATE);

        String isUploadedToken = sh.getString("isUploadedToken", "");
        Log.d("Token", "onCreate: isUploadedToken " + isUploadedToken);
//isUploadedToken.trim().equalsIgnoreCase(" ") || isUploadedToken.trim().equalsIgnoreCase("") ||
        if (isUploadedToken.trim().equalsIgnoreCase("") || isUploadedToken.trim().equalsIgnoreCase("null")) {
            Log.d("Token", "onCreate: isUploadedToken " + isUploadedToken);
            //sharedprefToken();
            if (AppStatus.getInstance(ActivityMainHomePage.this).isOnline()) {

                postDataToken();
            } else {

            }
        } else {

            Log.d("Token", "onCreate: isUploadedToken " + isUploadedToken);
        }

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
            return;
        }

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        circleIndicator = (CircleIndicator) findViewById(R.id.indicator);

        checkPermission();


        ImagePagerAdapter imagePagerAdapter = new ImagePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(imagePagerAdapter);

        circleIndicator.setViewPager(viewPager);
        pagerScheduleProxy = new PagerScheduleProxy(viewPager, 2); //param 2 is interval(s)
        circleIndicator.setOnPageChangeListener(pagerScheduleProxy.getOnPageChangeListener());

        // mNotifCount = databaseHelper.getNotificationCount();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvNumbers);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager
                = new GridLayoutManager(getApplicationContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<PojoHomePojo> mArrayList = prepareData();
        HomePageAdapter adapter = new HomePageAdapter(mArrayList, getApplicationContext());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //postDataToken();

    }

    @Override
    protected void onResume() {
        super.onResume();


        ImagePagerAdapter imagePagerAdapter = new ImagePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(imagePagerAdapter);

        circleIndicator.setViewPager(viewPager);
        pagerScheduleProxy = new PagerScheduleProxy(viewPager, 2); //param 2 is interval(s)
        circleIndicator.setOnPageChangeListener(pagerScheduleProxy.getOnPageChangeListener());

        mNotifCount = dataBaseHandler.getNotificationCount();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvNumbers);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager
                = new GridLayoutManager(getApplicationContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<PojoHomePojo> mArrayList = prepareData();
        HomePageAdapter adapter = new HomePageAdapter(mArrayList, getApplicationContext());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(UrlConstants.PUSH_NOTIFICATION));
    }

    @Override
    protected void onPause() {
         MessageBody="";
         message="";
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvNumbers);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager
                = new GridLayoutManager(getApplicationContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<PojoHomePojo> mArrayList = prepareData();
        HomePageAdapter adapter = new HomePageAdapter(mArrayList, getApplicationContext());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //Toast.makeText(getApplicationContext(),"onPause",Toast.LENGTH_LONG).show();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }


    private ArrayList<PojoHomePojo> prepareData() {

        ArrayList<PojoHomePojo> mArrayList = new ArrayList<>();
        for (int i = 0; i < imageId.length; i++) {
            PojoHomePojo pojo = new PojoHomePojo();
            //pojo.setMtxt(data[i]);
            pojo.setmImg(imageId[i]);
            if (i == 3) {
                pojo.setNotification(String.valueOf(mNotifCount));
            } else {
                pojo.setNotification(String.valueOf(0));
            }


            mArrayList.add(pojo);
        }
        return mArrayList;

    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Do you want to close this application ?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        ActivityMainHomePage.this.finish();
                    }
                }).setNegativeButton("No", null).show();
    }


    @Override
    public void onStart() {
        super.onStart();
        if (pagerScheduleProxy != null) pagerScheduleProxy.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (pagerScheduleProxy != null) pagerScheduleProxy.onStop();
    }

    public boolean checkPermission() {

        if (Build.VERSION.SDK_INT >= 23) {

            int cameraPermissionfine = checkSelfPermission(Manifest.permission.CAMERA);
            int callPermissionfine = checkSelfPermission(Manifest.permission.CALL_PHONE);
            int locationPermissionfine = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
            int storagePermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int readPermission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            int locationPermissioncoarse = checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION);


            List<String> listPermissionsNeeded = new ArrayList<>();

            if (locationPermissionfine != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (locationPermissioncoarse != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
            if (storagePermission != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (readPermission != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }

            if (cameraPermissionfine != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.CAMERA);
            }

            if (callPermissionfine != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.CALL_PHONE);
            }


            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), PERMISSION_REQUEST_CODE);
                return false;
            }
            return true;
        }
        return true;

    }





    public void postDataToken() {

        String url = "http://stona.exhibitionz.in/webapi/deviceregistration.php";

        SharedPreferences sharedpreferences = getSharedPreferences(ActivityMainHomePage.MyPREFERENCES,
                Context.MODE_PRIVATE);

        firebasetoken = sharedpreferences.getString("fcmtoken", null);


        //volley code

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Log.d("response", "onResponse: " + response.toString());
                        //   Toast.makeText(ActivityMainHomePage.this, response.toString(), Toast.LENGTH_LONG).show();


                        if (response.contains("{\"status\":\"true\",\"message\":\"Data is inserted\"}")) {
                            Log.d("response", "onResponse:  true");
                            SharedPreferences.Editor editor = getSharedPreferences("myprefe", MODE_PRIVATE).edit();
                            editor.putString("isUploadedToken", "YES");
                            editor.commit();
                        }



                       /* try {

                            JSONObject jsosnOBJ = new JSONObject(response);

                            Log.d("response", "onResponse: " + response.toString());
                            Toast.makeText(ActivityMainHomePage.this,response.toString(),Toast.LENGTH_LONG).show();
                            String statusnew = jsosnOBJ.getString("status");

                            if (statusnew.contains("true")) {
                                SharedPreferences.Editor editor = getSharedPreferences("myprefe", MODE_PRIVATE).edit();
                                editor.putString("isUploadedToken", "YES");
                                editor.commit();
                                Log.d("Token", "onResponse: status" + statusnew);
                            } else {
                                Log.d("Token", "onResponse: status" + statusnew);


                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*/

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(ActivityRegister.this, "No Interner Connection, please check your internet connection ", Toast.LENGTH_SHORT).show();
                        error.getMessage();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("deviceid", android_id);
                Log.d("Token :", "getParams: " + android_id);
                params.put("key", firebasetoken);

                Log.d("Token :", "getParams: " + firebasetoken);
                return params;
            }

        };
        mRequestQueue.add(stringRequest);

/*
        SharedPreferences.Editor editor = getSharedPreferences("myprefe", MODE_PRIVATE).edit();

        editor.putString("isUploadedToken", "Yes");
        Log.d("Token", "postDataToken: " + "Yes");

        editor.commit();
*/

    }

    public void comingsoon(View v) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ActivityMainHomePage.this);
        dialog.setCancelable(false);
        dialog.setIcon(R.drawable.appstonalogo);
        dialog.setTitle("Stona");
        dialog.setMessage("Coming Soon..");
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

                Toast.makeText(ActivityMainHomePage.this, "Adapter", Toast.LENGTH_LONG).show();
            }
        });

        final AlertDialog alert = dialog.create();
        alert.show();

    }

}
