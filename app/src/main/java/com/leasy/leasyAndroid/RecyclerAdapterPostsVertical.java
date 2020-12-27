package com.leasy.leasyAndroid;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.leasy.leasyAndroid.api.ApiUtils;
import com.leasy.leasyAndroid.api.UiCallBack;
import com.leasy.leasyAndroid.model.PostsListItem;
import com.leasy.leasyAndroid.model.User;
import com.leasy.leasyAndroid.util.Dates;
import com.leasy.leasyAndroid.util.ImageLoader;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Response;

public class RecyclerAdapterPostsVertical extends RecyclerView.Adapter<RecyclerAdapterPostsVertical.PostsVerticalRecyclerViewHolder> {

    private View.OnClickListener onClickListener;
    private List<PostsListItem> postsListItems;
    private Context context;

    private static final int item_type_post = 101;
    private static final int item_type_section = 282;
    private static final String LOG_TAG = "log_posts_recycler";

    public RecyclerAdapterPostsVertical(View.OnClickListener onClickListener, List<PostsListItem> postsListItems, Context context) {
        this.onClickListener = onClickListener;
        this.postsListItems = postsListItems;
        this.context = context;
    }

    @NonNull
    @Override
    public PostsVerticalRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        if (viewType == item_type_post) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.posts_list_item_vertical, parent, false);
            return new PostsVerticalRecyclerViewHolder(v, onClickListener);
        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.posts_list_section_title_item, parent, false);
            return new PostsVerticalRecyclerViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull PostsVerticalRecyclerViewHolder holder, int position) {
        Log.i(LOG_TAG, postsListItems.get(position).toString());
        if (getItemViewType(position) == item_type_section) {
            holder.txtSec.setText(postsListItems.get(position).getSectionTitle());
        } else {
            PostsListItem.PostItem item = postsListItems.get(position).getPostItem();
            holder.txtCategory.setText(item.getCategory());
            holder.txtTitle.setText(item.getTitle());
            holder.txtSummary.setText(item.getSummary());
            holder.txtDate.setText(Dates.fixDate(item.getDate()));
            holder.txtAuthor.setText(item.getAuthor());
            ApiUtils.getUser(new UiCallBack() {
                @Override
                public void onRequestSuccessful(Response response, int code) {
                    User u = ((List<User>)response.body()).get(0);
                    ImageLoader.loadImage(u.photo, holder.imgAuthor);
                    holder.txtAuthor.setText(u.name);
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
            }, 1, item.getAuthor());
//            ImageLoader.loadImage(item.getAuthorImageURL(), holder.imgAuthor);
            ImageLoader.loadImage(item.getPostImageURL(), holder.imgPost);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (postsListItems.get(position).getSectionTitle() != null)
            return item_type_section;
        else
            return item_type_post;
    }

    @Override
    public int getItemCount() {
        return postsListItems.size();
    }


    public static class PostsVerticalRecyclerViewHolder extends RecyclerView.ViewHolder{

        CircleImageView imgAuthor;
        ImageView imgPost;
        TextView txtTitle, txtSummary, txtAuthor, txtDate, txtCategory;

        /**
         * Use this constructor to create posts
         * @param itemView a View object referring to the list item
         * @param onClickListener click listener for post item
         */
        PostsVerticalRecyclerViewHolder(@NonNull View itemView, View.OnClickListener onClickListener) {
            super(itemView);
            imgAuthor = itemView.findViewById(R.id.img_post_author_vertical_list);
            imgPost = itemView.findViewById(R.id.img_post_image_vertical_list);
            txtTitle = itemView.findViewById(R.id.txt_post_title_vertical_list);
            txtSummary = itemView.findViewById(R.id.txt_post_summary_vertical_list);
            txtAuthor = itemView.findViewById(R.id.txt_post_author_vertical_list);
            txtDate = itemView.findViewById(R.id.txt_post_date_vertical_list);
            txtCategory = itemView.findViewById(R.id.txt_post_category_vertical_list);

            itemView.setTag(this);
            itemView.setOnClickListener(onClickListener);
        }


        TextView txtSec;
        /**
         * Use this constructor to create sections
         * @param itemView a View object referring to the title item
         */
        PostsVerticalRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSec = itemView.findViewById(R.id.txt_post_list_section_title);
        }
    }
}
