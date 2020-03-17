package com.project.rentaway;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private ArrayList<Property>  list;
    private Listener listener;

    public MyAdapter(ArrayList<Property> list, Listener listener) {
        this.list = list;
        this.listener=listener;
    }


    public void updateList(List<Property> newList)
    {
        list = new ArrayList<>();
        list.addAll(newList);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view,listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        Log.d("MyAdapter", "onBindViewHolder: called..");
        Property currentItem = list.get(position);
        holder.address.setText(currentItem.getAddress());
        holder.rent.setText(currentItem.getRent());
        holder.room.setText(currentItem.getBHK());
        Picasso.get().load(currentItem.getImageUri()).fit().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView address,room,rent;
        public ImageView imageView;
        Listener listener;

        public MyViewHolder(View itemView, Listener listener) {
            super(itemView);
            address = itemView.findViewById(R.id.place);  //address
            room = itemView.findViewById(R.id.bhk);      //room
            rent = itemView.findViewById(R.id.price);   //rent
            imageView = itemView.findViewById(R.id.card_image);
            this.listener = listener;

            itemView.setOnClickListener(this);
        }

    @Override
    public void onClick(View view) {
            listener.onClicked(getAdapterPosition());
    }
}

    public interface Listener{
        void onClicked(int position);
    }
}
