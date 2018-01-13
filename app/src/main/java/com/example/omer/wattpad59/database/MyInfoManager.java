package com.example.omer.wattpad59.database;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.example.omer.wattpad59.core.BookInfo;
import com.example.omer.wattpad59.network.utils.NetworkConnector;
import com.example.omer.wattpad59.network.utils.NetworkResListener;
import com.example.omer.wattpad59.network.utils.ResStatus;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by Yarden-PC on 11-Dec-17.
 * This is the singleton
 */

public class MyInfoManager implements NetworkResListener {

    private static MyInfoManager instance=null;
    Context context;
    private DatabaseHelper databaseHelper;

    private BookInfo editBook;
    private Bitmap takenBookPicture = null;
    private String myUserId = "wattpad";


    public static MyInfoManager getInstance(){

        if(instance == null){
            instance = new MyInfoManager();
        }
        return instance;
    }

    @Override
    public void onPreUpdate() {
        Toast.makeText(context,"Sync stated...",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBookUpdate(byte[] res, ResStatus status) {
        Toast.makeText(context,"Sync finished...status " + status.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBookUpdate(JSONObject res, ResStatus status) {
        if(res!=null){
            Toast.makeText(context,"Sync finished...status " + res.toString(),Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Sync finished...status " + status.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBookUpdate(Bitmap res, ResStatus status) {
    }

    public Bitmap getTakenBookPicture() {
        return takenBookPicture;
    }

    public void setTakenBookPicture(Bitmap takenBookPicture) {
        this.takenBookPicture = takenBookPicture;
    }

    public String getMyUserId() {
        return myUserId;
    }

    public void setMyUserId(String myUserId) {
        this.myUserId = myUserId;
    }


    public void addNewBook(BookInfo book){
        if(databaseHelper!=null) {
            if(databaseHelper.addNewBookInfo(book)) {
                NetworkConnector.getInstance().sendRequestToServer(NetworkConnector.INSERT_BOOK_REQ, book, instance);
            }
        }
    }
    /*public List<BookInfo> getAllBooksByCategory(String category) {
        List<BookInfo> list = new ArrayList<BookInfo>();
        NetworkConnector.getInstance().sendRequestToServer(NetworkConnector.GET_ALL_BOOKS_JSON_REQ, instance);
        return list;
    }*/


    public List<BookInfo> getAllBooks(){
        List<BookInfo> list = new ArrayList<BookInfo>();
        if(databaseHelper!=null) {
            list = databaseHelper.getAllBooks();
        }
        return list;
    }

    /*public List<BookInfo> getAllBooksByCategory(String category){
        List<BookInfo> list = new ArrayList<BookInfo>();
        NetworkConnector.getInstance().sendRequestToServer(NetworkConnector.INSERT_BOOK_REQ, category, instance);
        return list;
    }*/

    public List<BookInfo> getAllMyBooks(){
        List<BookInfo> list = new ArrayList<BookInfo>();
        if(databaseHelper!=null) {
            list = databaseHelper.getAllMyBooks();
        }
        return list;

    }


    public boolean deleteBook(BookInfo book){
        boolean result = false;
        if(databaseHelper!=null) {
            result=databaseHelper.deleteBookInfo(book);
            if(result){
                NetworkConnector.getInstance().sendRequestToServer(NetworkConnector.DELETE_BOOK_REQ, book, instance);
            }
        }
        return result;
    }

    public boolean updateBook(BookInfo book){
        boolean result = false;
        if(databaseHelper!=null) {
            result =databaseHelper.updateBookInfo(book);
            if(result){
                NetworkConnector.getInstance().sendRequestToServer(NetworkConnector.INSERT_BOOK_REQ, book, instance);
            }
        }
        return result;
    }

    public void setEditBook(BookInfo editBook) {
        this.editBook = editBook;
    }

    public BookInfo getEditBook() {
        return editBook;
    }

    public void updateBooks(JSONObject res) {
        if(databaseHelper==null){
            return;
        }
        List<BookInfo> books = BookInfo.parseJson(res);
        for(BookInfo book:books){
            if(!databaseHelper.addNewBookInfo(book)) {
                databaseHelper.updateBookInfo(book);
            }
        }
    }

    public void updateBookImage(BookInfo book){
        if(book.getImage()!=null) {
            String itemId = book.getId();
            if(databaseHelper!=null){
                databaseHelper.updateBookImage(book);
            }
        }
    }


    //Open the database
    public void openDatabase(Context context){
        databaseHelper = new DatabaseHelper(context);
        databaseHelper.open();
    }

    //Close database if open
    public void closeDatabase(){
        if(databaseHelper!=null){
            databaseHelper.close();
        }
    }

    //Add new book. Calling the addNewBook function in DatabaseHelper activity
    //public boolean addNewBook(BookInfo book){
    //    return databaseHelper.addNewBookInfo(book);
    //}


    //Delete a book. Calling the delete function in DatabaseHelper activity
    /*public boolean deleteBook(String id){
        return databaseHelper.delete(id);
    }*/
}
