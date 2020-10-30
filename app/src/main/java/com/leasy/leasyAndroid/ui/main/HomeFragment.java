package com.leasy.leasyAndroid.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.leasy.leasyAndroid.R;

public class HomeFragment extends Fragment {

    private ImageView imgUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        initialize(v);

        Glide.with(this).load(R.drawable.profile).centerInside().circleCrop().into(imgUser);

        return v;
    }

    private void initialize(View v) {
        imgUser = v.findViewById(R.id.img_user_image_home);
    }
}