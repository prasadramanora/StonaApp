package com.ramanora.stona.fragments;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.ramanora.stona.R;
import com.ramanora.stona.bean.AZExhibitorListPojo;
import com.ramanora.stona.database.DatabaseAccess;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HallAboutUs extends Fragment {

    private ArrayList<AZExhibitorListPojo> listprod;


    public HallAboutUs() {
        // Required empty public constructor
    }

    private TextView mCompanyname,azcontactpersonname,azcompanyprofile,aztelephone,azdesignamtion,azcompanynameinfo;
    private TextView mfirstname,azmobile,mdesignation,mcity,mcountry,memail,mwebsite;
    private ClipboardManager myClipboard;
    private ClipData myClip;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.hall_about_us, null);


        azcontactpersonname= (TextView) view.findViewById(R.id.azcontactpersonname);
        azcompanyprofile = (TextView) view.findViewById(R.id.azcompanyprofile);
        aztelephone= (TextView) view.findViewById(R.id.aztelephone);
        azdesignamtion= (TextView) view.findViewById(R.id.azdesignamtion);
        azcompanynameinfo= (TextView) view.findViewById(R.id.azcompanynameinfo);
        mCompanyname = (TextView) view.findViewById(R.id.azcompanyname);
        azmobile = (TextView) view.findViewById(R.id.azmobile);
      //  mfirstname = (TextView) view.findViewById(R.id.azfirstnaem);
        //mdesignation = (TextView) view.findViewById(R.id.azdesignation);
        mcity = (TextView) view.findViewById(R.id.azcity);
        mcountry = (TextView) view.findViewById(R.id.azcountry);
        memail = (TextView) view.findViewById(R.id.azemail);
        mwebsite = (TextView) view.findViewById(R.id.azwebsite);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        Bundle bundle2 = getArguments();

        String companyname = bundle2.getString("companyname");
        // String products = listprod.get(0).getProdcut();

        System.out.println("company name : " + companyname);


        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());
        databaseAccess.open();


        listprod = databaseAccess.getAllDatalist(companyname);

        databaseAccess.close();




       // String firstname = listprod.get(0).getFirstname();
        //String designation = listprod.get(0).getDesignation();
        String city = listprod.get(0).getCity();
        String country = listprod.get(0).getCountry();
        final String email = listprod.get(0).getEmail();
        String website = listprod.get(0).getWebsite();



        azmobile.setText("Mobile No : "+listprod.get(0).getMobile());
        azcompanynameinfo.setText("Company Name :"+listprod.get(0).getCompanyName());
        azdesignamtion.setText("Designamtion : "+listprod.get(0).getDesignation());
        aztelephone.setText("Telephone : "+listprod.get(0).getPhone());
        azcompanyprofile.setText("CompanyProfile : "+listprod.get(0).getCompanyprofie());
        azcontactpersonname.setText("Conatact Person Name : "+listprod.get(0).getFirstname());
       /* System.out.println("companyname"+CompanyName);
        System.out.println("firstname"+firstname);*/
      //  mCompanyProduct.setText(CompanyName);
      //  mfirstname.setText("First Name : "+firstname);
        //mdesignation.setText("Designation : "+designation);
        mcity.setText("City :"+city);
        mcountry.setText("Country : "+ country);
        memail.setText("Email :"+email);
        mwebsite.setText("Website : "+website);




        memail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] TO = {email};
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL,TO);
                startActivity(Intent.createChooser(intent, "Select your Email Provider :"));
            }
        });

        return view;


    }

}
