package com.example.myfffd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfffd.utility.AuthenticationUtility;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        Button btn_reset_pw,btn_reset_pw_back;
        TextView email_pw_reset;
        email_pw_reset = findViewById(R.id.tv_email_pw_reset);
        btn_reset_pw = findViewById(R.id.btn_reset_pw);
        btn_reset_pw_back = findViewById(R.id.btn_reset_pw_back);
        btn_reset_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sEmail = email_pw_reset.getText().toString();
                if (AuthenticationUtility.isEmailValid(sEmail))
                {
                    mAuth.sendPasswordResetEmail(sEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Verification email sent to: " + sEmail, Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Failed to send email to: " + sEmail + " please check that you provided the correct e-mail address !", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(),"Please check your login credentials !", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_reset_pw_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotPassword.this, Login.class));
                finish();
            }
        });
    }
}