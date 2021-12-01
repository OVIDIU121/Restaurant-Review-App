package com.example.myfffd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfffd.models.Restaurant;
import com.example.myfffd.utility.RestaurantAdaptor;
import com.example.myfffd.utility.Session;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Restaurant activity.
 */
public class Restaurant_Activity extends NavigationMenuActivity implements RestaurantAdaptor.EateryHolder.OnEateryClickListener {

    /**
     * The Rv.
     */
    RecyclerView rv;
    /**
     * The Adaptor.
     */
    RestaurantAdaptor adaptor;
    /**
     * The Restaurant list.
     */
    List<Restaurant> restaurantList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        rv = findViewById(R.id.restaurant_rv);
        Button btn_restaurant_add;
        btn_restaurant_add = findViewById(R.id.btn_restaurant_add);
        if (Session.ActiveSession.user.getType().compareTo("admin") == 0) {
            btn_restaurant_add.setVisibility(View.VISIBLE);
            btn_restaurant_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Restaurant_Activity.this, AddRestaurant.class));
                }
            });
        }
        else
        {
            btn_restaurant_add.setVisibility(View.INVISIBLE);
        }

        rv.setLayoutManager(new LinearLayoutManager(this)); // default vertical scrolling view
        FirebaseDatabase.getInstance().getReference("_restaurants_").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dss : snapshot.getChildren()) {
                    restaurantList.add(dss.getValue(Restaurant.class));
                }
                adaptor = new RestaurantAdaptor(restaurantList, Restaurant_Activity.this);
                rv.setAdapter(adaptor);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onEateryClick(int index) {
        Intent i = new Intent(this, RestaurantDetails.class);
        i.putExtra("OBJECT", restaurantList.get(index));
        startActivity(i);
    }

}