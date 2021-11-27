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

import com.example.myfffd.models.User;
import com.example.myfffd.utility.AuthenticationUtility;
import com.example.myfffd.utility.Session;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView textView = findViewById(R.id.tv_main_regnow);
        String text = "Not a foodie yet? Register Now, it's free!";
        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick( View v) {

            }

        };
        ss.setSpan(clickableSpan, 18,31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        textView = (TextView) findViewById(R.id.tv_main_regnow);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
        //login
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        EditText login_email, login_password;
        Button btn_login;
        login_email = findViewById(R.id.tv_email_pw_reset);
        login_password = findViewById(R.id.login_password);
        btn_login = findViewById(R.id.btn_rest_add);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sEmail =login_email.getText().toString();
                String sPass = login_password.getText().toString();
                if(!AuthenticationUtility.isEmailValid(sEmail)) // check field not empty
                {
                    Toast.makeText(Login.this, "Please Check Your login Credentials !",Toast.LENGTH_LONG).show();
                    login_email.requestFocus();
                    return;
                }
                if(!AuthenticationUtility.isPasswordValid(sPass))// check field not empty
                {
                    Toast.makeText(Login.this, "Please Check Your login Credentials !",Toast.LENGTH_LONG).show();
                    login_password.requestFocus();
                    return;
                }
                    mAuth.signInWithEmailAndPassword(sEmail,sPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                FirebaseDatabase.getInstance().getReference("_user_")
                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for (DataSnapshot dss: snapshot.getChildren()){
                                            User current_user = dss.getValue(User.class);
                                            if(mAuth.getUid().equals(current_user.getAuth_id())) {
                                                Session.ActiveSession.user = current_user; // save user in active session
                                            }
                                        }
                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        System.out.println(error);
                                    }
                                });
                                startActivity(new Intent(Login.this,MainActivity.class));// what activity to start if login is successful
                                Toast.makeText(Login.this,"Login Successful !",Toast.LENGTH_SHORT).show();
                                finish();
                                // save the current logged in user details in active session
                            }
                            else
                            {
                                Toast.makeText(Login.this,"Please Check Your login Credentials",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            }
        });
        TextView forgotPW;
        forgotPW = findViewById(R.id.tv_main_forgotpw);
        forgotPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, ForgotPassword.class));
                finish();
            }
        });
    }

}