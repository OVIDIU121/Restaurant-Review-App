package com.example.myfffd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfffd.account.Login;

/**
 * The type Welcome.
 */
public class Welcome extends AppCompatActivity {
    /**
     * The Welcome.
     */
    Button welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*Define the variables and bind the to the view ID`s*/
        setContentView(R.layout.activity_welcome);
        welcome = findViewById(R.id.welcome_button);
        welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Start next activity based on user choice*/
                Intent goToMain = new Intent(getApplicationContext(), Login.class);
                startActivity(goToMain);
            }
        });
    }
}