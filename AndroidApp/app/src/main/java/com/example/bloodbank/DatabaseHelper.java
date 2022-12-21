package com.example.bloodbank;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.DatePicker;

import java.util.Date;


public class DatabaseHelper extends SQLiteOpenHelper  {

    //Create DataBase
    public static final String Db_Name = "BloodBankApp.db";


    //Create Table Students
    public static final String TABLE_DONORS = "tblDonors";
    public static final String COL_2 = "UserName";
    public static final String COL_3 = "Phone";
    public static final String COL_4 = "Age";
    public static final String COL_5 = "Address";
    public static final String COL_6 = "Weight";
    public static final String COL_7 = "Gender";



    public DatabaseHelper(Context context){

        super(context, Db_Name,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_DONORS + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, UserName TEXT, Phone String, Address String, Weight INTEGER, Age INTEGER, Password String)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DONORS);
        onCreate(db);
    }

    public boolean RegisterDonor(String name, String phone, String dop, String address, String weight){
        String response = "Success";

        if(response == "Success")
            return false;
        else
            return true;
    }


    public Cursor Login(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM  tblDonors WHERE UserName = ?", new String[] { username } );
        return cursor;
    }

}
