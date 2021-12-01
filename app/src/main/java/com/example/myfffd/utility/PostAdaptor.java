package com.example.myfffd.utility;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfffd.R;
import com.example.myfffd.models.Post;
import com.example.myfffd.models.Restaurant;
import com.example.myfffd.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PostAdaptor extends RecyclerView.Adapter<PostAdaptor.ViewHolder> {

    private final List<Post> postList;
    RestaurantAdaptor.EateryHolder.OnEateryClickListener listener;
    private Context context;

    public PostAdaptor(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
    }


    @NonNull
    @Override
    public PostAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.postrow, parent, false);

        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdaptor.ViewHolder holder, int position) {
        Post post = this.postList.get(position);
        holder.title.setText(post.getTitle());
        holder.desc.setText(post.getDesc());
        java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        Date formattedDate = new Date((Long.valueOf(post.getTimestamp())));
        holder.timestamp.setText(formattedDate.toString());
        holder.alias.setText(post.getAlias());
        FirebaseDatabase.getInstance().getReference("_user_").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dss: snapshot.getChildren()){
                    User current_user = dss.getValue(User.class);
                    if(post.getUserid().equals(current_user.getAuth_id())){
                        Picasso.get().load(current_user.getProfile_pic_url()).fit().into(holder.iv_forum_avatar);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error);
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView desc;
        public TextView timestamp;
        public ImageView image;
        public TextView alias;
        public ImageView iv_forum_avatar;
        String userid;

        public ViewHolder(View view, Context ctx) {
            super(view);

            context = ctx;
            alias = view.findViewById(R.id.userAliasPost);
            title = view.findViewById(R.id.postTitleList);
            desc = view.findViewById(R.id.postTextList);
            image = view.findViewById(R.id.postImageList);
            timestamp = view.findViewById(R.id.timestampList);
            iv_forum_avatar = view.findViewById(R.id.iv_forum_avatar);
            userid = null;

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // we can go to the next activity...

                }
            });

        }
    }

}
