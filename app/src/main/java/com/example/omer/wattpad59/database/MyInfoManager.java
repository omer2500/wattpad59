package com.example.omer.wattpad59.database;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.example.omer.wattpad59.core.BookInfo;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yarden-PC on 11-Dec-17.
 * This is the singleton
 */

public class MyInfoManager {

    private static MyInfoManager instance=null;
    private Context context;
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
    public void onPostUpdate(byte[] res, ResStatus status) {
        Toast.makeText(context,"Sync finished...status " + status.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPostUpdate(JSONObject res, ResStatus status) {
        if(res!=null){
            Toast.makeText(context,"Sync finished...status " + res.toString(),Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Sync finished...status " + status.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPostUpdate(Bitmap res, ResStatus status) {
    }

    public Bitmap getTakenPostPicture() {
        return takenPostPicture;
    }

    public void setTakenPostPicture(Bitmap takenPostPicture) {
        this.takenPostPicture = takenPostPicture;
    }

    public String getMyUserId() {
        return myUserId;
    }

    public void setMyUserId(String myUserId) {
        this.myUserId = myUserId;
    }

    public void openDataBase(Context ctx){
        this.context = ctx;
        dataBase = new PostsDataBase(context);
        dataBase.open();
    }


    public void addNewPost(PostInfo post){
        if(dataBase!=null) {
            if(dataBase.addNewPostInfo(post)) {
                NetworkConnector.getInstance().sendRequestToServer(NetworkConnector.INSERT_POST_REQ, post, instance);
            }

        }
    }

    public List<PostInfo> getAllPosts(){
        List<PostInfo> list = new ArrayList<PostInfo>();
        if(dataBase!=null) {
            list = dataBase.getAllPosts();
        }
        return list;
    }

    public List<PostInfo> getAllMyPosts(){

        List<PostInfo> list = new ArrayList<PostInfo>();
        if(dataBase!=null) {
            list = dataBase.getAllMyPosts();
        }
        return list;

    }


    public boolean deletePost(PostInfo post){
        boolean result = false;
        if(dataBase!=null) {
            result=dataBase.deletePostInfo(post);
            if(result){
                NetworkConnector.getInstance().sendRequestToServer(NetworkConnector.DELETE_POST_REQ, post, instance);
            }
        }
        return result;
    }

    public boolean updatePost(PostInfo post){
        boolean result = false;
        if(dataBase!=null) {
            result =dataBase.updatePostInfo(post);
            if(result){
                NetworkConnector.getInstance().sendRequestToServer(NetworkConnector.INSERT_POST_REQ, post, instance);
            }
        }
        return result;
    }

    public void setEditPost(PostInfo editPost) {
        this.editPost = editPost;
    }

    public PostInfo getEditPost() {
        return editPost;
    }

    public void updatePosts(JSONObject res) {
        if(dataBase==null){
            return;
        }
        List<PostInfo> posts = PostInfo.parseJson(res);
        for(PostInfo post:posts){
            if(!dataBase.addNewPostInfo(post)) {
                dataBase.updatePostInfo(post);
            }
        }
    }


    public void updatePostImage(PostInfo post){
        if(post.getImg()!=null) {
            String itemId = post.getId();
            if(dataBase!=null){
                dataBase.updatePostImage(post);
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
    public boolean addNewBook(BookInfo book){
        return databaseHelper.addNewBookInfo(book);
    }

    //Get all books in DB. Calling the getAllBooks function in DatabaseHelper activity
    public List<BookInfo> getAllBooks(){
        return databaseHelper.getAllBooks();
    }

    //Delete a book. Calling the delete function in DatabaseHelper activity
    public boolean deleteBook(String id){
        return databaseHelper.delete(id);
    }
}
