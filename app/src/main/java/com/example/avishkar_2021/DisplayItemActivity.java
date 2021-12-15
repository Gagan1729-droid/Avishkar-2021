package com.example.avishkar_2021;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.avishkar_2021.Adapters.SliderAdapter;
import com.example.avishkar_2021.Models.ExchangeItemModel;
import com.example.avishkar_2021.databinding.ActivityDisplayItemBinding;
import com.google.android.material.slider.Slider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class DisplayItemActivity extends AppCompatActivity {

    ActivityDisplayItemBinding binding;
    ExchangeItemModel model;
    FirebaseDatabase database;
    FirebaseAuth fAuth;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDisplayItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String json = getIntent().getStringExtra("ItemModelClass");
        model = new Gson().fromJson(json, ExchangeItemModel.class);
        database = FirebaseDatabase.getInstance();
        fAuth = FirebaseAuth.getInstance();
        uid = fAuth.getUid();

        binding.itemName.setText(model.getItem_name());
        binding.itemDescription.setText(model.getDescription());
        binding.itemExchanges.setText(model.getItems_exchange());
        binding.itemLocation.setText(model.getCity());
        binding.itemSeller.setText(model.getSeller_name());

        binding.chatButton.setOnClickListener(v -> {
            Intent i = new Intent(DisplayItemActivity.this, ChatActivity.class);
            Gson gson = new Gson();
            i.putExtra("ItemModelClass", json);
            startActivity(i);
            overridePendingTransition(R.anim.zoom_in,R.anim.zoom_out);
        });

        String[] images = model.getItem_images().split("\n");

        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();
        SliderView sliderView = binding.slider;
        for(String i: images){
            sliderDataArrayList.add(new SliderData(i));
        }

        SliderAdapter adapter = new SliderAdapter(this, sliderDataArrayList);
        sliderView.setSliderAdapter(adapter);
        sliderView.setAutoCycle(true);
        sliderView.setScrollTimeInSec(2);
        sliderView.startAutoCycle();
    }
}