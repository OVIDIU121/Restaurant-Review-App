package com.example.myfffd.utility;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfffd.R;
import com.example.myfffd.models.StreetFood;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * The type Street food adaptor.
 */
public class StreetFoodAdaptor extends RecyclerView.Adapter<StreetFoodAdaptor.StreetFoodHolder> {
    /**
     * The Street food list.
     */
    List<StreetFood> streetFoodList;

    /**
     * The Listener.
     */
    StreetFoodAdaptor.StreetFoodHolder.OnStreetFoodClickListener listener;

    /**
     * Instantiates a new Street food adaptor.
     *
     * @param streetFoodList the street food list
     * @param _listener      the listener
     */
    public StreetFoodAdaptor(List<StreetFood> streetFoodList, StreetFoodAdaptor.StreetFoodHolder.OnStreetFoodClickListener _listener) {
        this.streetFoodList = streetFoodList;
        listener = _listener;
    }

    @NonNull
    @Override
    public StreetFoodAdaptor.StreetFoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        if (true) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.eaterycard, parent, false);// create view object and inflate it

        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.eaterycard, parent, false);
        }
        return new StreetFoodAdaptor.StreetFoodHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull StreetFoodAdaptor.StreetFoodHolder holder, int position) {
        Picasso.get().load(streetFoodList.get(position).getProfile_picture()).fit().into(holder.iv);
        holder.tv.setText(streetFoodList.get(position).getName());
        holder.tv_description.setText(streetFoodList.get(position).getDescription());
    }

    // Using Abstraction, Encapsulation, Polymorphism and Inheritance
    @Override
    public int getItemCount() {
        return streetFoodList.size();
    }

    /**
     * The type Street food holder.
     */
    public static class StreetFoodHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
        StreetFoodAdaptor.StreetFoodHolder.OnStreetFoodClickListener listener;

        /**
         * Instantiates a new Street food holder.
         *
         * @param itemView  the item view
         * @param _listener the listener
         */
        public StreetFoodHolder(@NonNull View itemView, StreetFoodAdaptor.StreetFoodHolder.OnStreetFoodClickListener _listener) {
            super(itemView);
            iv = itemView.findViewById(R.id.eatery_card_image);
            tv_description = itemView.findViewById(R.id.tv_description);
            tv = itemView.findViewById(R.id.eatery_text);// bind to widget views
            listener = _listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onStreetFoodClick(getAdapterPosition());// get index of the current clicked card
        }

        /**
         * The interface On street food click listener.
         */
        public interface OnStreetFoodClickListener {
            /**
             * On street food click.
             *
             * @param index the index
             */
            void onStreetFoodClick(int index);//abstract method
        }

    }

}
