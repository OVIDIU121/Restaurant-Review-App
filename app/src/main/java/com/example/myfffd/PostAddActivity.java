package com.example.myfffd;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfffd.utility.Session;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Post add activity.
 */
public class PostAddActivity extends AppCompatActivity {
    private ImageView iv_post_add;
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
        mPostDatabase = FirebaseDatabase.getInstance().getReference().child("_forum_").child(Session.ActiveSession.forum_choice);
        mPostTitle = findViewById(R.id.postTitleEt);
        mPostDesc = findViewById(R.id.descriptionEt);
        mSubmitButton = findViewById(R.id.btn_forum_post_submit);
        iv_post_add = findViewById(R.id.iv_post_add);
        if(Session.ActiveSession.forum_choice.equals("_offtopic_")){
            iv_post_add.setImageResource(R.drawable.oftopic);
        }
        if(Session.ActiveSession.forum_choice.equals("_suggestions_")){
            iv_post_add.setImageResource(R.drawable.suggestions);
        }
        if(Session.ActiveSession.forum_choice.equals("_deals_")){
            iv_post_add.setImageResource(R.drawable.hotdeals);
        }

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
            startActivity(new Intent(this, PostListActivity.class));
            finish();

        }
    }
}
