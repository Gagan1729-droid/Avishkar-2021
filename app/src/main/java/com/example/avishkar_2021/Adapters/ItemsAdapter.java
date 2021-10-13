package com.example.avishkar_2021.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.avishkar_2021.Models.ItemModel;
import com.example.avishkar_2021.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {
ArrayList<ItemModel> list;
Context context;
public ItemsAdapter(Context context, ArrayList<ItemModel> list){
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
        ItemModel model = list.get(position);
        Picasso.get().load(model.getImage()).placeholder(R.drawable.math_logo).into(holder.image);
        holder.name.setText(model.getName());
        holder.price.setText("Rs. "+ Integer.toString(model.getPrice()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
    ImageView image;
    TextView name, price;
        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_image);
            name = itemView.findViewById(R.id.item_name);
            price = itemView.findViewById(R.id.item_price);
        }
    }
}
