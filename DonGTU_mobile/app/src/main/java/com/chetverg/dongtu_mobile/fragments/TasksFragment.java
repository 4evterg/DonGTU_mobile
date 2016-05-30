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
public class TasksFragment extends Fragment{
    private static int LAYOUT = R.layout.fragment_schedule_tasks;

    private View view;

    public static TasksFragment getInstance(){
        Bundle args = new Bundle();
        TasksFragment fragment = new TasksFragment();
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
