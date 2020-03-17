package com.project.rentaway;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter_attribute extends RecyclerView.Adapter<adapter_attribute.MyViewHolder> {
    ArrayList<String> a1;
    ArrayList<String> a2;

    public adapter_attribute(ArrayList<String> a1, ArrayList<String> a2) {
        this.a1 = a1;
        this.a2 = a2;
    }

    @NonNull
    @Override
    public adapter_attribute.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_list_item,parent,false);
        adapter_attribute.MyViewHolder holder = new adapter_attribute.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.att.setText(a1.get(position));
        holder.att_val.setText(a2.get(position));
    }

    @Override
    public int getItemCount() {
        return a1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView att, att_val;

        public MyViewHolder(View itemView) {
            super(itemView);
            att = (TextView) itemView.findViewById(R.id.attribute);
            att_val = (TextView) itemView.findViewById(R.id.attribute_value);
        }
    }
}
