package com.ramanora.stona.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramanora.stona.R;
import com.ramanora.stona.bean.AZExhibitorListPojo;

/**
 * Created by amolrokade on 14/11/17.
 */

public class AZCallusFragment extends Fragment {


    public AZCallusFragment() {
        // Required empty public constructor
    }

    String mob1;
    TextView txtmob2, txtmob1;
    String mob2;
    String[] s1;
    TextView azcompanyname,aznameofperson,azmobile;
    ImageView iv_mob2icon;
String CallMobile;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_callus, null);
        txtmob2 = (TextView) view.findViewById(R.id.txtmob2);
        txtmob1 = (TextView) view.findViewById(R.id.txtmob1);
        azmobile = (TextView) view.findViewById(R.id.azmobile);
        azcompanyname = (TextView) view.findViewById(R.id.azcompanyname);
        aznameofperson= (TextView) view.findViewById(R.id.aznameofperson);
        iv_mob2icon = (ImageView) view.findViewById(R.id.iv_mob2icon);


        Bundle bundle2 = getArguments();
        AZExhibitorListPojo azExhibitorListPojo = (AZExhibitorListPojo) bundle2.getSerializable("companyname");


        mob1 = azExhibitorListPojo.getPhone();
        try {
            aznameofperson.setText("Contact Person Name :" + azExhibitorListPojo.getFirstname());
            azcompanyname.setText("Name Of Company :" + azExhibitorListPojo.getCompanyName());
            String[] mobile1=azExhibitorListPojo.getMobile().split("/");
            CallMobile=mobile1[0];
            azmobile.setText("Mobile :" + mobile1[0]);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        //  mob2 = azExhibitorListPojo.getPhone();

        try {
            if (mob1.equalsIgnoreCase("") || mob1.equalsIgnoreCase(" ") || mob1.equalsIgnoreCase(null)) {
                txtmob1.setVisibility(View.GONE);
            } else if (mob1.contains("/")) {

                s1 = mob1.split("/");
                // Log.d("mobiles1","mobile no "+s1[1]);
                mob1 = s1[0];
                mob2 = s1[1];
                Log.d("mobile", "mobile nos " + mob1);

                txtmob1.setText("Tel :"+mob1);
                txtmob2.setText("Tel :"+mob2.trim());

                Log.d("mobile", "mobile no " + mob1);

            } else {
                txtmob1.setText("Tel :"+mob1);
                txtmob2.setVisibility(View.GONE);
                iv_mob2icon.setVisibility(View.GONE);
            }

            /*if (mob2.equalsIgnoreCase("") || mob2.equalsIgnoreCase(" ") || mob2.equalsIgnoreCase(null)) {
                txtmob2.setVisibility(View.GONE);
            } else {
                txtmob2.setText("Phone : " + mob2);
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
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
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+91"+CallMobile));
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


