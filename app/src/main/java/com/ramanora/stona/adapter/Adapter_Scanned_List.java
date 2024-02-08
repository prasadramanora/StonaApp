package com.ramanora.stona.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.ramanora.stona.R;
import com.ramanora.stona.bean.Qrcode;

import java.util.ArrayList;

/**
 * Created by admin on 11/16/2017.
 */

public class Adapter_Scanned_List extends RecyclerView.Adapter<Adapter_Scanned_List.ViewHolder> {
    Context mContext;
    private ArrayList<Qrcode> mArrayListScannedInfo;


    public Adapter_Scanned_List(Context mContext, ArrayList<Qrcode> mArrayListScannedInfo) {
        this.mContext = mContext;
        this.mArrayListScannedInfo = mArrayListScannedInfo;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.adpter_listview_scanned_info, parent, false);
        ViewHolder dataHolder = new ViewHolder(view);
        return dataHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Qrcode qrcode = mArrayListScannedInfo.get(position);

        holder.mtxtPersonName.setVisibility(View.VISIBLE);
        holder.mtxtPersonName.setText("Regisration No : " + qrcode.getRegistrationno() + "\n" +
                "Name : " + qrcode.getName() + "\n" +
                "Designation : " + qrcode.getDesignation() + "\n" +
                "Company : " + qrcode.getCompany() + "\n" +
                "Country : " + qrcode.getCountry() + "\n" +
                "Email Id  : " + qrcode.getEmail() + "\n" +
                "Contact No : " + qrcode.getPhone()
        );
        Log.d("Country","Country "+qrcode.getCountry());




     /*   if (qrcode.getName().equalsIgnoreCase("null") || qrcode.getName() == null || qrcode.getName().equalsIgnoreCase(""))
        {

            //System.out.println(qrcode.getName());
        }
        else {
            holder.mtxtPersonName.setVisibility(View.VISIBLE);
            holder.mtxtPersonName.setText("Name : " + qrcode.getName());
        }

        if (qrcode.getEmail().equalsIgnoreCase("null") || qrcode.getEmail() == null || qrcode.getEmail().equalsIgnoreCase(""))
        {
          //  System.out.println(qrcode.getEmail());
        }
        else {
            holder.mtxtPersonEmail.setVisibility(View.VISIBLE);
            holder.mtxtPersonEmail.setText("Email : " + qrcode.getEmail());
        }

        if (qrcode.getCompany().equalsIgnoreCase("null") || qrcode.getCompany() == null || qrcode.getCompany().equalsIgnoreCase(""))
        {
            //nothing dispay
        }
        else {
            holder.mtxtPersonComapny.setVisibility(View.VISIBLE);
            holder.mtxtPersonComapny.setText("Company : " + qrcode.getCompany());
        }

        if (qrcode.getPhone().equalsIgnoreCase("null") || qrcode.getPhone() == null || qrcode.getPhone().equalsIgnoreCase(""))
        {
            //nothing dispay
        }
        else {
            holder.mtxtPersonPhone.setVisibility(View.VISIBLE);
            holder.mtxtPersonPhone.setText("Phone : " + qrcode.getPhone());
        }

        if (qrcode.getDesignation().equalsIgnoreCase("null") || qrcode.getDesignation() == null || qrcode.getDesignation().equalsIgnoreCase(""))
        {
            //nothing dispay
        }
        else {
            holder.mtxtPersondesignation.setVisibility(View.VISIBLE);
            holder.mtxtPersondesignation.setText("Designation :" + qrcode.getDesignation().trim());
        }

        if (qrcode.getCountry().equalsIgnoreCase("null") || qrcode.getCountry() == null || qrcode.getCountry().equalsIgnoreCase(""))
        {
            //nothing dispay
        }
        else {
            holder.mtxtPersoncountry.setVisibility(View.VISIBLE);
            holder.mtxtPersoncountry.setText("Country :" + qrcode.getCountry().trim());
        }

        if (qrcode.getCity().equalsIgnoreCase("null") || qrcode.getCity() == null || qrcode.getCity().equalsIgnoreCase(""))
        {
            //nothing dispay
        }
        else {
            holder.mtxtPersoncity.setVisibility(View.VISIBLE);
            holder.mtxtPersoncity.setText("City :" + qrcode.getCity().trim());
        }

        if (qrcode.getTypeofvisitor().equalsIgnoreCase("null") || qrcode.getTypeofvisitor() == null || qrcode.getTypeofvisitor().equalsIgnoreCase(""))
        {
            //nothing dispay
        }
        else {

            holder.mtxtPersontypeofvisitor.setVisibility(View.VISIBLE);
            holder.mtxtPersontypeofvisitor.setText("Type of Visitor : " + qrcode.getTypeofvisitor().trim());
        }


        if (qrcode.getOther().equalsIgnoreCase("null") || qrcode.getOther() == null || qrcode.getOther().equalsIgnoreCase(""))
        {
            //nothing dispay
        }
        else {
            holder.mtxtPersonother.setVisibility(View.VISIBLE);
            holder.mtxtPersonother.setText("Other : " + qrcode.getOther().trim());
        }*/


    }

    @Override
    public int getItemCount() {
        return mArrayListScannedInfo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView  mtxtPersonName, mtxtPersonEmail, mtxtPersonComapny, mtxtPersonPhone,
                  mtxtPersondesignation, mtxtPersoncountry, mtxtPersoncity, mtxtPersontypeofvisitor, mtxtPersonother ;


        public ViewHolder(View itemView) {
            super(itemView);

            mtxtPersonName = (TextView) itemView.findViewById(R.id.personName);
            mtxtPersonEmail = (TextView) itemView.findViewById(R.id.personEmail);
            mtxtPersonComapny = (TextView) itemView.findViewById(R.id.personCompany);
            mtxtPersonPhone = itemView.findViewById(R.id.personMobile);
            mtxtPersondesignation = itemView.findViewById(R.id.personDesignation);
            mtxtPersoncountry = itemView.findViewById(R.id.personCountry);
            mtxtPersoncity = itemView.findViewById(R.id.personCity);
            mtxtPersontypeofvisitor = itemView.findViewById(R.id.personTypeofvisitor);
            mtxtPersonother = itemView.findViewById(R.id.personOther);



        }
    }



}
