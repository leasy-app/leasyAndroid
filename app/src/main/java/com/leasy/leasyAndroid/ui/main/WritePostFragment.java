package com.leasy.leasyAndroid.ui.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
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
import com.leasy.leasyAndroid.WritePostRecyclerAdapter;
import com.leasy.leasyAndroid.model.WritePostItem;

import java.util.ArrayList;
import java.util.LinkedList;

public class WritePostFragment extends Fragment {

    private RecyclerView recyclerWrite;
    private Button btnPublish;
    private ImageView imgCover;
    private TextInputEditText edtTitle, edtDescription;
    private ImageButton btnAddText, btnAddHeading, btnAddImage, btnAddCode;

    private WritePostRecyclerAdapter writePostRecyclerAdapter;

    private LinkedList<WritePostItem> writePostItemLinkedList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (writePostItemLinkedList == null) {
            writePostItemLinkedList = new LinkedList<>();
        }
        writePostRecyclerAdapter = new WritePostRecyclerAdapter(writePostItemLinkedList, getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_write_post, container, false);
        initialize(v);


        recyclerWrite.setHasFixedSize(false);
        recyclerWrite.setAdapter(writePostRecyclerAdapter);

        btnAddText.setOnClickListener(v1 -> {
            writePostItemLinkedList.add(new WritePostItem.WritePostItemAddText());
            writePostRecyclerAdapter.notifyDataSetChanged();
        });

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