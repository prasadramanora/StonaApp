package com.ramanora.stona.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.ramanora.stona.R;
import com.ramanora.stona.bean.AZExhibitorListPojo;
import com.ramanora.stona.bean.HallExhibitorListPojo;
import com.ramanora.stona.database.DatabaseAccess;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HallNewLaunchesFragment extends Fragment {

    TextView txthallnewlauches;
    HallExhibitorListPojo azExhibitorListPojo;
    String companyname;
    private ArrayList<AZExhibitorListPojo> listprod;
    public HallNewLaunchesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.hall_new_launches, null);

        txthallnewlauches = (TextView) view.findViewById(R.id.hallnewlauches);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        Bundle bundle2 = getArguments();

        String companyname = bundle2.getString("companyname");
        // String products = azExhibitorListPojo.getProdcut();

        System.out.println("company name : " + companyname);


        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());
        databaseAccess.open();


        listprod = databaseAccess.getNewlauncheslist(companyname);



                databaseAccess.close();




        if(listprod.size()>0){
            System.out.println("mArrayListCompany company :" + listprod.get(0).getNewlauches());

            String newlaunch = listprod.get(0).getNewlauches();
            Log.d("test", "onCreateView: new launch "+newlaunch);

            String Phone = listprod.get(0).getPhone();
            Log.d("test", "onCreateView: phone "+Phone);

            String website = listprod.get(0).getWebsite();
            Log.d("test", "onCreateView: website "+website);


            String Mobile = listprod.get(0).getMobile();
            Log.d("test", "onCreateView: mobile "+ Mobile);



            String Email = listprod.get(0).getEmail();
            Log.d("test", "onCreateView: Email "+Email);


            if (newlaunch.equalsIgnoreCase("null") || newlaunch==null || newlaunch.equalsIgnoreCase("") ) {


                txthallnewlauches.setText("To know more connect with us. "+ "\n\n" + "" +  website  +"\n \n " + Phone +"\n \n " + Mobile +"\n \n " + Email) ;


            }
            else {
                txthallnewlauches.setText(newlaunch);

                String[] newlaunchlist = newlaunch.split(",");
                System.out.println("length" + newlaunchlist.length);

                String temp = "";


                for (int i = 0; i < newlaunchlist.length; i++) {
                    temp = temp + newlaunchlist[i].toString() + " " + "\n\n";
                    txthallnewlauches.setText(temp);
                }
            }
                newlaunch.replaceAll(",", "\n");
            }else{
                txthallnewlauches.setText("No data");
            }

        return view;


    }
}

