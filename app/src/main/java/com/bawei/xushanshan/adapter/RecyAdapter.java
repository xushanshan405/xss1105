package com.bawei.xushanshan.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.xushanshan.Bean;
import com.bawei.xushanshan.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.ViewHolder> {

    List<Bean.ResultBean> beans;
    FragmentActivity activity;

    public RecyAdapter(List<Bean.ResultBean> result, FragmentActivity activity) {
        this.beans = result;
        this.activity = activity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.activity_recy_adapter, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.name.setText(beans.get(i).getTitle());
        Glide.with(activity).load(beans.get(i).getImageUrl()).into(viewHolder.img);
    }

    @Override
    public int getItemCount() {
        return beans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
        }
    }
}
