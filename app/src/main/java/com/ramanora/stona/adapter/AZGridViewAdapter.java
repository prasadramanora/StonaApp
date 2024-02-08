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
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ramanora.stona.R;
import com.ramanora.stona.activities.ActivityExhibitiorlistTab;
import com.ramanora.stona.bean.AZExhibitorListPojo;
import com.ramanora.stona.bean.GridAZPojo;
import com.ramanora.stona.database.DatabaseAccess;
import com.ramanora.stona.fragments.AZAboutUs;
import com.ramanora.stona.fragments.AZCallusFragment;
import com.ramanora.stona.fragments.AZCompanyProduct;
import com.ramanora.stona.fragments.AZNewLaunchesFragment;
import com.ramanora.stona.mapsection.ImageMapping.ActivityImageMappingSingle;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Owner on 09/10/2017.
 */

public class AZGridViewAdapter extends RecyclerView.Adapter<AZGridViewAdapter.ViewHolder> {

    private ArrayList<GridAZPojo> marrayListGridAZ;
   ArrayList<AZExhibitorListPojo> marrayListCompany;
    private Context mContext;
    private ArrayList<AZExhibitorListPojo> mArrayListCompany;
    public AZGridViewAdapter(ArrayList<GridAZPojo> marrayListGridAZ,
                             ArrayList<AZExhibitorListPojo> marrayListCompany, Context mContext) {
        this.marrayListGridAZ = marrayListGridAZ;
        this.marrayListCompany = marrayListCompany;
        this.mContext = mContext;
    }

    // inflates the cell layout from xml when needed
    @Override

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.az_grid_view_adapter, viewGroup, false);

        return new ViewHolder(view);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.myTextView.setText(marrayListGridAZ.get(position).getMtxt());

        Picasso.with(mContext).load(marrayListGridAZ.get(position).getmImg()).resize(240, 120).into(viewHolder.myImageView);

        viewHolder.myImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

          if (marrayListGridAZ.get(position).getMtxt().equalsIgnoreCase("products")) {

                    AZCompanyProduct azCompanyProduct = new AZCompanyProduct();
                    Bundle bundle1 = new Bundle();
                    azCompanyProduct.setArguments(bundle1);
                    bundle1.putSerializable("companyname", marrayListCompany.get(0));
                    azCompanyProduct.setArguments(bundle1);

                    FragmentManager mFragMan = ((ActivityExhibitiorlistTab) mContext).getSupportFragmentManager();
                    mFragMan.beginTransaction().add(R.id.activityexhibitorlist, azCompanyProduct).addToBackStack("").commit();


                }
//for floor plan
                   else if(marrayListGridAZ.get(position).getMtxt().equals("Floor Plan")){

             // Toast.makeText(mContext, "Comming Soon...", Toast.LENGTH_SHORT).show();
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(mContext);
                    databaseAccess.open();

                    mArrayListCompany = databaseAccess.getCompanyCordinates(marrayListCompany.get(0).getCompanyName()
                            ,marrayListCompany.get(0).getMhallno(),marrayListCompany.get(0).getStallno());
                    Log.d("test", "list size: "+mArrayListCompany.size());
                    databaseAccess.close();

                    Intent intent= new Intent(mContext, ActivityImageMappingSingle.class);


                    intent.putExtra("Companyname",marrayListCompany.get(0).getCompanyName());
                    intent.putExtra("hallname",marrayListCompany.get(0).getMhallno());
                    intent.putExtra("stallno",marrayListCompany.get(0).getStallno());
                    hideKeyboard(mContext);
                    mContext.startActivity(intent);
                    hideKeyboard(mContext);


                }

            /*       //new launches
                else if(marrayListGridAZ.get(position).getMtxt().equals("New Launches")) {

                    AZNewLaunchesFragment azNewLaunchesFragment = new AZNewLaunchesFragment();
                    Bundle bundle2 = new Bundle();
                    azNewLaunchesFragment.setArguments(bundle2);
                    bundle2.putSerializable("companyname", marrayListCompany.get(0));
                    azNewLaunchesFragment.setArguments(bundle2);

                    FragmentManager mFragMan = ((ActivityExhibitiorlistTab) mContext).getSupportFragmentManager();
                    mFragMan.beginTransaction().add(R.id.activityexhibitorlist, azNewLaunchesFragment).addToBackStack("").commit();


                }
                //about us
                else*/ if(marrayListGridAZ.get(position).getMtxt().equals("About us")) {

                    AZAboutUs azAboutUs = new AZAboutUs();
                    Bundle bundle2 = new Bundle();
                    azAboutUs.setArguments(bundle2);
                    bundle2.putSerializable("companyname", marrayListCompany.get(0));
                    azAboutUs.setArguments(bundle2);

                    FragmentManager mFragMan = ((ActivityExhibitiorlistTab) mContext).getSupportFragmentManager();
                    mFragMan.beginTransaction().add(R.id.activityexhibitorlist, azAboutUs).addToBackStack("").commit();


                }
//call_us
                else if(marrayListGridAZ.get(position).getMtxt().equals("Call us")) {

                    AZCallusFragment azAboutUs = new AZCallusFragment();
                    Bundle bundle2 = new Bundle();
                    azAboutUs.setArguments(bundle2);
                    bundle2.putSerializable("companyname", marrayListCompany.get(0));
                    azAboutUs.setArguments(bundle2);

                    FragmentManager mFragMan = ((ActivityExhibitiorlistTab) mContext).getSupportFragmentManager();
                    mFragMan.beginTransaction().add(R.id.activityexhibitorlist, azAboutUs).addToBackStack("").commit();



                }

                hideKeyboard(mContext);
            }
        });


    }

    // total number of cells
    @Override
    public int getItemCount() {
        return marrayListGridAZ.size();
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

    public static void hideKeyboard(Context ctx) {
        InputMethodManager inputManager = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View v = ((ActivityExhibitiorlistTab) ctx).getCurrentFocus();
        if (v == null)
            return;

        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}