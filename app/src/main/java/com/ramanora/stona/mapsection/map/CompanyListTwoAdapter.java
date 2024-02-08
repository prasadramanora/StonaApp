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

import com.ramanora.stona.mapsection.ImageMapping.ActivityImageMapping;
import com.ramanora.stona.R;
import com.ramanora.stona.bean.AZExhibitorListPojo;

import java.util.ArrayList;

/**
 * Created by amolrokade on 17/11/17.
 */

public class CompanyListTwoAdapter extends RecyclerView.Adapter<CompanyListTwoAdapter.DataHolder> implements Filterable {

    private Context mContext;
    private ArrayList<AZExhibitorListPojo> mArrayListCompany;
    private ArrayList<AZExhibitorListPojo> mFilteredList;
    FragmentManager mFragment;
    String companyname1;
    String hall;
    String stallno;

    public CompanyListTwoAdapter(Context mContext, ArrayList<AZExhibitorListPojo> mArrayListCompany, String companyname1, String hall, String stallno) {
        this.mContext = mContext;
        this.mArrayListCompany = mArrayListCompany;
        this.mFilteredList = mArrayListCompany;
        this.companyname1 = companyname1;
        this.hall = hall;
        this.stallno = stallno;
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

        holder.mTvCompanyName.setText(companyPojo.getCompanyName());
        Log.e("Companynamelist",holder.mTvCompanyName.getText().toString());
        holder.mTvHallName.setText(companyPojo.getMhallno()+ " -  Stall No : " + companyPojo.getStallno());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AZExhibitorListPojo companyPojo = mFilteredList.get(position);

                Intent intent = new Intent(mContext, ActivityImageMapping.class);
                intent.putExtra("companyname1", companyname1);
                intent.putExtra("companyname2", companyPojo.getCompanyName());

                intent.putExtra("hall1", hall);
                intent.putExtra("hall2", companyPojo.getMhallno());

                intent.putExtra("stallno1",stallno);
                intent.putExtra("stallno2", companyPojo.getStallno());


                mContext.startActivity(intent);
                // ((Activity)mContext).finish();
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

        TextView mTvCompanyName, mTvHallName;

        public DataHolder(View itemView) {
            super(itemView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            mTvCompanyName = (TextView) itemView.findViewById(R.id.tvcompanyname);
            mTvHallName = (TextView) itemView.findViewById(R.id.tvhallname);

        }
    }

    public static void hideKeyboard(Context ctx) {
        InputMethodManager inputManager = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View v = ((CompanyListTwoActivity) ctx).getCurrentFocus();
        if (v == null)
            return;

        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}
