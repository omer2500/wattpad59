package com.example.omer.wattpad59;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omer.wattpad59.core.BookInfo;
import com.example.omer.wattpad59.database.MyInfoManager;

import java.util.List;

public class ReadBooksActivity extends AppCompatActivity {

    TextView textView, category, etImageTxt;
    String bookContent, bookName, bookId, bookDescription, wattpadId;
    byte[] BAbookImage;
    Bitmap bookImage;
    Button editBtn, submitBtn;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    ImageView image;
    EditText name, description, content;
    AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_books);

        //assign variables for xml attributes
        textView = (TextView) findViewById(R.id.readBookTextView);
        category = (TextView) findViewById(R.id.readBookCategory);
        bookContent = getIntent().getStringExtra("content");  //Get the book content that was transferred
        bookName = getIntent().getStringExtra("name");  //Get the book name that was transferred
        bookId = getIntent().getStringExtra("id");  //Get the book id that was transferred
        bookDescription = getIntent().getStringExtra("description");  //Get the book description that was transferred
        BAbookImage = getIntent().getByteArrayExtra("image");  //Get the book name that was transferred
        wattpadId = getIntent().getStringExtra("wattpadId");   //Get the book category that was transferred
        bookImage = BitmapFactory.decodeByteArray(BAbookImage, 0, BAbookImage.length);

        textView.setText(bookContent); //Show the book content
        category.setText(wattpadId);  //Show the book category
        setTitle(bookName);
        editBtn = (Button)findViewById(R.id.editBtn);


        //Opening dialog box for editing book details, when clicking on EditBtn
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ReadBooksActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_edit, null);

                etImageTxt = (TextView) mView.findViewById(R.id.etImageTxt);
                name = (EditText) mView.findViewById(R.id.etName);
                description = (EditText) mView.findViewById(R.id.etDescription);
                content = (EditText) mView.findViewById(R.id.etContent);
                image = (ImageView) mView.findViewById(R.id.etImage);
                submitBtn = (Button) mView.findViewById(R.id.submitBtn);

                name.setText(bookName);
                description.setText(bookDescription);
                content.setText(bookContent);
                image.setImageBitmap(bookImage);

                //Open  phone gallery when clicking on "Add cover"
                etImageTxt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openGallery();
                    }
                });

                //Update data when clicking on submit button
                submitBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String newName = name.getText().toString();
                        String newDescription = description.getText().toString();
                        Bitmap newImage = null;
                        if(image.getDrawable() != null){
                            newImage = ((BitmapDrawable)image.getDrawable()).getBitmap();
                        }
                        String newContent = content.getText().toString();
                        if(name.length() !=0 && description.length() !=0 && content.length() !=0){
                            updateBook(bookId, newName, newDescription, newImage, newContent, wattpadId);
                            toastMessage("Updated Successfully!");
                            dialog.cancel();
                        }else{
                            toastMessage("You must fill everything");
                        }

                    }
                });

                mBuilder.setView(mView);
                dialog  = mBuilder.create();
                dialog.show();


            }
        });


    }

    //update book in SQLite DB and in external DB
    public void updateBook(String id, String title, String description, Bitmap image, String content, String wattpadId){
        BookInfo book = new BookInfo(id, title, description, image, content, wattpadId);
        MyInfoManager.getInstance().updateBook(book);
    }

    //add a cover image from the gallery/camera
    private void openGallery(){//open the gallery
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,PICK_IMAGE);
    }

    //set the selected image as the imageView
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            image.setImageURI(imageUri);
        }
    }

    //create toast message
    public void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
