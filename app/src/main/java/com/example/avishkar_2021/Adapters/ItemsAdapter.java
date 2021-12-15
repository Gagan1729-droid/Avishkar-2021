package com.example.avishkar_2021.Adapters;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.avishkar_2021.DisplayItemActivity;
import com.example.avishkar_2021.Models.ExchangeItemModel;
import com.example.avishkar_2021.Models.ItemModel;
import com.example.avishkar_2021.R;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {
ArrayList<ExchangeItemModel> list;
Context context;
    public ItemsAdapter(Context context, ArrayList<ExchangeItemModel> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ItemsAdapter.ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull ItemsAdapter.ViewHolder holder, int position) {
        ExchangeItemModel model = list.get(position);
        String image = model.getItem_images();
        image = image.substring(0, image.indexOf("\n"));
        Picasso.get().load(image).placeholder(R.drawable.loading_image).into(holder.image);
        holder.name.setText(model.getItem_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gson gson = new Gson();
                String json = gson.toJson(model);
                Intent i = new Intent(context, DisplayItemActivity.class);
                i.putExtra("ItemModelClass",json);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
    ImageView image;
    TextView name;
        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_image);
            name = itemView.findViewById(R.id.item_name);
        }
    }
}
