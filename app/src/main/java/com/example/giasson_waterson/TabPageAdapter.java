package com.example.giasson_waterson;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class TabPageAdapter extends FragmentPagerAdapter {
    Context context;


    public TabPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() { return 4; }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new GenderFragment();
            case 1:
                return new AgeFragment();
            case 2:
                return new HAFragment();
            case 3:
                return new DateFragment();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getResources().getText(R.string.gender_tab);
            case 1:
                return context.getResources().getText(R.string.age_tab);
            case 2:
                return context.getResources().getText(R.string.ha_tab);
            case 3:
                return context.getResources().getText(R.string.date_tab);
        }
        return null;
    }

}