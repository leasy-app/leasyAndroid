package com.leasy.leasyAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.leasy.leasyAndroid.ui.main.MainFragment;
import com.leasy.leasyAndroid.ui.main.ReadPostFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_main_activity, new MainFragment())
                    .commitNow();
        }
    }
}