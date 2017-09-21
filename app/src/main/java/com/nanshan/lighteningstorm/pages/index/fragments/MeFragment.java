package com.nanshan.lighteningstorm.pages.index.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nanshan.lighteningstorm.R;


/**
 *
 */

public class MeFragment extends BaseFragment {

    public static MeFragment newInstance(String s){
        MeFragment homeFragment = new MeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BaseFragment.FRAGMENT_ARG_PARAM1,s);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }


    @Override
    public int getLayout() {
        return R.layout.fragment_me;
    }

    @Override
    public void initializing(Bundle savedInstanceState) {

    }
}
