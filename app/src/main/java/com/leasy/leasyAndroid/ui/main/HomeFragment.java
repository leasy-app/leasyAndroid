package com.leasy.leasyAndroid.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.leasy.leasyAndroid.R;
import com.leasy.leasyAndroid.RecyclerAdapterPostsVertical;
import com.leasy.leasyAndroid.model.ListItems;

import java.util.LinkedList;

public class HomeFragment extends Fragment implements View.OnClickListener{

    private ImageView imgUser;
    private RecyclerView recyclerPosts;
    private RecyclerAdapterPostsVertical recyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        initialize(v);

        Glide.with(this).load(R.drawable.profile).centerInside().into(imgUser);


        // FIXME: 10/31/20 Get the real list

        LinkedList<ListItems> list = new LinkedList<>();
        ListItems items = new ListItems();
        items.setSectionTitle("Latest Posts");
        list.add(items);
        recyclerAdapter = new RecyclerAdapterPostsVertical(this::onClick, list, getContext());
        recyclerPosts.setHasFixedSize(true);
        recyclerPosts.setAdapter(recyclerAdapter);
        return v;
    }

    @Override
    public void onClick(View v) {
        // TODO: 10/31/20 open posts
    }

    private void initialize(View v) {
        imgUser = v.findViewById(R.id.img_user_image_home);
        recyclerPosts = v.findViewById(R.id.recycler_home_page);
    }
}