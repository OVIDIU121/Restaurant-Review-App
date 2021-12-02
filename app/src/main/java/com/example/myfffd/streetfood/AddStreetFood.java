package com.example.myfffd.streetfood;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myfffd.NavigationMenuActivity;
import com.example.myfffd.R;
import com.example.myfffd.models.StreetFood;
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

/**
 * The type Add street food.
 */
public class AddStreetFood extends NavigationMenuActivity {
    /**
     * The Iv stall picture.
     */
    ImageView iv_stall_picture;
    /**
     * The Path.
     */
    Uri path;
    /**
     * The Sref.
     */
    StorageReference sref = FirebaseStorage.getInstance().getReference("streetFood_photos");
    /**
     * The Stall pic.
     */
    String stall_pic = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_street_food);
        /*Define the variables and bind the to the view ID`s*/
        TextView et_stall_name;
        final TextView et_stall_location;
        TextView et_stall_desc;
        Button btn_stall_add;
        CheckBox checkBox_stall;
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("_streetFood_");
        final Boolean[] check = {false};
        et_stall_name = findViewById(R.id.et_stall_name);
        et_stall_location = findViewById(R.id.et_stall_location);
        et_stall_desc = findViewById(R.id.et_stall_desc);
        iv_stall_picture = findViewById(R.id.iv_stall_picture);
        btn_stall_add = findViewById(R.id.btn_stall_add);
        checkBox_stall = findViewById(R.id.checkBox_stall);
        /*Get picture from user*/
        iv_stall_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(getIntent().ACTION_GET_CONTENT);
                startActivityForResult(i, 90);
                /*Change boolean check variable to true for later use*/
                check[0] = true;
            }
        });

        btn_stall_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Check the all the fields are filled with data*/
                if (!TextUtils.isEmpty(et_stall_name.getText().toString()) && !TextUtils.isEmpty(et_stall_location.getText().toString()) &&
                        !TextUtils.isEmpty(et_stall_desc.getText().toString())) {
                    String stall_id = et_stall_name.getText() + "-" + et_stall_location.getText();
                    /*Create a StreetFood ID based on name and postcode*/
                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        private final Map<String, String> test = null;

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                /*Check if there are any Streetfood objects in Firebase*/
                                for (DataSnapshot dss : snapshot.getChildren()) {
                                    StreetFood current_streetfood = dss.getValue(StreetFood.class);
                                    String current_streetfood_id = current_streetfood.getName() + "-" + current_streetfood.getLocation();
                                    if (!stall_id.equals(current_streetfood_id)) {
                                        /*Check the the StreetFood ID is not already registered*/
                                        /*If stall_id is not found send restaurant object to Firebase*/
                                        final String url = "https://firebasestorage.googleapis.com/v0/b/mobileappdevelopment-15143.appspot.com/o/streetFood_photos%2Fstreetfood_default.png?alt=media&token=24700770-c812-4acb-b37e-de0513babfd0";
                                        StreetFood streetFood = new StreetFood(et_stall_name.getText().toString(), et_stall_location.getText().toString(),
                                                url, et_stall_desc.getText().toString(), checkBox_stall.isChecked(), 0, test); //
                                        dbref.child(et_stall_name.getText() + "-" + et_stall_location.getText()).setValue(streetFood);
                                        if (check[0] == true) {
                                            /*upload a pic to Firebase storage if a file has been selected by the user*/
                                            uploadPic(stall_id);
                                        }
                                        Toast.makeText(AddStreetFood.this, "Street Food Stall successfully added to app !", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(AddStreetFood.this, "Current Street Food Stall  is already registered !", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            else {
                                /*If no StreetFood objects are in Firebase register this one*/
                                final String url = "https://firebasestorage.googleapis.com/v0/b/mobileappdevelopment-15143.appspot.com/o/streetFood_photos%2Fstreetfood_default.png?alt=media&token=24700770-c812-4acb-b37e-de0513babfd0";
                                StreetFood streetFood = new StreetFood(et_stall_name.getText().toString(), et_stall_location.getText().toString(),
                                        url, et_stall_desc.getText().toString(), checkBox_stall.isChecked(), 0, test); //
                                dbref.child(et_stall_name.getText() + "-" + et_stall_location.getText()).setValue(streetFood);
                                if (check[0] == true) {
                                    /*upload a pic to Firebase storage if a file has been selected by the user*/
                                    uploadPic(stall_id);
                                }
                                /*Send various Toasts to user based on circumstances*/
                                Toast.makeText(AddStreetFood.this, "Street Food Stall successfully added to app !", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(AddStreetFood.this, "Could not add the Street Food Stall , please check your internet conectivity !", Toast.LENGTH_SHORT).show();
                            System.out.println(error);
                        }
                    });

                } else {
                    Toast.makeText(AddStreetFood.this, "Please make sure all fields are completed correctly !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 90 && resultCode == Activity.RESULT_OK && data.getData() != null) {
            /*Load profile picture in Imageview*/
            Picasso.get().load(data.getData()).into(iv_stall_picture);
            path = data.getData();
        }
    }

    private String getExtension(Uri path)  // get file extension method
    {
        ContentResolver resolver = getContentResolver();
        MimeTypeMap map = MimeTypeMap.getSingleton();
        return map.getExtensionFromMimeType(resolver.getType(path));
    }

    /**
     * @param current_stall_id Upload picture to firebase, get download URL and save the url in the Firebase object
     */
    private void uploadPic(String current_stall_id) {
        StorageReference reference = sref.child(current_stall_id + "." + getExtension(path));
        reference.putFile(path).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String url = uri.toString();
                        FirebaseDatabase.getInstance().getReference("_streetFood_").child(current_stall_id).child("profile_picture").setValue(url);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddStreetFood.this, "File could not be uploaded, please check your internet connectivity !", Toast.LENGTH_SHORT).show();
                        reference.delete();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddStreetFood.this, "File could not be uploaded, please check your internet connectivity !", Toast.LENGTH_LONG).show();

            }
        });
    }
}