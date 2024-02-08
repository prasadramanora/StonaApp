package com.ramanora.stona.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.ramanora.stona.activities.ActivityMainHomePage;
import com.ramanora.stona.activities.ActivitySplash;
import com.ramanora.stona.services.SendData;

/**
 * Created by amolrokade on 16/11/17.
 */

public class NetworkChange extends BroadcastReceiver {
    SharedPreferences sharedpreferences;
    String isUploaded=null;
    @Override
    public void onReceive(final Context context, final Intent intent) {
        sharedpreferences = context.getSharedPreferences(ActivityMainHomePage.MyPREFERENCES,
                Context.MODE_PRIVATE);


        isUploaded = sharedpreferences.getString("isUploaded", "");
        Log.d("test", "onReceive: isUploaded"+ isUploaded);


        if (isUploaded.equalsIgnoreCase("null") || isUploaded==null || isUploaded.equalsIgnoreCase("") ) {

        }else if(isUploaded.equalsIgnoreCase("No")){
            if (AppStatus.getInstance(context).isOnline()) {
                Intent intent1=new Intent(context, SendData.class);
                context.startService(intent1);
            }
        }


    }
}