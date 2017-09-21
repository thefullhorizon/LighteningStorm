package com.nanshan.lighteningstorm.pages.index.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nanshan.lighteningstorm.R;

import butterknife.ButterKnife;


public abstract class BaseFragment extends Fragment {

    public static final String FRAGMENT_ARG_PARAM1 = "fragment";
    public String mParam1;

    public abstract int getLayout();
    public abstract void initializing(Bundle savedInstanceState);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this, view);
        initializing(savedInstanceState);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(FRAGMENT_ARG_PARAM1);
        }
    }

}
