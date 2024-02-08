package com.ramanora.stona.adapter;

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
import android.widget.TextView;

import com.ramanora.stona.R;
import com.ramanora.stona.activities.ActivityExhibitiorlistTab;
import com.ramanora.stona.bean.AZExhibitorListPojo;
import com.ramanora.stona.fragments.CountryGridViewFragment;

import java.util.ArrayList;

/**
 * Created by admin on 10/14/2017.
 */

public class CountryCompanyAdapter extends RecyclerView.Adapter<CountryCompanyAdapter.DataHolder> implements Filterable {

    private Context mContext;
    private ArrayList<AZExhibitorListPojo> mArrayListCompany;
    private ArrayList<AZExhibitorListPojo> mFilteredList;
    FragmentManager mFragment;

    public CountryCompanyAdapter(Context mContext, ArrayList<AZExhibitorListPojo> mArrayListCompany) {
        this.mContext = mContext;
        this.mArrayListCompany = mArrayListCompany;
        this.mFilteredList = mArrayListCompany;

    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(mContext);
        View view=inflater.inflate(R.layout.country_company_adapter,parent,false);

        DataHolder dataHolder=new DataHolder(view);
        return dataHolder;
    }

    @Override
    public void onBindViewHolder(final DataHolder holder, final int position) {

        final AZExhibitorListPojo companyPojo = mFilteredList.get(position);

        holder.mTvCompanyName.setText(companyPojo.getCompanyName());
        holder.mTvCompanyhall.setText(companyPojo.getMhallno()+ " -  Stall No : " + companyPojo.getStallno());
        String hallNo = companyPojo.getMhallno();
        Log.d("hallNo","Hall No "+hallNo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AZExhibitorListPojo companyPojo = mFilteredList.get(position);
                CountryGridViewFragment countryGridViewFragment=new CountryGridViewFragment();
                mFragment=((ActivityExhibitiorlistTab)mContext).getSupportFragmentManager();
                mFragment.beginTransaction().add(R.id.activityexhibitorlist,countryGridViewFragment).addToBackStack("").commit();

                Bundle bundle = new Bundle();
                bundle.putSerializable("AZData",companyPojo);

                countryGridViewFragment.setArguments(bundle);
                hideKeyboard(mContext);

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

        TextView mTvCompanyName, mTvCompanyhall;

        public DataHolder(View itemView) {
            super(itemView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            mTvCompanyName = (TextView) itemView.findViewById(R.id.tvcompanyname);
            mTvCompanyhall = (TextView) itemView.findViewById(R.id.tvhallname);


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