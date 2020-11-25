package com.leasy.leasyAndroid.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.leasy.leasyAndroid.R;
import com.leasy.leasyAndroid.WritePostRecyclerAdapter;
import com.leasy.leasyAndroid.api.ApiUtils;
import com.leasy.leasyAndroid.api.UiCallBack;
import com.leasy.leasyAndroid.model.WritePostItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

import retrofit2.Response;

public class WritePostFragment extends Fragment implements UiCallBack {

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

        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edtTitle.getText().toString();
                String photo = "NULL"; // FIXME: 11/20/20
                String category = "api"; // FIXME: 11/20/20
                String writer = "windows"; // FIXME: 11/20/20
                String summary = edtDescription.getText().toString();

                JSONObject jsonObject = new JSONObject();

                for (int i = 0; i < writePostItemLinkedList.size(); i++) {
                    WritePostItem postItem = writePostItemLinkedList.get(i);
                    switch (postItem.getPostType()) {
                        case text:
                            try {
                                jsonObject.put(
                                        "TEXT_" + i,
                                        ((WritePostItem.WritePostItemAddText) postItem).getText()
                                );
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            break;
                        default:
                            // TODO: 11/20/20
                            break;
                    }
                }

                ApiUtils.requestAddFullPost(
                        WritePostFragment.this,
                        title,
                        photo,
                        category,
                        writer,
                        "null",
                        "null",
                        jsonObject.toString(),
                        summary
                );
            }
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

    @Override
    public void onRequestSuccessful(Response response) {
        // TODO: 11/20/20 go to home page
    }

    @Override
    public void onRequestError(Response response) {
        Toast.makeText(getContext(), "on request error :/", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestSendFailure(Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void onRefreshTokenExpired(Response response) {
        Toast.makeText(getContext(), "refresh token failure", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onObtainAccessTokenError(Response response) {
        Toast.makeText(getContext(), "obtain access token error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onObtainAccessTokenFailure(Throwable t) {
        Toast.makeText(getContext(), "access token failure", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInternalErrorFailure() {
        Toast.makeText(getContext(), "Internal Error", Toast.LENGTH_SHORT).show();
    }
}