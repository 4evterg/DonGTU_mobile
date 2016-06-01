package com.chetverg.dongtu_mobile.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chetverg.dongtu_mobile.R;

/**
 * Created by chetverg on 30.05.16.
 */
public class TodayFragment extends Fragment{
    private static int LAYOUT = R.layout.fragment_schedule_today;

    private View view;
    private TextView Test1;

    public static TodayFragment getInstance(){
        Bundle args = new Bundle();
        TodayFragment fragment = new TodayFragment();
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
