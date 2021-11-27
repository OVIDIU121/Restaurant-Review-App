package com.example.myfffd.utility;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myfffd.R;
import com.example.myfffd.models.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EateryAdaptor extends RecyclerView.Adapter<EateryAdaptor.EateryHolder> {
    List<User> userList;

    EateryHolder.OnEateryClickListener listener;
    public EateryAdaptor(List<User> userList, EateryHolder.OnEateryClickListener _listener) {
        this.userList = userList;
        listener= _listener;
    }

    @NonNull
    @Override
    public EateryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        if(true) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.eaterycard, parent, false);// create view object and inflate it

        }
        else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.eaterycard, parent, false);
        }
        return new EateryHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull EateryHolder holder, int position) {
        Picasso.get().load(userList.get(position).getProfile_pic_url()).fit().into(holder.iv); //get url in assignemnt
        holder.tv.setText(userList.get(position).getFn());//get restaurant name in assignment
    }

    // Using Abstraction, Encapsulation, Polymorphism and Inheritance
    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class EateryHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView iv;
        TextView tv;
        OnEateryClickListener listener;
        public EateryHolder(@NonNull View itemView, OnEateryClickListener _listener) {
            super(itemView);
            iv = itemView.findViewById(R.id.eatery_card_image);
            tv = itemView.findViewById(R.id.eatery_text);// bind to widget views
            listener = _listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onEateryClick(getAdapterPosition());// get index of the current clicked card
        }

        public interface OnEateryClickListener
        {
            public void onEateryClick(int index);//abstract method
        }

    }

}
