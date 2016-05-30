package com.chetverg.dongtu_mobile.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.chetverg.dongtu_mobile.fragments.ExampleFragment;

/**
 * Created by chetverg on 30.05.16.
 */
public class TabsPagerFragmentAdapter extends FragmentPagerAdapter{

    //объявление массива вкладок
    private String[] tabs;

    public TabsPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
        //инициализация массива вкладок
        tabs = new String[] {
                "Сегодня", "Неделя", "Задания"
        };
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public Fragment getItem(int position) {
        //Переключение вкладок
        switch (position){
            case 0:
                return ExampleFragment.getInstance();

            case 1:
                return ExampleFragment.getInstance();

            case 2:
                return ExampleFragment.getInstance();

        }

        return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
