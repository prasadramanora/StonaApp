package com.ramanora.stona.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ramanora.stona.activities.ActivityMainHomePage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by amolrokade on 16/11/17.
 */

public class SendData extends Service {

    SharedPreferences sharedpreferences;
    String str_Name, str_FullName, str_Email, str_Phone, str_FullPhone,
            str_Company, str_City, str_Other, str_Designation, str_Country, str_Token, str_Typevisitor, isUploaded;
    RequestQueue mRequestQueue;
    String android_id, isUploadedToken;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mRequestQueue = Volley.newRequestQueue(getBaseContext());


        //register data

        sharedpreferences = getSharedPreferences(ActivityMainHomePage.MyPREFERENCES,
                Context.MODE_PRIVATE);
        isUploaded = sharedpreferences.getString("isUploaded", null);

        Log.d("test", "onStartCommand: isUploaded " + isUploaded);
        str_Name = sharedpreferences.getString("name", null);
        str_FullName = sharedpreferences.getString("name", null);
        str_Email = sharedpreferences.getString("email", null);
        str_Phone = sharedpreferences.getString("mobile", null);
        str_FullPhone = sharedpreferences.getString("mobile", null);
        str_Company = sharedpreferences.getString("company", null);
        str_City = sharedpreferences.getString("city", null);
        str_Other = sharedpreferences.getString("other", null);
        str_Designation = sharedpreferences.getString("designation", null);
        str_Country = sharedpreferences.getString("country", null);
        str_Typevisitor = sharedpreferences.getString("typevisitor", null);
        str_Token = sharedpreferences.getString("fcmtoken",null);
        Log.d("Token : ", "fcmtoken : " + str_Token);



        //for token
        isUploadedToken = sharedpreferences.getString("isUploadedToken",null);
        Log.d("isUploadedToken", "onStartCommand: " + isUploadedToken);

        android_id = sharedpreferences.getString("deviceid", null);
        Log.d("Token", "onStartCommand: isUploadedToken " + isUploadedToken);
/*
        if (isuploadedtoken.trim().contains("No")) {
            Log.d("test", "onStartCommand: true :");
            postDataToken();
        } else {
            Log.d("test", "onStartCommand: false :");
            stopSelf();
        }*/


//register
        if (isUploaded.trim().contains("No")) {
            Log.d("test", "onStartCommand: true :");
            postData();
        } else {
            Log.d("test", "onStartCommand: false :");
            stopSelf();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void postData() {

        String url = "http://stona.exhibitionz.in/webapi/registration.php";

        Log.d("test", "postData: ");
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsosnOBJ = new JSONObject(response);
                            String statusnew = jsosnOBJ.getString("status");
                            Log.d("test", "onResponse: status  :" + statusnew);
                            if (statusnew.contains("true")) {
                                ActivityMainHomePage.editor.putString("isUploaded", "YES");
                                ActivityMainHomePage.editor.commit();
                                Log.d("test", "onResponse: status" + statusnew);
                            } else {
                                Log.d("test", "onResponse: status" + statusnew);


                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                      /*  Toast.makeText(ActivityRegister.this,
                                "No Interner Connection, please check your internet connection ", Toast.LENGTH_SHORT).show();*/
                        error.getMessage();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();


                params.put("Name", str_FullName);
                params.put("EmailId", str_Email);
                params.put("Phone", str_FullPhone);
                params.put("Company", str_Company);
                params.put("City", str_City);
                params.put("Country", str_Country);
                params.put("Other", str_Other);
                params.put("Type_of_visitor", str_Typevisitor);
                params.put("Designation", str_Designation);
                params.put("Token", str_Token);


                return params;
            }

        };
        mRequestQueue.add(stringRequest);

        SharedPreferences.Editor editor = getSharedPreferences("myprefe", MODE_PRIVATE).edit();

        editor.putString("isUploaded", "YES");
        editor.commit();
    }



}
