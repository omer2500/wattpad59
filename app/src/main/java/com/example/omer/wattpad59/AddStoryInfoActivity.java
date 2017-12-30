package com.example.omer.wattpad59;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Yarden-PC on 27-Nov-17.
 */

public class AddStoryInfoActivity extends FragmentActivity {

    private EditText storyTitle, storyDescription, storyContent;
    private Button publishButton;
    private ImageView imageView;
    TextView textView;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_story_info);
        storyTitle = (EditText) findViewById(R.id.storyTitle);
        storyDescription = (EditText) findViewById(R.id.storyDescription);
        storyContent = (EditText) findViewById(R.id.storyContent);
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
                String title = storyTitle.getText().toString();
                String description = storyDescription.getText().toString();
                Bitmap image;
                if(imageView.getDrawable() != null){
                    image = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                }else{
                    image = null;
                }
                String content = storyContent.getText().toString();
                if(storyTitle.length() !=0 && storyDescription.length() !=0 && storyContent.length() !=0 && image !=null){
                    addData(title, description, image, content);
                    //Reset all input fields
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

    //create a new book and insert in into the DB
    public void addData(String title, String description, Bitmap image, String content){
        BookInfo book = new BookInfo(title, description, image, content, null);
        MyInfoManager.getInstance().addNewBook(book);
    }

    //create customizable toast
    public void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
