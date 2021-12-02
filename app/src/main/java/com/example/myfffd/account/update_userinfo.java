package com.example.myfffd.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myfffd.NavigationMenuActivity;
import com.example.myfffd.R;
import com.example.myfffd.utility.Session;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * The type Update userinfo.
 */
public class update_userinfo extends NavigationMenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_userinfo);
        /*Check the the user is logged in*/
        if (Session.ActiveSession.checkLogin()) {
            /*Start the update method read the cookie passed from the Profile activity
            * The cookie variable is used for class reusability*/
            update(Session.ActiveSession.cookie);
            Session.ActiveSession.clearOption();
        } else {
            startActivity(new Intent(this, Profile.class));
            finish();
        }
    }

    private void update(String i) {
        /*Define the variables and bind the to the view ID`s*/
        DatabaseReference updateData = FirebaseDatabase.getInstance().getReference("_user_").child(Session.ActiveSession.user.getAuth_id());
        TextView tv_update_currentinfo;
        EditText tx_update_info;
        Button btn_update_info;
        tv_update_currentinfo = findViewById(R.id.tv_update_currentinfo);
        btn_update_info = findViewById(R.id.btn_update_info);
        tx_update_info = findViewById(R.id.tx_update_info);
        tv_update_currentinfo.setText(Session.ActiveSession.option); // update current displayed info
        DatabaseReference dbref;
        dbref = FirebaseDatabase.getInstance().getReference("_user_");

        btn_update_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = tx_update_info.getText().toString();
                /*Check if the alias is already used*/
                dbref.orderByChild("alias").equalTo(value).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            Toast.makeText(update_userinfo.this,"Alias is already taken, please try a different one !",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            /*Update the Firebase data
                            * The i variable is the user field which needs to be updated
                            * The value variable us the user inputed text*/
                            updateData.child(i).setValue(value);
                            switch (i) {
                                case "fn":
                                    Session.ActiveSession.user.setFn(value);
                                    break;
                                case "sn":
                                    Session.ActiveSession.user.setSn(value);
                                    break;
                                case "alias":
                                    Session.ActiveSession.user.setAlias(value);
                                    break;
                            }
                            Toast.makeText(update_userinfo.this, "Details updated successfully", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(update_userinfo.this, Profile.class));
                            finish();

                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

            }
        });

    }
}