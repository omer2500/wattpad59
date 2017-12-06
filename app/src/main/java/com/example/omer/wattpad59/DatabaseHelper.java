package com.example.omer.wattpad59;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

/**
 * Created by Yarden-PC on 05-Dec-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String TAG = "DatabaseHelper";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MyDB";

    // Books table
    private static final String BOOKS_TABLE_NAME = "books";
    private static final String BOOKS_COLUMN_1_ID = "id";
    private static final String BOOKS_COLUMN_2_NAME = "name";
    private static final String BOOKS_COLUMN_3_DESCRIPTION = "description";
    private static final String BOOKS_COLUMN_4__IMAGE = "image";
    private static final String BOOKS_COLUMN_5__TEXT = "text";


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            // SQL statement to create item table
            String CREATE_BOOKS_TABLE = "create table " + BOOKS_TABLE_NAME + "("
                    + BOOKS_COLUMN_1_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + BOOKS_COLUMN_2_NAME + " TEXT, "
                    + BOOKS_COLUMN_3_DESCRIPTION + " TEXT, "
                    + BOOKS_COLUMN_4__IMAGE + " BLOB, "
                    + BOOKS_COLUMN_5__TEXT + " TEXT)";
            db.execSQL(CREATE_BOOKS_TABLE);

        }catch(Throwable throwable){
            throwable.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BOOKS_TABLE_NAME);

        onCreate(db);
    }

    public boolean addData(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BOOKS_COLUMN_2_NAME,item);

        Log.d(TAG, "addData: Adding " + item + "to" + BOOKS_TABLE_NAME);
        long result = db.insert(BOOKS_TABLE_NAME,null, contentValues);

        if (result == -1){
            return false;
        }else{
            return true;
        }
    }



}
