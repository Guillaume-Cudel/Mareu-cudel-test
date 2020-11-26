package com.example.mareu.ui.meeting_list;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {

    public PageAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public int getCount(){
        return 1;
    }

    @Override
    public Fragment getItem(int position){
        return MeetingFragment.newInstance();
    }
}
