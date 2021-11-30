package com.example.myfffd;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfffd.models.Restaurant;
import com.example.myfffd.utility.Session;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Write reviews.
 */
public class WriteReviews extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_reviews);
        TextInputEditText tx_review_input;
        TextView tx_review_name;
        RatingBar rtb_review_rating;
        Button btn_review_write;
        double rating_set;
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("_restaurants_");

        Restaurant restaurant = getIntent().getParcelableExtra("OBJECT");
        tx_review_input = findViewById(R.id.tx_review_input);
        tx_review_name = findViewById(R.id.tx_review_read_name);
        rtb_review_rating = findViewById(R.id.rtb_review_read_rating);
        btn_review_write = findViewById(R.id.btn_review_read_next);

        tx_review_name.setText(restaurant.getName());
        rtb_review_rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                restaurant.setRating(rating);
            }
        });

        btn_review_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int textLength = (tx_review_input.getText().toString()).length();
                if (textLength < 481) {
                    Map<String, String> map = new HashMap<String, String>() {{
                        put(Session.ActiveSession.user.getAuth_id(), tx_review_input.getText().toString());
                    }};
                    restaurant.setReview(map);
                    dbref.child(restaurant.getName() + "-" + restaurant.getPostcode()).child("rating").setValue(restaurant.getRating());
                    dbref.child(restaurant.getName() + "-" + restaurant.getPostcode()).child("review").child(Session.ActiveSession.user.getAuth_id()).setValue(tx_review_input.getText().toString());
                    Toast.makeText(WriteReviews.this, "Review Succesfully uploaded !", Toast.LENGTH_SHORT).show();
                } else if (textLength < 20) {
                    Toast.makeText(WriteReviews.this, "Please write a valid sentence ! \n Only: " + textLength + "written.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(WriteReviews.this, "You wrote: " + textLength + " characters, the review must be under 480 characters !", Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}