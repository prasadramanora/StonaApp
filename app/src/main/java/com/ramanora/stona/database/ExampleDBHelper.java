package com.ramanora.stona.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ramanora.stona.bean.Qrcode;

import java.util.ArrayList;

/**
 * Created by admin on 9/15/2017.
 */

public class ExampleDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SQLiteExample";
    private static final int DATABASE_VERSION = 2;

    public static final String PERSON_TABLE_NAME = "scanqrcodeinfo";

   public static final String PERSON_COLUMN_ID = "_id";
    public static final String PERSON_COLUMN_REGISTRATIONNO = "registrationno";
    public static final String PERSON_COLUMN_NAME = "name";
    public static final String PERSON_COLUMN_EMAIL = "email";
    public static final String PERSON_COLUMN_PHONE = "phone";
    public static final String PERSON_COLUMN_COMPANY = "company";
    public static final String PERSON_COLUMN_DESIGNATION = "designation";
    public static final String PERSON_COLUMN_COUNTRY = "country";
    public static final String PERSON_COLUMN_CITY = "city";
    public static final String PERSON_COLUMN_TYPEOFVISITOR = "typepfvisitor";
    public static final String PERSON_COLUMN_OTHER = "other";


    public ExampleDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + PERSON_TABLE_NAME +
                        " ( "
                        + PERSON_COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + PERSON_COLUMN_REGISTRATIONNO + " TEXT,"
                        + PERSON_COLUMN_NAME + " TEXT,"
                        + PERSON_COLUMN_DESIGNATION + " TEXT,"
                        + PERSON_COLUMN_COMPANY + " TEXT,"
                        + PERSON_COLUMN_COUNTRY + " TEXT,"
                        + PERSON_COLUMN_EMAIL + " TEXT,"
                        + PERSON_COLUMN_PHONE + " TEXT" + " ) "
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
      //  db.execSQL("DROP TABLE IF EXISTS " + PERSON_TABLE_NAME);
        db.execSQL("alter table scanqrcodeinfo RENAME TO TEMPscanqrcodeinfo");
        db.execSQL(PERSON_TABLE_NAME);
        db.execSQL("INSERT INTO scanqrcodeinfo select * from TEMPscanqrcodeinfo;");
        db.execSQL("DROP TABLE TEMPscanqrcodeinfo;");
    }



 /*   public boolean insertPerson(String result) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PERSON_COLUMN_NAME, result);
        db.insert(PERSON_TABLE_NAME, null, contentValues);

        return true;
    }*/

   /* public void addScannedInfo(String name,String email,String phone,String company, String designation, String country,
                               String city, String typeofvisitor, String other) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(PERSON_COLUMN_NAME, name); // Contact Name
        values.put(PERSON_COLUMN_EMAIL, email);
        values.put(PERSON_COLUMN_PHONE, phone);
        values.put(PERSON_COLUMN_COMPANY, company);
        values.put(PERSON_COLUMN_DESIGNATION, designation);
        values.put(PERSON_COLUMN_COUNTRY, country);
        values.put(PERSON_COLUMN_CITY, city);
        values.put(PERSON_COLUMN_TYPEOFVISITOR, typeofvisitor);
        values.put(PERSON_COLUMN_OTHER,other);

        // Inserting Row
        long a = db.insert(PERSON_TABLE_NAME, null, values);
        Log.d("test", "addScannedInfo: insert value " + a);
        db.close(); // Closing database connection

    }
*/

    public void addScannedInfo(String registrationno, String name,  String designation, String company, String country,String email,String phone)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(PERSON_COLUMN_REGISTRATIONNO, registrationno);
        values.put(PERSON_COLUMN_NAME, name);
        values.put(PERSON_COLUMN_DESIGNATION, designation);
        values.put(PERSON_COLUMN_COMPANY, company);
        values.put(PERSON_COLUMN_COUNTRY,country);
        values.put(PERSON_COLUMN_EMAIL, email);
        values.put(PERSON_COLUMN_PHONE, phone);



        // Inserting Row
        long a = db.insert(PERSON_TABLE_NAME, null, values);
        Log.d("test", "addScannedInfo: insert value " + a);
        db.close(); // Closing database connection
    }




    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, PERSON_TABLE_NAME);
        return numRows;
    }



    /*public boolean updatePerson(Integer id, String result) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PERSON_COLUMN_NAME, result);
        db.update(PERSON_TABLE_NAME, contentValues, PERSON_COLUMN_ID + " = ? ", new String[] { Integer.toString(id) } );
        return true;
    }


    public Integer deletePerson(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(PERSON_TABLE_NAME,
                PERSON_COLUMN_ID + " = ? ",
                new String[] { Integer.toString(id) });
    }

    public Cursor getPerson(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM " + PERSON_TABLE_NAME + " WHERE " +
                PERSON_COLUMN_ID + "=?", new String[]{Integer.toString(id)});
        return res;
    }*/

 /*   public Cursor getAllPersons() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + PERSON_TABLE_NAME
                +   " ORDER BY " + ExampleDBHelper.PERSON_COLUMN_ID


                " DESC", null );

        return res;
    }*/

/*    public ArrayList<Qrcode> getAllqrcode() {
        ArrayList<Qrcode> qrcodesscanlist = new ArrayList<Qrcode>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + PERSON_TABLE_NAME+ " ORDER BY _id DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Qrcode qrcode = new Qrcode();

                qrcode.setName(cursor.getString(1));
                qrcode.setEmail(cursor.getString(2));
                qrcode.setPhone(cursor.getString(3));
                qrcode.setCompany(cursor.getString(4));
                qrcode.setDesignation(cursor.getString(5));
                qrcode.setCountry(cursor.getString(6));
                qrcode.setCity(cursor.getString(7));
                qrcode.setTypeofvisitor(cursor.getString(8));
                qrcode.setOther(cursor.getString(9));

                // Adding  to list
                qrcodesscanlist.add(qrcode);
            } while (cursor.moveToNext());
        }

        // return contact list
        return qrcodesscanlist;
    }*/

    public ArrayList<Qrcode> getAllqrcode() {
        ArrayList<Qrcode> qrcodesscanlist = new ArrayList<Qrcode>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + PERSON_TABLE_NAME+ " ORDER BY _id DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Qrcode qrcode = new Qrcode();
                qrcode.setRegistrationno(cursor.getString(1));
                qrcode.setName(cursor.getString(2));
                qrcode.setDesignation(cursor.getString(3));
                qrcode.setCompany(cursor.getString(4));
                qrcode.setCountry(cursor.getString(5));
                qrcode.setEmail(cursor.getString(6));
                qrcode.setPhone(cursor.getString(7));

                // Adding  to list
                qrcodesscanlist.add(qrcode);
            } while (cursor.moveToNext());
        }

        // return contact list
        return qrcodesscanlist;
    }


}
