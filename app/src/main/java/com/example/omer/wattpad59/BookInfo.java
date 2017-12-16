package com.example.omer.wattpad59;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;

/**
 * Created by Yarden-PC on 11-Dec-17.
 */

public class BookInfo {

    String id;
    String name;
    String description;
    Bitmap image;
    String content;

    public BookInfo(String name, String description, Bitmap image, String content) {
        this.id = generateID();
        this.name = name;
        this.description = description;
        this.image = image;
        this.content = content;
    }

    private String generateID(){
        return "book_" + System.currentTimeMillis();
    }

    public BookInfo(){
        this.id = generateID();
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

    //convert image from bitmap to byte array
    public byte[] getImageAsByteArray(){
        byte[] res = new byte[0];
        if(image!=null){
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG,0,outputStream);
            res = outputStream.toByteArray();
        }
        return res;
    }

    public void setImageFromByteArray(byte[] imageFromByteArray) {
        if(imageFromByteArray!=null){
            image = BitmapFactory.decodeByteArray(imageFromByteArray, 0, imageFromByteArray.length);
        }
    }
}
