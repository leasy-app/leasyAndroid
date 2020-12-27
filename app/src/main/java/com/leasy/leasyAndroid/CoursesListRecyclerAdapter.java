package com.leasy.leasyAndroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.leasy.leasyAndroid.model.CourseModel;
import com.leasy.leasyAndroid.util.ImageLoader;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CoursesListRecyclerAdapter extends RecyclerView.Adapter<CoursesListRecyclerAdapter.CoursesRecyclerViewHolder> {

    private List<CourseModel> coursesList;
    private Context context;
    private View.OnClickListener onClickListener;

    public CoursesListRecyclerAdapter(List<CourseModel> coursesList, Context context, View.OnClickListener onClickListener) {
        this.coursesList = coursesList;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public CoursesRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CoursesRecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_list_layout, parent, false),
                onClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesRecyclerViewHolder holder, int position) {
        CourseModel courseModel = coursesList.get(position);
        holder.txtTitle.setText(courseModel.getTitle());
        ImageLoader.loadImage(courseModel.getCoverURL(), holder.imgCover);
//        holder.txtAuthor.setText(courseModel.getAuthor());
        // TODO: 12/26/20 cover and author images
    }

    @Override
    public int getItemCount() {
        return coursesList.size();
    }

    static class CoursesRecyclerViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imgAuthor;
        ImageView imgCover;
        TextView txtTitle, txtAuthor;

        public CoursesRecyclerViewHolder(@NonNull View itemView, View.OnClickListener onClickListener) {
            super(itemView);
            imgAuthor = itemView.findViewById(R.id.img_course_list_author_photo);
            imgCover = itemView.findViewById(R.id.img_course_list_item_cover);
            txtAuthor = itemView.findViewById(R.id.txt_course_list_author);
            txtTitle = itemView.findViewById(R.id.txt_course_list_title);
            itemView.setTag(this);
            itemView.setOnClickListener(onClickListener);
        }
    }
}
