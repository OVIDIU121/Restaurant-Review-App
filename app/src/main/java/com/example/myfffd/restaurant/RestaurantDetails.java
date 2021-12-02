package com.example.myfffd.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.myfffd.NavigationMenuActivity;
import com.example.myfffd.R;
import com.example.myfffd.models.Restaurant;
import com.example.myfffd.utility.Session;
import com.squareup.picasso.Picasso;

import java.util.Locale;

/**
 * The type Restaurant details.
 */
public class RestaurantDetails extends NavigationMenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
        /*Define the variables and bind the to the view ID`s*/
        TextView tx_rest_details_name;
        final TextView tx_rest_details_tel;
        TextView tx_rest_details_desc;
        ImageView iv_rest_details;
        RatingBar rtb_rest_details;
        Button btn_rest_details_read;
        final Button btn_rest_details_reserv;
        Button btn_rest_details_write;

        tx_rest_details_name = findViewById(R.id.tx_rest_details_name);
        tx_rest_details_tel = findViewById(R.id.tx_rest_details_tel);
        tx_rest_details_desc = findViewById(R.id.tx_rest_details_desc);
        iv_rest_details = findViewById(R.id.iv_rest_details);
        rtb_rest_details = findViewById(R.id.rtb_rest_details);
        btn_rest_details_read = findViewById(R.id.btn_rest_details_read);
        btn_rest_details_reserv = findViewById(R.id.btn_rest_details_reserv);
        btn_rest_details_write = findViewById(R.id.btn_rest_details_write);
        //receive the parcelable info
        Restaurant restaurant = getIntent().getParcelableExtra("OBJECT");
        tx_rest_details_name.setText(restaurant.getName() + ", " + restaurant.getCity() + ", " + restaurant.getStreet() + ", " + restaurant.getPostcode());
        tx_rest_details_tel.setText(restaurant.getTel());
        tx_rest_details_desc.setText(restaurant.getDescription());
        /*Load the restaurant profile pic into the image view*/
        Picasso.get().load(restaurant.getProfile_picture()).fit().into(iv_rest_details);
        rtb_rest_details.setRating(restaurant.getRating());
        rtb_rest_details.setIsIndicator(true);
        /*Set button visibility based on user type*/
        if (Session.ActiveSession.user.getType().toLowerCase(Locale.ROOT).compareTo("critic") == 0
                || Session.ActiveSession.user.getType().toLowerCase(Locale.ROOT).compareTo("admin") == 0) {
            btn_rest_details_write.setVisibility(View.VISIBLE);
        } else {
            btn_rest_details_write.setVisibility(View.INVISIBLE);
        }

        btn_rest_details_reserv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Start next activity*/
                Intent i = new Intent(RestaurantDetails.this, BookRestaurant.class);
                startActivity(i);
            }
        });

        btn_rest_details_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Start next activity and send clicked restaurant object*/
                Intent i = new Intent(RestaurantDetails.this, RestaurantWriteReviews.class);
                i.putExtra("OBJECT", restaurant);
                startActivity(i);
            }
        });

        btn_rest_details_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Start next activity and send clicked restaurant object*/
                Intent i = new Intent(RestaurantDetails.this, ReadRestReviews.class);
                i.putExtra("OBJECT", restaurant);
                startActivity(i);
            }
        });

    }
}