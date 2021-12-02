package com.example.myfffd.forum;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myfffd.MainActivity;
import com.example.myfffd.NavigationMenuActivity;
import com.example.myfffd.R;
import com.example.myfffd.utility.Session;

/**
 * The type Admin dashboard.
 */
public class ForumMain extends NavigationMenuActivity {

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*Define the variables and bind the to the view ID`s*/
        setContentView(R.layout.activity_forum_main);
        ImageView image_template_deals, image_template_offtopic, image_template_suggestions;
        Button btn_forum_main_back;
        image_template_deals = findViewById(R.id.image_template_deals);
        image_template_offtopic = findViewById(R.id.image_template_offtopic);
        image_template_suggestions = findViewById(R.id.image_template_suggestions);
        btn_forum_main_back = findViewById(R.id.btn_forum_main_back);
        /*Take the user to the next activity based on the clicked option*/
        image_template_deals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Set the static variable forum_choice based on the user choice
                 * This is done for code reusability*/
                Intent i = new Intent(ForumMain.this, PostListActivity.class);
                Session.ActiveSession.forum_choice = "_deals_";
                startActivity(i);
            }
        });
        /*Take the user to the next activity based on the clicked option*/
        image_template_offtopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Set the static variable forum_choice based on the user choice
                * This is done for code reusability*/
                Intent i = new Intent(ForumMain.this, PostListActivity.class);
                Session.ActiveSession.forum_choice = "_offtopic_";
                startActivity(i);
            }
        });
        /*Take the user to the next activity based on the clicked option*/
        image_template_suggestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Set the static variable forum_choice based on the user choice
                 * This is done for code reusability*/
                Intent i = new Intent(ForumMain.this, PostListActivity.class);
                Session.ActiveSession.forum_choice = "_suggestions_";
                startActivity(i);
            }
        });
        /*Take the user to the next activity based on the clicked option*/
        btn_forum_main_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ForumMain.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}