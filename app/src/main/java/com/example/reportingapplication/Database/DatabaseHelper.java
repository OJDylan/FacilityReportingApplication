package com.example.reportingapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Data.db";
    private static final String TABLE_NAME_USER = "user_data";
    private static final String COL1_USER = "USER_ID";
    private static final String COL2_USER = "USERNAME";
    private static final String COL3_USER = "PASSWORD";
    private static final String TABLE_NAME_REPORT = "report_data";
    private static final String COL1_REPORT = "REPORT_ID";
    private static final String COL2_REPORT = "TITLE";
    private static final String COL3_REPORT = "DESCRIPTION";
    private static final String COL4_REPORT = "LOCATION";
    private static final String COL5_REPORT = "CITY";
    private static final String COL6_REPORT = "DATE";

    public static final String createTableUser = "CREATE TABLE " + TABLE_NAME_USER +
            "(USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "USERNAME TEXT, " +
            "PASSWORD TEXT)";

    public static final String createTableReport = "CREATE TABLE " + TABLE_NAME_REPORT +
            "(REPORT_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "TITLE TEXT, " +
            "DESCRIPTION TEXT, " +
            "LOCATION TEXT, " +
            "CITY TEXT, " +
            "DATE TEXT)";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableUser);
        db.execSQL(createTableReport);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_REPORT);
        onCreate(db);
    }

    //adds data to database
    public boolean addData (String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2_USER, username);
        contentValues.put(COL3_USER, password);

        long result = db.insert(TABLE_NAME_USER, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    //checks if user exists in database
    public boolean checkUser(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = { COL1_USER };
        String[] selectionArgs = { username, password };
        String selection = COL2_USER + "=?" + " AND " + COL3_USER + "=?";
        Cursor cursor = db.query(TABLE_NAME_USER, columns, selection, selectionArgs,
                null,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        //return true when there is more than 0 data within database
        return (count > 0);
    }

    //checks if database is empty
    public boolean checkEmpty(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT (*) FROM " + TABLE_NAME_REPORT;
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);

        cursor.close();
        db.close();
        return (count > 0);
    }

    public boolean addReport(String title, String desc, String loc, String city, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2_REPORT, title);
        contentValues.put(COL3_REPORT, desc);
        contentValues.put(COL4_REPORT, loc);
        contentValues.put(COL5_REPORT, city);
        contentValues.put(COL6_REPORT, date);

        long result = db.insert(TABLE_NAME_REPORT, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_REPORT + " ORDER BY REPORT_ID DESC";
        Cursor data = db.rawQuery(query, null);

        return data;
    }

    public Cursor getSAData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_REPORT +
                " WHERE " + "CITY='Shah Alam'" + " ORDER BY REPORT_ID DESC";
        Cursor data = db.rawQuery(query, null);

        return data;
    }

    public Cursor getPJData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_REPORT +
                " WHERE " + "CITY='Petaling Jaya'" + " ORDER BY REPORT_ID DESC";
        Cursor data = db.rawQuery(query, null);

        return data;
    }

    public Cursor getKData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_REPORT +
                " WHERE " + "CITY='Klang'" + " ORDER BY REPORT_ID DESC";
        Cursor data = db.rawQuery(query, null);

        return data;
    }
}
