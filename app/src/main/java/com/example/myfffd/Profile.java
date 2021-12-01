package com.example.myfffd;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfffd.models.Restaurant;
import com.example.myfffd.models.StreetFood;
import com.example.myfffd.utility.Session;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


/**
 * The type Profile.
 */
public class Profile extends NavigationMenuActivity {
    /**
     * The Tv profile fn.
     */
    TextView tv_profile_fn, /**
     * The Tv profile sn.
     */
    tv_profile_sn, /**
     * The Tv profile em.
     */
    tv_profile_em, /**
     * The Tv profile alias.
     */
    tv_profile_alias, /**
     * The Tv profile type.
     */
    tv_profile_type, /**
     * The Tv profile change.
     */
    tv_profile_change;
    /**
     * The Iv profile avatar.
     */
    ImageView iv_profile_avatar;
    /**
     * The Btn profile delete.
     */
    Button btn_profile_delete, /**
     * The Btn profile upload.
     */
    btn_profile_upload;
    /**
     * The Sref.
     */
    StorageReference sref;
    /**
     * The Path.
     */
    Uri path;
    /**
     * The M auth.
     */
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    /**
     * The Dbref street food.
     */
    DatabaseReference dbref_streetFood = FirebaseDatabase.getInstance().getReference("_streetFood_");
    /**
     * The Dbref rest.
     */
    DatabaseReference dbref_rest = FirebaseDatabase.getInstance().getReference("_restaurants_");
    /**
     * The Update data.
     */
    DatabaseReference updateData = FirebaseDatabase.getInstance().getReference("_user_").child(Session.ActiveSession.user.getAuth_id());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        if (Session.ActiveSession.checkLogin()) {
            updateProfile();// enable changes for user
        }


        btn_profile_delete = findViewById(R.id.btn_profile_delete);
        btn_profile_delete.setOnClickListener(new View.OnClickListener() {
            /**
             * @param v
             */
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Profile.this)
                        .setTitle("Delete Account")
                        .setMessage("Do you really want to delete your account? This will delete all of your data, including your reviews.")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, (dialog, whichButton) -> {
                            // check if the user has any reviews, and if he does delete the reviews before deleting the account
                            dbref_streetFood.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot dss : snapshot.getChildren()) {
                                        StreetFood streetFood = dss.getValue(StreetFood.class);
                                        dbref_streetFood.child(streetFood.getName() + "-" + streetFood.getLocation()).child("review").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                for (DataSnapshot rss : snapshot.getChildren()) {
                                                    if (rss.getKey().equals(Session.ActiveSession.user.getAuth_id())) {
                                                        dbref_streetFood.child(streetFood.getName() + "-" + streetFood.getLocation()).child("review").child(rss.getKey()).removeValue();
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                            // check if the user has any reviews, and if he does delete the reviews before deleting the account
                            dbref_rest.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot dss : snapshot.getChildren()) {
                                        Restaurant restaurant = dss.getValue(Restaurant.class);
                                        dbref_rest.child(restaurant.getName() + "-" + restaurant.getPostcode()).child("review").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                for (DataSnapshot rss : snapshot.getChildren()) {
                                                    if (rss.getKey().equals(Session.ActiveSession.user.getAuth_id())) {
                                                        dbref_rest.child(restaurant.getName() + "-" + restaurant.getPostcode()).child("review").child(rss.getKey()).removeValue();
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                            // delete user account
                            updateData.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    // delete auth account
                                    mAuth.getCurrentUser().delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {

                                            startActivity(new Intent(Profile.this, Welcome.class));
                                            Toast.makeText(Profile.this, "Account successfully deleted", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(Profile.this, "Account could not be deleted, please check yout internet connection !", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });
                        })
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });

        tv_profile_fn = findViewById(R.id.tv_profile_fn);
        tv_profile_sn = findViewById(R.id.tv_profile_sn);
        tv_profile_em = findViewById(R.id.tv_profile_em);
        tv_profile_alias = findViewById(R.id.tv_profile_alias);
        tv_profile_type = findViewById(R.id.tv_profile_type);
        tv_profile_fn.setText(Session.ActiveSession.user.getFn());
        tv_profile_sn.setText(Session.ActiveSession.user.getSn());
        tv_profile_em.setText(Session.ActiveSession.user.getEm());
        tv_profile_alias.setText(Session.ActiveSession.user.getAlias());
        tv_profile_type.setText(Session.ActiveSession.user.getType());
        btn_profile_upload = findViewById(R.id.btn_profile_upload);
        iv_profile_avatar = findViewById(R.id.iv_forum_avatar);
        if (!Session.ActiveSession.user.getProfile_pic_url().isEmpty()) {
            String url2 = Session.ActiveSession.user.getProfile_pic_url();
            Picasso.get().load(url2).fit().into(iv_profile_avatar);
        }
        iv_profile_avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(getIntent().ACTION_GET_CONTENT);
                startActivityForResult(i, 150);
                btn_profile_upload.setVisibility(View.VISIBLE);

            }
        });

