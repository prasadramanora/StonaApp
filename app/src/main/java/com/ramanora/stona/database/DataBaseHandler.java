package com.ramanora.stona.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ramanora.stona.bean.CameraPojo;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 4;

	// Database Name
	private static final String DATABASE_NAME = "imagedb";
	// Contacts table name
	private static final String TABLE_CONTACTS = "contacts";
	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_IMAGE = "image";


	public static final String TABLE_NOTIFICATION = "notification";
	private static final String KEY_MSG = "msg";
	private static final String KEY_TYPE = "type";




	public DataBaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY,"
				+ KEY_IMAGE + " BLOB" + ")";
		db.execSQL(CREATE_CONTACTS_TABLE);


		String CREATE_TABLE_NOTIFICATION = "CREATE TABLE " + TABLE_NOTIFICATION +
				"(msg text,type text)";
		db.execSQL(CREATE_TABLE_NOTIFICATION);

	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATION);
		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */
// Adding new contact
	public	void addContact(CameraPojo contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_IMAGE, contact._image); // CameraPojo Phone

		// Inserting Row
		db.insert(TABLE_CONTACTS, null, values);
		db.close(); // Closing database connection
	}

	//add notification
	public void addNotification(String msg ,String type ) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put("msg",msg);
		values.put("type",type);

	long q=	db.insert(TABLE_NOTIFICATION, null, values);

		//Log.d("count", "addNotification: "+q);
		db.close();
	}


	// Getting single contact
	CameraPojo getContact(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID, KEY_IMAGE }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		CameraPojo contact = new CameraPojo(Integer.parseInt(cursor.getString(0)),
				cursor.getBlob(1));

		// return contact
		return contact;

	}

	// Getting All Contacts
	public List<CameraPojo> getAllContacts() {
		List<CameraPojo> contactList = new ArrayList<CameraPojo>();
		// Select All Query
		String selectQuery = "SELECT  * FROM contacts ORDER BY id";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				CameraPojo contact = new CameraPojo();
				contact.setID(Integer.parseInt(cursor.getString(0)));
				contact.setImage(cursor.getBlob(1));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		// close inserting data from database
		db.close();
		// return contact list
		return contactList;

	}

	public int getNotificationCount() {
		String countQuery = "SELECT * FROM " + TABLE_NOTIFICATION;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int cnt = cursor.getCount();
		Log.d("notificationcount", String.valueOf(cnt));
		cursor.close();
		return cnt;
	}


	public int clearNotificationCount(){

		String countQuery = "select * from " + TABLE_NOTIFICATION;
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_NOTIFICATION,null,null);
		Cursor cursor = db.rawQuery(countQuery, null);
		int cnt = cursor.getCount();
		Log.d("clr", "clearNotificationCount: "+ cnt);
		cursor.close();
		return cnt;
	}

/*	public void remove(long id){
		String string =String.valueOf(id);
		SQLiteDatabase db=this.getWritableDatabase();
		db.execSQL("DELETE FROM contacts WHERE id = '" + string + "'");
	}*/
}
