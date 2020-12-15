package com.leasy.leasyAndroid.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.leasy.leasyAndroid.R;

public class MainFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {

    private MainViewModel mainViewModel;
    private BottomNavigationView bottomNav;
    private WritePostFragment writePostFragment;
    private CreateCourseFragment createCourseFragment;


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    enum EnumDir {rightToLeft, leftToRight}

    enum EnumPages {home, write, course, categories, profile}

    EnumPages activePage = EnumPages.home;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment, container, false);
        initialize(v);
        switch (activePage) {
            case home:
                loadHomePage(false);
                break;
            case write:
                loadWritePage();
                break;
            case course:
                loadCoursePage(false);
                break;
            case categories:
                loadCategoriesPage();
                break;
            case profile:
                loadProfilePage();
                break;
        }
        bottomNav.setOnNavigationItemSelectedListener(this);
        return v;
    }

    private void loadCoursePage(boolean addToBackStack) {
        if (createCourseFragment == null)
            createCourseFragment = new CreateCourseFragment();
        changeMainFragment(createCourseFragment, null, addToBackStack);
        activePage = EnumPages.course;
    }

    public void returnToHomeFromWrite() {
        loadHomePage(true);
        writePostFragment = null;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == bottomNav.getSelectedItemId()) {
            return true;
        }
        if (item.getItemId() == R.id.home_bottom_nav_main) {
            loadHomePage(false);
        } else if (item.getItemId() == R.id.write_bottom_nav_main) {
            loadWritePage();
        } else if (item.getItemId() == R.id.create_course_bottom_nav_main) {
            loadCoursePage(true);
        } else if (item.getItemId() == R.id.categories_bottom_nav_main) {
            loadCategoriesPage();
        } else if (item.getItemId() == R.id.profile_bottom_nav_main) {
            loadProfilePage();
        }

        return true;
    }

    private void changeMainFragment(Fragment newFragment, @Nullable EnumDir transitionDir, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        if (transitionDir == EnumDir.rightToLeft) {
            fragmentTransaction = fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                    R.anim.slide_in_right, R.anim.slide_out_left);
        } else if (transitionDir == EnumDir.leftToRight) {
            fragmentTransaction = fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                    android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        }
        fragmentTransaction = fragmentTransaction.replace(R.id.container_main_fragment, newFragment);
//        if (addToBackStack) {
//            fragmentTransaction = fragmentTransaction.addToBackStack(null);
//        }
        fragmentTransaction.commit();
    }

    private void loadHomePage(boolean addToBackStack) {
        changeMainFragment(new HomeFragment(), null, addToBackStack);
        activePage = EnumPages.home;
    }

    private void loadCategoriesPage() {
        changeMainFragment(new CategoriesListFragment(), null, false);
        activePage = EnumPages.categories;
    }

    private void loadProfilePage() {
        // TODO: 10/30/20
    }

    private void loadWritePage() {
        if (writePostFragment == null)
            writePostFragment = new WritePostFragment();
        changeMainFragment(writePostFragment, null, false);
        activePage = EnumPages.write;
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