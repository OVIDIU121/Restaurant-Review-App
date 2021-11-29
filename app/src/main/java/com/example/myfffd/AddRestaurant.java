package com.example.myfffd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfffd.models.Restaurant;
import com.example.myfffd.models.User;
import com.example.myfffd.utility.AuthenticationUtility;
import com.example.myfffd.utility.DateTime;
import com.example.myfffd.utility.Session;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Map;

public class AddRestaurant extends AppCompatActivity {
    ImageView iv_rest_picture;
    Uri path;
    StorageReference sref = FirebaseStorage.getInstance().getReference("restaurant_photos");
    String rest_pic = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);
        TextView et_rest_name,et_rest_city,et_rest_street, et_rest_tel, et_rest_postcode, et_rest_desc;
        Button btn_rest_add;
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("_restaurants_");
        final Boolean[] check = {false};
        et_rest_name = findViewById(R.id.et_rest_name);
        et_rest_city = findViewById(R.id.et_rest_city);
        et_rest_street = findViewById(R.id.et_rest_street);
        et_rest_tel = findViewById(R.id.et_rest_tel);
        et_rest_postcode = findViewById(R.id.et_rest_postcode);
        et_rest_desc = findViewById(R.id.et_rest_desc);
        iv_rest_picture = findViewById(R.id.iv_rest_picture);
        btn_rest_add = findViewById(R.id.btn_rest_add);

        iv_rest_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(getIntent().ACTION_GET_CONTENT);
                startActivityForResult(i,90);
                check[0] = true;
            }
        });

        btn_rest_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(et_rest_name.getText().toString()) && !TextUtils.isEmpty(et_rest_city.getText().toString()) &&
                        !TextUtils.isEmpty(et_rest_street.getText().toString())&&!TextUtils.isEmpty(et_rest_postcode.getText().toString()) &&
                        !TextUtils.isEmpty(et_rest_desc.getText().toString()))
                {
                    String rest_id = et_rest_name.getText().toString() + "-" + et_rest_postcode.getText().toString();
                    // check if the id already exists
                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        private Map <String,String> test = null;

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                for (DataSnapshot dss: snapshot.getChildren()) {
                                    Restaurant current_restaurant = dss.getValue(Restaurant.class);
                                    String current_rest_id = current_restaurant.getName() + "-" + current_restaurant.getPostcode();
                                    if(!rest_id.equals(current_rest_id)){
                                        String url = "https://firebasestorage.googleapis.com/v0/b/mobileappdevelopment-15143.appspot.com/o/restaurant_photos%2Frestaurant_default.png?alt=media&token=23bf735f-c866-4309-9655-6793689fab74";
                                        Restaurant restaurant = new Restaurant (et_rest_name.getText().toString(),et_rest_city.getText().toString(),
                                                et_rest_street.getText().toString(),et_rest_tel.getText().toString(),et_rest_postcode.getText().toString(),
                                                url,et_rest_desc.getText().toString(),0, test); // create restaurant object
                                        dbref.child(et_rest_name.getText().toString()+ "-" + et_rest_postcode.getText().toString()).setValue(restaurant);
                                        if(check[0] == true){
                                            uploadPic(rest_id); // try to upload pic to firebase storage
                                        }
                                        Toast.makeText(AddRestaurant.this,"Restaurant successfully added to app !" ,Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(AddRestaurant.this,"Current restaurant is already registered !",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            else {
                                String url = "https://firebasestorage.googleapis.com/v0/b/mobileappdevelopment-15143.appspot.com/o/restaurant_photos%2Frestaurant_default.png?alt=media&token=23bf735f-c866-4309-9655-6793689fab74";
                                Restaurant restaurant = new Restaurant (et_rest_name.getText().toString(),et_rest_city.getText().toString(),
                                        et_rest_street.getText().toString(),et_rest_tel.getText().toString(),et_rest_postcode.getText().toString(),
                                        url,et_rest_desc.getText().toString(),0, test); // create restaurant object
                                dbref.child(et_rest_name.getText().toString()+ "-" + et_rest_postcode.getText().toString()).setValue(restaurant);
                                if(check[0] == true){
                                    uploadPic(rest_id); // try to upload pic to firebase storage
                                }
                                Toast.makeText(AddRestaurant.this,"Restaurant successfully added to app !" ,Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(AddRestaurant.this,"Could not add the restaurant, please check your internet conectivity !" ,Toast.LENGTH_SHORT).show();
                            System.out.println(error);
                        }
                    });

                }
                else {
                    Toast.makeText(AddRestaurant.this,"Please make sure all fields are completed correctly !" ,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 90 && resultCode == RESULT_OK && data.getData() != null)
        {
            Picasso.get().load(data.getData()).into(iv_rest_picture);
            path = data.getData();
        }
    }
    private String getExtension(Uri path)  // get file extension method
    {
        ContentResolver resolver = getContentResolver();
        MimeTypeMap map = MimeTypeMap.getSingleton();
        return map.getExtensionFromMimeType(resolver.getType(path));
    }
    private void uploadPic(String current_rest_id){
        StorageReference reference =sref.child(current_rest_id + "." + getExtension(path));
        reference.putFile(path).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String url = uri.toString();
                        Task<Void> updateData = FirebaseDatabase.getInstance().getReference("_restaurants_").child(current_rest_id).child("profile_picture").setValue(url);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddRestaurant.this, "File could not be uploaded, please check your internet connectivity !", Toast.LENGTH_SHORT).show();
                        reference.delete();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddRestaurant.this, "File could not be uploaded, please check your internet connectivity !", Toast.LENGTH_LONG).show();

            }
        });
    }
}