package com.messed.chatappatg.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.messed.chatappatg.ViewModel.Fragments.ChatScreen;
import com.messed.chatappatg.ViewModel.Fragments.UserScreen;

public class TabPageAdapter extends FragmentStatePagerAdapter {

    public TabPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment= new UserScreen();
        } else if (position == 1) {
            fragment = new ChatScreen();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
