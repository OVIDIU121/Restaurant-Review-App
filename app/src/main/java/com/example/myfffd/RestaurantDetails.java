package com.example.myfffd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.myfffd.models.Restaurant;
import com.squareup.picasso.Picasso;

public class RestaurantDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
        TextView tx_rest_details_name,tx_rest_details_tel, tx_rest_details_desc;
        ImageView iv_rest_details;
        RatingBar rtb_rest_details;
        Button btn_rest_details_read,btn_rest_details_reserv, btn_rest_details_write;
        tx_rest_details_name = findViewById(R.id.tx_rest_details_name);
        tx_rest_details_tel = findViewById(R.id.tx_rest_details_tel);
        tx_rest_details_desc = findViewById(R.id.tx_rest_details_desc);
        iv_rest_details = findViewById(R.id.iv_rest_details);
        rtb_rest_details = findViewById(R.id.rtb_rest_details);
        btn_rest_details_read = findViewById(R.id.btn_rest_details_read);
        btn_rest_details_reserv = findViewById(R.id.btn_rest_details_reserv);
        btn_rest_details_write = findViewById(R.id.btn_rest_details_write);
        //receive the parcelable info
        Restaurant restaurant =getIntent().getParcelableExtra("OBJECT");
        tx_rest_details_name.setText(restaurant.getName() + ", " + restaurant.getCity() + ", " + restaurant.getStreet() + ", " + restaurant.getPostcode());
        tx_rest_details_tel.setText(restaurant.getTel());
        tx_rest_details_desc.setText(restaurant.getDescription());
        Picasso.get().load(restaurant.getProfile_picture()).fit().into(iv_rest_details);
        rtb_rest_details.setRating(restaurant.getRating());


    }
}