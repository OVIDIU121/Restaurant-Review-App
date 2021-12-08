package com.example.myfffd.restaurant;

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
import com.example.myfffd.models.Restaurant;
import com.example.myfffd.models.User;
import com.example.myfffd.streetfood.ReadStallReviews;
import com.example.myfffd.streetfood.StreetFoodActivity;
import com.example.myfffd.utility.Session;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Read rest reviews.
 */
public class ReadRestReviews extends NavigationMenuActivity {
    /**
     * The Index.
     */
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_rest_reviews);
        /*Define the variables and bind the to the view ID`s*/
        TextView tx_review_read_name;
        final TextView tx_rest_read_review;
        TextView tx_rest_read_review_alias;
        Button btn_review_read_next;
        Button btn_review_read_previous,btn_rest_delete_review;
        RatingBar rtb_review_read_rating;
        Restaurant restaurant = getIntent().getParcelableExtra("OBJECT");
        String rest_id = restaurant.getName() + "-" + restaurant.getPostcode();
        DatabaseReference dbref_rest = FirebaseDatabase.getInstance().getReference("_restaurants_").child(rest_id).child("review");
        List<String> idList = new ArrayList<String>();
        List<String> reviewList = new ArrayList<String>();
        final String[] reviewToDelete = new String[1];
        btn_rest_delete_review = findViewById(R.id.btn_rest_delete_review);
        tx_review_read_name = findViewById(R.id.tx_review_read_name);
        tx_rest_read_review = findViewById(R.id.tx_rest_read_review);
        tx_rest_read_review_alias = findViewById(R.id.tx_rest_read_review_alias);
        btn_review_read_next = findViewById(R.id.btn_review_read_next);
        btn_review_read_previous = findViewById(R.id.btn_review_read_previous);
        rtb_review_read_rating = findViewById(R.id.rtb_review_read_rating);

        tx_review_read_name.setText(restaurant.getName());
        dbref_rest.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i = 1;
                /*Check how many reviews are for the current restaurant*/
                long count = snapshot.getChildrenCount();
                for (DataSnapshot dss : snapshot.getChildren()) {
                    /*Add the review ids in a List*/
                    idList.add(dss.getKey());
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
                                    /*Find the User alias based on user auth_id stored with the review*/
                                    User current_user = dss.getValue(User.class);
                                    if (idList.get(index).equals(current_user.getAuth_id())) {
                                        tx_rest_read_review_alias.setText(current_user.getAlias());
                                    }
                                    if(idList.get(index).equals(Session.ActiveSession.user.getAuth_id()) ||
                                            Session.ActiveSession.user.getType().equals("admin")){
                                        reviewToDelete[0] = idList.get(index);
                                        btn_rest_delete_review.setVisibility(View.VISIBLE);
                                    }
                                    else
                                    {
                                        btn_rest_delete_review.setVisibility(View.INVISIBLE);
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                System.out.println(error);
                            }
                        });
                        tx_rest_read_review.setText(reviewList.get(index));
                        /*If the user presses the next post button display the next index in the review list,
                        while the index is less then the size of the list*/
                        btn_review_read_next.setOnClickListener(new View.OnClickListener() {
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
                                                    tx_rest_read_review_alias.setText(current_user.getAlias());
                                                }
                                                if(idList.get(index).equals(Session.ActiveSession.user.getAuth_id()) ||
                                                        Session.ActiveSession.user.getType().equals("admin")){
                                                    reviewToDelete[0] = idList.get(index);
                                                    btn_rest_delete_review.setVisibility(View.VISIBLE);
                                                }
                                                else
                                                {
                                                    btn_rest_delete_review.setVisibility(View.INVISIBLE);
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            System.out.println(error);
                                        }
                                    });
                                    tx_rest_read_review.setText(reviewList.get(index));
                                }
                            }
                        });
                        /*If the user presses the previous post button display the previous index in the review list,
                        while the index is greater then 0 */
                        btn_review_read_previous.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (index > 0) {
                                    index--;
                                    dbref_user.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            for (DataSnapshot dss : snapshot.getChildren()) {
                                                User current_user = dss.getValue(User.class);
                                                /*Find the User alias based on user auth_id stored with the review*/
                                                if (idList.get(index).equals(current_user.getAuth_id())) {
                                                    tx_rest_read_review_alias.setText(current_user.getAlias());
                                                }
                                                if(idList.get(index).equals(Session.ActiveSession.user.getAuth_id()) ||
                                                        Session.ActiveSession.user.getType().equals("admin")){
                                                    reviewToDelete[0] = idList.get(index);
                                                    btn_rest_delete_review.setVisibility(View.VISIBLE);
                                                }
                                                else
                                                {
                                                    btn_rest_delete_review.setVisibility(View.INVISIBLE);
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            System.out.println(error);
                                        }
                                    });
                                    tx_rest_read_review.setText(reviewList.get(index));
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
        btn_rest_delete_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbref_rest.child(reviewToDelete[0]).removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        startActivity(new Intent(ReadRestReviews.this, Restaurant_Activity.class));
                        Toast.makeText(ReadRestReviews.this,"Review deleted",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}