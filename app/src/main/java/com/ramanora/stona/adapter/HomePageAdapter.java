package com.ramanora.stona.adapter;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ramanora.stona.R;
import com.ramanora.stona.VolleySingelton;
import com.ramanora.stona.activities.ActivityAboutEvent;
import com.ramanora.stona.activities.ActivityExhibitiorlistTab;
import com.ramanora.stona.activities.ActivityMainHomePage;
import com.ramanora.stona.activities.ActivityNewsAndEventMainPage;
import com.ramanora.stona.activities.ActivityQRCode;
import com.ramanora.stona.activities.ActivityRegister;
import com.ramanora.stona.activities.ActivitySeminar;
import com.ramanora.stona.activities.ActivitySocialMedia;
import com.ramanora.stona.activities.FeedbackFormActivity;
import com.ramanora.stona.activities.VisitingCameraActivity;
import com.ramanora.stona.bean.PojoHomePojo;
import com.ramanora.stona.database.DataBaseHandler;
import com.ramanora.stona.mapsection.map.ActivityMap;
import com.ramanora.stona.utils.UrlConstants;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 9/14/2017.
 */

public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.ViewHolder> {
    //  private DataBaseHandler databaseHelper;
    private ArrayList<PojoHomePojo> android;
    public Context context;

    int mNotifCount = 0;
    SharedPreferences sharedpreferences;
    public static String str_login_test;
    public static SharedPreferences sh;
    FragmentManager fragmentManager;
    private DataBaseHandler databaseHelper;


    public HomePageAdapter(ArrayList<PojoHomePojo> android, Context context) {
        this.android = android;
        this.context = context;
        // databaseHelper = new DataBaseHandler(context);
        //notifyDataSetChanged();
    }


    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.homepage_recyclerview_item, viewGroup, false);


        // str_login_test = sh.getString("loginTest1", null);

        return new ViewHolder(view);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
