package com.ramanora.stona.mapsection.hallsection;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramanora.stona.R;
import com.ramanora.stona.mapsection.hallsection.activity.ActivityHall1LowerLevel;
import com.ramanora.stona.mapsection.hallsection.activity.ActivityHall2LowerLevel;
import com.ramanora.stona.mapsection.hallsection.activity.ActivityHall3LowerLevel;
import com.ramanora.stona.mapsection.hallsection.activity.ActivityHall3MiddleLevel;
import com.ramanora.stona.mapsection.hallsection.activity.ActivityHall4LowerLevel;
import com.ramanora.stona.mapsection.outdoorsection.OutdoorSectionAdapter;
import com.ramanora.stona.mapsection.outdoorsection.OutdoorZoomActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by abc on 11/20/2017.
 */

public class HallSectionAdapter extends RecyclerView.Adapter<HallSectionAdapter.ViewHolder> {

private ArrayList<PojoHallSection> android;
private Context context;


public HallSectionAdapter(ArrayList<PojoHallSection> android, Context context) {
        this.android = android;
        this.context = context;
        }


// inflates the cell layout from xml when needed
@Override
public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.outdoor_recyclerview_item, viewGroup, false);

        return new ViewHolder(view);
        }

// binds the data to the textview in each cell
@Override
public void onBindViewHolder(ViewHolder viewHolder, final int position) {
//          viewHolder.myTextView.setText(android.get(position).getMtxt());
       // Picasso.get().load(android.get(position).getmImg()).resize(240, 120).into(viewHolder.myImageView);

   /* Picasso.with(context)
            .load(android.get(position).getmImg())
            .placeholder(R.drawable.ic_launcher_background) //this is optional the image to display while the url image is downloading
            //this is also optional if some error has occurred in downloading the image this image would be displayed
            .into(viewHolder.myImageView);
*/

    PojoHallSection pojoHallSection = android.get(position);
          viewHolder.myImageView.setImageResource(android.get(position).getmImg());
         // viewHolder.myTextView.setText(android.get(position).getMtxt());

    viewHolder.myTextView.setText(pojoHallSection.getMtxt());
    Log.d("name","name "+pojoHallSection.getMtxt());
}

// total number of cells
@Override
public int getItemCount() {
        return android.size();
        }


// stores and recycles views as they are scrolled off screen
public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView myTextView;
    public ImageView myImageView;


    private final Context context;


    public ViewHolder(View itemView) {
        super(itemView);

         myTextView = (TextView) itemView.findViewById(R.id.textView);
        myImageView = itemView.findViewById(R.id.imageView);
        context = itemView.getContext();
        itemView.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        Intent intent = null;
        switch (getAdapterPosition()) {

            case 0:

                intent = new Intent(context, ActivityHall1LowerLevel.class);

                break;
            case 1:

                intent = new Intent(context, ActivityHall2LowerLevel.class);
                break;

            case 2:


                intent = new Intent(context, ActivityHall3LowerLevel.class);
                break;


            case 3:
                intent = new Intent(context, ActivityHall3MiddleLevel.class);
                break;

            case 4:
                intent = new Intent(context, ActivityHall4LowerLevel.class);
                break;

            case 5:
                intent = new Intent(context, OutdoorZoomActivity.class);
                break;





       /*     case 5:

                intent = new Intent(context, ActivitySocialMedia.class);
                break;

            case 6:

                intent = new Intent(context, VisitingCameraActivity.class);
                break;

            case 7:

                intent = new Intent(context, ActivityQRCode.class);
                break;


            case 8:

                intent = new Intent(context, ActivityVirtualExhibition.class);
                break;
*/


        }
        context.startActivity(intent);

    }

}
}