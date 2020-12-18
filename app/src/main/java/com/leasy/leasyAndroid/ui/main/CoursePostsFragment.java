package com.leasy.leasyAndroid.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leasy.leasyAndroid.R;

public class CoursePostsFragment extends Fragment {

   private String courseID;

    public static CoursePostsFragment newInstance(String courseID) {
        CoursePostsFragment fragment = new CoursePostsFragment();
        fragment.courseID = courseID;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_course_posts, container, false);
        initialize(v);


        return v;
    }

    private void initialize(View v) {

    }
}