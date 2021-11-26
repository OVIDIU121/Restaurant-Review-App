package com.example.myfffd;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import com.example.myfffd.utility.Session;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity {
    TextView tv_profile_fn,tv_profile_sn,tv_profile_em,tv_profile_alias,tv_profile_type,tv_profile_change;
    ImageView iv_profile_avatar;
    Button btn_profile_back, btn_profile_upload;
    StorageReference sref;
    Uri path;
    DatabaseReference updateData = FirebaseDatabase.getInstance().getReference("_user_").child(Session.ActiveSession.user.getAuth_id());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        if(Session.ActiveSession.checkLogin()) {
            updateProfile();// enable changes for user
        }

        btn_profile_back = findViewById(R.id.btn_profile_back);
        btn_profile_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this,MainActivity.class));
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

        iv_profile_avatar = findViewById(R.id.iv_profile_avatar);
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
                startActivityForResult(i,150);
                btn_profile_upload.setVisibility(View.VISIBLE);
            }
        });
        btn_profile_upload = findViewById(R.id.btn_profile_upload);
        sref = FirebaseStorage.getInstance().getReference("profile_photos");

        btn_profile_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String file_name = Session.ActiveSession.user.getAuth_id();
                StorageReference reference =sref.child(file_name + "." + getExtension(path));
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
                                Toast.makeText(Profile.this, e.toString(), Toast.LENGTH_LONG).show();
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
        if (requestCode == 150 && resultCode == RESULT_OK && data.getData() != null)
        {
            Picasso.get().load(data.getData()).fit().into(iv_profile_avatar);
            path = data.getData();
        }
    }

    public void updateProfile()
    {
        TableRow pf_fn,pf_sn,pf_alias,pf_em,pf_type;
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
                Toast.makeText(Profile.this,"Email address can not be updated !",Toast.LENGTH_SHORT).show();
            }
        });
        pf_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Session.ActiveSession.user.getType().compareTo("admin")== 0){
                    Session.ActiveSession.cookie = "type";
                    Session.ActiveSession.option = tv_profile_type.getText().toString();
                    startActivity(new Intent(Profile.this, update_userinfo.class));
                }
                else {
                    Toast.makeText(Profile.this,"Only admins can change user type",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}