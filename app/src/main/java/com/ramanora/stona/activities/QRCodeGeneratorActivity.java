package com.ramanora.stona.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.ramanora.stona.R;
import com.ramanora.stona.utils.Utils;

public class QRCodeGeneratorActivity extends Activity {
    WebView webviewProducts;
    ProgressDialog dialog;

    public static final String URL_QR_CODE_GENERATOR = "http://ebadge.exhibitionz.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_qr_code_genrator);

        webviewProducts = (WebView) findViewById(R.id.webviewfacebook);


        webviewProducts.setWebViewClient(new WebViewClient() {

            // This method will be triggered when the Page Started Loading


            // This method will be triggered when error page appear

            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                dialog.dismiss();
                // You can redirect to your own page instead getting the default
                // error page
                Toast.makeText(QRCodeGeneratorActivity.this,
                        "The Requested Page Does Not Exist", Toast.LENGTH_SHORT).show();
                webviewProducts.loadUrl(URL_QR_CODE_GENERATOR);
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });


        webviewProducts.loadUrl(URL_QR_CODE_GENERATOR);
        webviewProducts.getSettings().setLoadWithOverviewMode(true);
        webviewProducts.getSettings().setUseWideViewPort(true);
        WebSettings webSettings = webviewProducts.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setCacheMode( WebSettings.LOAD_DEFAULT );

        if (!Utils.isNetworkAvailable(QRCodeGeneratorActivity.this)) {
            webSettings.setCacheMode( WebSettings.LOAD_CACHE_ELSE_NETWORK );
        }

    }


    @Override
    public void onBackPressed()
    {
        if(webviewProducts.canGoBack())
            webviewProducts.goBack();
        else
            super.onBackPressed();
    }


}

