package com.leasy.leasyAndroid.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leasy.leasyAndroid.R;

public class CourseInfoFragment extends Fragment {

    private String courseID;

    private TextView txtDescription, txtTitle, txtAuthor, txtShowPosts;
    private ImageView imgCover, imgShowPosts, imgAuthor;
    
    public static CourseInfoFragment newInstance(String courseID) {
        CourseInfoFragment fragment = new CourseInfoFragment();
        fragment.courseID = courseID;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_course_info, container, false);
        initialize(v);
        
        return v;
    }

    private void initialize(View v) {
        txtAuthor = v.findViewById(R.id.txt_course_info_author_name);
        txtTitle = v.findViewById(R.id.txt_course_info_title);
        txtDescription = v.findViewById(R.id.txt_course_description);
        txtShowPosts = v.findViewById(R.id.txt_course_content);
        imgAuthor = v.findViewById(R.id.img_course_info_author_image);
        imgShowPosts = v.findViewById(R.id.img_course_info_arrow_down);
        imgCover = v.findViewById(R.id.img_course_info_cover_image);
    }
}