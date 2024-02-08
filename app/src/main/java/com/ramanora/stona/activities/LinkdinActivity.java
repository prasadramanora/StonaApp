package com.ramanora.stona.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.ramanora.stona.R;
import com.ramanora.stona.utils.UrlConstants;
import com.ramanora.stona.utils.Utils;

public class LinkdinActivity extends Activity {
    WebView webviewProducts;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_linkdin);

        webviewProducts = (WebView) findViewById(R.id.webviewlinkin);

        // webviewProducts.loadUrl("http://www.bfwindia.com/products");


        webviewProducts.setWebViewClient(new WebViewClient() {

            // This method will be triggered when the Page Started Loading

         /*   public void onPageStarted(WebView view, String url, Bitmap favicon) {
                dialog = ProgressDialog.show(LinkdinActivity.this, null,
                        "Please Wait...Page is Loading...");
                dialog.setCancelable(true);
                super.onPageStarted(view, url, favicon);
            }

            // This method will be triggered when the Page loading is completed

            public void onPageFinished(WebView view, String url) {
                dialog.dismiss();
                super.onPageFinished(view, url);
            }
*/
            // This method will be triggered when error page appear

            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                dialog.dismiss();
                // You can redirect to your own page instead getting the default
                // error page
                Toast.makeText(LinkdinActivity.this,
                        "The Requested Page Does Not Exist", Toast.LENGTH_SHORT).show();
                webviewProducts.loadUrl(UrlConstants.URL_SOCIAL_LINKEDIN);
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });


        webviewProducts.loadUrl(UrlConstants.URL_SOCIAL_LINKEDIN);
        webviewProducts.getSettings().setLoadWithOverviewMode(true);
        webviewProducts.getSettings().setUseWideViewPort(true);
        WebSettings webSettings = webviewProducts.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setCacheMode( WebSettings.LOAD_DEFAULT );

        if (!Utils.isNetworkAvailable(LinkdinActivity.this)) {
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
   /*     // Enable Javascript
        WebSettings webSettings = webviewProducts.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setCacheMode( WebSettings.LOAD_DEFAULT );

        if (!Utils.isNetworkAvailable(ActivityProducts.this)) {
            webSettings.setCacheMode( WebSettings.LOAD_CACHE_ELSE_NETWORK );
        }



        webviewProducts.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return false;
            }
        });
*/

}

