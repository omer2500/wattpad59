package com.example.omer.wattpad59;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.util.ArrayList;
import java.util.List;


/**
 * Created by omer on 27/11/2017.
 */

public class BooksActivity extends AppCompatActivity {

    private ListView bookListView;
    private customAdapter2 adapter;
    List<BookInfo> bookList;
    //favorite books array
    static List<BookInfo> bookListfav=new ArrayList<>();
    Integer position;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_activity);

        bookListView = (ListView)findViewById(R.id.listView);

        //set the chosen category from Browse_fragment to  variable
        String data=getIntent().getExtras().getString("str");
        bookList=new ArrayList<>();

        //inserting the books to array according to user choice from browser fragment
        setContent(data);

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


    }


    //Set the list according to the category
    public void setContent(String data) {
        switch (data) {
            case "action":
                //bookList = MyInfoManager.getInstance().getAllBooks("action");
                //convertToBitmap(R.drawable.actionbook1);
                bookList.add(new BookInfo(convertToBitmap(R.drawable.actionbook1), "Breaker", "this is book1"));
                bookList.add(new BookInfo(convertToBitmap(R.drawable.actionbook2), "The Eye Of Minds", "this is book2"));
                bookList.add(new BookInfo(convertToBitmap(R.drawable.actionbook3), "Loose Ends", "this is book3"));
                bookList.add(new BookInfo(convertToBitmap(R.drawable.actionbook4), "Hunger Games", "this is book4"));
                bookList.add(new BookInfo(convertToBitmap(R.drawable.actionbook5), "In The Blood", "this is book5"));
                Log.d("TAG","inserted action books to array");

                break;

            case "fantasy":
                bookList.add(new BookInfo(convertToBitmap(R.drawable.fantacybook1),"The Hundred Thousand Kindoms","this is book1"));
                bookList.add(new BookInfo(convertToBitmap(R.drawable.fantacybook2),"The Stone In The Skull","this is book2"));
                bookList.add(new BookInfo(convertToBitmap(R.drawable.fantacybook3),"Harry Potter","this is book3"));
                bookList.add(new BookInfo(convertToBitmap(R.drawable.fantacybook4),"Dragon Rider","this is book4"));
                bookList.add(new BookInfo(convertToBitmap(R.drawable.fantacybook5),"The White Dragon","this is book5"));
                Log.d("TAG","inserted fantasy books to array");
                break;

            case "horror":
                bookList.add(new BookInfo(convertToBitmap(R.drawable.horrorbook1),"The Devils Cat","this is book1"));
                bookList.add(new BookInfo(convertToBitmap(R.drawable.horrorbook2),"The Ritual","this is book2"));
                bookList.add(new BookInfo(convertToBitmap(R.drawable.horrorbook3),"Dead Place","this is book3"));
                bookList.add(new BookInfo(convertToBitmap(R.drawable.horrorbook4),"To Watch You Bleed","this is book4"));
                bookList.add(new BookInfo(convertToBitmap(R.drawable.horrorbook5),"Isnt That Funn","this is book5"));
                Log.d("TAG","inserted horror books to array");
                break;

            case "comedy":
                bookList.add(new BookInfo(convertToBitmap(R.drawable.comedybook1), "Popeye", "this is book1"));
                bookList.add(new BookInfo(convertToBitmap(R.drawable.comedybook2), "I Must Say", "this is book2"));
                bookList.add(new BookInfo(convertToBitmap(R.drawable.comedybook3), "The Best Intentions", "this is book3"));
                bookList.add(new BookInfo(convertToBitmap(R.drawable.comedybook4), "Maxx Comedy", "this is book4"));
                bookList.add(new BookInfo(convertToBitmap(R.drawable.comedybook5), "Language OF A Broken Heart", "this is book5"));
                Log.d("TAG","inserted comedy books to array");
                break;

            case "kids":
              bookList.add(new BookInfo(convertToBitmap(R.drawable.kidsbook1),"The Cat In The Hat","this is book1"));
                bookList.add(new BookInfo(convertToBitmap(R.drawable.kidsbook2),"Little Mother Dragons","this is book2"));
                bookList.add(new BookInfo(convertToBitmap(R.drawable.kidsbook3),"The Nose Book","this is book3"));
                bookList.add(new BookInfo(convertToBitmap(R.drawable.kidsbook4),"The Gruffalo","this is book4"));
                bookList.add(new BookInfo(convertToBitmap(R.drawable.kidsbook5),"The Wizard Of Oz","this is book5"));
                Log.d("TAG","inserted kids books to array");
                break;

            case "romance":
              bookList.add(new BookInfo(convertToBitmap(R.drawable.romancebook1),"Impulsive","this is book1"));
                bookList.add(new BookInfo(convertToBitmap(R.drawable.romancebook2),"Pirate Conquest","this is book2"));
                bookList.add(new BookInfo(convertToBitmap(R.drawable.romancebook3),"The Target","this is book3"));
                bookList.add(new BookInfo(convertToBitmap(R.drawable.romancebook4),"Time To Forgive","this is book4"));
                bookList.add(new BookInfo(convertToBitmap(R.drawable.romancebook5),"Trust You","this is book5"));
                Log.d("TAG","inserted romance books to array");
                break;

            case "war":
              bookList.add(new BookInfo(convertToBitmap(R.drawable.warbook1),"Whose War Is It?","this is book1"));
                bookList.add(new BookInfo(convertToBitmap(R.drawable.warbook2),"World War 2","this is book2"));
                bookList.add(new BookInfo(convertToBitmap(R.drawable.warbook3),"World War Z","this is book3"));
                bookList.add(new BookInfo(convertToBitmap(R.drawable.warbook4),"The Whell OF Osheim","this is book4"));
                bookList.add(new BookInfo(convertToBitmap(R.drawable.warbook5),"War Hawk","this is book5"));
                Log.d("TAG","inserted war books to array");
                break;
        }
    }

    //converting drawble toBitmap
    private Bitmap convertToBitmap(int image){

        Bitmap bitmap = BitmapFactory.decodeResource(getResources() , image);

        return bitmap;
    }

    //return the favorite books array.
    public List<BookInfo> getBookListfav() {
        return bookListfav;
    }

    //create customizable toast
    public void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
