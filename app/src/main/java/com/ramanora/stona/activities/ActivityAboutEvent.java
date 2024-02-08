package com.ramanora.stona.activities;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ramanora.stona.R;
import com.ramanora.stona.utils.UrlConstants;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityAboutEvent extends AppCompatActivity {
/*    private JustifiedTextView myMsg;*/


    TextView txtRead;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutevent_lay);

        txtRead = findViewById(R.id.txtreadMore);

        txtRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(UrlConstants.URL_STONA_FEGSI));
                startActivity(intent);
            }
        });
    }
}

