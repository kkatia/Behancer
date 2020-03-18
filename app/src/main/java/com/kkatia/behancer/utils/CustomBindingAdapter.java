package com.kkatia.behancer.utils;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import androidx.databinding.BindingAdapter;

public class CustomBindingAdapter {
    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView,String imageUrl){
        Picasso.get().load(imageUrl).into(imageView);
    }
}
