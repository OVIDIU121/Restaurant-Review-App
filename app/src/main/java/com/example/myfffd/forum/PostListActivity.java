package com.example.myfffd.forum;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfffd.NavigationMenuActivity;
import com.example.myfffd.R;
import com.example.myfffd.models.Post;
import com.example.myfffd.utility.PostAdaptor;
import com.example.myfffd.utility.Session;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The type Post list activity.
 */
public class PostListActivity extends NavigationMenuActivity {
    /*Define the variables and bind the to the view ID`s*/
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
        /*Define the variables and bind the to the view ID`s*/
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        /*Select the correct Firebase reference based on the user`s previous choice*/
        mDatabaseReference = mDatabase.getReference().child("_forum_").child(Session.ActiveSession.forum_choice);
        /*Keep activity synced with Firebase*/
        mDatabaseReference.keepSynced(true);
        forumList = new ArrayList<>();
        /*Define the recyler view and bind it to the view ID*/
        recyclerView = findViewById(R.id.recyclerViewForum);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Button btn_forum_main_addPost;
        btn_forum_main_addPost = findViewById(R.id.btn_forum_main_addPost);
        /*Start next activity*/
        btn_forum_main_addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PostListActivity.this, PostAddActivity.class);
                startActivity(i);
                finish();
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        mDatabaseReference.addChildEventListener(new ChildEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                /*Get every post object from FB and add it to the forumList List*/
                Post post = dataSnapshot.getValue(Post.class);
                forumList.add(post);
                /*Sorting the list*/
                Collections.sort(forumList, new Comparator<Post>() {
                    @Override
                    public int compare(Post o1, Post o2) {
                        if (o1.getTimestamp() == null || o2.getTimestamp() == null)
                            return 0;
                        return o2.getTimestamp().compareTo(o1.getTimestamp());
                    }
                });
                postAdaptor = new PostAdaptor(PostListActivity.this, forumList);
                /*Send the post object list to the recycler view*/
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