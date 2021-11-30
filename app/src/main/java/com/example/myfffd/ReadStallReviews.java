package com.example.myfffd;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfffd.models.StreetFood;
import com.example.myfffd.models.User;
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
public class ReadStallReviews extends AppCompatActivity {
    /**
     * The Index.
     */
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_stall_reviews);


        TextView tx_review_stall_read_name;
        final TextView tx_rest_stall_read_review;
        TextView tx_stall_read_review_alias;
        Button btn_review_stall_read_next;
        Button btn_review_stall_read_previous;
        RatingBar rtb_review_stall_read_rating;
        StreetFood streetFood = getIntent().getParcelableExtra("STALL");
        String stall_id = streetFood.getName() + "-" + streetFood.getLocation();
        DatabaseReference dbref_rest = FirebaseDatabase.getInstance().getReference("_streetFood_").child(stall_id).child("review");
        List<String> idList = new ArrayList<String>();
        List<String> reviewList = new ArrayList<String>();


        tx_review_stall_read_name = findViewById(R.id.tx_stall_read_name);
        tx_rest_stall_read_review = findViewById(R.id.tx_stall_read_review);
        tx_stall_read_review_alias = findViewById(R.id.tx_stall_read_alias);
        btn_review_stall_read_next = findViewById(R.id.btn_stall_read_next);
        btn_review_stall_read_previous = findViewById(R.id.btn_stall_read_previous);
        rtb_review_stall_read_rating = findViewById(R.id.rtb_read_stall_rating);

        tx_review_stall_read_name.setText(streetFood.getName());

        dbref_rest.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i = 1;
                long count = snapshot.getChildrenCount();
                for (DataSnapshot dss : snapshot.getChildren()) {
                    idList.add(dss.getKey());
                    reviewList.add(dss.getValue(String.class));
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
                                        tx_stall_read_review_alias.setText(current_user.getAlias());
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                System.out.println(error);
                            }
                        });
                        tx_rest_stall_read_review.setText(reviewList.get(index));
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
                                                if (idList.get(index).equals(current_user.getAuth_id())) {
                                                    tx_stall_read_review_alias.setText(current_user.getAlias());
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
                        btn_review_stall_read_previous.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (index > 0) {
                                    index--;
                                    dbref_user.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            for (DataSnapshot dss : snapshot.getChildren()) {
                                                User current_user = dss.getValue(User.class);
                                                if (idList.get(index).equals(current_user.getAuth_id())) {
                                                    tx_stall_read_review_alias.setText(current_user.getAlias());
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
    }
}