package com.example.omer.wattpad59.core;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yarden-PC on 11-Dec-17.
 */

public class BookInfo {

    String id;
    String name;
    String description;
    Bitmap image;
    String content;
    String wattpadId;

    //Constructor
    public BookInfo(String id, String name, String description, Bitmap image, String content) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.content = content;
    }

    public BookInfo(Bitmap image, String name, String description){
        this.image = image;
        this.name = name;
        this.description = description;
    }

    //Getters and Setters

    /*private String generateID(){
        return "book_" + System.currentTimeMillis();
    }*/

    public BookInfo(){
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getContent() {
        return content;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWattpadId() {
        return wattpadId;
    }

    public void setWattpadId(String wattpadId) {
        this.wattpadId = wattpadId;
    }

    //convert image from bitmap to byte array
    public static byte[] getImgAsByteArray(Bitmap bm){
        byte[] res= new byte[0];
        if(bm != null){
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG,0,outputStream);
            res = outputStream.toByteArray();
        }
        return res;
    }

    public void setImg(byte[] imgeArray){
        if(imgeArray!=null){
            this.image = BitmapFactory.decodeByteArray(imgeArray, 0, imgeArray.length);
        }
    }

    public static List<BookInfo> parseJson(JSONObject json) {

        List<BookInfo> books = null;
        try {

            books = new ArrayList<BookInfo>();

            JSONArray foldersJsonArr = json.getJSONArray("books");

            for (int i = 0; i < foldersJsonArr.length(); i++) {
                try {
                    JSONObject iObj = foldersJsonArr.getJSONObject(i);
                    BookInfo book = new BookInfo();
                    book.setId(iObj.getString("id"));
                    book.setName(iObj.getString("name"));
                    book.setDescription(iObj.getString("description"));
                    book.setContent(iObj.getString("content"));
                    book.setWattpadId(iObj.getString("wattpad_id"));

                    books.add(book);

                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }

        } catch (Throwable e) {
            e.printStackTrace();
        }

        return books;
    }
}
