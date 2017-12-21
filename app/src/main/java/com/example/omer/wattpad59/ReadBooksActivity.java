package com.example.omer.wattpad59;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class ReadBooksActivity extends AppCompatActivity {

    TextView textView;
    String content;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_books);

        textView = findViewById(R.id.readBookTextView);
        content = getIntent().getStringExtra("content");  //Get the book content that was transferred
        name = getIntent().getStringExtra("name");  //Get the book name that was transferred
        textView.setText(content); //Show the book content
        setTitle(name);


    }
}
