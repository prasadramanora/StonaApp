package com.ramanora.stona.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.ramanora.stona.R;
import com.ramanora.stona.activities.ActivityExhibitiorlistTab;
import com.ramanora.stona.bean.AZExhibitorListPojo;
import com.ramanora.stona.bean.GridcountryPojo;
import com.ramanora.stona.database.DatabaseAccess;
import com.ramanora.stona.fragments.CountryCallusFragment;
import com.ramanora.stona.fragments.CountryAboutUs;
import com.ramanora.stona.fragments.CountryCompanyProduct;
import com.ramanora.stona.mapsection.ImageMapping.ActivityImageMappingSingle;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Owner on 09/10/2017.
 */

public class CountryGridViewAdapter extends RecyclerView.Adapter<CountryGridViewAdapter.ViewHolder> {

    private ArrayList<GridcountryPojo> marrayListGridCountry;
    private ArrayList<AZExhibitorListPojo> marrayListCompany;
    private ArrayList<AZExhibitorListPojo> marrayList;
    private Context mContext;

    public CountryGridViewAdapter(ArrayList<GridcountryPojo> marrayListGridAZ, ArrayList<AZExhibitorListPojo> marrayListCompany, Context mContext) {
        this.marrayListGridCountry = marrayListGridAZ;
        this.marrayListCompany = marrayListCompany;
        this.mContext = mContext;
    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.country_grid_view_adapter, viewGroup, false);

        return new ViewHolder(view);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.myTextView.setText(marrayListGridCountry.get(position).getMtxt());

        Picasso.with(mContext).load(marrayListGridCountry.get(position).getmImg()).resize(240, 120).into(viewHolder.myImageView);

        viewHolder.myImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (marrayListGridCountry.get(position).getMtxt().equals("Products")) {

                    CountryCompanyProduct azCompanyProduct = new CountryCompanyProduct();
                    Bundle bundle1 = new Bundle();
                    azCompanyProduct.setArguments(bundle1);
                    bundle1.putSerializable("companyname", marrayListCompany.get(0).getCompanyName());
                    azCompanyProduct.setArguments(bundle1);

                    FragmentManager mFragMan = ((ActivityExhibitiorlistTab) mContext).getSupportFragmentManager();
                    mFragMan.beginTransaction().add(R.id.activityexhibitorlist, azCompanyProduct).addToBackStack("").commit();





                    System.out.println("mArrayList company :"+marrayListCompany.get(0).getCompanyName());
                    System.out.println("mArrayList company all size :"+marrayListCompany.size());
                    System.out.println("mArrayList company all size position:"+marrayListCompany.get(position).getCompanyName());


                }
//for floor plan
                else if(marrayListGridCountry.get(position).getMtxt().equals("Floor Plan")){

                    /*AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
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
                */   // Toast.makeText(mContext, "Coming Soon..", Toast.LENGTH_SHORT).show();
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(mContext);
                    databaseAccess.open();

                    marrayList = databaseAccess.getCompanyCordinates(marrayListCompany.get(0).getCompanyName(),
                            marrayListCompany.get(0).getMhallno(),
                            marrayListCompany.get(0).getStallno());
                    Log.d("test", "list size: "+marrayList.size());
                    databaseAccess.close();

                    Intent intent= new Intent(mContext, ActivityImageMappingSingle.class);


                    intent.putExtra("Companyname",marrayListCompany.get(0).getCompanyName());
                    intent.putExtra("hallname",marrayListCompany.get(0).getMhallno());
                    intent.putExtra("stallno",marrayListCompany.get(0).getStallno());

                    mContext.startActivity(intent);
                    //Toast.makeText(mContext, "Comming Soon...", Toast.LENGTH_SHORT).show();

                }
/*
  //new launches
                else if(marrayListGridCountry.get(position).getMtxt().equals("New Launches")) {

                    CountryNewLaunchesFragment azNewLaunchesFragment = new CountryNewLaunchesFragment();
                    Bundle bundle2 = new Bundle();
                    azNewLaunchesFragment.setArguments(bundle2);
                    bundle2.putSerializable("companyname", marrayListCompany.get(0).getCompanyName());
                    azNewLaunchesFragment.setArguments(bundle2);

                    FragmentManager mFragMan = ((ActivityExhibitiorlistTab) mContext).getSupportFragmentManager();
                    mFragMan.beginTransaction().add(R.id.activityexhibitorlist, azNewLaunchesFragment).addToBackStack("").commit();


                }*/
  //about us
                else if(marrayListGridCountry.get(position).getMtxt().equals("About us")) {

                    CountryAboutUs azAboutUs = new CountryAboutUs();
                    Bundle bundle2 = new Bundle();
                    azAboutUs.setArguments(bundle2);
                    bundle2.putSerializable("companyname", marrayListCompany.get(0).getCompanyName());
                    azAboutUs.setArguments(bundle2);

                    FragmentManager mFragMan = ((ActivityExhibitiorlistTab) mContext).getSupportFragmentManager();
                    mFragMan.beginTransaction().add(R.id.activityexhibitorlist, azAboutUs).addToBackStack("").commit();


                }
//call_us
                else if(marrayListGridCountry.get(position).getMtxt().equals("Call us")) {

                    CountryCallusFragment countruCallusFragment = new CountryCallusFragment();
                    Bundle bundle2 = new Bundle();
                    countruCallusFragment.setArguments(bundle2);
                    bundle2.putSerializable("companyname", marrayListCompany.get(0).getCompanyName());
                    countruCallusFragment.setArguments(bundle2);

                    FragmentManager mFragMan = ((ActivityExhibitiorlistTab) mContext).getSupportFragmentManager();
                    mFragMan.beginTransaction().add(R.id.activityexhibitorlist, countruCallusFragment).addToBackStack("").commit();
            }

            }
        });

    }

    // total number of cells
    @Override
    public int getItemCount() {
        return marrayListGridCountry.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView myTextView;
        public ImageView myImageView;


        private final Context context;


        public ViewHolder(View itemView) {
            super(itemView);

            myTextView = (TextView) itemView.findViewById(R.id.textView);
            myImageView = (ImageView) itemView.findViewById(R.id.imageView);
            context = itemView.getContext();



        }

    }
}