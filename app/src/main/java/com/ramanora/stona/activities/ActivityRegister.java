package com.ramanora.stona.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import com.google.firebase.iid.FirebaseInstanceId;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.ramanora.stona.R;
import com.ramanora.stona.utils.AppStatus;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ActivityRegister extends AppCompatActivity {

    private static final String TAG = "VisitorRegActivity";
    RelativeLayout relativeLayout;
    private EditText mEdtVFullName,mEdtVEmail,mEdtVPhone,mEdtVCompany,mEdtVCity,mEdtVOther,mEdtDesignation,mEdtVCountryCode,mEdtPassword, edtConformpassword;
    String str_QrCode,str_mrandmiss,str_Name,/*str_FullName,*/ str_Email, str_CountryCode, str_Phone, str_FullPhone,
           str_Company, str_City, str_Other, str_Designation, str_Country, str_Typevisitor, str_Password, str_conform_password/*, str_token*/;
    private Button mBtnVRegister;
    Spinner mSpnRegion,mSpnCountry,mSpnTypeVisitor,mSpnNameVisitor;
    Bitmap bitmap;
    public final static int QRcodeWidth = 500 ;
    int Rnumber;
    Random Number;
    String randamNo;
    //private CheckBox mChkV1_1,mChkV1_2,mChkV1_3,mChkV1_4,mChkV2_1,mChkV2_2,mChkV2_3;

    //region
    String SpinnerRegionValue;
    String[] SPINNERREGION={"None","Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chhattisgarh","Dadra and Nagar Haveli","Daman and Diu"
            ,"Delhi","Goa","Gujarat","Haryana","Himachal Pradesh","Jammu and Kashmir","Jharkhand","Karnataka","Kerala","Madhya Pradesh"
            , "Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Orissa","Punjab","Pondicherry","Rajasthan","Sikkim" ,
            "Tamil Nadu","Tripura","Uttar Pradesh","Uttarakhand","West Bengal","Other"
    };

    //country

    String spinnerCountryValue;
    String[] mcountryNames={"Select Country","India","China","Australia","Portugal",
            "New Zealand","Austria","Belgium","Canada",
            "Finland","France","Germany","Italy","Japan",
            "Romania","Russia","Singapore","South Africa",
            "SouthKorea","Spain","Srilanka","Turkey","UAE","UK","USA","Other"};

    //country code arraylist

    ArrayList<String>mCountryCode=new ArrayList<String>();

    //visitor type

    String spinnerTypeVisitorValue;
    String[] mvisitorTypes={"Select Visitor Type",
            "Architects ",
            "Hoteliers",
            "Builders & Real Estate Promoters",
            "Town Planners",
            "Decorators & Designers",
            "Interior Designers",
            "Stone Quarry Owners",
            "Stone Processors",
            "Importers & Exporters",
            "Traders",
            "Transporters of Stone Products","All in all, every market leader in the Natural Stone Industry"
    };

    //Mr & Miss

    String SpinnerNameValue;
    String[] visitorName={"Mr","Mrs","Miss"};
    String CountryCode="";
    RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        StrictMode.ThreadPolicy old = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(old)
                .permitDiskWrites()
                .build());

        StrictMode.setThreadPolicy(old);
        mCountryCode.add("");
        mCountryCode.add("+91");
        mCountryCode.add("+86");
        mCountryCode.add("+61");
        mCountryCode.add("+351");
        mCountryCode.add("+64");
        mCountryCode.add("+43");
        mCountryCode.add("+32");
        mCountryCode.add("+1");
        mCountryCode.add("+358");
        mCountryCode.add("+33");
        mCountryCode.add("+49");
        mCountryCode.add("+39");
        mCountryCode.add("+81");
        mCountryCode.add("+40");
        mCountryCode.add("+7");
        mCountryCode.add("+65");
        mCountryCode.add("+27");
        mCountryCode.add("+82");
        mCountryCode.add("+34");
        mCountryCode.add("+94");
        mCountryCode.add("+90");
        mCountryCode.add("+971");
        mCountryCode.add("+44");
        mCountryCode.add("+1");
        mCountryCode.add("");
        init();



        mBtnVRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signup();

            }
        });

        mRequestQueue = Volley.newRequestQueue(ActivityRegister.this);
        relativeLayout= (RelativeLayout) findViewById(R.id.regid);

        //country spinner

        ArrayAdapter<String> adaptercountry = new ArrayAdapter<String>(ActivityRegister.this, R.layout.spinner_item, mcountryNames);

        mSpnCountry.setAdapter(adaptercountry);
        adaptercountry.setDropDownViewResource(R.layout.spinner_dropdown_item);


        mSpnCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                String s= parent.getItemAtPosition(position).toString();
                System.out.println("asass"+s);

                if (mCountryCode.size()>0)
                {
                    CountryCode=mCountryCode.get(position).toString();
                    System.out.println("Country code "+CountryCode);
                    mEdtVCountryCode.setText(CountryCode);
                }
                spinnerCountryValue = (String) mSpnCountry.getSelectedItem();
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        //type of visitor

        ArrayAdapter<String> adaptervisitor = new ArrayAdapter<String>(ActivityRegister.this, R.layout.spinner_item, mvisitorTypes);

        mSpnTypeVisitor.setAdapter(adaptervisitor);

        adaptervisitor.setDropDownViewResource(R.layout.spinner_dropdown_item);

        mSpnTypeVisitor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                spinnerTypeVisitorValue = (String) mSpnTypeVisitor.getSelectedItem();
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        ArrayAdapter<String> adapterName = new ArrayAdapter<String>(ActivityRegister.this, R.layout.spinner_item, visitorName);

        mSpnNameVisitor.setAdapter(adapterName);

        adapterName.setDropDownViewResource(R.layout.spinner_dropdown_item);

        mSpnNameVisitor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                SpinnerNameValue = (String) mSpnNameVisitor.getSelectedItem();
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

    }


    private void init() {

        //EditText initilization
        mEdtVFullName= (EditText) findViewById(R.id.edtVfullname);
        mEdtVEmail= (EditText) findViewById(R.id.edtVEmail);

        mEdtVCompany= (EditText) findViewById(R.id.edtVCompanyname);
        mEdtVCity= (EditText) findViewById(R.id.edtVcity);
        mEdtVOther= (EditText) findViewById(R.id.edtVOther);

        //  mSpnRegion= (Spinner) findViewById(R.id.spn_region);
        mSpnCountry = (Spinner) findViewById(R.id.spn_country);
        mEdtVPhone= (EditText) findViewById(R.id.edtVPhone);
        mSpnTypeVisitor = (Spinner) findViewById(R.id.spn_visitor);
        mEdtDesignation = (EditText) findViewById(R.id.edtVDesignation);
        mSpnNameVisitor = (Spinner) findViewById(R.id.spn_Name);
        mEdtVCountryCode = (EditText) findViewById(R.id.edtVCountryCode);

        mEdtPassword = (EditText) findViewById(R.id.edtVpassword);
        edtConformpassword = (EditText) findViewById(R.id.edtConformpassword);

        //Button initilization
        mBtnVRegister= (Button) findViewById(R.id.btnVRegister);
    }

    //shared preferance for visitor

    public void sharedPref() {
        str_mrandmiss = mSpnNameVisitor.getSelectedItem().toString();
        str_Name = mEdtVFullName.getText().toString();
        //str_FullName = str_mrandmiss+ ". " + str_Name;
        str_Country = mSpnCountry.getSelectedItem().toString();
        str_CountryCode = mEdtVCountryCode.getText().toString();
        str_Phone = mEdtVPhone.getText().toString();
        str_FullPhone = str_CountryCode + str_Phone;
        str_Company = mEdtVCompany.getText().toString();
        str_City = mEdtVCity.getText().toString();
        str_Other = mEdtVOther.getText().toString();
        str_Typevisitor = mSpnTypeVisitor.getSelectedItem().toString();
        str_Designation = mEdtDesignation.getText().toString();
        str_Email = mEdtVEmail.getText().toString();
        str_Password = mEdtPassword.getText().toString();
       // str_conform_password = edtConformpassword.getText().toString();




      /*  Number = new Random();
        Rnumber = Number.nextInt(100);

     //   Rnumber = randamNo;

        Log.d("Randomnumber", "sharedPref: " + Rnumber);

        str_QrCode = Rnumber + "\t" + str_Name + "\t" + str_Designation + "\t" +  str_Company + "\t" + str_Email + "\t" + str_FullPhone;

        try {
            bitmap = TextToImageEncode(str_QrCode);
            //imageView.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }
        SharedPreferences.Editor editor = getSharedPreferences("myprefe", MODE_PRIVATE).edit();

        //    str_QrCode = mEdtVFullName.getText().toString()+ "\n" +mEdtVEmail.getText().toString()+"\n"+mEdtVPhone.getText().toString();
        editor.putString("name", str_Name);
        editor.putString("email", str_Email);
        editor.putString("country",str_Country);
        editor.putString("mobile", str_FullPhone);
        editor.putString("company", str_Company);
        editor.putString("city", str_City);
        editor.putString("other", str_Other);
        editor.putString("typevisitor", str_Typevisitor);
        editor.putString("designation", str_Designation);
        editor.putString("password", str_Password);


       // editor.putString("fcmtoken" , str_token);
        //   ActivitySplash.editor.putString("qrcode",str_QrCode);
        editor.putString("imagePreferance", encodeToBase64(bitmap));
        editor.putString("isUploaded", "No");
        editor.putString("loginTest1", "true");
        editor.commit();
*/


    }

    //validation

    public void signup() {
        Log.d(TAG, "Signup");





        if (AppStatus.getInstance(ActivityRegister.this).isOnline()) {

            sharedPref();
            SharedPreferences.Editor editor = getSharedPreferences("myprefe", MODE_PRIVATE).edit();

            editor.putString("isUploaded", "YES");
            if(mEdtVEmail.getText().toString().length()==0)
            {
                mEdtVEmail.setError("Enter This field");
                mEdtVEmail.requestFocus();
            }else if(mEdtVPhone.getText().toString().length()==0)
            {
                mEdtVPhone.setError("Enter This field");
                mEdtVPhone.requestFocus();
            }
            else  if(mEdtVFullName.getText().toString().length()==0)
            {
                mEdtVFullName.setError("Enter This field");
                mEdtVFullName.requestFocus();
            }else {
                postData();
            }


        } else {
            Toast.makeText(this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    public void onSignupSuccess() {
        mBtnVRegister.setEnabled(true);
        setResult(RESULT_OK, null);
    }

    public void onSignupFailed() {
       // Toast.makeText(getBaseContext(), "Please Fill the All Blank Fields", Toast.LENGTH_LONG).show();

        mBtnVRegister.setEnabled(true);
    }


    public boolean validate() {
        boolean valid = true;

        String name = mEdtVFullName.getText().toString();
        String email = mEdtVEmail.getText().toString();
        String phone = mEdtVPhone.getText().toString();
        String company = mEdtVCompany.getText().toString();
        String city = mEdtVCity.getText().toString();
        String passord = mEdtPassword.getText().toString();
        String cpassword = edtConformpassword.getText().toString();

//        String other = mEdtVOther.getText().toString();


        if (name.isEmpty()) {
            mEdtVFullName.setError("enter name");
            mEdtVFullName.requestFocus();
            valid = false;
        } else {
            mEdtVFullName.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEdtVEmail.setError("enter a valid email address");
            mEdtVEmail.requestFocus();
            valid = false;
        } else {
            mEdtVEmail.setError(null);
        }

        if (phone.isEmpty()  ||phone.length() < 8 || phone.length() >13) {
            mEdtVPhone.setError("enter valid mobile number");
            mEdtVPhone.requestFocus();
            valid = false;
        } else {
            mEdtVPhone.setError(null);
        }
 /*       if (passord.equalsIgnoreCase(cpassword)) {
            mEdtPassword.setError("Enter  ");
            valid = false;
        } else {
            mEdtPassword.setError(null);
        }*/



      /*  if (company.isEmpty()) {
            mEdtVCompany.setError("enter a company name ");
            valid = false;
        } else {
            mEdtVCompany.setError(null);
        }

        if (city.isEmpty()) {
            mEdtVCity.setError("enter a city  ");
            valid = false;
        } else {
            mEdtVCity.setError(null);
        }*/

        return valid;
    }

    //post the visitor data

    public void postData() {

        String url = "https://myexpos.in/stona/registration.php";

        final  String name=mEdtVFullName.getText().toString().trim();
        final  String email =mEdtVEmail.getText().toString().trim();
        final  String phone = mEdtVPhone.getText().toString().trim();
        final  String company = mEdtVCompany.getText().toString().trim();
        final  String city = mEdtVCity.getText().toString().trim();
        //  final String region=mSpnRegion.getSelectedItem().toString();
        final  String country= mSpnCountry.getSelectedItem().toString();
        final String typevisitor = mSpnTypeVisitor.getSelectedItem().toString();
        final String designation = mEdtDesignation.getText().toString().trim();
      //  final String mrandmiss = mSpnNameVisitor.getSelectedItem().toString();
        final  String other= mEdtVOther.getText().toString().trim();
        final String countrycode = mEdtVCountryCode.getText().toString().trim();
        final String password = mEdtPassword.getText().toString().trim();

        SharedPreferences sharedpreferences;

        sharedpreferences = getSharedPreferences(ActivityMainHomePage.MyPREFERENCES,
                Context.MODE_PRIVATE);



        String str_Token = "";
        Log.d("Token : ", "fcmtoken : " + str_Token);
        final String fullPhone = countrycode + phone;

        final String FullName = name;


        //volley code

        final ProgressDialog progressDialog = new ProgressDialog(ActivityRegister.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();
        new CountDownTimer(8000, 8000) {
           @Override
            public void onTick(long millisUntilFinished) {
                progressDialog.show();

            }

            @Override
            public void onFinish() {
               try {
                   progressDialog.dismiss();
               }catch (Exception e)
               {
                   e.printStackTrace();
               }

            }
        }.start();
Log.e("urlresponce",url);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    //    progressDialog.dismiss();
                        try {

                            JSONObject jsosnOBJ = new JSONObject(response);
                            Log.e("urlresponce",response);
                           String status= jsosnOBJ.getString("status");
                           Log.d("status","stausData"+response);
                           String msg= jsosnOBJ.getString("message");

                            String randomid = jsosnOBJ.getString("randomid");
                            SharedPreferences sharedpreferences;

                            sharedpreferences = getSharedPreferences(ActivityMainHomePage.MyPREFERENCES,
                                    Context.MODE_PRIVATE);

                            randamNo = sharedpreferences.getString("randamNo",randomid);
                            if (status.equalsIgnoreCase("true")) {

                                 // Number = new Random();
    //    Rnumber = Number.nextInt(100);

     //   Rnumber = randamNo;

                                Log.d("Randomnumber", "sharedPref: " + randomid);

                                str_QrCode = randomid + "\t" + str_Name + "\t" + str_Designation + "\t" +  str_Company + "\t" + str_Country +"\t" + str_Email + "\t" + str_FullPhone;

                                try {
                                    bitmap = TextToImageEncode(str_QrCode);
                                    //imageView.setImageBitmap(bitmap);

                                } catch (WriterException e) {
                                    e.printStackTrace();
                                }
                                SharedPreferences.Editor editor = getSharedPreferences("myprefe", MODE_PRIVATE).edit();

                                //    str_QrCode = mEdtVFullName.getText().toString()+ "\n" +mEdtVEmail.getText().toString()+"\n"+mEdtVPhone.getText().toString();
                                editor.putString("name", str_Name);
                                editor.putString("email", str_Email);
                                editor.putString("country",str_Country);
                                Log.d("Randomnumber", "sharedPref:str_Country " + str_Country);

                                editor.putString("mobile", str_FullPhone);
                                editor.putString("company", str_Company);
                                editor.putString("city", str_City);
                                editor.putString("other", str_Other);
                                editor.putString("typevisitor", str_Typevisitor);
                                editor.putString("designation", str_Designation);

                                editor.putString("str_login_test", "LoginSucessfully");


                               // editor.putString("fcmtoken" , str_token);
                                //   ActivitySplash.editor.putString("qrcode",str_QrCode);
                                editor.putString("imagePreferance", encodeToBase64(bitmap));
                                editor.putString("isUploaded", "No");
                                editor.putString("loginTest1", "true");
                                Log.e("CheckBlock22","LoginValuestored");
                                editor.putString("password", str_Password);
                                editor.commit();
                              //  progressDialog.dismiss();


                                Intent intent=new Intent(ActivityRegister.this,ActivityMainHomePage.class);
                                startActivity(intent);
                                finish();
                            }
                                Log.d("test", "onResponse: " + randamNo);
                            Log.d("test", "onResponserandamNo: " + randamNo);


                            Log.d("test", "onResponse: " + jsosnOBJ);
                           Log.d("test", "onResponse: " + status);
                          /*  if (status.equalsIgnoreCase("true")){
                                Log.d("Stona", "getParams: ===>> " + status);

                             *//*   Intent intent=new Intent(ActivityRegister.this,ActivityMainHomePage.class);
                                startActivity(intent);
                                finish();*//*
                            }else{

                            }*/





                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       Toast.makeText(ActivityRegister.this, "No ", Toast.LENGTH_SHORT).show();
                        error.getMessage();
                        Log.d("error","cancelApi ");

                        progressDialog.dismiss();

                    }
                } )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("Name",FullName);
                params.put("emailid",email);
                params.put("Phone",fullPhone);
                params.put("Company",company);
                params.put("City",city);
                //  params.put("State",region);
                params.put("Country",country);
                params.put("Other",other);
                params.put("Type_of_visitor",typevisitor);
                params.put("Designation",designation);
                params.put("Token", str_Token);
                params.put("password", password);
                Log.e("urlresponce",params.toString());
                //     params.put("Equipment_Vehicles_Materialhandling",chkfinal);
                //     params.put("Material_Mixture_Plants_Machinery",chkfinal1);
                Log.d("Stona", "getParamm: ===>> " + params.toString());

                return params;

            }

        };
        mRequestQueue.add(stringRequest);
    }
    public static String encodeToBase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }


    Bitmap TextToImageEncode(String Value) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRcodeWidth, QRcodeWidth, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        getResources().getColor(R.color.QRCodeBlackColor):getResources().getColor(R.color.QRCodeWhiteColor);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }
}
