package com.example.omer.wattpad59;


/**
 * Created by NgocTri on 11/15/2015.
 */
public class bookTemplate {
    private int image;
    private String bookName;
    private String bookDescription;

    //Constructor

    public bookTemplate(int image, String bookName,String bookDescription) {
        this.image = image;
        this.bookName = bookName;
        this.bookDescription = bookDescription;
    }

    //Setter, getter

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }
}
