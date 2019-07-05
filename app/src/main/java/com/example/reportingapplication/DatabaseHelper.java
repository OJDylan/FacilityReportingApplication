package com.example.reportingapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "User.db";
    private static final String TABLE_NAME = "user_data";
    private static final String COL1 = "ID";
    private static final String COL2 = "USERNAME";
    private static final String COL3 = "PASSWORD";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT," +
                "PASSWORD TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //adds data to database
    public boolean addData (String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, username);
        contentValues.put(COL3, password);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    //checks if user exists in database
    public boolean checkUser(String username, String password){
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = { COL1 };
        String[] selectionArgs = { username, password };
        String selection = COL2 + "=?" + " AND " + COL3 + "=?";
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs,
                null,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        return (count > 0);
    }

    public Cursor getName(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT USERNAME FROM " + TABLE_NAME, null);
        return res;
    }
}