//          viewHolder.myTextView.setText(android.get(position).getMtxt());

        Picasso.with(context).load(android.get(position).getmImg()).resize(240, 120).into(viewHolder.myImageView);
        if (position == 0||position==1||position==3||position==4||position==5||position==6||position==8||position==2)
        {
            Picasso.with(context).load(android.get(position).getmImg()).resize(240, 120).into(viewHolder.myImageView);
          //  viewHolder.myImageView.setAlpha(0.45f);
        }

        else {



        }



        SharedPreferences.Editor sharedEditor = context.getSharedPreferences("myprefe", MODE_PRIVATE).edit();
        str_login_test = ActivityMainHomePage.sh.getString("loginTest1", null);
        Log.d("sharedpreferance", "onBindViewHolder: " + str_login_test);

        databaseHelper = new DataBaseHandler(context);

        mNotifCount = Integer.parseInt(android.get(position).getNotification());
        int img = android.get(position).getmImg();

        if (position == 3) {
            mNotifCount = databaseHelper.getNotificationCount();

            viewHolder.count.setVisibility(View.GONE);
        }

        if (mNotifCount == 0) {

            viewHolder.count.setVisibility(View.GONE);
        } else if (mNotifCount > 0) {

            Log.d("adaptercount", "onBindViewHolder: " + mNotifCount);
            viewHolder.count.setVisibility(View.VISIBLE);

            viewHolder.count.setText(String.valueOf(mNotifCount));

        }


    }


    @Override
    public int getItemCount() {
        return android.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView myTextView, count;
        public ImageView myImageView;


        public ViewHolder(View itemView) {
            super(itemView);

            // myTextView = (TextView) itemView.findViewById(R.id.textView);
            myImageView = itemView.findViewById(R.id.imageView);

            count = itemView.findViewById(R.id.badge_notification);


            context = itemView.getContext();
            itemView.setOnClickListener(this);
            // notifyDataSetChanged();
        }


        @Override
        public void onClick(View view) {

            final Intent[] intent = {null};
            switch (getAdapterPosition()) {

                case 0:

                   //dialog();
                        intent[0] = new Intent(context, ActivityAboutEvent.class);
                    context.startActivity(intent[0]);
                    //  Toast.makeText(context,"Adapter",Toast.LENGTH_LONG).show();
                    break;


                case 1:
                    //dialog();
                      intent[0] = new Intent(context, ActivityExhibitiorlistTab.class);
                      context.startActivity(intent[0]);

                    break;

                case 2:
                   // dialog();
                   // Toast.makeText(context, "Comming Soon...", Toast.LENGTH_SHORT).show();
                   intent[0] = new Intent(context, ActivitySeminar.class);
                     context.startActivity(intent[0]);
                    break;

                case 3:
                    //dialog();

                    intent[0] = new Intent(context, ActivityNewsAndEventMainPage.class);
                //    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(UrlConstants.URL_SOCIAL_FACEBOOK));
                     context.startActivity(intent[0]);
                    break;

                case 4:
                   // dialog();

                    intent[0] = new Intent(context, ActivityMap.class);
                     context.startActivity(intent[0]);
                    break;

                case 5:
                 //   dialog();
                    intent[0] = new Intent(context, ActivitySocialMedia.class);
                    context.startActivity(intent[0]);
                    break;

                case 6:
                   // dialog();
                    intent[0] = new Intent(context, VisitingCameraActivity.class);
                    context.startActivity(intent[0]);
                    break;

                case 7:

                    if (str_login_test != null) {
                        Log.e("CheckBlock3","1");
                        Log.e("CheckBloc4",str_login_test);
                        intent[0] = new Intent(context, ActivityQRCode.class);
                        context.startActivity(intent[0]);
                        break;
                    } else {
                        Log.e("CheckBlock1","2");
                        Log.e("CheckBlock2",str_login_test);
                        intent[0] = new Intent(context, ActivityRegister.class);
                        context.startActivity(intent[0]);
                        break;
                    }

                case 8:
                  //  dialog();
                    intent[0] = new Intent(context, FeedbackFormActivity.class);
                     context.startActivity(intent[0]);
                    break;
                case 9:
                    //  dialog();

                    AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                    dialog.setCancelable(false);
                    dialog.setIcon(R.drawable.appstonalogo);
                    dialog.setTitle("Stona 2023" + "");
                    dialog.setMessage("Are You sire want to exit Stona2023 app?");
                    dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            SharedPreferences.Editor editor = context.getSharedPreferences("myprefe", MODE_PRIVATE).edit();

                            //    str_QrCode = mEdtVFullName.getText().toString()+ "\n" +mEdtVEmail.getText().toString()+"\n"+mEdtVPhone.getText().toString();
                            editor.putString("loginTest1", "");

                            editor.commit();
                            Logout();

                            intent[0] = new Intent(context, ActivityRegister.class);
                            context.startActivity(intent[0]);
                            dialog.dismiss();
                        }
                    });
                    dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {

                            dialog.dismiss();
                        }
                    });
                    final AlertDialog alert = dialog.create();
                    alert.show();


                    break;

            }
            //  context.startActivity(intent);

        }

    }

    public void Logout() {


        String urlupdatelogin = "https://myexpos.in/stona/deleteaccount.php" ;


        Log.d("test", "onResponse: url " + urlupdatelogin);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                urlupdatelogin,
                new Response.Listener<String>() {
                    @Override

                    public void onResponse(String response) {





                            Log.d("test", "onResponse: Track Data :" + response);



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "No Internet Connection, please check your internet connection ", Toast.LENGTH_SHORT).show();
                        error.getMessage();
                        Log.d("test_testnew", "onErrorResponse: Error " + error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();


                params.put("emailId ", ActivityMainHomePage.emailid);


                Log.d("test_testnew", "getParams: " + params);
                return params;

            }

        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingelton.getInstance(context).getRequestQueue().add(stringRequest);

    }


    private void setAlpha(float alpha, View... views) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            for (View view : views) {
                view.setAlpha(alpha);
            }
        }
    }

    private void setNotifCount(int count1) {
        mNotifCount = count1;

    }

    public void dialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setIcon(R.drawable.appstonalogo);
        dialog.setTitle("Stona");
        dialog.setMessage("Coming Soon..");
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                //Toast.makeText(context,"Adapter",Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog alert = dialog.create();
        alert.show();


    }


}

