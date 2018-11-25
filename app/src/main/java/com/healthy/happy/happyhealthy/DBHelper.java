package com.healthy.happy.happyhealthy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DBNAME = "happyusers.db";
    public static final String DBTABLENAME1 = "users";
    public static final String DBTABLENAME2 = "logs";

    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE users ( _id INTEGER PRIMARY KEY AUTOINCREMENT, fullname TEXT NOT NULL, email TEXT NOT NULL, password TEXT NOT NULL, birthday TEXT NOT NULL, gender TEXT NOT NULL);";
        String createTable2 = "CREATE TABLE logs ( _id INTEGER PRIMARY KEY AUTOINCREMENT, fullname TEXT NOT NULL, calorie TEXT NOT NULL, date TEXT NOT NULL);";
        db.execSQL(createTable);
        db.execSQL(createTable2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBTABLENAME1);
        db.execSQL("DROP TABLE IF EXISTS " + DBTABLENAME2);
        onCreate(db);
    }

    public boolean insert(String pfullname, String pemail, String ppassword, String pbirthday, String pgender){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("fullname", pfullname);
        values.put("email", pemail);
        values.put("password", ppassword);
        values.put("birthday", pbirthday);
        values.put("gender", pgender);
        long result = db.insert(DBTABLENAME1, null, values);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor checkUser(String pemail, String ppassword){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("Select * FROM users where email=? AND password=?", new String[] {pemail, ppassword});
        if(cur.getCount() > 0)
            return cur;
        else
            return null;
    }

    public boolean insertCalorie(String pfullname, String pcalorie, String pdate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("fullname", pfullname);
        values.put("calorie", pcalorie);
        values.put("date", pdate);
        long result = db.insert(DBTABLENAME2, null, values);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor selectRecords(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + DBTABLENAME2, null);
    }
}