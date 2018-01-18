package com.example.omer.wattpad59;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.omer.wattpad59.adapters.customAdapter2;
import com.example.omer.wattpad59.core.BookInfo;
import com.example.omer.wattpad59.database.MyInfoManager;
import com.example.omer.wattpad59.interfaces.CallBackListener;
import com.example.omer.wattpad59.network.utils.NetworkConnector;
import com.example.omer.wattpad59.network.utils.NetworkResListener;
import com.example.omer.wattpad59.network.utils.ResStatus;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by omer on 27/11/2017.
 */

public class BooksActivity extends AppCompatActivity implements CallBackListener, NetworkResListener{

    private ListView bookListView;
    private customAdapter2 adapter;
    List<BookInfo> bookList;
    //favorite books array
    static List<BookInfo> bookListfav=new ArrayList<>();
    Integer position;
private String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_activity);

        MyInfoManager.getInstance().openDatabase(this);
        NetworkConnector.getInstance().initialize(this);
        NetworkConnector.getInstance().updateBooksFeed(this);

        bookListView = (ListView)findViewById(R.id.listView);

        //set the chosen category from Browse_fragment to variable
         category=getIntent().getExtras().getString("str");
        bookList=new ArrayList<>();

        //inserting the books to array according to user choice from browser fragment
        setContent(category);

        //Init adapter
        adapter = new customAdapter2(getApplicationContext(), bookList);
        bookListView.setAdapter(adapter);


        //add book to library when long-clicking on listView item
        bookListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                AlertDialog.Builder alert = new AlertDialog.Builder(BooksActivity.this);
                alert.setMessage(R.string.alertDialogMessageFav).setCancelable(false)
                        .setPositiveButton(R.string.positiveButtonAlertDialog, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                BookInfo book = bookList.get(position);
                                String name = book.getName().toString();
                                bookListfav.add(book);
                                toastMessage(name + " added to Library"); //toast delete message (Not working with R.strings!)
                            }
                        })
                        .setNegativeButton(R.string.negativeButtonAlertDialog, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                alert.show();
                return true;
            }
        });

        //Set reading the book content when clicking on item
        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(BooksActivity.this, ReadBooksActivity.class);
                String content = bookList.get(i).getContent().toString();
                String name = bookList.get(i).getName().toString();
                String id = bookList.get(i).getId().toString();
                String description = bookList.get(i).getDescription().toString();
                byte[] image = bookList.get(i).getImgAsByteArray(bookList.get(i).getImage());
                String wattpadId = bookList.get(i).getWattpadId().toString();
                intent.putExtra("content", content);
                intent.putExtra("name", name);
                intent.putExtra("id", id);
                intent.putExtra("description", description);
                intent.putExtra("image", image);
                intent.putExtra("wattpadId", wattpadId);
                startActivity(intent);
            }
        });


    }


    //Set the list according to the chosen category
    public void setContent(String data) {
        switch (data) {
            case "action":
                //Init adapter
                bookList = MyInfoManager.getInstance().getAllBooksByCategory("Action");
                adapter = new customAdapter2(getApplicationContext(), bookList);
                bookListView.setAdapter(adapter);
                Log.d("TAG","inserted action books to array");


                break;

            case "fantasy":
                bookList = MyInfoManager.getInstance().getAllBooksByCategory("Fantasy");
                //Init adapter
                adapter = new customAdapter2(getApplicationContext(), bookList);
                bookListView.setAdapter(adapter);
                Log.d("TAG","inserted fantasy books to array");
                break;

            case "horror":
                bookList = MyInfoManager.getInstance().getAllBooksByCategory("Horror");
                //Init adapter
                adapter = new customAdapter2(getApplicationContext(), bookList);
                bookListView.setAdapter(adapter);
                Log.d("TAG","inserted horror books to array");
                break;

            case "comedy":
                bookList = MyInfoManager.getInstance().getAllBooksByCategory("Comedy");
                //Init adapter
                adapter = new customAdapter2(getApplicationContext(), bookList);
                bookListView.setAdapter(adapter);
                Log.d("TAG","inserted comedy books to array");
                break;

            case "kids":
                bookList = MyInfoManager.getInstance().getAllBooksByCategory("Kids");
                //Init adapter
                adapter = new customAdapter2(getApplicationContext(), bookList);
                bookListView.setAdapter(adapter);
                Log.d("TAG","inserted kids books to array");
                break;

            case "romance":
                bookList = MyInfoManager.getInstance().getAllBooksByCategory("Romance");
                //Init adapter
                adapter = new customAdapter2(getApplicationContext(), bookList);
                bookListView.setAdapter(adapter);
                Log.d("TAG","inserted romance books to array");
                break;

            case "war":
                bookList = MyInfoManager.getInstance().getAllBooksByCategory("War");
                //Init adapter
                adapter = new customAdapter2(getApplicationContext(), bookList);
                bookListView.setAdapter(adapter);
                Log.d("TAG","inserted war books to array");
                break;
        }
    }


    //return the favorite books array.
    public List<BookInfo> getBookListfav() {
        return bookListfav;
    }

    //create toast message
    public void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveButtonClicked() {

    }

    @Override
    public void onPreUpdate() {
    }

    @Override
    public void onBookUpdate(byte[] res, ResStatus status) {

    }

    @Override
    public void onBookUpdate(JSONObject res, ResStatus status) {
        if (status.equals(ResStatus.SUCCESS) && res != null) {
            MyInfoManager.getInstance().updateBooks(res);
            setContent(category);
        }
    }


    @Override
    public void onBookUpdate(Bitmap res, ResStatus status) {

    }




}
