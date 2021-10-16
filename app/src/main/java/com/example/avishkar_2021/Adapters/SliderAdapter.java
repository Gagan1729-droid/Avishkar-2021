package com.example.avishkar_2021.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.avishkar_2021.R;
import com.example.avishkar_2021.SliderData;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder> {
    private List<SliderData> sliderDataList;
    Context context;
    public SliderAdapter(Context context, ArrayList<SliderData> sliderData){
        sliderDataList = sliderData;
        this.context = context;
    }

    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.sliderlayout, null);
        return new SliderAdapterViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, int position) {
        SliderData sliderItem = sliderDataList.get(position);
//        Glide.with(viewHolder.itemView)
//                .load(sliderItem.getImgUrl())
//                .fitCenter()
//                .into(viewHolder.imageView);
        Picasso.get().load(sliderItem.getImgUrl()).into(viewHolder.imageView);
    }

    @Override
    public int getCount() {
        return sliderDataList.size();
    }


    static class SliderAdapterViewHolder extends SliderViewAdapter.ViewHolder {
        View itemView;
        ImageView imageView;
        public SliderAdapterViewHolder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView_slider);
        }
    }
}
