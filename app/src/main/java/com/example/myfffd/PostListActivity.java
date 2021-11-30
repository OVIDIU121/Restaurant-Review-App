package com.example.myfffd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.myfffd.models.Post;
import com.example.myfffd.utility.PostAdaptor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostListActivity extends AppCompatActivity {

    private DatabaseReference mDatabaseReference;
    private RecyclerView recyclerView;
    private PostAdaptor postAdaptor;
    private List<Post> forumList;
    private FirebaseDatabase mDatabase;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postlist_main);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("_forum_");
        mDatabaseReference.keepSynced(true);
        forumList = new ArrayList<>();
        recyclerView =(RecyclerView) findViewById(R.id.recyclerViewForum);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Button btn_forum_main_addPost;
        btn_forum_main_addPost = findViewById(R.id.btn_forum_main_addPost);
        btn_forum_main_addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PostListActivity.this,PostAddActivity.class));
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();

        mDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Post post = dataSnapshot.getValue(Post.class);
                forumList.add(post);
                Collections.reverse(forumList);
                postAdaptor = new PostAdaptor(PostListActivity.this,forumList);
                recyclerView.setAdapter(postAdaptor);
                postAdaptor.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

}