package com.example.myfffd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfffd.models.User;
import com.example.myfffd.utility.AuthenticationUtility;
import com.example.myfffd.utility.RandomStringGenerator;
import com.example.myfffd.utility.Session;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity
{
    EditText et_fname, et_sname, et_email, et_pw, et_cpw;
    Button register;

     //Declaring the firebaseAuth object
    FirebaseAuth mAuth;
    DatabaseReference dbref;
    private CheckBox id_checkbox;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Initializing the FirebaseAuth object
        mAuth = FirebaseAuth.getInstance();
        dbref = FirebaseDatabase.getInstance().getReference("_user_");
        et_email = findViewById(R.id.et_reg_email);
        et_pw = findViewById(R.id.et_reg_pw);
        et_cpw = findViewById(R.id.et_reg_cpw);
        et_fname = findViewById(R.id.et_reg_fname);
        et_sname = findViewById(R.id.et_reg_sname);
        id_checkbox = findViewById(R.id.id_reg_check);
        register = findViewById(R.id.btn_reg_register);

        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                if (!TextUtils.isEmpty(et_pw.getText().toString()) &&
                        !TextUtils.isEmpty(et_cpw.getText().toString()) &&
                        !TextUtils.isEmpty(et_email.getText().toString())&&
                        !TextUtils.isEmpty(et_fname.getText().toString())&&
                        !TextUtils.isEmpty(et_sname.getText().toString())&&
                        id_checkbox.isChecked()) {
                    if (et_pw.getText().toString().compareTo(et_cpw.getText().toString()) == 0) {
                        if (et_pw.getText().toString().length() >= 6) {
                            if (AuthenticationUtility.isEmailValid(et_email.getText().toString())){
                                //3. Register user
                                mAuth.createUserWithEmailAndPassword(et_email.getText().toString(), et_pw.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        // Save in authentification
                                        String alias = "ChangeMe_" + RandomStringGenerator.RandomString() + "_";
                                        String auth_id = mAuth.getUid();
                                        String default_pic = "https://firebasestorage.googleapis.com/v0/b/mobileappdevelopment-15143.appspot.com/o/profile_photos%2Fblank_user.png?alt=media&token=b435b87d-f63d-483f-967e-9267b1fc62eb";
                                        //capture the fn and sn and save to database
                                        User user = new User(et_fname.getText().toString(), et_sname.getText().toString(), et_email.getText().toString(), et_pw.getText().toString(), mAuth.getUid(), alias, "user", default_pic);

                                        //3. Save the object
                                        dbref.child(auth_id).setValue(user);
                                        Session.ActiveSession.user = user; // save the user in Active Session
                                        Toast.makeText(Register.this, "Registration succesfull", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(Register.this, MainActivity.class));
                                        finish();
                                    }

                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Register.this, "Email is already registere, please use another email address !", Toast.LENGTH_SHORT).show();
                                    }
                                });
                        }
                            else {
                                Toast.makeText(Register.this,"Email is not valid, please enter a valid e-mail address !",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(Register.this,"Password must be at least 6 characters !",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(Register.this,"The password does not match the confirmed password !",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Register.this,"Please make sure all fields are completed and that you agree to the Terms and Conditions !",Toast.LENGTH_LONG).show();
                }
                }
        });

        TextView textView = findViewById(R.id.id_reg_checktext);
        String text = "I accept the Terms of Service and Privacy Policy";
        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick( View v) {
                //if conditions

            }

        };
        ss.setSpan(clickableSpan, 13,48, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, TermsAndConditions.class));
                finish();
            }
        });
    }
}