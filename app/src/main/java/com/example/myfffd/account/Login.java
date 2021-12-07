package com.example.myfffd.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfffd.MainActivity;
import com.example.myfffd.R;
import com.example.myfffd.models.User;
import com.example.myfffd.utility.AuthenticationUtility;
import com.example.myfffd.utility.Session;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * The type Login. This class will help the user login.
 */
public class Login extends AppCompatActivity {

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView textView = findViewById(R.id.tv_main_regnow);
        /*Start the register activity if the register text is clicked*/
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
        /*Define the variables and bind the to the view ID`s*/
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        EditText login_email;
        EditText login_password;
        Button btn_login;
        login_email = findViewById(R.id.tv_email_pw_reset);
        login_password = findViewById(R.id.login_password);
        btn_login = findViewById(R.id.btn_rest_add);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sEmail = login_email.getText().toString();
                String sPass = login_password.getText().toString();
                /*Check if the email is valid*/
                if (!AuthenticationUtility.isEmailValid(sEmail))
                {
                    Toast.makeText(Login.this, "Please Check Your login Credentials !", Toast.LENGTH_LONG).show();
                    login_email.requestFocus();
                    return;
                }
                /*Check if the password is valid*/
                if (!AuthenticationUtility.isPasswordValid(sPass))
                {
                    Toast.makeText(Login.this, "Please Check Your login Credentials !", Toast.LENGTH_LONG).show();
                    login_password.requestFocus();
                    return;
                }
                /*Try to login with the provided details*/
                mAuth.signInWithEmailAndPassword(sEmail, sPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseDatabase.getInstance().getReference("_user_")
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            for (DataSnapshot dss : snapshot.getChildren()) {
                                                User current_user = dss.getValue(User.class);
                                                /*Check that the logged in user has a user object in Realtime Database*/
                                                if (mAuth.getUid().equals(current_user.getAuth_id())) {
                                                    /*Save a copy of the user object in the Active Session static class*/
                                                    Session.ActiveSession.user = current_user;
                                                }
                                            }
                                        }
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            /*Error not handled yet*/
                                            System.out.println(error);
                                        }
                                    });
                            /*Start Main Activity when the login process is successful*/
                            startActivity(new Intent(Login.this, MainActivity.class));
                            Toast.makeText(Login.this, "Login Successful !", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else
                            {
                            Toast.makeText(Login.this, "Please Check Your login Credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println(e);
                        Toast.makeText(Login.this,"Please check your internet connectivity !",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        /*Create a listener for the forgot password text view and direct the user to the forgot password activity if pressed*/
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