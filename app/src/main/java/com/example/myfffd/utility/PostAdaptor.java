package com.example.myfffd.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfffd.R;
import com.example.myfffd.models.Post;

import java.util.Date;
import java.util.List;

public class PostAdaptor extends RecyclerView.Adapter<PostAdaptor.ViewHolder>  {

    private List<Post> postList;
    private Context context;

    RestaurantAdaptor.EateryHolder.OnEateryClickListener listener;
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
        String imageUrl = null;
        holder.title.setText(post.getTitle());
        holder.desc.setText(post.getDesc());
        java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
        String formattedDate = dateFormat.format(new Date(Long.valueOf(post.getTimestamp())).getTime());
        holder.timestamp.setText(formattedDate);
        holder.alias.setText(post.getAlias());
        imageUrl = post.getImage();


    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        public TextView desc;
        public TextView timestamp;
        public ImageView image;
        public TextView alias;
        String userid;

        public ViewHolder(View view, Context ctx) {
            super(view);

            context = ctx;
            alias = (TextView) view.findViewById(R.id.userAliasPost);
            title = (TextView) view.findViewById(R.id.postTitleList);
            desc = (TextView) view.findViewById(R.id.postTextList);
            image = (ImageView) view.findViewById(R.id.postImageList);
            timestamp = (TextView) view.findViewById(R.id.timestampList);
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
