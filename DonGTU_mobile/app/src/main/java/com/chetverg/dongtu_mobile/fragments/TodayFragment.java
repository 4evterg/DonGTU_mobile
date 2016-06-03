package com.chetverg.dongtu_mobile.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chetverg.dongtu_mobile.R;
import com.chetverg.dongtu_mobile.SQLiteHandler;

import java.util.HashMap;

/**
 * Created by chetverg on 30.05.16.
 */
public class TodayFragment extends Fragment{
    private static int LAYOUT = R.layout.fragment_schedule_today;

    private View view;
    private SQLiteHandler db;

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

        //установка текста из учетки юзера
        db = new SQLiteHandler(getActivity().getApplicationContext());
        HashMap<String, String> user = db.getUserDetails();
        String uid = user.get("uid");
        String name = user.get("name");
        String surname = user.get("second_name");
        String third_name = user.get("third_name");

        View tv_uid = view.findViewById(R.id.user_id);
        View tv_name = view.findViewById(R.id.user_name);
        View tv_surname = view.findViewById(R.id.user_surname);
        View tv_thirdname = view.findViewById(R.id.user_thirdname);

        ((TextView)tv_uid).setText(uid);
        ((TextView)tv_name).setText(name);
        ((TextView)tv_surname).setText(surname);
        ((TextView)tv_thirdname).setText(third_name);

        return view;
    }


}
