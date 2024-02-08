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
import com.ramanora.stona.database.DatabaseAccess;
import com.ramanora.stona.fragments.HallCompanyNameFragment;

import java.util.ArrayList;

/**
 * Created by Owner on 09/10/2017.
 */

public class HallMainAdapter extends RecyclerView.Adapter<HallMainAdapter.DataHolder> implements Filterable {

    private Context mContext;
    private ArrayList<AZExhibitorListPojo> mArrayListAZ;
    private ArrayList<AZExhibitorListPojo>  mArrayListAZ1;
    private ArrayList<AZExhibitorListPojo> mFilteredList;
    FragmentManager mFragment;

    public HallMainAdapter(Context mContext, ArrayList<AZExhibitorListPojo> mArrayListAZ) {
        this.mContext = mContext;
        this.mArrayListAZ = mArrayListAZ;
        this.mFilteredList = mArrayListAZ;


    }


    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.hall_exhibitor_hall_adapter, parent, false);
        DataHolder dataHolder = new DataHolder(view);
        return dataHolder;
    }

    @Override
    public void onBindViewHolder(final DataHolder holder, final int position) {

        AZExhibitorListPojo azExhibitorListPojo = mFilteredList.get(position);

        holder.mhallno.setText(azExhibitorListPojo.getMhallno());
        if(holder.mhallno.getText().toString().length()==0)
        {
            holder.mhallno.setVisibility(View.GONE);
        }else {
            holder.mhallno.setVisibility(View.VISIBLE);
        }
Log.e("mallNoCheck",holder.mhallno.getText().toString().length()+"");



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //AZExhibitorListPojo azExhibitorListPojo = mArrayListAZ1.get(position);

                AZExhibitorListPojo azExhibitorListPojo = mFilteredList.get(position);



                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(mContext);
                databaseAccess.open();

Log.e("hallNumber=",azExhibitorListPojo.getMhallno());
                mArrayListAZ1= databaseAccess.getHallAlllist(azExhibitorListPojo.getMhallno());



                System.out.println("mArrayListAZ1 company :"+mArrayListAZ1.size());



                Bundle bundle=new Bundle();

                bundle.putString("key",azExhibitorListPojo.getMhallno());


                HallCompanyNameFragment companyNameFragment = new HallCompanyNameFragment();
                companyNameFragment.setArguments(bundle);
                FragmentManager mFragment;
                mFragment = ((ActivityExhibitiorlistTab)mContext).getSupportFragmentManager();
                mFragment.beginTransaction().add(R.id.activityexhibitorlist,companyNameFragment).addToBackStack("").commit();
                databaseAccess.close();
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

                    mFilteredList = mArrayListAZ;
                } else {

                    ArrayList<AZExhibitorListPojo> filteredList = new ArrayList<>();

                    for (AZExhibitorListPojo azExhibitorListPojo : mArrayListAZ) {

                        if (azExhibitorListPojo.getMhallno().toLowerCase().contains(charString)
                                || azExhibitorListPojo.getMhallno().toUpperCase().contains(charString)) {

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

        TextView mTvAzExhibitorlist, mhallno;

        public DataHolder(View itemView) {
            super(itemView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

           // mTvAzExhibitorlist = (TextView) itemView.findViewById(R.id.tvazexhibitorlist);
            mhallno = (TextView) itemView.findViewById(R.id.tvhallno);


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
