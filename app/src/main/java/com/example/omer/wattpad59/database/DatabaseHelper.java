package com.example.omer.wattpad59.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.omer.wattpad59.core.BookInfo;

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
    private static final String BOOKS_COLUMN_6__WATTPAD_ID = "wattpad_id";

    //Book columns order
    private static final String[] BOOKS_COLUMNS = {BOOKS_COLUMN_1_ID, BOOKS_COLUMN_2_NAME,
            BOOKS_COLUMN_3_DESCRIPTION, BOOKS_COLUMN_4__IMAGE, BOOKS_COLUMN_5__CONTENT, BOOKS_COLUMN_6__WATTPAD_ID};


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
                    + BOOKS_COLUMN_5__CONTENT + " TEXT, "
                    + BOOKS_COLUMN_6__WATTPAD_ID + " TEXT)";
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

    //Add new book the DB
    public boolean addNewBookInfo(BookInfo book){
        long result = -1;
        try{
            ContentValues values = new ContentValues();
            values.put(BOOKS_COLUMN_1_ID, book.getId());
            values.put(BOOKS_COLUMN_2_NAME, book.getName());
            values.put(BOOKS_COLUMN_3_DESCRIPTION, book.getDescription());

            Bitmap image = book.getImage();
            if (image != null) {
                byte[] img= book.getImgAsByteArray(image);
                if(img!=null && img.length>0){
                    values.put(BOOKS_COLUMN_4__IMAGE, img);
                }
            }
            values.put(BOOKS_COLUMN_5__CONTENT, book.getContent());
            values.put(BOOKS_COLUMN_6__WATTPAD_ID, book.getWattpadId());

            result = db.insert(BOOKS_TABLE_NAME,null,values);
        }catch (Throwable e){
            e.printStackTrace();
        }

        if(result > 0){
            return true;
        }
        return false;
    }


    public boolean updateBookInfo(BookInfo bookInfo){
        long result = -1;
        try {
            ContentValues values = new ContentValues();
            values.put(BOOKS_COLUMN_1_ID, bookInfo.getId());
            values.put(BOOKS_COLUMN_2_NAME, bookInfo.getName());
            values.put(BOOKS_COLUMN_3_DESCRIPTION, bookInfo.getDescription());
            Bitmap image =bookInfo.getImage();
            if (image != null) {
                byte[] img= bookInfo.getImgAsByteArray(image);
                if(img!=null && img.length>0){
                    values.put(BOOKS_COLUMN_4__IMAGE, img);
                }
            }
            values.put(BOOKS_COLUMN_5__CONTENT, bookInfo.getContent());
            values.put(BOOKS_COLUMN_6__WATTPAD_ID, bookInfo.getWattpadId());
            result = db.update(BOOKS_TABLE_NAME, values, BOOKS_COLUMN_1_ID +"=?",new String[]{bookInfo.getId()});
        } catch (Throwable e) {
            e.printStackTrace();
        }

        if(result>0){
            return true;
        }
        return false;
    }

    public boolean deleteBookInfo(BookInfo bookInfo){

        String[] arguments = {bookInfo.getId()};

        int result = db.delete(BOOKS_TABLE_NAME, BOOKS_COLUMN_1_ID+" = ?",arguments);

        if(result>0){
            return true;
        }
        return false;
    }

    public int updateBookImage(BookInfo book) {

        int cnt = 0;
        try {

            // make values to be inserted
            ContentValues values = new ContentValues();

            //images
            if (book.getImage() != null) {
                byte[] data = BookInfo.getImgAsByteArray(book.getImage());
                if (data != null && data.length > 0) {
                    values.put(BOOKS_COLUMN_4__IMAGE, data);
                }
            }
            else{
                values.putNull(BOOKS_COLUMN_4__IMAGE);
            }

            // update
            cnt = db.update(BOOKS_TABLE_NAME, values, BOOKS_COLUMN_1_ID + " = ?",
                    new String[] { book.getId() });
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return  cnt;
    }


    public List<BookInfo> getAllMyBooks(){
        List<BookInfo> results = new ArrayList<BookInfo>();
        Cursor cursor = null;
        try{
            String whereArgs[] = {MyInfoManager.getInstance().getMyUserId()};
            cursor = db.query(BOOKS_TABLE_NAME, BOOKS_COLUMNS, BOOKS_COLUMN_1_ID+"=?",whereArgs, null,null,null);
            if(cursor!=null && cursor.getCount()>0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    BookInfo book = new BookInfo();
                    book.setId(cursor.getString(0));
                    book.setName(cursor.getString(1));
                    book.setDescription(cursor.getString(2));
                    book.setImg(cursor.getBlob(3));
                    book.setContent(cursor.getString(4));
                    book.setWattpadId(cursor.getString(5));
                    results.add(book);
                    cursor.moveToNext();
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            if(cursor!=null){
                cursor.close();
            }
        }

        return results;
    }


    public List<BookInfo> getAllBooksByCategory(String category){
        List<BookInfo> results = new ArrayList<BookInfo>();
        Cursor cursor = null;
        try{
            String whereArgs[] = {category};
            cursor = db.query(BOOKS_TABLE_NAME, BOOKS_COLUMNS, BOOKS_COLUMN_6__WATTPAD_ID+"=?",whereArgs, null,null,null);
            if(cursor!=null && cursor.getCount()>0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    BookInfo book = new BookInfo();
                    book.setId(cursor.getString(0));
                    book.setName(cursor.getString(1));
                    book.setDescription(cursor.getString(2));
                    book.setImg(cursor.getBlob(3));
                    book.setContent(cursor.getString(4));
                    book.setWattpadId(cursor.getString(5));
                    results.add(book);
                    cursor.moveToNext();
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            if(cursor!=null){
                cursor.close();
            }
        }

        return results;
    }

    //Get all the books in the DB as a list
    public List<BookInfo> getAllBooks(){
        List<BookInfo> result = new ArrayList<BookInfo>();
        //BOOKS_COLUMN_1_ID, BOOKS_COLUMN_2_NAME,
        // BOOKS_COLUMN_3_DESCRIPTION, BOOKS_COLUMN_4__IMAGE, BOOKS_COLUMN_5__CONTENT, BOOKS_COLUMN_6__WATTPAD_ID
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
                    book.setImg(cursor.getBlob(3));
                    book.setContent(cursor.getString(4));
                    book.setWattpadId(cursor.getString(5));
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

    //Delete book by given ID
    public boolean delete(String id){
        long result = -1;
        result = db.delete(BOOKS_TABLE_NAME, BOOKS_COLUMN_1_ID + " = ?", new String[] {id});
        Log.d(TAG,"Book deleted");
        if(result > 0){
            return true;
        }
        return false;
    }
}
