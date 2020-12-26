package com.leasy.leasyAndroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.leasy.leasyAndroid.model.CourseModel;
import com.leasy.leasyAndroid.model.PostsListItem;
import com.leasy.leasyAndroid.ui.main.MainFragment;
import com.leasy.leasyAndroid.ui.main.PostsListFragment;
import com.leasy.leasyAndroid.ui.main.ReadPostFragment;
import com.leasy.leasyAndroid.ui.main.ViewCourseFragment;

public class MainActivity extends AppCompatActivity {

    private MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Window window = getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.black_palette));

        if (savedInstanceState == null) {
            mainFragment = new MainFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_main_activity, mainFragment)
                    .commitNow();
        }
    }

    public void showPost(PostsListItem postsListItem) {
        ReadPostFragment readPostFragment = ReadPostFragment.newInstance(postsListItem);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fragment_open_enter, R.anim.fragment_open_exit,
                        R.anim.fragment_close_enter, R.anim.fragment_close_exit)
                .replace(R.id.container_main_activity, readPostFragment)
                .addToBackStack(null)
                .commit();
    }

    public void showCourse(CourseModel courseModel) {
        ViewCourseFragment viewCourseFragment = ViewCourseFragment.newInstance(courseModel.getCourseID());
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fragment_open_enter, R.anim.fragment_open_exit,
                        R.anim.fragment_close_enter, R.anim.fragment_close_exit)
                .replace(R.id.container_main_activity, viewCourseFragment)
                .addToBackStack(null)
                .commit();
    }

    public void showCategoryPosts(String category) {
        PostsListFragment postsListFragment = PostsListFragment.newInstance(category);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fragment_open_enter, R.anim.fragment_open_exit,
                        R.anim.fragment_close_enter, R.anim.fragment_close_exit)
                .replace(R.id.container_main_activity, postsListFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() >= 1){
            getSupportFragmentManager().popBackStack();
        }
        else if (mainFragment.getChildFragmentManager().getBackStackEntryCount() >= 1) {
            mainFragment.getChildFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}