package com.example.dailytask.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.dailytask.AllTasksFragment;
import com.example.dailytask.DoneFragment;
import com.example.dailytask.R;
import com.example.dailytask.ToDoFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    int mNumOfTabs;

    public SectionsPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AllTasksFragment tab1 = new AllTasksFragment();
                return tab1;
            case 1:
                ToDoFragment tab2 = new ToDoFragment();
                return tab2;
            case 2:
                DoneFragment tab3 = new DoneFragment();
                return tab3;
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        // Show 2 total pages.
        return mNumOfTabs;
    }
}