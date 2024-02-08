package com.ramanora.stona.fragments;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.aztec.encoder.AztecCode;
import com.ramanora.stona.R;
import com.ramanora.stona.bean.AZExhibitorListPojo;

import static android.content.Context.CLIPBOARD_SERVICE;
import static android.support.v4.content.ContextCompat.getSystemService;


/**
 * A simple {@link Fragment} subclass.
 */
public class AZAboutUs extends Fragment {




    public AZAboutUs() {
        // Required empty public constructor
    }

    TextView mCompanyname;
    TextView mfirstname,azmobile,mdesignation,mcity,azcontactpersonname,azcompanyprofile,mcountry,memail,aztelephone,mwebsite,azcompanynameinfo,azdesignamtion;
    private ClipboardManager myClipboard;
    private ClipData myClip;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this


        View view = inflater.inflate(R.layout.az_about_us, null);
        mCompanyname = (TextView) view.findViewById(R.id.azcompanyname);
        azmobile= (TextView) view.findViewById(R.id.azmobile);
        azcontactpersonname= (TextView) view.findViewById(R.id.azcontactpersonname);
        azcompanyprofile = (TextView) view.findViewById(R.id.azcompanyprofile);
        aztelephone= (TextView) view.findViewById(R.id.aztelephone);
        azdesignamtion= (TextView) view.findViewById(R.id.azdesignamtion);
        azcompanynameinfo= (TextView) view.findViewById(R.id.azcompanynameinfo);
       // mfirstname = (TextView) view.findViewById(R.id.azfirstnaem);
        //mdesignation = (TextView) view.findViewById(R.id.azdesignation);
        mcity = (TextView) view.findViewById(R.id.azcity);

        mcountry = (TextView) view.findViewById(R.id.azcountry);
        memail = (TextView) view.findViewById(R.id.azemail);
        memail.setClickable(true);
        mwebsite = (TextView) view.findViewById(R.id.azwebsite);
        mwebsite.setClickable(true);
        /*mwebsite.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='http://www.google.com'> Google </a>";
        mwebsite.setText(Html.fromHtml(text));*/

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        Bundle bundle2=getArguments();
        AZExhibitorListPojo azExhibitorListPojo = (AZExhibitorListPojo) bundle2.getSerializable("companyname");
        // int position=bundle2.getInt("companyname");
        // String companyname =AZFragment.mArrayAZ.get(position).getCompanyName();

      //  String CompanyName = azExhibitorListPojo.getCompanyName();
        String firstname = azExhibitorListPojo.getFirstname();
        String designation = azExhibitorListPojo.getDesignation();
        String city = azExhibitorListPojo.getCity();
        String country = azExhibitorListPojo.getCountry();
        final String email = azExhibitorListPojo.getEmail();
        final String website = azExhibitorListPojo.getWebsite();
        azmobile.setText("Mobile No : "+azExhibitorListPojo.getMobile());
        azcompanynameinfo.setText("Company Name: "+azExhibitorListPojo.getCompanyName());
        azdesignamtion.setText("Designamtion :"+azExhibitorListPojo.getDesignation());
        aztelephone.setText("Telephone : "+azExhibitorListPojo.getPhone());
        azcompanyprofile.setText("CompanyProfile : "+azExhibitorListPojo.getCompanyprofie());
        azcontactpersonname.setText("Conatact Person Name :"+azExhibitorListPojo.getFirstname());
       /* System.out.println("companyname"+CompanyName);
        System.out.println("firstname"+firstname);*/
      //  mCompanyProduct.setText(CompanyName);
//        mfirstname.setText("First Name : "+firstname);
  //      mdesignation.setText("Designation : "+designation);
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


        mwebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setClipboard(getContext(),website);
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", website);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getContext(), "Website is Copy", Toast.LENGTH_SHORT).show();
            }

        });
        return view;



    }
    private void setClipboard(Context context, String text) {
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
            clipboard.setPrimaryClip(clip);


        }
    }

}
