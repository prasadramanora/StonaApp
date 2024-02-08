package com.ramanora.stona.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ramanora.stona.R;

public class SeminarClass1 extends AppCompatActivity {

TextView tv_date,tv_time,tv_orgnise,tv_guestname,tv_guestnamemore,tv_guestnamemore1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminardescription);
        tv_date=findViewById(R.id.tv_date);
        tv_time=findViewById(R.id.tv_time);
        tv_orgnise=findViewById(R.id.tv_orgnize);
        tv_guestname=findViewById(R.id.tv_guestname);
        tv_guestnamemore=findViewById(R.id.tv_guestnamemore);
        tv_guestnamemore1=findViewById(R.id.tv_guestnamemore1);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String Seminar = extras.getString("Seminar");

            if(Seminar.equals("seminar1"))
            {
                tv_date.setText("Seminar Date : 15 February 2022");
                tv_time.setText("Seminar Time : 10.30pm");
                tv_orgnise.setText("Invitation Inauguration");
                tv_guestname.setText("Presidant FIGCE : Sri.Achar Hallapa Bassapa(Hon'ble Minister for Mines and Geology goverment of Karnataka");

                tv_guestnamemore.setText("Presidant FIGCE : Ishwinder Sing");
                tv_guestnamemore1.setText("Chairman Stona2023 : Manoj kumar Singh");
            }else  if(Seminar.equals("seminar2"))
            {
                tv_date.setText("Seminar Date : 16 February 2022");
                tv_time.setText("Seminar Time : 10.30pm");
                tv_orgnise.setText("Seminar on : Sustanable Qurrying of Natural Stones &Panel Discussion");
                tv_guestname.setText("Moderator : Sri. N Ashwat Narayan(Environment Consultant)");

                tv_guestnamemore.setText("Vote of Thanks : Sri.Guru Shastrimath(Chairman Seminar and Confrence Sub Committee.  ");
                tv_guestnamemore1.setText("Chairman Stona2023 : Manoj kumar Singh");
            }else  if(Seminar.equals("seminar3"))
            {
                tv_date.setText("Seminar Date : 17 February 2022");
                tv_time.setText("Seminar Time : 10.30pm");
                tv_orgnise.setText("Architectural Seminar on Sustainable use of Natural Stones in Architecture." );
                tv_guestname.setText("Speakers : Sri. Anil Taneja(LITOSonline.com, Spain");
                tv_guestnamemore.setText("Presentation by Sri S Vinay(Vice President FKGQSI");
                tv_guestnamemore1.setText("Chairman Stona2023 : Manoj kumar Singh");
            }else {
                tv_date.setText("Seminar Date : 18 February 2022");
                tv_time.setText("Seminar Time : 10.30pm");
                tv_orgnise.setText("Valedictory Function");
                tv_guestname.setText("Chief Guest : Sri.Basavaraj Bommai");
                tv_guestnamemore.setText("Fellcistation to sponcers  : Sri rajiv Arora(Chairman The Rajastan small industries corporation Limited  ");
                tv_guestnamemore1.setText("Chairman Stona2023 : Manoj kumar Singh");
            }
            //The key argument here must match that used in the other activity
        }

    }
}