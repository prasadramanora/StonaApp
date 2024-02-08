package com.ramanora.stona.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ramanora.stona.R;
import com.ramanora.stona.bean.AZExhibitorListPojo;


/**
 * Created by Owner on 09/10/2017.
 */

public class AZCompanyProduct extends Fragment {

    TextView mCompanyProduct, mCompanyName;

    public AZCompanyProduct() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.az_company_product, null);

        mCompanyProduct = (TextView) view.findViewById(R.id.azcompanyproduct);
        mCompanyName = (TextView) view.findViewById(R.id.azcompanyname);

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
        String products = azExhibitorListPojo.getProductname();

        System.out.println("companyname" + CompanyName);
        System.out.println("products111" + products);
        // mCompanyName.setText(CompanyName);
        mCompanyProduct.setText(products);

        String[] productlist = products.split(",");
        System.out.println("length" + productlist.length);

        String temp = "",temp1="";



        for (int i = 0; i < productlist.length; i++) {
            temp = temp + productlist[i].toString() + " " + "\n\n";
            mCompanyProduct.setText(temp);


        }

      /*  for (int i = 0; i < productlist.length; i++) {
            temp = temp + productlist[i].toString() + "\n\n" + "<br><font color='#33B5E5'>" +
                    " Catalog : http://www.sany.in/products/index.html" + " </font><br><br>\n\n";

// temp = temp + productlist[i].toString() + " " + "\n\n";
            mCompanyProduct.setText(Html.fromHtml(temp+"\n\n"));


        }
        mCompanyProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sany.in/products/index.html")));
            }
        });

*/
        products.replaceAll(",", "\n");


        return view;


    }
}
