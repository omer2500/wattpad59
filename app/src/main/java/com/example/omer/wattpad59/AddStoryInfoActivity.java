package com.example.omer.wattpad59;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.omer.wattpad59.core.BookInfo;
import com.example.omer.wattpad59.database.MyInfoManager;
import com.example.omer.wattpad59.network.utils.VolleyMultipartRequest;

import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yarden-PC on 27-Nov-17.
 */

public class AddStoryInfoActivity extends Activity implements On {

    private EditText storyId, storyTitle, storyDescription, storyContent;
    private Button publishButton;
    private ImageView imageView;
    private Spinner wattpadId;

    TextView textView;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_story_info);

        storyId = (EditText) findViewById(R.id.storyId);
        storyTitle = (EditText) findViewById(R.id.storyTitle);
        storyDescription = (EditText) findViewById(R.id.storyDescription);
        storyContent = (EditText) findViewById(R.id.storyContent);
        wattpadId=(Spinner)findViewById(R.id.wattpadId);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cat_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        wattpadId.setAdapter(adapter);

        imageView = (ImageView)findViewById(R.id.addCoverImageView) ;
        textView = (TextView)findViewById(R.id.addCoverTextView) ;
        publishButton = (Button) findViewById(R.id.publishButton);

        MyInfoManager.getInstance().openDatabase(this);



        //Open gallery when clicking on "Add cover"
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        setTitle(R.string.add_story_info); //set toolbar title


        //***************************BOTTOM NAVIGATION BAR*****************************************************
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        //bottom navigation color animation
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.ic_home:
                        intent = new Intent(AddStoryInfoActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.ic_library:
                        intent = new Intent(AddStoryInfoActivity.this, LibraryActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.ic_add_story_info:
                        break;

                    case R.id.ic_my_books:
                        intent = new Intent(AddStoryInfoActivity.this, MyBooks_Activity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });


        //save the data inserted when clicking on the publish button
        publishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = storyId.getText().toString();
                String name = storyTitle.getText().toString();
                String description = storyDescription.getText().toString();
                Bitmap image;
                if(imageView.getDrawable() != null){
                    image = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                }else{
                    image = null;
                }
                String content = storyContent.getText().toString();
                if(storyId.length() != 0 && storyTitle.length() !=0 && storyDescription.length() !=0 && storyContent.length() !=0){
                    addData(id, name, description, image, content);
                    //Reset all input fields
                    storyId.setText("");
                    storyTitle.setText("");
                    storyDescription.setText("");
                    imageView.setImageURI(null);
                    storyContent.setText("");
                    toastMessage("Your story was successfully published!"); //toast delete message (Not working with R.strings!)
                    //the myBooks activity will open after adding the book
                    Intent intent = new Intent(AddStoryInfoActivity.this, MyBooks_Activity.class);
                    startActivity(intent);


                }else{
                    toastMessage("You must fill everything"); //toast delete message (Not working with R.strings!)
                }
            }
        });
    }

    //add a cover image from the gallery/camera
    private void openGallery(){ //open the gallery
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,PICK_IMAGE);
    }

    //set the selected image as the imageView
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }

    //create a new book and insert in into the SQLite DB and the external DB
    public void addData(String id, String title, String description, Bitmap image, String content){
        BookInfo book = new BookInfo(id, title, description, image, content);
        MyInfoManager.getInstance().addNewBook(book);
    }

    //create customizable toast
    public void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
