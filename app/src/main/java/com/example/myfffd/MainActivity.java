package com.example.myfffd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfffd.utility.Session;
import com.google.firebase.auth.FirebaseAuth;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        Button btn_logout;
        btn_logout = findViewById(R.id.btn_dash_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth mAuth;
                mAuth = FirebaseAuth.getInstance();
                try {
                    mAuth.signOut();
                    Session.ActiveSession.logout();
                    Toast.makeText(MainActivity.this, "Logged out successfully !", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this, Login.class));
                    finish(); // Destroy activity A and not exist in Back stack
                } catch (Exception exception) {
                    Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show(); // show error, to be removed
                }
            }
        });
        ImageView tv_main_profile_pic;
        tv_main_profile_pic = findViewById(R.id.tv_main_profile_pic);
        tv_main_profile_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Profile.class));
            }
        });
        ImageView img_main_restaurants;
        img_main_restaurants = findViewById(R.id.img_main_restaurants);
        img_main_restaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, Restaurant_Activity.class));
            }
        });

        ImageView img_main_streetfood;
        img_main_streetfood = findViewById(R.id.img_main_streetfood);
        img_main_streetfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StreetFoodActivity.class));
            }
        });
        ImageView img_main_forum;
        img_main_forum = findViewById(R.id.img_main_forum);
        img_main_forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PostListActivity.class));
            }
        });
    }
}