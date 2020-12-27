package com.leasy.leasyAndroid.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.leasy.leasyAndroid.App;

public class ImageLoader {
    public static void loadImage(String addr, ImageView imageView){
        if(addr != null && addr.startsWith("cimg-")){
            Glide.with(App.ctx())
                    .load("https://calm-hamlet-80940.herokuapp.com/polls/download?dis=" + addr).into(imageView);
        }
    }
}