        sref = FirebaseStorage.getInstance().getReference("profile_photos");


        btn_profile_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String file_name = Session.ActiveSession.user.getAuth_id();
                StorageReference reference = sref.child(file_name + "." + getExtension(path));
                reference.putFile(path).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String url = uri.toString();// url of captured image
                                updateData.child("profile_pic_url").setValue(url);
                                Session.ActiveSession.user.setProfile_pic_url(url);
                                Toast.makeText(Profile.this, "Successfully updated !", Toast.LENGTH_LONG).show();
                                // update firebase user to be added
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Profile.this, "File could not be uploaded, please check your internet connectivity !", Toast.LENGTH_LONG).show();
                                reference.delete();
                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Profile.this, e.toString(), Toast.LENGTH_LONG).show();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                    }
                });

            }
        });

    }

    private String getExtension(Uri path)  // get file extension method
    {
        ContentResolver resolver = getContentResolver();
        MimeTypeMap map = MimeTypeMap.getSingleton();
        return map.getExtensionFromMimeType(resolver.getType(path));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 150 && resultCode == Activity.RESULT_OK && data.getData() != null) {
            Picasso.get().load(data.getData()).fit().into(iv_profile_avatar);
            path = data.getData();
        }
    }

    /**
     * Update profile.
     */
    public void updateProfile() {
        TableRow pf_fn;
        final TableRow pf_sn;
        final TableRow pf_alias;
        final TableRow pf_em;
        TableRow pf_type;
        pf_fn = findViewById(R.id.pf_fn);
        pf_sn = findViewById(R.id.pf_sn);
        pf_alias = findViewById(R.id.pf_alias);
        pf_em = findViewById(R.id.pf_em);
        pf_type = findViewById(R.id.pf_type);
        pf_fn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Session.ActiveSession.cookie = "fn";
                Session.ActiveSession.option = tv_profile_fn.getText().toString();
                startActivity(new Intent(Profile.this, update_userinfo.class));
            }
        });
        pf_sn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Session.ActiveSession.cookie = "sn";
                Session.ActiveSession.option = tv_profile_sn.getText().toString();
                startActivity(new Intent(Profile.this, update_userinfo.class));
            }
        });
        pf_alias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Session.ActiveSession.cookie = "alias";
                Session.ActiveSession.option = tv_profile_alias.getText().toString();
                startActivity(new Intent(Profile.this, update_userinfo.class));
            }
        });
        pf_em.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Profile.this, "Email address can not be updated !", Toast.LENGTH_SHORT).show();
            }
        });
        pf_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Session.ActiveSession.user.getType().compareTo("admin") == 0) {
                    Session.ActiveSession.cookie = "type";
                    Session.ActiveSession.option = tv_profile_type.getText().toString();
                    startActivity(new Intent(Profile.this, update_userinfo.class));
                } else {
                    Toast.makeText(Profile.this, "Only admins can change user type", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}