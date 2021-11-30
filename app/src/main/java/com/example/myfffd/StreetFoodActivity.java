package com.example.myfffd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
public class StreetFoodActivity extends AppCompatActivity implements StreetFoodAdaptor.StreetFoodHolder.OnStreetFoodClickListener {

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
                    streetFoodList.add(dss.getValue(StreetFood.class));
                }
                adaptor = new StreetFoodAdaptor(streetFoodList, StreetFoodActivity.this);
                rv.setAdapter(adaptor);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onStreetFoodClick(int index) {
        Intent i = new Intent(this, StreetFoodDetails.class);
        i.putExtra("STALL", streetFoodList.get(index));
        startActivity(i);
    }

}