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
import com.ramanora.stona.bean.GridHallPojo;
import com.ramanora.stona.database.DatabaseAccess;
import com.ramanora.stona.fragments.HallAboutUs;
import com.ramanora.stona.fragments.HallCallusFragment;
import com.ramanora.stona.fragments.HallCompanyProduct;
import com.ramanora.stona.fragments.HallNewLaunchesFragment;
import com.ramanora.stona.mapsection.ImageMapping.ActivityImageMappingSingle;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Owner on 09/10/2017.
 */

public class HallGridViewAdapter extends RecyclerView.Adapter<HallGridViewAdapter.ViewHolder> {

    private ArrayList<GridHallPojo> marrayListGridHall;
    private ArrayList<AZExhibitorListPojo> mArrayList;
    private ArrayList<AZExhibitorListPojo> marrayListCompany;
    private Context mContext;
    String hall;
    String stallno;

    public HallGridViewAdapter(ArrayList<GridHallPojo> marrayListGridAZ, ArrayList<AZExhibitorListPojo> marrayListCompany, Context mContext, String hall, String stallno) {
        this.marrayListGridHall = marrayListGridAZ;
        this.marrayListCompany = marrayListCompany;
        this.mContext = mContext;
        this.hall = hall;
        this.stallno = stallno;
    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.hall_grid_view_adapter, viewGroup, false);

        return new ViewHolder(view);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.myTextView.setText(marrayListGridHall.get(position).getMtxt());

        Picasso.with(mContext).load(marrayListGridHall.get(position).getmImg()).resize(240, 120).into(viewHolder.myImageView);

        viewHolder.myImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (marrayListGridHall.get(position).getMtxt().equalsIgnoreCase("Products")) {

                    HallCompanyProduct hCompanyProduct = new HallCompanyProduct();

                    Bundle bundle1 = new Bundle();
                    hCompanyProduct.setArguments(bundle1);
                    bundle1.putString("companyname", marrayListCompany.get(position).getCompanyName());
                    hCompanyProduct.setArguments(bundle1);

                    FragmentManager mFragMan = ((ActivityExhibitiorlistTab) mContext).getSupportFragmentManager();
                    mFragMan.beginTransaction().add(R.id.activityexhibitorlist, hCompanyProduct).addToBackStack("").commit();

                    System.out.println("mArrayList company :" + marrayListCompany.get(0).getCompanyName());
                    System.out.println("mArrayList company all size :" + marrayListCompany.size());
                    System.out.println("mArrayList company all size position:" + marrayListCompany.get(position).getCompanyName());
                }
//for floor plan
                else if (marrayListGridHall.get(position).getMtxt().equals("Floor Plan")) {

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
*/
                   DatabaseAccess databaseAccess = DatabaseAccess.getInstance(mContext);
                    databaseAccess.open();

                    mArrayList = databaseAccess.getCompanyCordinates(marrayListCompany.get(0).getCompanyName()
                            ,marrayListCompany.get(0).getMhallno(),
                            marrayListCompany.get(0).getStallno());
                    Log.d("test", "list size: " + mArrayList.size());
                    databaseAccess.close();

                    Intent intent = new Intent(mContext, ActivityImageMappingSingle.class);

                    intent.putExtra("Companyname", marrayListCompany.get(0).getCompanyName());

                    Log.d("test", "hall: " + hall);

                    intent.putExtra("hallname",hall);
                    intent.putExtra("stallno",stallno);

                    mContext.startActivity(intent);
                   // Toast.makeText(mContext, "Comming Soon...", Toast.LENGTH_SHORT).show();

                }

          /*      //new launches
                else if (marrayListGridHall.get(position).getMtxt().equals("New Launches")) {

                    HallNewLaunchesFragment hallNewLaunchesFragment = new HallNewLaunchesFragment();

                    Bundle bundle1 = new Bundle();
                    hallNewLaunchesFragment.setArguments(bundle1);
                    bundle1.putString("companyname", marrayListCompany.get(0).getCompanyName());
                    hallNewLaunchesFragment.setArguments(bundle1);

                    FragmentManager mFragMan = ((ActivityExhibitiorlistTab) mContext).getSupportFragmentManager();
                    mFragMan.beginTransaction().add(R.id.activityexhibitorlist, hallNewLaunchesFragment).addToBackStack("").commit();


                }
       */         //about us
                else if (marrayListGridHall.get(position).getMtxt().equals("About us")) {

                    HallAboutUs azAboutUs = new HallAboutUs();
                    Bundle bundle1 = new Bundle();
                    azAboutUs.setArguments(bundle1);
                    bundle1.putString("companyname", marrayListCompany.get(0).getCompanyName());
                    azAboutUs.setArguments(bundle1);

                    FragmentManager mFragMan = ((ActivityExhibitiorlistTab) mContext).getSupportFragmentManager();
                    mFragMan.beginTransaction().add(R.id.activityexhibitorlist, azAboutUs).addToBackStack("").commit();


                }
//call_us
                else if (marrayListGridHall.get(position).getMtxt().equals("Call us")) {

                    HallCallusFragment hallCallusFragment = new HallCallusFragment();
                    Bundle bundle2 = new Bundle();
                    hallCallusFragment.setArguments(bundle2);
                    bundle2.putSerializable("companyname", marrayListCompany.get(0).getCompanyName());
                    hallCallusFragment.setArguments(bundle2);

                    FragmentManager mFragMan = ((ActivityExhibitiorlistTab) mContext).getSupportFragmentManager();
                    mFragMan.beginTransaction().add(R.id.activityexhibitorlist, hallCallusFragment).addToBackStack("").commit();
                }

            }
        });

    }

    // total number of cells
    @Override
    public int getItemCount() {
        return marrayListGridHall.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
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