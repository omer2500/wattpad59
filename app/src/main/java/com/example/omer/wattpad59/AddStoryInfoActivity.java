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

import com.example.omer.wattpad59.core.BookInfo;
import com.example.omer.wattpad59.database.MyInfoManager;


/**
 * Created by Yarden-PC on 27-Nov-17.
 */

public class AddStoryInfoActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private EditText storyId, storyTitle, storyDescription, storyContent;
    private Button publishButton;
    private ImageView imageView;
    private Spinner wattpadIdSpinner;
    String category;

    TextView textView;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_story_info);

        //assigning xml attributes to a variable
        storyId = (EditText) findViewById(R.id.storyId);
        storyTitle = (EditText) findViewById(R.id.storyTitle);
        storyDescription = (EditText) findViewById(R.id.storyDescription);
        storyContent = (EditText) findViewById(R.id.storyContent);
        wattpadIdSpinner =(Spinner)findViewById(R.id.wattpadId);
        imageView = (ImageView)findViewById(R.id.addCoverImageView) ;
        textView = (TextView)findViewById(R.id.addCoverTextView) ;
        publishButton = (Button) findViewById(R.id.publishButton);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cat_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        wattpadIdSpinner.setAdapter(adapter);
        wattpadIdSpinner.setOnItemSelectedListener(this);

        //open dataBase Connection
        MyInfoManager.getInstance().openDatabase(this);



        //Open gallery when clicking on "Add cover"
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        //set toolbar title
        setTitle(R.string.add_story_info);

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
                Bitmap image,photo;
                if(imageView.getDrawable() != null){
                    photo=((BitmapDrawable)imageView.getDrawable()).getBitmap();
                    image = getResizedBitmap(photo, 200);

                }else{
                    image = null;
                }
                String content = storyContent.getText().toString();
                String wattpadId = category;
                if(storyId.length() != 0 && storyTitle.length() !=0 && storyDescription.length() !=0 && storyContent.length() !=0){
                    addData(id, name, description, image, content, wattpadId);
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

    //create a new book and insert in into the SQLite DB and the MySQL
    public void addData(String id, String title, String description, Bitmap image, String content, String wattpad_id){
        BookInfo book = new BookInfo(id, title, description, image, content, wattpad_id);
        MyInfoManager.getInstance().addNewBook(book);
    }

    //create Toast Message
    public void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    //will execute when user choose book category
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        category = (String)parent.getItemAtPosition(pos);
        Log.d("choose a category:", category);
    }

    //Deafult value for category if not selected
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        category="Action";
    }

    /**
     * reduces the size of the image
     * @param image
     * @param maxSize
     * @return
     */
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

}


