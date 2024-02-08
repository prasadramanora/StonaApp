package com.ramanora.stona.fragments;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.ramanora.stona.R;
import com.ramanora.stona.bean.AZExhibitorListPojo;
import com.ramanora.stona.database.DatabaseAccess;

import java.util.ArrayList;

/**
 * Created by amolrokade on 14/11/17.
 */

public class CountryCallusFragment extends Fragment {

    private ArrayList<AZExhibitorListPojo> listprod;

    public CountryCallusFragment() {
        // Required empty public constructor
    }

    String mob1;
    TextView txtmob2, txtmob1, aznameofperson, azcompanyname, azmobile;
    String mob2;
    String[] s1;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    ImageView iv_mob2icon;
String CallMobileNumber;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_callus, null);
        txtmob2 = (TextView) view.findViewById(R.id.txtmob2);
        txtmob1 = (TextView) view.findViewById(R.id.txtmob1);
        iv_mob2icon = (ImageView) view.findViewById(R.id.iv_mob2icon);
        azmobile = (TextView) view.findViewById(R.id.azmobile);
        azcompanyname = (TextView) view.findViewById(R.id.azcompanyname);
        aznameofperson = (TextView) view.findViewById(R.id.aznameofperson);

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
        mob1 = listprod.get(0).getPhone();
        //   mob2 = listprod.get(0).getPhone();
        try {
            aznameofperson.setText("Contact Person Name :" + listprod.get(0).getFirstname());
            azcompanyname.setText("Name Of Company :" + listprod.get(0).getCompanyName());
            String[] mobile1=listprod.get(0).getMobile().split("/");
            CallMobileNumber=mobile1[0];
            azmobile.setText("Mobile :" + mobile1[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (mob1.equalsIgnoreCase("") || mob1.equalsIgnoreCase(" ") || mob1.equalsIgnoreCase(null)) {
                txtmob1.setVisibility(View.GONE);
            } else if (mob1.contains("/")) {
                s1 = mob1.split("/");
                mob1 = s1[0];
                mob2 = s1[1];
                txtmob1.setText(mob1);
                txtmob2.setText(mob2.trim());

            } else {
                txtmob1.setText(mob1);
                txtmob2.setVisibility(View.GONE);
                iv_mob2icon.setVisibility(View.GONE);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        /*if (mob2.equalsIgnoreCase("") || mob2.equalsIgnoreCase(" ") || mob2.equalsIgnoreCase(null)) {
            txtmob2.setVisibility(View.GONE);
        } else {
            txtmob2.setText("Phone : " + mob2);
        }*/


        txtmob1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mob1));
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {


                }
                getActivity().startActivity(intent);

            }
        });
        azmobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+91"+CallMobileNumber));
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {


                }
                getActivity().startActivity(intent);

            }
        });
        txtmob2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mob2));
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {


                }
                getActivity().startActivity(intent);

            }
        });


        return view;


    }

}

