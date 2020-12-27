package com.leasy.leasyAndroid.ui.main;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.leasy.leasyAndroid.R;
import com.leasy.leasyAndroid.RecyclerAdapterReadPost;
import com.leasy.leasyAndroid.api.ApiUtils;
import com.leasy.leasyAndroid.api.UiCallBack;
import com.leasy.leasyAndroid.model.PostsListItem;
import com.leasy.leasyAndroid.model.ReadPostItem;
import com.leasy.leasyAndroid.model.User;
import com.leasy.leasyAndroid.util.Dates;
import com.leasy.leasyAndroid.util.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Response;

public class ReadPostFragment extends Fragment implements UiCallBack {

    private TextView txtTitle, txtAuthor, txtDate;
    private CircleImageView imgAuthorPhoto;
    private ImageView imgCoverImage;
    private RecyclerView recyclerPost;

    private RecyclerAdapterReadPost adapterReadPost;
    private List<ReadPostItem> readPostItemList;

    private ImageButton btnAddLike, btnAddBookmark;

    private static final String ARG_PARAM_POST_ITEM = "param1";

    private PostsListItem paramPostItem;

    private static final int REQUEST_CODE_LOAD_CONTENT = 0;
    private static final int REQUEST_CODE_ADD_READ = 1;
    private static final int REQUEST_CODE_ADD_LIKE = 2;
    private static final int REQUEST_CODE_ADD_BOOKMARK = 3;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param paramPostItem Parameter 1.
     * @return A new instance of fragment ReadPostFragment.
     */
    public static ReadPostFragment newInstance(PostsListItem paramPostItem) {
        ReadPostFragment fragment = new ReadPostFragment();
        fragment.setParamPostItem(paramPostItem);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_read_post, container, false);
        initialize(v);

        txtTitle.setText(paramPostItem.getPostItem().getTitle());
        txtDate.setText(Dates.fixDate(paramPostItem.getPostItem().getDate()));
        txtAuthor.setText(paramPostItem.getPostItem().getAuthor());
        String authorId = paramPostItem.getPostItem().getAuthor();
        String coverUrl = paramPostItem.getPostItem().getPostImageURL();
        ImageLoader.loadImage(coverUrl, imgCoverImage);
        ApiUtils.getUser(new UiCallBack() {
            @Override
            public void onRequestSuccessful(Response response, int code) {
                User u = ((List<User>)response.body()).get(0);
                ImageLoader.loadImage(u.photo, imgAuthorPhoto);
                txtAuthor.setText(u.name);
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
        }, 1, authorId);
        // FIXME: 11/2/20 Show read content
        ApiUtils.requestGetPostContent(this, 0, paramPostItem.getPostItem().getId());

        btnAddLike.setOnClickListener(v1 -> {
            String user = getActivity().getSharedPreferences("user_leasy", Context.MODE_PRIVATE).getString("username", "windows");
            ApiUtils.requestAddLike(this, REQUEST_CODE_ADD_LIKE, user, paramPostItem.getPostItem().getId());
        });
        btnAddBookmark.setOnClickListener(v1 -> {
            String user = getActivity().getSharedPreferences("user_leasy", Context.MODE_PRIVATE).getString("username", "windows");
            ApiUtils.requestAddBookmark(this, REQUEST_CODE_ADD_BOOKMARK, user, paramPostItem.getPostItem().getId());
        });


        return v;
    }

    private void setupList() {
        adapterReadPost = new RecyclerAdapterReadPost(readPostItemList, getContext());
        recyclerPost.setHasFixedSize(true);
        recyclerPost.setAdapter(adapterReadPost);
    }

    @Override
    public void onRequestSuccessful(Response response, int code) {
        if (code == REQUEST_CODE_LOAD_CONTENT) {
            ReadPostItem readPostItem;
            try {
                readPostItem = ((List<List<ReadPostItem>>) response.body()).get(0).get(0);
            } catch (Exception e){
                return;
            }
            String content = readPostItem.getMainContent();
            System.out.println(content);
            readPostItemList = new LinkedList<>();
            readPostItemList.add(new ReadPostItem.ReadPostItemText(0, paramPostItem.getPostItem().getSummary()));
            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(content);
                Iterator<String> iter = jsonObject.keys();
                int i = 1;
                while (iter.hasNext()) {
                    String key = iter.next();
                    if (key.startsWith("TEXT")) {
                        try {
                            ReadPostItem.ReadPostItemText itemText = new ReadPostItem.ReadPostItemText(i,
                                    jsonObject.getString(key));
                            readPostItemList.add(itemText);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else if (key.startsWith("IMAGE")) {
                        try {
                            ReadPostItem.ReadPostItemImage readPostItemImage = new ReadPostItem.ReadPostItemImage(i,
                                    jsonObject.getString(key));
                            readPostItemList.add(readPostItemImage);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    i++;
                }
                setupList();
                String user = getActivity().getSharedPreferences("user_leasy", Context.MODE_PRIVATE).getString("username", "windows");
                ApiUtils.requestAddRead(this, REQUEST_CODE_ADD_READ, user, paramPostItem.getPostItem().getId());
            } catch (Exception e) {
                readPostItemList.add(new ReadPostItem.ReadPostItemText(1, content));
                System.out.println("shit");
                setupList();
                return;
            }
        } else if (code == REQUEST_CODE_ADD_READ) {
            Log.i("read", "read_added");
        } else if (code == REQUEST_CODE_ADD_LIKE){
            btnAddLike.setImageResource(R.drawable.ic_icons8_love_filled);
            btnAddLike.setColorFilter(ContextCompat.getColor(getContext(), R.color.pink_palette), PorterDuff.Mode.SRC_ATOP);
            btnAddLike.setEnabled(false);
        } else if (code == REQUEST_CODE_ADD_BOOKMARK) {
            btnAddBookmark.setImageResource(R.drawable.ic_baseline_bookmark_24);
            btnAddBookmark.setColorFilter(ContextCompat.getColor(getContext(), R.color.green_palette), PorterDuff.Mode.MULTIPLY);
            btnAddBookmark.setEnabled(false);
        }
    }

    @Override
    public void onRequestError(Response response, int code) {

    }

    @Override
    public void onRequestSendFailure(Throwable t, int code) {
        t.printStackTrace();
    }

    @Override
    public void onRefreshTokenExpired(Response response, int code) {
        Toast.makeText(getContext(), "refresh token failure", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onObtainAccessTokenError(Response response, int code) {
        Toast.makeText(getContext(), "obtain access token error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onObtainAccessTokenFailure(Throwable t, int code) {
        Toast.makeText(getContext(), "access token failure", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInternalErrorFailure(int code) {
//        Toast.makeText(getContext(), "Internal Error", Toast.LENGTH_SHORT).show();
    }

    private void initialize(View v) {
        recyclerPost = v.findViewById(R.id.recycler_read_post_recycler);
        txtAuthor = v.findViewById(R.id.txt_read_post_author_name);
        txtDate = v.findViewById(R.id.txt_read_post_date);
        txtTitle = v.findViewById(R.id.txt_read_post_title);
        imgAuthorPhoto = v.findViewById(R.id.img_read_post_author_image);
        imgCoverImage = v.findViewById(R.id.img_read_post_cover_image);
        btnAddBookmark = v.findViewById(R.id.btn_read_post_bookmark);
        btnAddLike = v.findViewById(R.id.btn_read_post_like);
    }

    public PostsListItem getParamPostItem() {
        return paramPostItem;
    }

    public void setParamPostItem(PostsListItem paramPostItem) {
        this.paramPostItem = paramPostItem;
    }
}