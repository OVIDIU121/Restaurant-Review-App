package com.example.myfffd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myfffd.account.Login;
import com.example.myfffd.forum.ForumMain;
import com.example.myfffd.restaurant.Restaurant_Activity;
import com.example.myfffd.streetfood.StreetFoodActivity;
import com.example.myfffd.utility.Session;
import com.google.firebase.auth.FirebaseAuth;

/**
 * The type Main activity.
 */
public class MainActivity extends NavigationMenuActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Define the variables and bind the to the view ID`s*/
        Button btn_logout;
        btn_logout = findViewById(R.id.btn_dash_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Initiate the Firebase Auth*/
                FirebaseAuth mAuth;
                mAuth = FirebaseAuth.getInstance();
                try {
                    /*SignOut user from FB and from active session*/
                    mAuth.signOut();
                    Session.ActiveSession.logout();
                    Toast.makeText(MainActivity.this, "Logged out successfully !", Toast.LENGTH_LONG).show();
                    /*Take user to the login activity*/
                    startActivity(new Intent(MainActivity.this, Login.class));
                    finish(); // Destroy activity A and not exist in Back stack
                } catch (Exception exception) {
                    Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show(); // show error, to be removed
                }
            }
        });

        ImageView img_main_restaurants;
        img_main_restaurants = findViewById(R.id.img_main_restaurants);
        img_main_restaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Start next activity based on user choice*/
                startActivity(new Intent(MainActivity.this, Restaurant_Activity.class));

            }
        });

        ImageView img_main_streetfood;
        img_main_streetfood = findViewById(R.id.img_main_streetfood);
        img_main_streetfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
                /*Start next activity based on user choice*/{
                startActivity(new Intent(MainActivity.this, StreetFoodActivity.class));

            }
        });
        ImageView img_main_forum;
        img_main_forum = findViewById(R.id.img_main_forum);
        img_main_forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Start next activity based on user choice*/
                startActivity(new Intent(MainActivity.this, ForumMain.class));

            }
        });
    }

}