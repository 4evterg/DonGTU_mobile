package com.chetverg.dongtu_mobile.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chetverg.dongtu_mobile.R;

/**
 * Created by chetverg on 30.05.16.
 */
public class WeekFragment extends Fragment{
    private static int LAYOUT = R.layout.fragment_schedule_week;

    private View view;

    public static WeekFragment getInstance(){
        Bundle args = new Bundle();
        WeekFragment fragment = new WeekFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        return view;
    }
}
