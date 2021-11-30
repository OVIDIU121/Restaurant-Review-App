package com.example.myfffd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.myfffd.utility.Session;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class PostAddActivity extends AppCompatActivity {
    private EditText mPostTitle;
    private EditText mPostDesc;
    private Button mSubmitButton;
    private DatabaseReference mPostDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_add);
        mProgress = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mPostDatabase = FirebaseDatabase.getInstance().getReference().child("_forum_");
        mPostTitle = (EditText) findViewById(R.id.postTitleEt);
        mPostDesc = (EditText) findViewById(R.id.descriptionEt);
        mSubmitButton = (Button) findViewById(R.id.btn_forum_post_submit);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Posting to our database
                startPosting();
            }
        });

    }

    private void startPosting() {

        mProgress.setMessage("Posting to blog...");
        mProgress.show();
        final String titleVal = mPostTitle.getText().toString().trim();
        final String descVal = mPostDesc.getText().toString().trim();

        if (!TextUtils.isEmpty(titleVal) && !TextUtils.isEmpty(descVal)) {
                    DatabaseReference newPost = mPostDatabase.push();
                    Map<String, String> dataToSave = new HashMap<>();
                    dataToSave.put("title", titleVal);
                    dataToSave.put("desc", descVal);
                    dataToSave.put("alias", Session.ActiveSession.user.getAlias());
                    dataToSave.put("timestamp", String.valueOf(java.lang.System.currentTimeMillis()));
                    dataToSave.put("userid", mUser.getUid());
                    dataToSave.put("username", mUser.getEmail());
                    newPost.setValue(dataToSave);
                    mProgress.dismiss();
                    startActivity(new Intent(PostAddActivity.this, PostListActivity.class));
                    finish();

        }
    }
}
