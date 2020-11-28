package com.leasy.leasyAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.leasy.leasyAndroid.model.PostsListItem;
import com.leasy.leasyAndroid.ui.main.MainFragment;
import com.leasy.leasyAndroid.ui.main.PostsListFragment;
import com.leasy.leasyAndroid.ui.main.ReadPostFragment;

public class MainActivity extends AppCompatActivity {

    private MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
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