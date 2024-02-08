package com.ramanora.stona.mapsection.map;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.ramanora.stona.database.DatabaseAccess;
import com.ramanora.stona.mapsection.ImageMapping.ActivityImageMappingSingle;
import com.ramanora.stona.mapsection.ImageMapping.ActivityMapping;
import com.ramanora.stona.R;
import com.ramanora.stona.bean.AZExhibitorListPojo;

import java.util.ArrayList;

/**
 * Created by amolrokade on 17/11/17.
 */

public class CompanylistAdapter extends RecyclerView.Adapter<CompanylistAdapter.DataHolder> implements Filterable {

    private Context mContext;
    private ArrayList<AZExhibitorListPojo> mArrayListCompany;
    private ArrayList<AZExhibitorListPojo> mFilteredList;
    FragmentManager mFragment;


    public CompanylistAdapter(Context mContext, ArrayList<AZExhibitorListPojo> mArrayListCompany) {
        this.mContext = mContext;
        this.mArrayListCompany = mArrayListCompany;
        this.mFilteredList = mArrayListCompany;
    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.country_company_adapter, parent, false);

        DataHolder dataHolder = new DataHolder(view);
        return dataHolder;
    }

    @Override
    public void onBindViewHolder(final DataHolder holder, final int position) {

        final AZExhibitorListPojo companyPojo = mFilteredList.get(position);

        holder.tv_y_cordinate.setText(companyPojo.getY());
        holder.tv_x_cordinate.setText(companyPojo.getX());
    //    Log.e("CheckX",companyPojo.getX());
     //   Log.e("CheckY",companyPojo.getY());

        holder.mTvCompanyName.setText(companyPojo.getCompanyName());
        holder.mTvHallName.setText(companyPojo.getMhallno() + " -  Stall No : " + companyPojo.getStallno());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AZExhibitorListPojo companyPojo = mFilteredList.get(position);

                Intent intent = new Intent(mContext, ActivityMapping.class);
                intent.putExtra("companyname", companyPojo.getCompanyName());
                intent.putExtra("hall", companyPojo.getMhallno());
                intent.putExtra("stallno", companyPojo.getStallno());
               // intent.putExtra("X", companyPojo.getX());
               // intent.putExtra("Y", companyPojo.getY());
                mContext.startActivity(intent);
             //   ((Activity)mContext).finish();
                hideKeyboard(mContext);


              /*  DatabaseAccess databaseAccess = DatabaseAccess.getInstance(mContext);
                databaseAccess.open();

                mArrayListCompany = databaseAccess.getCompanyCordinates(mArrayListCompany.get(0).getCompanyName()
                        ,mArrayListCompany.get(0).getMhallno(),mArrayListCompany.get(0).getStallno());
                Log.d("test", "list size: "+mArrayListCompany.size());
                databaseAccess.close();

                Intent intent= new Intent(mContext, ActivityMapping.class);


                intent.putExtra("companyname",mArrayListCompany.get(0).getCompanyName());
                intent.putExtra("hall",mArrayListCompany.get(0).getMhallno());
                intent.putExtra("stallno",mArrayListCompany.get(0).getStallno());
                hideKeyboard(mContext);
                mContext.startActivity(intent);*/


            }
        });

    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = mArrayListCompany;
                } else {

                    ArrayList<AZExhibitorListPojo> filteredList = new ArrayList<>();

                    for (AZExhibitorListPojo hallExhibitorListPojo : mArrayListCompany) {

                        if (hallExhibitorListPojo.getCompanyName().toLowerCase().contains(charString) || hallExhibitorListPojo.getCompanyName().toUpperCase().contains(charString)) {

                            filteredList.add(hallExhibitorListPojo);
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

        TextView mTvCompanyName, mTvHallName,tv_x_cordinate,tv_y_cordinate;

        public DataHolder(View itemView) {
            super(itemView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            mTvCompanyName = (TextView) itemView.findViewById(R.id.tvcompanyname);
            tv_x_cordinate= (TextView) itemView.findViewById(R.id.tv_x_cordinate);
            tv_y_cordinate= (TextView) itemView.findViewById(R.id.tv_y_cordinate);
            mTvHallName = (TextView) itemView.findViewById(R.id.tvhallname);

        }
    }

    public static void hideKeyboard(Context ctx) {
        InputMethodManager inputManager = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View v = ((CompanyListOneActivity) ctx).getCurrentFocus();
        if (v == null)
            return;

        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}