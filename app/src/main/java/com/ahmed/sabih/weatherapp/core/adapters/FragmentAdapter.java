package com.ahmed.sabih.weatherapp.core.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ahmed.sabih.weatherapp.model.FragmentHolder;

import java.util.ArrayList;

public class FragmentAdapter extends FragmentPagerAdapter{


    private ArrayList<FragmentHolder> mDataset;

    public FragmentAdapter(FragmentManager fm,
                           ArrayList<FragmentHolder> fragmentList) {
        super(fm);
        mDataset = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mDataset.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return mDataset.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mDataset.get(position).getTitle();
    }
}
