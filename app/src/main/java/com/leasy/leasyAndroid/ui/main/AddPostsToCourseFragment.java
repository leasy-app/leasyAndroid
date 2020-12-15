package com.leasy.leasyAndroid.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.leasy.leasyAndroid.R;
import com.leasy.leasyAndroid.RecyclerAdapterPostsSelectable;
import com.leasy.leasyAndroid.RecyclerAdapterPostsVertical;
import com.leasy.leasyAndroid.model.PostsListItem;

import java.util.List;


public class AddPostsToCourseFragment extends Fragment {
    
    private FloatingActionButton fabAdd;
    private RecyclerView recyclerPosts;

    private RecyclerAdapterPostsSelectable recyclerAdapterPosts;
    private String writer;
    private List<PostsListItem> previouslyAddedPosts;
    
    public static AddPostsToCourseFragment newInstance(List<PostsListItem> previouslyAddedPosts, String writer) {
        AddPostsToCourseFragment fragment = new AddPostsToCourseFragment();
        fragment.previouslyAddedPosts = previouslyAddedPosts;
        fragment.writer = writer;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_posts_to_course, container, false);
        initialize(v);

        // TODO: 12/16/20 get posts from server

        fabAdd.setOnClickListener(v1 -> {
            // TODO: 12/16/20 add posts to course
        });
        return v;
    }

    private void selectedClickListener(View v) {
        // TODO: 12/16/20
    }

    private void initialize(View v) {
        recyclerPosts = v.findViewById(R.id.recycler_add_posts_to_course);
        fabAdd = v.findViewById(R.id.fab_add_posts_to_course);
    }
}