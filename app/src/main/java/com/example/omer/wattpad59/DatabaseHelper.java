package com.example.omer.wattpad59;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yarden-PC on 05-Dec-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String TAG = "DatabaseHelper";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "myDatabase2";
    private SQLiteDatabase db;

    // Books table
    private static final String BOOKS_TABLE_NAME = "books";
    private static final String BOOKS_COLUMN_1_ID = "id";
    private static final String BOOKS_COLUMN_2_NAME = "name";
    private static final String BOOKS_COLUMN_3_DESCRIPTION = "description";
    private static final String BOOKS_COLUMN_4__IMAGE = "image";
    private static final String BOOKS_COLUMN_5__CONTENT = "content";

    private static final String[] BOOKS_COLUMNS = {BOOKS_COLUMN_1_ID, BOOKS_COLUMN_2_NAME,
            BOOKS_COLUMN_3_DESCRIPTION, BOOKS_COLUMN_4__IMAGE, BOOKS_COLUMN_5__CONTENT};


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

            // SQL statement to create item table
            String CREATE_BOOKS_TABLE = "create table if not exists " + BOOKS_TABLE_NAME + "("
                    + BOOKS_COLUMN_1_ID + " TEXT PRIMARY KEY, "
                    + BOOKS_COLUMN_2_NAME + " TEXT, "
                    + BOOKS_COLUMN_3_DESCRIPTION + " TEXT, "
                    + BOOKS_COLUMN_4__IMAGE + " BLOB, "
                    + BOOKS_COLUMN_5__CONTENT + " TEXT)";
            db.execSQL(CREATE_BOOKS_TABLE);
            Log.d(TAG,"created books table");
    }

    //open database
    public  void open(){
        db = getWritableDatabase();
    }

    //close database if open
    public void close(){
        if(db !=null){
            db.close();
        }
    }


    public boolean addNewBookInfo(BookInfo book){
        long result = -1;
        ContentValues values = new ContentValues();
        values.put(BOOKS_COLUMN_1_ID, book.getId());
        values.put(BOOKS_COLUMN_2_NAME, book.getName());
        values.put(BOOKS_COLUMN_3_DESCRIPTION, book.getDescription());
        values.put(BOOKS_COLUMN_4__IMAGE, book.getImageAsByteArray());
        values.put(BOOKS_COLUMN_5__CONTENT, book.getContent());

        result = db.insert(BOOKS_TABLE_NAME,null,values);
        if(result > 0){
            return true;
        }
        return false;
    }

    public List<BookInfo> getAllBooks(){
        List<BookInfo> result = new ArrayList<BookInfo>();
        //BOOKS_COLUMN_1_ID, BOOKS_COLUMN_2_NAME,
        // BOOKS_COLUMN_3_DESCRIPTION, BOOKS_COLUMN_4__IMAGE, BOOKS_COLUMN_5__CONTENT
        Cursor cursor = null;
        try{
            cursor = db.query(BOOKS_TABLE_NAME, BOOKS_COLUMNS, null, null, null, null, null);
            if(cursor!=null && cursor.getCount()>0){
                cursor.moveToFirst();
                while(!cursor.isAfterLast()){
                    BookInfo book = new BookInfo();
                    book.setId(cursor.getString(0));
                    book.setName(cursor.getString(1));
                    book.setDescription(cursor.getString(2));
                    book.setImageFromByteArray(cursor.getBlob(3));
                    book.setContent(cursor.getString(4));
                    result.add(book);
                    cursor.moveToNext();
                }
            }
        }catch(Throwable t){
            t.printStackTrace();
        }
        finally {
            if(cursor!=null){
                cursor.close();
            }
        }
        return result;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
