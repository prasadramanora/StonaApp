package com.ramanora.stona.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ramanora.stona.R;
import com.ramanora.stona.bean.AZExhibitorListPojo;

/**
 * A simple {@link Fragment} subclass.
 */
public class AZNewLaunchesFragment extends Fragment {

    TextView mCompanyProduct;
    AZExhibitorListPojo azExhibitorListPojo;
    String companyname;

    public AZNewLaunchesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.az_new_launches, null);

        mCompanyProduct = (TextView) view.findViewById(R.id.aznewlauches);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        Bundle bundle2 = getArguments();
        AZExhibitorListPojo azExhibitorListPojo = (AZExhibitorListPojo) bundle2.getSerializable("companyname");
        // int position=bundle2.getInt("companyname");
        // String companyname =AZFragment.mArrayAZ.get(position).getCompanyName();


        String CompanyName = azExhibitorListPojo.getCompanyName();
        String newlaunches = azExhibitorListPojo.getNewlauches();
        String website = azExhibitorListPojo.getWebsite();
        String Phone = azExhibitorListPojo.getPhone();
        String Mobile = azExhibitorListPojo.getMobile();
        String Email = azExhibitorListPojo.getEmail();

        System.out.println("companyname" + CompanyName);
        System.out.println("newlaunches" + newlaunches);


            if (newlaunches.equalsIgnoreCase("null") || newlaunches==null || newlaunches.equalsIgnoreCase("") )
        {

            mCompanyProduct.setText("To know more connect with us. "+ "\n\n" + "" +  website  +"\n \n " + Phone +"\n \n " + Mobile +"\n \n " + Email) ;

        }
        else
        {
          //  mCompanyProduct.setText(CompanyName);
            String[]productlist = newlaunches.split(",");
            System.out.println("length"+productlist.length);

            String temp="";


            for(int i=0;i<productlist.length;i++){
                temp=temp+productlist[i].toString()+" "+"\n\n";
                mCompanyProduct.setText(temp);



            }
        }



        newlaunches.replaceAll(",","\n");




        return view;


    }
}

