package com.leasy.leasyAndroid;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.leasy.leasyAndroid.model.ListItems;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapterPostsVertical extends RecyclerView.Adapter<RecyclerAdapterPostsVertical.PostsVerticalRecyclerViewHolder> {

    private View.OnClickListener onClickListener;
    private List<ListItems> listItems;
    private Context context;

    private static final int item_type_post = 101;
    private static final int item_type_section = 282;
    private static final String LOG_TAG = "log_posts_recycler";

    public RecyclerAdapterPostsVertical(View.OnClickListener onClickListener, List<ListItems> listItems, Context context) {
        this.onClickListener = onClickListener;
        this.listItems = listItems;
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
        Log.i(LOG_TAG, listItems.get(position).toString());
        if (getItemViewType(position) == item_type_section) {
            holder.txtSec.setText(listItems.get(position).getSectionTitle());
        } else {
            ListItems.PostItem item = listItems.get(position).getPostItem();
            holder.txtCategory.setText(item.getCategory());
            holder.txtTitle.setText(item.getTitle());
            holder.txtSummary.setText(item.getSummary());
            holder.txtDate.setText(item.getDate());
            holder.txtAuthor.setText(item.getAuthor());
            if (item.getAuthorImageURL() != null)
                Glide.with(context).load(item.getAuthorImageURL()).into(holder.imgAuthor);
            if (item.getPostImageURL() != null)
                Glide.with(context).load(item.getPostImageURL()).into(holder.imgPost);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (listItems.get(position).getSectionTitle() != null)
            return item_type_section;
        else
            return item_type_post;
    }

    @Override
    public int getItemCount() {
        return listItems.size();
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
