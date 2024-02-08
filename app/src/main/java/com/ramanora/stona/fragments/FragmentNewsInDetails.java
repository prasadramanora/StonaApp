package com.ramanora.stona.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ramanora.stona.R;
import com.ramanora.stona.bean.NewsArticlesPojo;
import com.squareup.picasso.Picasso;


/**
 * Created by pragati on 1/9/17.
 */

public class FragmentNewsInDetails extends Fragment {

    private TextView mTxtTitle, mTxtDesc,mTxtComapanyName;
    private ImageView mImgCompany;
    View view;
    RelativeLayout relativeLayout;
    private ImageView mImgNewsDesc;
    Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_lay_news_in_details, null);


        relativeLayout = view.findViewById(R.id.fragId);
        //mTxtTitle = (TextView) view.findViewById(R.id.txtTitle);
        mTxtDesc = (TextView) view.findViewById(R.id.txtDesc);
        mImgNewsDesc=view.findViewById(R.id.imgNewsDesc);
        mTxtComapanyName=view.findViewById(R.id.TvCompanyNameDetails);
        mImgCompany=view.findViewById(R.id.imgCompanyDetails);


        Bundle bundle = getArguments();
        NewsArticlesPojo articlesPojo = (NewsArticlesPojo) bundle.getSerializable("Data");

//        mTxtTitle.setText( articlesPojo.getmTitle());
        mTxtDesc.setText( articlesPojo.getmDescription());
        mTxtComapanyName.setText(articlesPojo.getmCompanyName());



        Picasso.with(mContext).
                load(articlesPojo.getmImageOfCompany()).
                into(mImgCompany);

        Picasso.with(mContext).
                load(articlesPojo.getmNEwsimage()).
                into(mImgNewsDesc);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                Log.i("KeyCode", "keyCode: " + keyCode);
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    Log.i("On Back Press", "onKey Back listener is working!!!");
                    getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                }
                return false;
            }
        });

        return view;

    }

}

