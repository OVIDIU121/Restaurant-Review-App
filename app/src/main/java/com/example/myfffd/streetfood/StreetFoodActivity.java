package com.example.myfffd.streetfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfffd.NavigationMenuActivity;
import com.example.myfffd.R;
import com.example.myfffd.models.StreetFood;
import com.example.myfffd.utility.StreetFoodAdaptor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Street food activity.
 */
public class StreetFoodActivity extends NavigationMenuActivity implements StreetFoodAdaptor.StreetFoodHolder.OnStreetFoodClickListener {

    /**
     * The Rv.
     */
    RecyclerView rv;
    /**
     * The Adaptor.
     */
    StreetFoodAdaptor adaptor;
    /**
     * The Street food list.
     */
    List<StreetFood> streetFoodList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street_food);
        /*Define the variables and bind the to the view ID`s*/
        rv = findViewById(R.id.streetFood_rv);
        Button btn_streetfood_add;
        btn_streetfood_add = findViewById(R.id.btn_streetfood_add);
        btn_streetfood_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StreetFoodActivity.this, AddStreetFood.class));
            }
        });

        rv.setLayoutManager(new LinearLayoutManager(this)); // default vertical scrolling view
        FirebaseDatabase.getInstance().getReference("_streetFood_").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dss : snapshot.getChildren()) {
                    /*Add all StreetFood objects from Firebase to a List*/
                    streetFoodList.add(dss.getValue(StreetFood.class));
                }
                adaptor = new StreetFoodAdaptor(streetFoodList, StreetFoodActivity.this);
                /*Send the StreetFood list to the recycler view*/
                rv.setAdapter(adaptor);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onStreetFoodClick(int index) {
        /*Start the next activity and send the clicked restaurant object to it*/
        Intent i = new Intent(this, StreetFoodDetails.class);
        i.putExtra("STALL", streetFoodList.get(index));
        startActivity(i);
    }

}