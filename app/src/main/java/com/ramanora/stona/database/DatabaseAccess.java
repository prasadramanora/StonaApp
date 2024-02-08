package com.ramanora.stona.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.ramanora.stona.bean.AZExhibitorListPojo;

import java.util.ArrayList;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;


    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }


    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
    public ArrayList<AZExhibitorListPojo> getExhibitorlist() {
        ArrayList<AZExhibitorListPojo> list = new ArrayList<>(267);

       // ORDER BY companyname ASC

        Cursor cursor = database.rawQuery("SELECT * FROM exhibitor ", null);
        cursor.moveToFirst();
        Log.d("test_cursor", "getExhibitorlist: "+cursor.getCount());
        while (!cursor.isAfterLast()) {
            AZExhibitorListPojo listPojo = new AZExhibitorListPojo();

            listPojo.setCompanyName(cursor.getString(cursor.getColumnIndex("companyname")));
            listPojo.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            listPojo.setCity(cursor.getString(cursor.getColumnIndex("city")));
            listPojo.setPincode(cursor.getString(cursor.getColumnIndex("pincode")));
            listPojo.setState(cursor.getString(cursor.getColumnIndex("state")));
            listPojo.setCountry(cursor.getString(cursor.getColumnIndex("country")));
            listPojo.setPhone(cursor.getString(cursor.getColumnIndex("telephone")));
            listPojo.setFax(cursor.getString(cursor.getColumnIndex("fax")));
            listPojo.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            listPojo.setWebsite(cursor.getString(cursor.getColumnIndex("website")));
            listPojo.setProductname(cursor.getString(cursor.getColumnIndex("products")));
            listPojo.setCompanyprofie(cursor.getString(cursor.getColumnIndex("companyprofile")));
            listPojo.setMhallno(cursor.getString(cursor.getColumnIndex("halls")));
            listPojo.setStallno(cursor.getString(cursor.getColumnIndex("stallnumbers")));
            listPojo.setX(cursor.getString(cursor.getColumnIndex("x")));
            listPojo.setY(cursor.getString(cursor.getColumnIndex("y")));
            listPojo.setProducting(cursor.getString(cursor.getColumnIndex("productimg")));
            listPojo.setDesignation(cursor.getString(cursor.getColumnIndex("designation")));
            listPojo.setFirstname(cursor.getString(cursor.getColumnIndex("contactperson")));

            listPojo.setMobile(cursor.getString(cursor.getColumnIndex("mobileno")));




            list.add(listPojo);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public ArrayList<AZExhibitorListPojo> getHalllist() {
        ArrayList<AZExhibitorListPojo> list = new ArrayList<>();
        AZExhibitorListPojo listPojo = null;
        Cursor cursor = database.rawQuery("SELECT DISTINCT halls " +
                "FROM exhibitor ORDER BY halls ASC", null);
        Log.d("test", "list" + cursor.getCount());

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            listPojo = new AZExhibitorListPojo();


              /*  listPojo.setCompanyName(cursor.getString(cursor.getColumnIndex("companyname")));
                listPojo.setStallno(cursor.getString(cursor.getColumnIndex("stallno")));*/
            listPojo.setMhallno(cursor.getString(cursor.getColumnIndex("halls")));

            list.add(listPojo);


            cursor.moveToNext();
        }


        cursor.close();
        return list;
    }

    public ArrayList<AZExhibitorListPojo> getCountrylist() {
        ArrayList<AZExhibitorListPojo> list = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT DISTINCT country " +
                "FROM exhibitor  ORDER BY country ASC", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            AZExhibitorListPojo listPojo = new AZExhibitorListPojo();

            listPojo.setCountry(cursor.getString(cursor.getColumnIndex("country")));

            list.add(listPojo);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }


    public ArrayList<AZExhibitorListPojo> getHallAlllist(String halls) {
        ArrayList<AZExhibitorListPojo> list = new ArrayList<>();
        AZExhibitorListPojo listPojo = null;
        Cursor cursor = database.rawQuery("SELECT companyname,halls,stallnumbers FROM exhibitor WHERE halls ='" + halls + "'  ORDER BY companyname ASC", null);
       // Log.d("test", "list" + cursor.getCount());
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            listPojo = new AZExhibitorListPojo();


            listPojo.setCompanyName(cursor.getString(cursor.getColumnIndex("companyname")));
            listPojo.setStallno(cursor.getString(cursor.getColumnIndex("stallnumbers")));
           // listPojo.setAddress(cursor.getString(cursor.getColumnIndex("address")));

            listPojo.setMhallno(cursor.getString(cursor.getColumnIndex("halls")));

            list.add(listPojo);

            cursor.moveToNext();
        }


        cursor.close();
        return list;
    }

    public ArrayList<AZExhibitorListPojo> getProductlist(String companyname) {
        ArrayList<AZExhibitorListPojo> list = new ArrayList<>();
        AZExhibitorListPojo listPojo = null;
        Cursor cursor = database.rawQuery("SELECT products " +
                "FROM exhibitor WHERE companyname ='" + companyname + "'  ORDER BY products ASC", null);
        Log.d("test", "list" + cursor.getCount());

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            listPojo = new AZExhibitorListPojo();


            listPojo.setProductname(cursor.getString(cursor.getColumnIndex("products")));

            list.add(listPojo);

            System.out.println("db product name  :" + list.get(0).getProductname());
            cursor.moveToNext();
        }


        cursor.close();
        return list;
    }

    public ArrayList<AZExhibitorListPojo> getNewlauncheslist(String companyname) {
        ArrayList<AZExhibitorListPojo> list = new ArrayList<>();
        AZExhibitorListPojo listPojo = null;
        Cursor cursor = database.rawQuery("SELECT productlaunch,website,contactno,email,mobile  " +
                "FROM exhibitorlist WHERE companyname ='" + companyname + "' ORDER BY productlaunch ASC", null);
        Log.d("test", "list" + cursor.getCount());

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            listPojo = new AZExhibitorListPojo();


            listPojo.setNewlauches(cursor.getString(cursor.getColumnIndex("productlaunch")));
            listPojo.setWebsite(cursor.getString(cursor.getColumnIndex("website")));
            listPojo.setPhone(cursor.getString(cursor.getColumnIndex("telephone")));
         //   listPojo.setMobile(cursor.getString(cursor.getColumnIndex("telephone")));
            listPojo.setEmail(cursor.getString(cursor.getColumnIndex("email")));


            list.add(listPojo);

            System.out.println("db product name  :" + list.get(0).getNewlauches());
            cursor.moveToNext();
        }


        cursor.close();
        return list;
    }

    public ArrayList<AZExhibitorListPojo> getAllDatalist(String companyname) {
        ArrayList<AZExhibitorListPojo> list = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM exhibitor WHERE companyname ='" + companyname + "' ORDER BY companyname ASC", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            AZExhibitorListPojo listPojo = new AZExhibitorListPojo();

            listPojo.setCompanyName(cursor.getString(cursor.getColumnIndex("companyname")));
            listPojo.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            listPojo.setCity(cursor.getString(cursor.getColumnIndex("city")));
            listPojo.setPincode(cursor.getString(cursor.getColumnIndex("pincode")));
            listPojo.setState(cursor.getString(cursor.getColumnIndex("state")));
            listPojo.setCountry(cursor.getString(cursor.getColumnIndex("country")));
            listPojo.setPhone(cursor.getString(cursor.getColumnIndex("telephone")));
            listPojo.setFax(cursor.getString(cursor.getColumnIndex("fax")));
            listPojo.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            listPojo.setWebsite(cursor.getString(cursor.getColumnIndex("website")));
            listPojo.setProductname(cursor.getString(cursor.getColumnIndex("products")));
            listPojo.setCompanyprofie(cursor.getString(cursor.getColumnIndex("companyprofile")));
            listPojo.setMhallno(cursor.getString(cursor.getColumnIndex("halls")));
            listPojo.setStallno(cursor.getString(cursor.getColumnIndex("stallnumbers")));
            listPojo.setX(cursor.getString(cursor.getColumnIndex("x")));
            listPojo.setY(cursor.getString(cursor.getColumnIndex("y")));
            listPojo.setProducting(cursor.getString(cursor.getColumnIndex("productimg")));

            listPojo.setDesignation(cursor.getString(cursor.getColumnIndex("designation")));
            listPojo.setFirstname(cursor.getString(cursor.getColumnIndex("contactperson")));
            listPojo.setMobile(cursor.getString(cursor.getColumnIndex("mobileno")));

            list.add(listPojo);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public ArrayList<AZExhibitorListPojo> getCountryAlllist(String country) {
        ArrayList<AZExhibitorListPojo> list = new ArrayList<>();
        AZExhibitorListPojo listPojo = null;
        Cursor cursor = database.rawQuery("SELECT halls,companyname,address,stallnumbers " +
                "FROM exhibitor WHERE country ='" + country + "' ORDER BY country ASC", null);
        Log.d("test", "list" + cursor.getCount());

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            listPojo = new AZExhibitorListPojo();


            listPojo.setCompanyName(cursor.getString(cursor.getColumnIndex("companyname")));
            try {
               // listPojo.setMhallno(cursor.getString(cursor.getColumnIndex("halls")));

                listPojo.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                listPojo.setMhallno(cursor.getString(cursor.getColumnIndex("halls")));

            }catch (Exception e)
            {
                e.printStackTrace();
            }
            listPojo.setStallno(cursor.getString(cursor.getColumnIndex("stallnumbers")));
            list.add(listPojo);


            cursor.moveToNext();
        }

        Log.d("test", "country " + country + " -" + list.size());

        cursor.close();
        return list;
    }





    public ArrayList<AZExhibitorListPojo> getSubcategorylist(String cat) {
        ArrayList<AZExhibitorListPojo> list = new ArrayList<>();

        Cursor c = database.rawQuery("SELECT DISTINCT subcategory " +
                "FROM exhibitor WHERE category ='" + cat + "'", null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            AZExhibitorListPojo listPojo = new AZExhibitorListPojo();

            listPojo.setSubcategory(c.getString(c.getColumnIndex("subcategory")));

            list.add(listPojo);
            c.moveToNext();
        }
        Log.d("test", "subcat " + list.size());
        c.close();
        return list;
    }


    public ArrayList<AZExhibitorListPojo> getCategorylist_new() {
        ArrayList<AZExhibitorListPojo> list = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT DISTINCT category " +
                "FROM exhibitorlist", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String cat = cursor.getString(cursor.getColumnIndex("category"));


            String[] catlist = cat.split("\\$");
            System.out.println("length  catlist.length" + catlist.length);

            for (int i = 0; i < catlist.length; i++) {

                //   System.out.println("catlist[i]: "+i+" :"+catlist[i]);

                Cursor c = database.rawQuery("SELECT companyname " +
                        "FROM exhibitorlist  WHERE category ='" + catlist[i] + "'", null);
                c.moveToFirst();
                while (!c.isAfterLast()) {


                    AZExhibitorListPojo listPojo = new AZExhibitorListPojo();

                    listPojo.setCategory(catlist[i]);

                    listPojo.setCompanyName(c.getString(c.getColumnIndex("companyname")));

                    System.out.println("catlist[i]: companyname " + catlist[i] + " :" + c.getString(c.getColumnIndex("companyname")));
                    list.add(listPojo);

                    c.moveToNext();
                }

                c.close();
            }


            cursor.moveToNext();
        }
        Log.d("test", "cat " + list.size());
        cursor.close();
        return list;
    }

    public ArrayList<AZExhibitorListPojo> getSubcategorylist_new(String subcategory) {
        // ArrayList<AZExhibitorListPojo> list = new ArrayList<>();
        ArrayList<AZExhibitorListPojo> list1 = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT DISTINCT subcategory " +
                "FROM exhibitorlist ORDER BY companyname ASC", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String subcat = cursor.getString(cursor.getColumnIndex("subcategory"));


            String[] subcatlist = subcat.split("\\$");
            // System.out.println("length  subcatlist.length" + subcatlist.length);

            for (int i = 0; i < subcatlist.length; i++) {

                // System.out.println("subcatlist[i]: " + i + " :" + subcatlist[i]);

                Cursor c1 = database.rawQuery("SELECT DISTINCT companyname " +
                        " FROM exhibitorlist  WHERE subcategory ='" + subcat + "' ORDER BY companyname ASC", null);
                c1.moveToFirst();
                while (!c1.isAfterLast()) {


                    String companyname = c1.getString(c1.getColumnIndex("companyname"));

                    Cursor c2 = database.rawQuery("SELECT DISTINCT location , stallno " +
                            "FROM exhibitorlist  WHERE " +
                            " companyname='" + companyname + "' ORDER BY companyname ASC", null);//subcategory ='" + subcat + "' and


                    if (c2.getCount() > 0) {
                        c2.moveToFirst();
                        while (!c2.isAfterLast()) {


                            AZExhibitorListPojo listPojo1 = new AZExhibitorListPojo();

                            listPojo1.setSubcategory(subcatlist[i]);
                            listPojo1.setCompanyName(companyname);
                            listPojo1.setMhallno(c2.getString(c2.getColumnIndex("location")));
                            listPojo1.setStallno(c2.getString(c2.getColumnIndex("stallno")));


                            list1.add(listPojo1);


                            c2.moveToNext();
                        }
                        c2.close();
                    }

                    c1.moveToNext();
                }
                c1.close();

            }


            cursor.moveToNext();
        }

        cursor.close();
        return list1;
    }


    public ArrayList<AZExhibitorListPojo> getCompanydetailslist(String companyname, String hallname, String stallno) {
        ArrayList<AZExhibitorListPojo> list = new ArrayList<>();
        AZExhibitorListPojo listPojo = null;
        Cursor cursor = database.rawQuery("SELECT * " +
                " FROM exhibitorlist WHERE companyname ='" + companyname + "'  and location ='"
                + hallname + "' and stallno ='" + stallno + "'", null);
        Log.d("test", "list" + cursor.getCount());

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            listPojo = new AZExhibitorListPojo();

            listPojo.setCompanyName(cursor.getString(cursor.getColumnIndex("companyname")));
            listPojo.setStallno(cursor.getString(cursor.getColumnIndex("stallno")));
            listPojo.setMhallno(cursor.getString(cursor.getColumnIndex("address")));
            listPojo.setX(cursor.getString(cursor.getColumnIndex("x")));
            listPojo.setY(cursor.getString(cursor.getColumnIndex("y")));
            listPojo.setCity(cursor.getString(cursor.getColumnIndex("city")));
            listPojo.setCountry(cursor.getString(cursor.getColumnIndex("country")));
            listPojo.setPhone(cursor.getString(cursor.getColumnIndex("telephone")));
            listPojo.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            listPojo.setWebsite(cursor.getString(cursor.getColumnIndex("website")));
            listPojo.setFirstname(cursor.getString(cursor.getColumnIndex("personname")));
            listPojo.setDesignation(cursor.getString(cursor.getColumnIndex("designation")));
           // listPojo.setMobile(cursor.getString(cursor.getColumnIndex("telephone")));
            listPojo.setNewlauches(cursor.getString(cursor.getColumnIndex("productlaunch")));
            listPojo.setCategory(cursor.getString(cursor.getColumnIndex("category")));
            listPojo.setSubcategory(cursor.getString(cursor.getColumnIndex("subcategory")));
            listPojo.setProdcut(cursor.getString(cursor.getColumnIndex("productname")));
            listPojo.setExilogo(cursor.getString(cursor.getColumnIndex("exilogo")));
            listPojo.setExiproimg1(cursor.getString(cursor.getColumnIndex("exiproimg1")));
            listPojo.setExiproimg2(cursor.getString(cursor.getColumnIndex("exiproimg2")));
            //  listPojo.setExiproimg3(cursor.getString(cursor.getColumnIndex("exiproimg3")));
            listPojo.setQrcodeimg(cursor.getString(cursor.getColumnIndex("qrcodeimg")));

            list.add(listPojo);


            cursor.moveToNext();
        }


        cursor.close();
        return list;
    }


    public ArrayList<AZExhibitorListPojo> getCompanylist() {
        ArrayList<AZExhibitorListPojo> list = new ArrayList<>();

       /* Cursor cursor = database.rawQuery("SELECT x,y,halls,companyname,stallnumbers " +
                "FROM exhibitor ", null);*/
        Cursor cursor = database.rawQuery("SELECT * FROM exhibitor ", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            AZExhibitorListPojo listPojo = new AZExhibitorListPojo();

            listPojo.setCompanyName(cursor.getString(cursor.getColumnIndex("companyname")));
            listPojo.setMhallno(cursor.getString(cursor.getColumnIndex("halls")));
            listPojo.setStallno(cursor.getString(cursor.getColumnIndex("stallnumbers")));
            listPojo.setX(cursor.getString(cursor.getColumnIndex("x")));
            listPojo.setY(cursor.getString(cursor.getColumnIndex("y")));


            list.add(listPojo);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public ArrayList<AZExhibitorListPojo> getCompanyCordinates(String companyname, String hall, String stallno) {
        ArrayList<AZExhibitorListPojo> list = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT  x,y FROM exhibitor WHERE companyname='" + companyname + "'  and halls='" + hall + "'  and stallnumbers='" + stallno + "'", null);

       /* Log.d("test", "getCompanyCordinates: cnt " + cursor.getCount());
        Log.d("test", "getCompanyCordinates: Companyname:  " + Companyname);
        Log.d("test", "getCompanyCordinates: location:  " + hall);

        Log.d("test", "getCompanyCordinates: stallno:  " + stallno);
*/

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                AZExhibitorListPojo listPojo = new AZExhibitorListPojo();

                listPojo.setX(cursor.getString(cursor.getColumnIndex("x")));
                listPojo.setY(cursor.getString(cursor.getColumnIndex("y")));
                Log.d("test", "getCompanyCordinates: x" + cursor.getString(cursor.getColumnIndex("x")));
                Log.d("test", "getCompanyCordinates: y" + cursor.getString(cursor.getColumnIndex("y")));
                list.add(listPojo);
                cursor.moveToNext();
            }
            cursor.close();


        }

        Log.d("test", "getCompanyCordinates: list  " + list.size());
        return list;
    }
}
