package com.ramanora.stona.utils;

import android.app.ProgressDialog;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by abc on 19/01/2018.
 */

class MyWebViewClient extends WebViewClient {

    ProgressDialog pd;
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);

        if (!pd.isShowing()) {
            pd.show();
        }

        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        System.out.println("on finish");
        if (pd.isShowing()) {
            pd.dismiss();
        }

    }
}