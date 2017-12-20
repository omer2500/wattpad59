package com.example.omer.wattpad59;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omer on 01/12/2017.
 */

public class MyBooks_Activity extends AppCompatActivity {

    private static final String TAG="myBooksActivity";
    DatabaseHelper mDatabaseHelper;
    private ListView mListView;
    private List<BookInfo> bookList;
    ImageButton deleteBtn;
    TextView bookId;
    customAdapter2 adapter;
    Integer position;
    String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mybooks_activity);
        setTitle(R.string.myBooks); //set toolbar title

        mListView =(ListView) findViewById(R.id.listView1) ;
        deleteBtn = findViewById(R.id.delete_btn);
        bookId = findViewById(R.id.bookName);

        bookList= MyInfoManager.getInstance().getAllBooks();

        //create the list adapter and set the adapter
        adapter = new customAdapter2(this, bookList);
        mListView.setAdapter(adapter);

        //delete a book with alertDialog when long-clicking on listView item
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                AlertDialog.Builder alert = new AlertDialog.Builder(MyBooks_Activity.this);
                alert.setMessage("Do You Want To Delete This Book?").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                BookInfo book = bookList.get(position);
                                String id = book.getId().toString();
                                delete(id);
                                bookList= MyInfoManager.getInstance().getAllBooks();
                                adapter = new customAdapter2(MyBooks_Activity.this, bookList);
                                mListView.setAdapter(adapter);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                alert.show();
                return true;
            }
        });

        //read book content
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MyBooks_Activity.this, ReadBooksActivity.class);
                content = bookList.get(i).getContent().toString();
                intent.putExtra("content", content);
                startActivity(intent);
            }
        });


        //delete book
        //mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            //@Override
            //public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //BookInfo book = bookList.get(i);
                //String id = book.getId().toString();
                //delete(id);
                //adapter.notifyDataSetChanged();
                //adapter = new customAdapter2(context, bookList);
                //mListView.setAdapter(adapter);
                //return true;
            //}
        //});


        //***************************BOTTOM NAVIGATION BAR*****************************************************
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        //bottom navigation color animation
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;

                switch (item.getItemId()){
                    case R.id.ic_home:
                        intent = new Intent(MyBooks_Activity.this, MainActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.ic_library:
                        intent = new Intent(MyBooks_Activity.this, LibraryActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.ic_add_story_info:
                        intent = new Intent(MyBooks_Activity.this, AddStoryInfoActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.ic_my_books:
                        intent= new Intent(MyBooks_Activity.this, MyBooks_Activity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });
        //***************************BOTTOM NAVIGATION BAR*****************************************************
    }

    public void delete(String id){
        MyInfoManager.getInstance().deleteBook(id);
    }

}
