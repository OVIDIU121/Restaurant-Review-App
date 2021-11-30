package com.example.myfffd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfffd.models.StreetFood;
import com.squareup.picasso.Picasso;

/**
 * The type Street food details.
 */
public class StreetFoodDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street_food_details);
        TextView tx_stall_details_name;
        TextView tx_stall_details_desc;
        ImageView iv_stall_details;
        RatingBar rtb_stall_details;
        Button btn_stall_details_read;
        Button btn_stall_details_write;
        CheckBox checkbox_streetfoord_detail;

        tx_stall_details_name = findViewById(R.id.tx_stall_details_name);
        tx_stall_details_desc = findViewById(R.id.tx_stall_details_desc);
        iv_stall_details = findViewById(R.id.iv_stall_details);
        rtb_stall_details = findViewById(R.id.rtb_stall_details);
        btn_stall_details_read = findViewById(R.id.btn_stall_details_read);
        btn_stall_details_write = findViewById(R.id.btn_stall_details_write);
        checkbox_streetfoord_detail = findViewById(R.id.checkbox_streetfoord_detail);

        //receive the parcelable info
        StreetFood streetFood = getIntent().getParcelableExtra("STALL");

        checkbox_streetfoord_detail.setChecked(streetFood.isVegetarian());
        checkbox_streetfoord_detail.setEnabled(false);
        tx_stall_details_name.setText(streetFood.getName() + ", " + streetFood.getLocation());
        tx_stall_details_desc.setText(streetFood.getDescription());
        Picasso.get().load(streetFood.getProfile_picture()).fit().into(iv_stall_details);
        rtb_stall_details.setRating(streetFood.getRating());
        rtb_stall_details.setIsIndicator(true);

        btn_stall_details_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StreetFoodDetails.this, StallWriteReviews.class);
                i.putExtra("STALL", streetFood);
                startActivity(i);
            }
        });

        btn_stall_details_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StreetFoodDetails.this, ReadStallReviews.class);
                i.putExtra("STALL", streetFood);
                startActivity(i);
            }
        });

    }
}
