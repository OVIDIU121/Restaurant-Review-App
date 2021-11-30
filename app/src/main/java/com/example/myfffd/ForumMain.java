package com.example.myfffd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfffd.utility.Session;

/**
 * The type Admin dashboard.
 */
public class ForumMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_main);
        ImageView image_template_deals, image_template_offtopic, image_template_suggestions;
        Button btn_forum_main_back;
        image_template_deals = findViewById(R.id.image_template_deals);
        image_template_offtopic = findViewById(R.id.image_template_offtopic);
        image_template_suggestions = findViewById(R.id.image_template_suggestions);
        btn_forum_main_back = findViewById(R.id.btn_forum_main_back);


        image_template_deals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ForumMain.this, PostListActivity.class);
                Session.ActiveSession.forum_choice = "_deals_";
                startActivity(i);
            }
        });
        image_template_offtopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ForumMain.this, PostListActivity.class);
                Session.ActiveSession.forum_choice = "_offtopic_";
                startActivity(i);
            }
        });
        image_template_suggestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ForumMain.this, PostListActivity.class);
                Session.ActiveSession.forum_choice = "_suggestions_";
                startActivity(i);
            }
        });
        btn_forum_main_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ForumMain.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}