package com.example.omer.wattpad59;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Yarden-PC on 27-Nov-17.
 */

public class AddStoryInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_story_info);

        TextView title = (TextView) findViewById(R.id.activityTitleAddStoryInfo);
        title.setText("This is activity add story info");


    }
}
