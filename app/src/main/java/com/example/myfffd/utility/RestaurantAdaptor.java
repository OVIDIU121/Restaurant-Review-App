package com.example.myfffd.utility;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfffd.R;
import com.example.myfffd.models.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * The type Restaurant adaptor.
 */
public class RestaurantAdaptor extends RecyclerView.Adapter<RestaurantAdaptor.EateryHolder> {
    /**
     * The Restaurant list.
     */
    List<Restaurant> restaurantList;

    /**
     * The Listener.
     */
    EateryHolder.OnEateryClickListener listener;

    /**
     * Instantiates a new Restaurant adaptor.
     *
     * @param restaurantList the restaurant list
     * @param _listener      the listener
     */
    public RestaurantAdaptor(List<Restaurant> restaurantList, EateryHolder.OnEateryClickListener _listener) {
        this.restaurantList = restaurantList;
        listener = _listener;
    }

    @NonNull
    @Override
    public EateryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        if (true) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.eaterycard, parent, false);
            // create view object and inflate it

        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.eaterycard, parent, false);
        }
        return new EateryHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull EateryHolder holder, int position) {
        Picasso.get().load(restaurantList.get(position).getProfile_picture()).fit().into(holder.iv);
        holder.tv.setText(restaurantList.get(position).getName() + ", " + restaurantList.get(position).getCity());
        holder.tv_description.setText(restaurantList.get(position).getDescription());
    }

    // Using Abstraction, Encapsulation, Polymorphism and Inheritance
    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    /**
     * The type Eatery holder.
     */
    public static class EateryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        /**
         * The Iv.
         */
        ImageView iv;
        /**
         * The Tv.
         */
        TextView tv, /**
         * The Tv description.
         */
        tv_description;
        /**
         * The Listener.
         */
        OnEateryClickListener listener;

        /**
         * Instantiates a new Eatery holder.
         *
         * @param itemView  the item view
         * @param _listener the listener
         */
        public EateryHolder(@NonNull View itemView, OnEateryClickListener _listener) {
            super(itemView);
            /*Define the variables and bind the to the view ID`s*/
            iv = itemView.findViewById(R.id.eatery_card_image);
            tv_description = itemView.findViewById(R.id.tv_description);
            tv = itemView.findViewById(R.id.eatery_text);// bind to widget views
            listener = _listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onEateryClick(getAdapterPosition());// get index of the current clicked card
        }

        /**
         * The interface On eatery click listener.
         */
        public interface OnEateryClickListener {
            /**
             * On eatery click.
             *
             * @param index the index
             */
            void onEateryClick(int index);//abstract method
        }

    }

}
