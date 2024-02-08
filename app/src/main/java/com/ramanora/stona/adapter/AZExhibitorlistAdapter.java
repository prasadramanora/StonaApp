package com.ramanora.stona.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.ramanora.stona.R;
import com.ramanora.stona.activities.ActivityExhibitiorlistTab;
import com.ramanora.stona.bean.AZExhibitorListPojo;
import com.ramanora.stona.fragments.ExhibitorListGridViewFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class AZExhibitorlistAdapter extends RecyclerView.Adapter<AZExhibitorlistAdapter.DataHolder> implements Filterable {

    private Context mContext;
    private ArrayList<AZExhibitorListPojo> mArrayListAZ;
    private ArrayList<AZExhibitorListPojo> mFilteredList = new ArrayList<>(267);
    FragmentManager mFragment;
    public static String x_cordinate;
    public static String y_cordinate;

    public AZExhibitorlistAdapter(Context mContext, ArrayList<AZExhibitorListPojo> mArrayListAZ) {
        this.mContext = mContext;
        this.mArrayListAZ = mArrayListAZ;
        this.mFilteredList = mArrayListAZ;

    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.exhibitor_az_adapter, parent, false);
        DataHolder dataHolder = new DataHolder(view);
        return dataHolder;
    }

    @Override
    public void onBindViewHolder(final DataHolder holder, @SuppressLint("RecyclerView") int position) {


        AZExhibitorListPojo azExhibitorListPojo = mFilteredList.get(position);
        holder.mTvAzExhibitorlist.setText(azExhibitorListPojo.getCompanyName());

        holder.tv_y_coordinate.setText(azExhibitorListPojo.getY());
        holder.tv_x_coordinate.setText(azExhibitorListPojo.getX());


        holder.mhallno.setText(azExhibitorListPojo.getMhallno() + " -  Stall No : " + azExhibitorListPojo.getStallno());
        Log.e("Checkleagth", holder.mTvAzExhibitorlist.getText().toString().length() + "");

        if (holder.mTvAzExhibitorlist.getText().toString().length() == 0) {

            holder.rl_mainlayout.setVisibility(View.GONE);
        } else {
            holder.rl_mainlayout.setVisibility(View.VISIBLE);
        }


        String hallNo = azExhibitorListPojo.getMhallno();
//        Log.d("hallNo","Hall No AZ "+azExhibitorListPojo.getStallno().length());

//        Log.e("getY()",azExhibitorListPojo.getY());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AZExhibitorListPojo azExhibitorListPojo = mFilteredList.get(position);
                ExhibitorListGridViewFragment exhibitorListGridViewFragment = new ExhibitorListGridViewFragment();
                mFragment = ((ActivityExhibitiorlistTab) mContext).getSupportFragmentManager();
                mFragment.beginTransaction().add(R.id.activityexhibitorlist, exhibitorListGridViewFragment).addToBackStack("").commit();

                Bundle bundle = new Bundle();
                bundle.putSerializable("AZData", azExhibitorListPojo);
                exhibitorListGridViewFragment.setArguments(bundle);
                hideKeyboard(mContext);

            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("test_exhibitor_list", "getItemCount: " + mFilteredList.size());
        return mFilteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = mArrayListAZ;
                } else {

                    ArrayList<AZExhibitorListPojo> filteredList = new ArrayList<>(267);

                    for (AZExhibitorListPojo azExhibitorListPojo : mArrayListAZ) {

                        if (azExhibitorListPojo.getCompanyName().toLowerCase().contains(charString)
                                || azExhibitorListPojo.getCompanyName().toUpperCase().contains(charString)) {

                            filteredList.add(azExhibitorListPojo);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<AZExhibitorListPojo>) filterResults.values;

                notifyDataSetChanged();
            }
        };
    }

    public class DataHolder extends RecyclerView.ViewHolder {

        TextView mTvAzExhibitorlist, mhallno, tvproductname, tv_x_coordinate, tv_y_coordinate;
        RelativeLayout rl_mainlayout;

        public DataHolder(View itemView) {
            super(itemView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            rl_mainlayout = (RelativeLayout) itemView.findViewById(R.id.rl_mainlayout);
            mTvAzExhibitorlist = (TextView) itemView.findViewById(R.id.tvazexhibitorlist);
            mhallno = (TextView) itemView.findViewById(R.id.tvhallno);
            tvproductname = (TextView) itemView.findViewById(R.id.tvproductname);
            tv_x_coordinate = (TextView) itemView.findViewById(R.id.tv_x_coordinate);

            tv_y_coordinate = (TextView) itemView.findViewById(R.id.tv_y_coordinate);
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
