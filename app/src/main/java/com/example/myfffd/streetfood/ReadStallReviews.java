package com.example.myfffd.streetfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfffd.NavigationMenuActivity;
import com.example.myfffd.R;
import com.example.myfffd.forum.PostListActivity;
import com.example.myfffd.models.Post;
import com.example.myfffd.models.StreetFood;
import com.example.myfffd.models.User;
import com.example.myfffd.utility.Session;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Read stall reviews.
 */
public class ReadStallReviews extends NavigationMenuActivity {
    /**
     * The Index.
     */
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_stall_reviews);
        /*Define the variables and bind the to the view ID`s*/
        TextView tx_review_stall_read_name;
        final TextView tx_rest_stall_read_review;
        TextView tx_stall_read_review_alias;
        Button btn_review_stall_read_next;
        Button btn_review_stall_read_previous,btn_stall_delete_review;
        RatingBar rtb_review_stall_read_rating;
        StreetFood streetFood = getIntent().getParcelableExtra("STALL");
        String stall_id = streetFood.getName() + "-" + streetFood.getLocation();
        DatabaseReference dbref_stall = FirebaseDatabase.getInstance().getReference("_streetFood_").child(stall_id).child("review");
        List<String> idList = new ArrayList<String>();
        List<String> reviewList = new ArrayList<String>();
        final String[] reviewToDelete = new String[1];

        btn_stall_delete_review = findViewById(R.id.btn_stall_delete_review);
        tx_review_stall_read_name = findViewById(R.id.tx_stall_read_name);
        tx_rest_stall_read_review = findViewById(R.id.tx_stall_read_review);
        tx_stall_read_review_alias = findViewById(R.id.tx_stall_read_alias);
        btn_review_stall_read_next = findViewById(R.id.btn_stall_read_next);
        btn_review_stall_read_previous = findViewById(R.id.btn_stall_read_previous);
        rtb_review_stall_read_rating = findViewById(R.id.rtb_read_stall_rating);

        tx_review_stall_read_name.setText(streetFood.getName());

        dbref_stall.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i = 1;
                /*Check how many reviews are for the current StreetFood*/
                long count = snapshot.getChildrenCount();
                for (DataSnapshot dss : snapshot.getChildren()) {
                    idList.add(dss.getKey());
                    /*Add the review ids in a List*/
                    reviewList.add(dss.getValue(String.class));
                    /*Add all the reviews in a List*/
                    i++;
                    if (count < i) {
                        index = 0;
                        DatabaseReference dbref_user = FirebaseDatabase.getInstance().getReference("_user_");
                        dbref_user.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dss : snapshot.getChildren()) {
                                    User current_user = dss.getValue(User.class);
                                    if (idList.get(index).equals(current_user.getAuth_id())) {
                                        /*Find the User alias based on user auth_id stored with the review*/
                                        tx_stall_read_review_alias.setText(current_user.getAlias());
                                    }
                                    if(idList.get(index).equals(Session.ActiveSession.user.getAuth_id()) ||
                                            Session.ActiveSession.user.getType().equals("admin")){
                                        reviewToDelete[0] = idList.get(index);
                                        btn_stall_delete_review.setVisibility(View.VISIBLE);
                                    }
                                    else
                                    {
                                        btn_stall_delete_review.setVisibility(View.INVISIBLE);
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                System.out.println(error);
                            }
                        });
                        tx_rest_stall_read_review.setText(reviewList.get(index));
                        /*If the user presses the next post button display the next index in the review list,
                        while the index is less then the size of the list*/
                        btn_review_stall_read_next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (idList.size() > index + 1) {
                                    index++;
                                    dbref_user.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            for (DataSnapshot dss : snapshot.getChildren()) {
                                                User current_user = dss.getValue(User.class);
                                                /*Find the User alias based on user auth_id stored with the review*/
                                                if (idList.get(index).equals(current_user.getAuth_id())) {
                                                    tx_stall_read_review_alias.setText(current_user.getAlias());
                                                }
                                                if(idList.get(index).equals(Session.ActiveSession.user.getAuth_id()) ||
                                                        Session.ActiveSession.user.getType().equals("admin")){
                                                    reviewToDelete[0] = idList.get(index);
                                                    btn_stall_delete_review.setVisibility(View.VISIBLE);
                                                }
                                                else
                                                {
                                                    btn_stall_delete_review.setVisibility(View.INVISIBLE);
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            System.out.println(error);
                                        }
                                    });
                                    tx_rest_stall_read_review.setText(reviewList.get(index));
                                }
                            }
                        });
                        /*If the user presses the previous post button display the previous index in the review list,
                        while the index is greater then 0 */
                        btn_review_stall_read_previous.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (index > 0) {
                                    index--;
                                    dbref_user.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            for (DataSnapshot dss : snapshot.getChildren()) {
                                                /*Find the User alias based on user auth_id stored with the review*/
                                                User current_user = dss.getValue(User.class);
                                                if (idList.get(index).equals(current_user.getAuth_id())) {
                                                    tx_stall_read_review_alias.setText(current_user.getAlias());
                                                }
                                                if(idList.get(index).equals(Session.ActiveSession.user.getAuth_id()) ||
                                                        Session.ActiveSession.user.getType().equals("admin")){
                                                    reviewToDelete[0] = idList.get(index);
                                                    btn_stall_delete_review.setVisibility(View.VISIBLE);
                                                }
                                                else
                                                {
                                                    btn_stall_delete_review.setVisibility(View.INVISIBLE);
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            System.out.println(error);
                                        }
                                    });
                                    tx_rest_stall_read_review.setText(reviewList.get(index));
                                }
                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error);
            }
        });

        btn_stall_delete_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbref_stall.child(reviewToDelete[0]).removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        startActivity(new Intent(ReadStallReviews.this,StreetFoodActivity.class));
                        Toast.makeText(ReadStallReviews.this,"Review deleted",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}