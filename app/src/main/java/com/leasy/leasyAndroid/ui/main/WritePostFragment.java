package com.leasy.leasyAndroid.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.leasy.leasyAndroid.R;

public class WritePostFragment extends Fragment {

    private RecyclerView recyclerWrite;
    private Button btnPublish;
    private ImageView imgCover;
    private TextInputEditText edtTitle, edtDescription;
    private ImageButton btnAddText, btnAddHeading, btnAddImage, btnAddCode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_write_post, container, false);
        initialize(v);

        return v;
    }

    private void initialize(View v) {
        recyclerWrite = v.findViewById(R.id.recycler_write_post);
        btnPublish = v.findViewById(R.id.btn_write_post_publish);
        imgCover = v.findViewById(R.id.img_write_post_add_cover_image);
        edtTitle = v.findViewById(R.id.edt_write_post_title);
        edtDescription = v.findViewById(R.id.edt_write_post_description);
        btnAddText = v.findViewById(R.id.btn_write_post_add_text);
        btnAddHeading = v.findViewById(R.id.btn_write_post_add_heading);
        btnAddImage = v.findViewById(R.id.btn_write_post_add_image);
        btnAddCode = v.findViewById(R.id.btn_write_post_add_code);
    }
}