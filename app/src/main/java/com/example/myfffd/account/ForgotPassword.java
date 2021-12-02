package com.example.myfffd.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myfffd.NavigationMenuActivity;
import com.example.myfffd.R;
import com.example.myfffd.utility.AuthenticationUtility;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

/**
 * The type Forgot password.
 * This class will help the user reset his password
 */
public class ForgotPassword extends NavigationMenuActivity {

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        /*Define the variables and bind the to the view ID`s*/

        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        Button btn_reset_pw;
        Button btn_reset_pw_back;
        TextView email_pw_reset;
        email_pw_reset = findViewById(R.id.tv_email_pw_reset);
        btn_reset_pw = findViewById(R.id.btn_reset_pw);
        btn_reset_pw_back = findViewById(R.id.btn_reset_pw_back);
        btn_reset_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Getting the email from the user and checking with FBaUTH if the email is used, if it is it will send an email*/
                String sEmail = email_pw_reset.getText().toString();
                if (AuthenticationUtility.isEmailValid(sEmail)) {
                    mAuth.sendPasswordResetEmail(sEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        /**
                         * When the task is complete send a Toast to the user
                         */
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                                Toast.makeText(getApplicationContext(), "Verification email sent to: " + sEmail, Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Failed to send email to: " + sEmail + " please check that you provided the correct e-mail address !", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Please check your login credentials !", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_reset_pw_back.setOnClickListener(new View.OnClickListener() {
            /**
             * Going back to the login activity when the back button is pressed
             */
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ForgotPassword.this, Login.class));
                finish();
            }
        });
    }
}