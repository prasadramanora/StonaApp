package com.ramanora.stona.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.ramanora.stona.R;
import com.ramanora.stona.bean.Qrcode;
import com.ramanora.stona.database.ExampleDBHelper;

import static com.google.zxing.integration.android.IntentIntegrator.PRODUCT_CODE_TYPES;

/**
 * Created by admin on 9/13/2017.
 */

public class ActivityQRCode extends AppCompatActivity {


 /*implements View.OnClickListener*/

    ImageView imageView;

    Thread thread;
    public final static int QRcodeWidth = 500;
    Bitmap bitmap;
    String str_qrCode;

    private ImageView scanBtn, BtnSave;
    ExampleDBHelper exampleDBHelper;
    Intent intent;
    IntentResult result;
    String resulT;
    TextView TxtName;
  //  private ShowcaseView showcaseView;
    private int counter = 0;
    SharedPreferences sharedpreferences;
    String isFirst = "";
    String txt1 = "<br/>Print your entry badges using QR code ";
    String txt2 = "Exchange your information with exhibitiors";
    String txt3 = "<br/>Scan QR code on machines, catalogue etc and get information";
    String txt = "<br/><br/><br/>&#8226;" + txt1 + "<br/>&#8226;" + txt2 + "<br/>&#8226;" + txt3;
    String txt4 = "<br/>View information of the scanned QR codes";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_qr_code);

        imageView = (ImageView) findViewById(R.id.imageView);
        scanBtn = (ImageView) findViewById(R.id.scan_button);
        BtnSave = (ImageView) findViewById(R.id.save1);
        TxtName = (TextView) findViewById(R.id.qrname);

        sharedpreferences = getSharedPreferences(ActivityMainHomePage.MyPREFERENCES,
                Context.MODE_PRIVATE);
        //for exhibitor
        //   str_qrCode = ActivitySplash.sh.getString("qrcode", null);

        isFirst = sharedpreferences.getString("isFirst", "");
        Log.d("test", "onStartCommand: isFirst " + isFirst);


    /*    if (isFirst.equalsIgnoreCase("")) {

            showcaseView = new ShowcaseView.Builder(this)
                    .setTarget(new ViewTarget(findViewById(R.id.imageView)))
                    .setOnClickListener(this)
                    .setContentTitle(Html.fromHtml(txt1 + txt2))

                    .setStyle(R.style.CustomShowcaseTheme4)

                    .build();
            showcaseView.setButtonText(getString(R.string.next));
            *//*RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(70,80,10,20);
            showcaseView.setButtonPosition(params);*//*

        }*/


        String imageS = ActivityMainHomePage.sh.getString("imagePreferance", null);
        String Name = ActivityMainHomePage.sh.getString("name", null);
        TxtName.setText(Name);


        Bitmap imageB = decodeToBase64(imageS);
        imageView.setImageBitmap(imageB);
        ActivityMainHomePage.editor.putString("loginTest", "true");

        exampleDBHelper = new ExampleDBHelper(this);

        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent(ActivityQRCode.this, ActivityFormat.class);

                startActivityForResult(intent, 1);

            }
        });


        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //    llSearch.setVisibility(View.GONE);
                /*IntentIntegrator integrator = new IntentIntegrator(ActivityQRCode.this);
                integrator.setPrompt("Scan a barcode or QRcode");
                integrator.setOrientationLocked(true);
                integrator.setBarcodeImageEnabled(true);
                integrator.setBeepEnabled(true);
                integrator.initiateScan();
*/

               // startActivity(new Intent(ActivityQRCode.this, ScannedBarcodeActivity.class));
                Intent intent = new Intent(ActivityQRCode.this,ScannedBarcodeActivity.class);
                startActivityForResult(intent,12);

            }
        });

    }

    private void setAlpha(float alpha, View... views) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            for (View view : views) {
                view.setAlpha(alpha);
            }
        }
    }


   /* @Override
    public void onClick(View v) {
        //scanBtn, BtnSave;
        switch (counter) {

            case 0:
                //Html.fromHtml(temp+"\n\n")
                showcaseView.setShowcase(new ViewTarget(scanBtn), true);
                showcaseView.setTarget(Target.NONE);


                showcaseView.setContentTitle(Html.fromHtml(txt3));

                showcaseView.setStyle(R.style.CustomShowcaseTheme4);
                showcaseView.setButtonText("Next");
                Log.d("test", "onClick: " + counter);
                //  setAlpha(0.4f, BtnSave);

                break;

            case 1:
                //Html.fromHtml(temp+"\n\n")
                showcaseView.setShowcase(new ViewTarget(BtnSave), true);
                showcaseView.setTarget(Target.NONE);


                showcaseView.setContentTitle(Html.fromHtml(txt4));
                showcaseView.setButtonText("Got It!");
                showcaseView.setStyle(R.style.CustomShowcaseTheme4);
                Log.d("test", "onClick: " + counter);
                //  setAlpha(0.4f, BtnSave);

                break;

            case 2:
                Log.d("test", "onClick: " + counter);
                showcaseView.setSingleShot(42);
                showcaseView.hide();
                // setAlpha(1.0f, scanBtn, BtnSave);
                ActivitySplash.editor.putString("isFirst", "No");
                ActivitySplash.editor.commit();
                break;

           *//* case 2:
                showcaseView.setTarget(Target.NONE);
                showcaseView.setContentTitle("Check it out");
                showcaseView.setContentText("You don't always need a target to showcase");
                showcaseView.setButtonText(getString(R.string.close));
                setAlpha(0.4f, textView1, textView2, textView3);
                break;
*//*
           *//* case 3:
                showcaseView.hide();
                setAlpha(1.0f, scanBtn, BtnSave);
                break;*//*
        }
        counter++;
    }
*/

    public static Bitmap decodeToBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (data == null)
        {
           // Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            startActivity(new Intent(ActivityQRCode.this,ActivityMainHomePage.class));

        }
       else if (requestCode ==12) {
           if (data!=null) {
               //  result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

               resulT = data.getStringExtra("intentData");
               Log.d("resulT", "resulT " + resulT);
               // if (resulT != null) {
/*
                if (resulT.equals("")) {

                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                } else {*/

/*
                String Registrationno = "", name = "", phone = "", email = "", company = "", deignation = "", country = "",
                        city = "", typeofvisitor = "", other = "";
*/

                    String Registrationno = "", name = "", deignation = "", company = "", country = "", email = "", phone = "",
                            city = "", typeofvisitor = "", other = "";

                    //     Log.d("test", "onActivityResultfinal: " + result.getContents());

                    String array[] = resulT.split("\t");
                    Qrcode qrcode = null;
                    for (int i = 0; i < array.length; i++) {
                        Log.d("test", "onActivityResult: " + i + "  " + array[i]);
                        Log.d("test", "onActivityResultArray: " + array.length);
                        qrcode = new Qrcode();
                        if (array.length == 1) {
                            if (i == 3)
                                company = array[i];
                            Log.d("test", "onActivityResultcompany: " + i + "  " + company);
                            qrcode.setCompany(company);
                        } else if (i == 0) {
                            Registrationno = array[i];
                            Log.d("test", "onActivityResult: " + i + "  " + Registrationno);
                            qrcode.setRegistrationno(Registrationno);
                        } else if (i == 1) {
                            name = array[i];
                            qrcode.setName(name);
                            Log.d("test", "onActivityResult: " + i + "  " + name);
                        } else if (i == 2) {
                            deignation = array[i];
                            Log.d("test", "onActivityResult: " + i + "  " + deignation);
                            qrcode.setDesignation(deignation);
                        } else if (i == 3) {
                            company = array[i];
                            Log.d("test", "onActivityResult: " + i + "  " + company);
                            qrcode.setCompany(company);

                        } else if (i == 4) {
                            country = array[i];
                            Log.d("test", "onActivityResultCountry: " + i + "  " + country);
                            qrcode.setCountry(country);

                        } else if (i == 5) {
                            email = array[i];
                            Log.d("test", "onActivityResult: " + i + "  " + email);
                            qrcode.setEmail(email);

                        } else if (i == 6) {
                            phone = array[i];
                            Log.d("test", "onActivityResult: " + i + "  " + phone);
                            qrcode.setPhone(phone);
                        }

/*
                    else if (i == 6) {
                        city = array[i];
                        Log.d("test", "onActivityResult: " + i + "  " +city);
                        qrcode.setCity(city);
                    }


                    else if (i == 7) {
                        typeofvisitor = array[i];
                        Log.d("test", "onActivityResult: " + i + "  " +typeofvisitor);
                        qrcode.setTypeofvisitor(typeofvisitor);
                    }


                    else if (i == 8) {
                        other = array[i];
                        Log.d("test", "onActivityResult: " + i + "  " +other);
                        qrcode.setOther(other);*/


                    }
                    exampleDBHelper.addScannedInfo(Registrationno, name, deignation, company, country, email, phone);
                }


        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}