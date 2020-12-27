package com.leasy.leasyAndroid.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.leasy.leasyAndroid.R;
import com.leasy.leasyAndroid.RecyclerAdapterPostsVertical;
import com.leasy.leasyAndroid.api.ApiUtils;
import com.leasy.leasyAndroid.api.UiCallBack;
import com.leasy.leasyAndroid.model.PostsListItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class CreateCourseFragment extends Fragment implements UiCallBack {

    private RecyclerView recyclerPosts;
    private Button btnPublish, btnAddPosts;
    private TextInputEditText edtTitle, edtDescription;
    private ImageView imgCover;

    private RecyclerAdapterPostsVertical recyclerAdapter;
    private List<PostsListItem> selectedPostsList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_create_course, container, false);
        initialize(v);

        if (selectedPostsList == null)
            selectedPostsList = new ArrayList<>();

        recyclerAdapter = new RecyclerAdapterPostsVertical(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        return;
                    }
                },
                selectedPostsList,
                getContext()
        );
        recyclerPosts.setAdapter(recyclerAdapter);
        recyclerPosts.setHasFixedSize(false);

        btnPublish.setOnClickListener(v1 -> {
            if (selectedPostsList.isEmpty()) {
                Toast.makeText(getContext(), "add posts.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (edtTitle.getText().length() == 0 || edtDescription.getText().length() == 0)
                return;
            String title = edtTitle.getText().toString();
            String descr = edtDescription.getText().toString();
            StringBuilder posts = new StringBuilder();
            for (int i = 0; i < selectedPostsList.size(); i++) {
                posts.append(selectedPostsList.get(i).getPostItem().getId());
                if (i != selectedPostsList.size()-1)
                    posts.append('-');
            }
            ApiUtils.requestAddCourse(
                    title,
                    "none",
                    descr,
                    posts.toString(),
                    CreateCourseFragment.this,
                    0
            );
        });

        btnAddPosts.setOnClickListener(v1 -> {
            String writer = getActivity().getSharedPreferences("user_leasy", Context.MODE_PRIVATE).getString("username", "windows");
            AddPostsToCourseFragment fragment = AddPostsToCourseFragment.newInstance(writer, this);
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.container_main_fragment, fragment)
                    .addToBackStack(null)
                    .commit();
        });

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

    void addPost(PostsListItem.PostItem postItem) {
        for (PostsListItem item :
             selectedPostsList) {
            if (item.getPostItem().getId().equals(postItem.getId()))
                return;
        }
        selectedPostsList.add(new PostsListItem(postItem));
    }

    @Override
    public void onRequestSuccessful(Response response, int code) {
        getActivity().onBackPressed();
    }

    @Override
    public void onRequestError(Response response, int code) {

    }

    @Override
    public void onRequestSendFailure(Throwable t, int code) {

    }

    @Override
    public void onRefreshTokenExpired(Response response, int code) {

    }

    @Override
    public void onObtainAccessTokenError(Response response, int code) {

    }

    @Override
    public void onObtainAccessTokenFailure(Throwable t, int code) {

    }

    @Override
    public void onInternalErrorFailure(int code) {

    }
}