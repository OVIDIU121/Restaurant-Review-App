package com.example.myfffd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // log out
        Button btn_logout;
        btn_logout = findViewById(R.id.btn_dash_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth mAuth;
                mAuth = FirebaseAuth.getInstance();
                try {
                    mAuth.signOut();
                    Toast.makeText(MainActivity.this, "Logged out successfully !", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this, Login.class));
                }
                catch (Exception exception)
                {
                    Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show(); // show error, to be removed
                }
            }
        });

    }
}