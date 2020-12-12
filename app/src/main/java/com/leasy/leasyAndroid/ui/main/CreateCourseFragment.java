package com.leasy.leasyAndroid.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.leasy.leasyAndroid.R;

public class CreateCourseFragment extends Fragment {

    private RecyclerView recyclerPosts;
    private Button btnPublish, btnAddPosts;
    private TextInputEditText edtTitle, edtDescription;
    private ImageView imgCover;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_create_course, container, false);
        initialize(v);


        return v;
    }

    private void initialize(View v) {
        btnAddPosts = v.findViewById(R.id.btn_create_course_add_post);
        btnPublish = v.findViewById(R.id.btn_create_course_publish);
        recyclerPosts = v.findViewById(R.id.recycler_create_course);
        edtTitle = v.findViewById(R.id.edt_create_course_title_title);
        edtDescription = v.findViewById(R.id.edt_create_course_description);
        imgCover = v.findViewById(R.id.img_create_course_add_cover_image);
    }


}