package com.example.myfffd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfffd.models.Restaurant;

public class BookRestaurant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_restaurant);
        Restaurant restaurant =getIntent().getParcelableExtra("OBJECT");
        TextView tx_book_name;
        Button btn_book_reserve;
        DatePicker dt_book_picker;

        tx_book_name = findViewById(R.id.tx_review_name);
        btn_book_reserve = findViewById(R.id.btn_review_write);
        dt_book_picker = findViewById(R.id.dt_book_picker);

        tx_book_name.setText(restaurant.getName());
        btn_book_reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the values for day of month , month and year from a date picker
                String day = "" + dt_book_picker.getDayOfMonth();
                String month = "" + (dt_book_picker.getMonth() + 1);
                String year = "" + dt_book_picker.getYear();
                String url = "https://www.opentable.com/s?dateTime="+year+"-"+month+"-"+day+"T19%3A00%3A00&covers=2&metroId=72&regionIds=5316&pinnedRids%5B0%5D=87967&enableSimpleCuisines=true&includeTicketedAvailability=true&pageType=0";
                Toast.makeText(getApplicationContext(), "Loading your reservation !", Toast.LENGTH_LONG).show();
                System.out.println(url);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });
    }
}