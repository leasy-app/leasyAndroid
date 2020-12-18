package com.leasy.leasyAndroid.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leasy.leasyAndroid.R;

public class ViewCourseFragment extends Fragment {

    private String courseID;
    private CourseInfoFragment courseInfoFragment;
    private CoursePostsFragment coursePostsFragment;

    public static ViewCourseFragment newInstance(String courseID) {
        ViewCourseFragment fragment = new ViewCourseFragment();
        fragment.courseID = courseID;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_view_course_framgent, container, false);
//        initialize(v);

        return v;
    }

    public void showCourseInfo(int animIn, int animOut) {
        if (courseInfoFragment == null)
            courseInfoFragment = CourseInfoFragment.newInstance(courseID);
        getChildFragmentManager().beginTransaction()
                .replace(R.id.frame_course_view_fragment_container, courseInfoFragment)
                .setCustomAnimations(animIn, animOut)
                .commit();

    }

    public void showCoursePosts(int animIn, int animOut) {
        if (coursePostsFragment == null)
            coursePostsFragment = CoursePostsFragment.newInstance(courseID);
        getChildFragmentManager().beginTransaction()
                .replace(R.id.frame_course_view_fragment_container, coursePostsFragment)
                .setCustomAnimations(animIn, animOut)
                .commit();

    }

    private void initialize(View v) {
    }
}