package com.example.myfffd.account;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * The type Register.
 */
public class Register extends AppCompatActivity {
    /**
     * The Et fname.
     */
    EditText et_fname, /**
     * The Et sname.
     */
    et_sname, /**
     * The Et email.
     */
    et_email, /**
     * The Et pw.
     */
    et_pw, /**
     * The Et cpw.
     */
    et_cpw, /**
     * The Et reg alias.
     */
    et_reg_alias;
    /**
     * The Register.
     */
    Button register;

    /**
     * The M auth.
     */
//Declaring the firebaseAuth object
    FirebaseAuth mAuth;
    /**
     * The Dbref.
     */
    DatabaseReference dbref;
    private CheckBox id_checkbox;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        /*Define the variables and bind the to the view ID`s
        * Set the FirebaseAuth and FirebaseDatabase instances*/
        mAuth = FirebaseAuth.getInstance();
        dbref = FirebaseDatabase.getInstance().getReference("_user_");
        et_email = findViewById(R.id.et_reg_email);
        et_pw = findViewById(R.id.et_reg_pw);
        et_cpw = findViewById(R.id.et_reg_cpw);
        et_fname = findViewById(R.id.et_reg_fname);
        et_sname = findViewById(R.id.et_reg_sname);
        et_reg_alias = findViewById(R.id.et_reg_alias);
        id_checkbox = findViewById(R.id.id_reg_check);
        register = findViewById(R.id.btn_reg_register);

        register.setOnClickListener(new View.OnClickListener() {
            /**
             * When the register button is clicked start the registration checks
             */
            @Override
            public void onClick(View view) {
            /*Check that the provided fields are not empty and that the checkbox is ticked*/
                if (!TextUtils.isEmpty(et_pw.getText().toString()) &&
                        !TextUtils.isEmpty(et_cpw.getText().toString()) &&
                        !TextUtils.isEmpty(et_email.getText().toString()) &&
                        !TextUtils.isEmpty(et_fname.getText().toString()) &&
                        !TextUtils.isEmpty(et_sname.getText().toString()) &&
                        !TextUtils.isEmpty(et_reg_alias.getText().toString()) &&
                        id_checkbox.isChecked()) {
                    /*Check the the pw and cpw match*/
                    if (et_pw.getText().toString().compareTo(et_cpw.getText().toString()) == 0) {
                        /*Check that the password has at least 6 characters*/
                        if (et_pw.getText().toString().length() >= 6) {
                            /*Check that the provided text is in email format*/
                            if (AuthenticationUtility.isEmailValid(et_email.getText().toString())) {
                                /*Check that the text entered as alias is not already taken by other users*/
                                dbref.orderByChild("alias").equalTo(et_reg_alias.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        /*If a snapshot that has the entered text exists do not create account*/
                                        if(snapshot.exists()){
                                            Toast.makeText(Register.this,"Alias is already taken, please try a different one !",Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            /*Registering the user*/
                                            mAuth.createUserWithEmailAndPassword(et_email.getText().toString(), et_pw.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    // Save in authentification
                                                    String auth_id = mAuth.getUid();
                                                    /*Set the deafult picture for the account*/
                                                    final String default_pic = "https://firebasestorage.googleapis.com/v0/b/mobileappdevelopment-15143.appspot.com/o/profile_photos%2Fblank_user.png?alt=media&token=b435b87d-f63d-483f-967e-9267b1fc62eb";
                                                    //capture the fn and sn and save to database
                                                    User user = new User(et_fname.getText().toString(), et_sname.getText().toString(), et_email.getText().toString(),
                                                            et_pw.getText().toString(), mAuth.getUid(), et_reg_alias.getText().toString(), "user", default_pic);
                                                    // Save the object
                                                    dbref.child(auth_id).setValue(user);
                                                    Session.ActiveSession.user = user; // save the user in Active Session
                                                    Toast.makeText(Register.this, "Registration succesfull", Toast.LENGTH_LONG).show();
                                                    startActivity(new Intent(Register.this, MainActivity.class));
                                                    finish();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(Register.this, "Email is already registered, please use another email address !", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                                /*Give various toasts to the user based on the entered text*/
                            } else {
                                Toast.makeText(Register.this, "Email is not valid, please enter a valid e-mail address !", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Register.this, "Password must be at least 6 characters !", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Register.this, "The password does not match the confirmed password !", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Register.this, "Please make sure all fields are completed and that you agree to the Terms and Conditions !", Toast.LENGTH_LONG).show();
                }
            }
        });

        TextView textView = findViewById(R.id.id_reg_checktext);
        final String text = "I accept the Terms of Service and Privacy Policy";
        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View v) {
                //if conditions

            }

        };
        ss.setSpan(clickableSpan, 13, 48, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        /*When the text is clicked take the user to next activity*/
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, TermsAndConditions.class));
                finish();
            }
        });
    }
}