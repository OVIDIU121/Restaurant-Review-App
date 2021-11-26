package com.example.myfffd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfffd.utility.Session;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class update_userinfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_userinfo);
        if (Session.ActiveSession.checkLogin())
        {
            update(Session.ActiveSession.cookie);
            Session.ActiveSession.clearOption();
        }
        else {
            startActivity(new Intent(update_userinfo.this, Profile.class));
        }
    }
    private void update(String i){
        DatabaseReference updateData = FirebaseDatabase.getInstance().getReference("_user_").child(Session.ActiveSession.user.getAuth_id());
        TextView tv_update_currentinfo;
        EditText tx_update_info;
        Button btn_update_info;
        tv_update_currentinfo = findViewById(R.id.tv_update_currentinfo);
        btn_update_info = findViewById(R.id.btn_update_info);
        tx_update_info = findViewById(R.id.tx_update_info);
        tv_update_currentinfo.setText(Session.ActiveSession.option); // update current displayed info

        btn_update_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = tx_update_info.getText().toString();
                updateData.child(i).setValue(value);
                switch (i){
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

                Toast.makeText(update_userinfo.this,"Details updated successfully",Toast.LENGTH_LONG).show();
                startActivity(new Intent(update_userinfo.this,Profile.class));
            }
        });

    }
}