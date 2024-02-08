package com.ramanora.stona.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.ramanora.stona.R;
import com.ramanora.stona.utils.Utils;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentEventSchedule extends Fragment {

    WebView mWebViewEventSchedule;
    //ProgressDialog pd;



    public FragmentEventSchedule() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.news_frag_event_schedule, container, false);

        mWebViewEventSchedule = view.findViewById(R.id.webview);
        ProgressBar simpleProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
       // simpleProgressBar.setVisibility(View.VISIBLE);

     /*   pd = new ProgressDialog(getActivity());
        pd.setMessage("Please wait Loading...");
        pd.show();*/
//set visible
        mWebViewEventSchedule.loadUrl("https://drive.google.com/file/d/1w98F6g-1ci-V9ukaNX9mzPSiREvJcv9H/view");


        // Enable Javascript
        WebSettings webSettings = mWebViewEventSchedule.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setCacheMode( WebSettings.LOAD_DEFAULT );


        if (!Utils.isNetworkAvailable(getActivity()) ) {
            webSettings.setCacheMode( WebSettings.LOAD_CACHE_ELSE_NETWORK );
        }


        return view;
    }




}
