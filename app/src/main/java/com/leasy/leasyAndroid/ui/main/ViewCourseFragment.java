package com.leasy.leasyAndroid.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.leasy.leasyAndroid.R;

public class ViewCourseFragment extends Fragment {

    private String courseID;
    private CourseInfoFragment courseInfoFragment;
    private CoursePostsFragment coursePostsFragment;

    private FrameLayout frameLayout;

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
        initialize(v);
        showCourseInfo(0, 0);
        return v;
    }

    public void showCourseInfo(int animIn, int animOut) {
        if (courseInfoFragment == null)
            courseInfoFragment = CourseInfoFragment.newInstance(courseID);
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        if (animIn != 0)
            fragmentTransaction = fragmentTransaction.setCustomAnimations(animIn, animOut);
        fragmentTransaction = fragmentTransaction.replace(R.id.frame_course_view_fragment_container, courseInfoFragment);
        fragmentTransaction.commit();
    }

    public void showCoursePosts(int animIn, int animOut) {
        if (coursePostsFragment == null)
            coursePostsFragment = CoursePostsFragment.newInstance(courseID);
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        if (animIn != 0)
            fragmentTransaction = fragmentTransaction.setCustomAnimations(animIn, animOut);
        fragmentTransaction = fragmentTransaction.replace(R.id.frame_course_view_fragment_container, coursePostsFragment);
        fragmentTransaction.commit();
    }

    private void initialize(View v) {
        frameLayout = v.findViewById(R.id.frame_course_view_fragment_container);
    }
}