package com.ramanora.stona.activities;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ramanora.stona.R;
import com.ramanora.stona.utils.UrlConstants;
import com.ramanora.stona.utils.Utils;

public class FeedbackFormActivity extends AppCompatActivity {

    WebView webviewProducts;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_form);

        webviewProducts = (WebView) findViewById(R.id.webviewfeedbackform);
        WebSettings webSettings = webviewProducts.getSettings();

        progressBar = (ProgressBar) findViewById(R.id.awv_progressBar);
        //swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        //LoadWeb();

        webviewProducts.setWebViewClient(new WebViewClient());
        webviewProducts.loadUrl(UrlConstants.URL_SOCIAL_Feedbackform);


        // webviewProducts.loadUrl("http://www.bfwindia.com/products");


       /* webviewProducts.setWebViewClient(new WebViewClient() {

            // This method will be triggered when the Page Started Loading

          *//*  public void onPageStarted(WebView view, String url, Bitmap favicon) {
                dialog = ProgressDialog.show(QRCodeGeneratorActivity.this, null,
                        "Please Wait...Page is Loading...");
                dialog.setCancelable(true);
                super.onPageStarted(view, url, favicon);
            }

            // This method will be triggered when the Page loading is completed

            public void onPageFinished(WebView view, String url) {
                dialog.dismiss();
                super.onPageFinished(view, url);
            }
*//*
            // This method will be triggered when error page appear

            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                dialog.dismiss();
                // You can redirect to your own page instead getting the default
                // error page
                Toast.makeText(FeedbackFormActivity.this,
                        "The Requested Page Does Not Exist", Toast.LENGTH_SHORT).show();
                webviewProducts.loadUrl(UrlConstants.URL_SOCIAL_Feedbackform);
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });


        webviewProducts.loadUrl(UrlConstants.URL_SOCIAL_Feedbackform);
        webviewProducts.getSettings().setLoadWithOverviewMode(true);
        webviewProducts.getSettings().setUseWideViewPort(true);
        WebSettings webSettings = webviewProducts.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setCacheMode( WebSettings.LOAD_DEFAULT );*/
//        getWindow().requestFeature(Window.FEATURE_PROGRESS);
       // setContentView(R.layout.main );
        // Makes Progress bar Visible
       /* getWindow().setFeatureInt( Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);

       // webview = (WebView) findViewById(R.id.webview);
        webviewProducts.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress)
            {
                //Make the bar disappear after URL is loaded, and changes string to Loading...
                setTitle("Loading...");
                setProgress(progress * 100); //Make the bar disappear after URL is loaded

                // Return the app name after finish loading
                if(progress == 100)
                    setTitle(R.string.app_name);
            }
        });
       // webviewProducts.setWebViewClient(new HelloWebViewClient());
        webviewProducts.getSettings().setJavaScriptEnabled(true);
        webviewProducts.loadUrl(UrlConstants.URL_SOCIAL_Feedbackform);
        if (!Utils.isNetworkAvailable(FeedbackFormActivity.this)) {
            webSettings.setCacheMode( WebSettings.LOAD_CACHE_ELSE_NETWORK );
        }*/

    }

    public class WebViewClient extends android.webkit.WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            // TODO Auto-generated method stub
            view.loadUrl(url);
            return true;
        }
        @Override
        public void onPageFinished(WebView view, String url) {

            // TODO Auto-generated method stub

            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);

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
