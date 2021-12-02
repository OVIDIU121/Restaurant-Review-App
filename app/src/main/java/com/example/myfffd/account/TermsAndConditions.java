package com.example.myfffd.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfffd.R;

/**
 * The type Terms and conditions.
 */
public class TermsAndConditions extends AppCompatActivity {
    /**
     * The Button.
     */
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions);
        /*Define the variables and bind the to the view ID`s*/
        button = findViewById(R.id.btn_terms_back);
        /*Take the user to next activity*/
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TermsAndConditions.this, Register.class));
            }
        });
    }

}