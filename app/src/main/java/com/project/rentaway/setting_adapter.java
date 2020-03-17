package com.project.rentaway;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class setting_adapter extends RecyclerView.Adapter {

    private static final String TAG = "setting_adapter";
    private ArrayList<Model> list;
    private Listener listener;

    public setting_adapter(ArrayList<Model> list,Listener listener) {
        this.list = list;
        Log.d(TAG, "setting_adapter: list");
        this.listener=listener;
    }
    @Override
    public int getItemViewType(int position) {
        
        switch (list.get(position).getType())
        {
            case 0:
                Log.d(TAG, "getItemViewType: 0");
                return Model.NORMAL_TYPE;
            case 1:
                Log.d(TAG, "getItemViewType: 1");
                return Model.BOLD_TYPE;
            default:
                    return -1;
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType)
        {
            case Model.NORMAL_TYPE:
                Log.d(TAG, "onCreateViewHolder: 0");
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_list, parent, false);
                return new SViewHolder(view,listener);
            case Model.BOLD_TYPE:
                Log.d(TAG, "onCreateViewHolder: 1");
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_text_separator, parent, false);
                return new Separator_ViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        
        switch (list.get(position).getType())
        {
            case 0:
                Log.d(TAG, "onBindViewHolder: 0");
                ((SViewHolder)holder).text.setText(list.get(position).getText());
                break;
            case 1:
                Log.d(TAG, "onBindViewHolder: 1");
                ((Separator_ViewHolder)holder).text.setText(list.get(position).getText());
                Log.d(TAG, "onBindViewHolder: after bind");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView text;
        Listener listener;

        public SViewHolder(@NonNull View itemView,Listener listener) {
            super(itemView);
            Log.d(TAG, "SViewHolder: 0");
            this.text = itemView.findViewById(R.id.text);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClicked(getAdapterPosition());
        }
    }
    public class Separator_ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;

        public Separator_ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d(TAG, "Separator_ViewHolder: 1");
            text=itemView.findViewById(R.id.text_separator);
        }
    }


    public interface Listener{
            void onClicked(int position);
    }
}
