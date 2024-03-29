package com.example.myfffd.streetfood;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfffd.NavigationMenuActivity;
import com.example.myfffd.R;
import com.example.myfffd.models.StreetFood;
import com.example.myfffd.utility.Session;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Stall write reviews.
 */
public class StallWriteReviews extends NavigationMenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stall_write_reviews);
        /*Define the variables and bind the to the view ID`s*/
        TextInputEditText tx_review_stall_input;
        TextView tv_stall_name_write;
        RatingBar rtb_stall_write_rating;
        Button btn_review_write_stall;
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("_streetFood_");
        /*Get the parcelable StreetFood object*/
        StreetFood streetFood = getIntent().getParcelableExtra("STALL");
        tx_review_stall_input = findViewById(R.id.tx_review_stall_input);
        rtb_stall_write_rating = findViewById(R.id.rtb_stall_write_rating);
        btn_review_write_stall = findViewById(R.id.btn_review_write_stall);
        tv_stall_name_write = findViewById(R.id.tv_stall_name_write);

        tv_stall_name_write.setText(streetFood.getName());

        rtb_stall_write_rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                streetFood.setRating(rating);
            }
        });

        btn_review_write_stall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int textLength = (tx_review_stall_input.getText().toString()).length();
                /*Check that the review has less than 480 characters*/
                if (textLength < 481) {
                    /*Store the review in a hasmap object*/
                    Map<String, String> map = new HashMap<String, String>() {{
                        put(Session.ActiveSession.user.getAuth_id(), tx_review_stall_input.getText().toString());
                    }};
                    streetFood.setReview(map);
                    /*Send the rating and the review to the correct Firebase restaurant object*/
                    dbref.child(streetFood.getName() + "-" + streetFood.getLocation()).child("rating").setValue(streetFood.getRating());
                    dbref.child(streetFood.getName() + "-" + streetFood.getLocation()).child("review").child(Session.ActiveSession.user.getAuth_id()).setValue(tx_review_stall_input.getText().toString());
                    Toast.makeText(StallWriteReviews.this, "Review Succesfully uploaded !", Toast.LENGTH_SHORT).show();
                    finish();
                    /*Display various toast based on user input*/
                } else if (textLength < 20) {
                    Toast.makeText(StallWriteReviews.this, "Please write a valid sentence ! \n Only: " + textLength + "written.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(StallWriteReviews.this, "You wrote: " + textLength + " characters, the review must be under 480 characters !", Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}