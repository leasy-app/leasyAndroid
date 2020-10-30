package com.leasy.leasyAndroid.ui.main;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelStoreOwner;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.leasy.leasyAndroid.R;

public class MainFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {

    private MainViewModel mainViewModel;
    private BottomNavigationView bottomNav;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    enum EnumDir {rightToLeft, leftToRight};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment, container, false);
        initialize(v);
        bottomNav.setOnNavigationItemSelectedListener(this);
        return v;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == bottomNav.getSelectedItemId()) {
            return true;
        }

        if (item.getItemId() == R.id.home_bottom_nav_main){
            loadHomePage();
        } else if (item.getItemId() == R.id.write_bottom_nav_main){
            loadWritePage();
        } else if (item.getItemId() == R.id.categories_bottom_nav_main){
            loadCategoriesPage();
        } else if (item.getItemId() == R.id.profile_bottom_nav_main){
            loadProfilePage();
        }

        return true;
    }

    private void changeMainFragment(Fragment newFragment, @Nullable EnumDir transitionDir, boolean addToBackStack){
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        if (transitionDir == EnumDir.rightToLeft){
            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                    R.anim.slide_in_right, R.anim.slide_out_left);
        } else if (transitionDir == EnumDir.leftToRight){
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                    android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        }
        if (addToBackStack){
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.container_main_fragment, newFragment);
        fragmentTransaction.commitNow();
    }

    private void loadHomePage(){
        // TODO: 10/30/20
    }

    private void loadCategoriesPage(){
        // TODO: 10/30/20
    }

    private void loadProfilePage(){
        // TODO: 10/30/20
    }

    private void loadWritePage(){
        // TODO: 10/30/20
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

    private void initialize(View v) {
        bottomNav = v.findViewById(R.id.bottom_nav_main);
    }

}