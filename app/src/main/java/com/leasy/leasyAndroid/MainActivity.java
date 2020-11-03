package com.leasy.leasyAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.leasy.leasyAndroid.ui.main.MainFragment;
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

    @Override
    public void onBackPressed() {
        if (mainFragment.getChildFragmentManager().getBackStackEntryCount() >= 1) {
            mainFragment.getChildFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}