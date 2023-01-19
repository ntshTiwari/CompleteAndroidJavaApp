package com.example.completeandroidjavaapp.utils.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.completeandroidjavaapp.R;
import com.example.completeandroidjavaapp.models.SliderData;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends
        SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder>
{

    private final List<SliderData> sliderItems;

    public SliderAdapter(ArrayList<SliderData> sliderItems){
        this.sliderItems = sliderItems;
    }

    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.caraousel_slider_layout, null);
        return new SliderAdapterViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, int position) {
        SliderData sliderItem = sliderItems.get(position);
        Glide.with(viewHolder.itemView)
                .load(sliderItem.imageUrl)
                .fitCenter()
                .into(viewHolder.imageView);
    }

    @Override
    public int getCount() {
        return sliderItems.size();
    }

    static class SliderAdapterViewHolder extends SliderViewAdapter.ViewHolder{
        View item;
        ImageView imageView;

        public SliderAdapterViewHolder(View item) {
            super(item);
            this.item = item;
            imageView = item.findViewById(R.id.sliderImage);
        }
    }

}
