package com.ramanora.stona.activities;

/**
 * Created by adExconmin on 9/18/2017.
 */

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.ramanora.stona.R;
import com.ramanora.stona.adapter.Adapter_Scanned_List;
import com.ramanora.stona.bean.Qrcode;
import com.ramanora.stona.database.ExampleDBHelper;
import com.ramanora.stona.services.SQLiteToExcel;
import com.ramanora.stona.utils.Utils;

import java.io.File;
import java.util.ArrayList;

public class ActivityFormat extends AppCompatActivity {



    ExampleDBHelper exampleDBHelper;
    Button mBtnExport;
    ArrayList<Qrcode> mArrayListQrCode;
    RecyclerView recyclerView;
    LinearLayoutManager manager;
    Adapter_Scanned_List adapter;

    public static final String DATABASE_NAME = "SQLiteExample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_scanqrcode_listview);


        mBtnExport = (Button) findViewById(R.id.exporttocsv);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        mArrayListQrCode = new ArrayList<>();

        exampleDBHelper = new ExampleDBHelper(this);

        mArrayListQrCode = exampleDBHelper.getAllqrcode();

        mBtnExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                String directory_path = Environment.getExternalStorageDirectory().getPath() + "/Stona/";
                File file = new File(directory_path);
                if (!file.exists()) {
                    file.mkdirs();
                }
                // Export SQLite DB as EXCEL FILE
                SQLiteToExcel sqliteToExcel = new SQLiteToExcel(getApplicationContext(), DATABASE_NAME, directory_path);
                sqliteToExcel.exportAllTables("Stona.xls", new SQLiteToExcel.ExportListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onCompleted(String filePath) {
                        Utils.showSnackBar(view, "Exported Successfully in Stona Folder");
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
            }

        });


/*        String[] columns = new String[]{
                ExampleDBHelper.PERSON_COLUMN_NAME,
                ExampleDBHelper.PERSON_COLUMN_EMAIL,
                ExampleDBHelper.PERSON_COLUMN_PHONE,
                ExampleDBHelper.PERSON_COLUMN_COMPANY,
                ExampleDBHelper.PERSON_COLUMN_DESIGNATION,
                ExampleDBHelper.PERSON_COLUMN_COUNTRY,
                ExampleDBHelper.PERSON_COLUMN_COUNTRY,
                ExampleDBHelper.PERSON_COLUMN_CITY,
                ExampleDBHelper.PERSON_COLUMN_TYPEOFVISITOR,
                ExampleDBHelper.PERSON_COLUMN_OTHER


        };
        int[] widgets = new int[]{

                R.id.personName,
                R.id.personEmail,
                R.id.personMobile,
                R.id.personCompany,
                R.id.personDesignation,
                R.id.personCountry,
                R.id.personCountry,
                R.id.personCity,
                R.id.personTypeofvisitor,
                R.id.personOther
        };*/



        adapter=new Adapter_Scanned_List(getApplicationContext(),mArrayListQrCode);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

}