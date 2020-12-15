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
import com.leasy.leasyAndroid.model.PostsListItem;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapterPostsSelectable extends RecyclerView.Adapter<RecyclerAdapterPostsSelectable.PostsSelectableRecyclerViewHolder> {

    private View.OnClickListener onClickListener;
    private List<PostsListItem> postsListItems;
    private List<String> selectedIDs;
    private Context context;

    private static final int item_type_post = 101;
    private static final int item_type_section = 282;
    private static final String LOG_TAG = "log_posts_recycler";

    public RecyclerAdapterPostsSelectable(View.OnClickListener onClickListener,
                                          List<PostsListItem> postsListItems,
                                          List<String> selectedIDs,
                                          Context context) {
        this.onClickListener = onClickListener;
        this.postsListItems = postsListItems;
        this.selectedIDs = selectedIDs;
        this.context = context;
    }

    @NonNull
    @Override
    public PostsSelectableRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.posts_list_item_vertical, parent, false);
        return new PostsSelectableRecyclerViewHolder(v, onClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsSelectableRecyclerViewHolder holder, int position) {
        Log.i(LOG_TAG, postsListItems.get(position).toString());
        PostsListItem.PostItem item = postsListItems.get(position).getPostItem();
        if (selectedIDs.contains(item.getId()))
            holder.itemView.setBackground(context.getDrawable(R.drawable.rectangle_rounded));
        holder.txtCategory.setText(item.getCategory());
        holder.txtTitle.setText(item.getTitle());
        holder.txtSummary.setText(item.getSummary());
        holder.txtDate.setText(item.getDate());
        holder.txtAuthor.setText(item.getAuthor());
        if (item.getAuthorImageURL() != null)
            if (URLUtil.isValidUrl(item.getAuthorImageURL()))
                Glide.with(context).load(item.getAuthorImageURL()).into(holder.imgAuthor);
        if (item.getPostImageURL() != null)
            if (URLUtil.isValidUrl(item.getPostImageURL()))
                Glide.with(context).load(item.getPostImageURL()).into(holder.imgPost);
    }

    @Override
    public int getItemCount() {
        return postsListItems.size();
    }


    public static class PostsSelectableRecyclerViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imgAuthor;
        ImageView imgPost;
        TextView txtTitle, txtSummary, txtAuthor, txtDate, txtCategory;

        /**
         * Use this constructor to create posts
         *
         * @param itemView        a View object referring to the list item
         * @param onClickListener click listener for post item
         */
        PostsSelectableRecyclerViewHolder(@NonNull View itemView, View.OnClickListener onClickListener) {
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
    }
}
