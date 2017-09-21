package com.nanshan.lighteningstorm.pages.index.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nanshan.lighteningstorm.R;


/**
 * Created by Kevin on 2016/11/28.
 * Blog:http://blog.csdn.net/student9128
 * Description:
 */

public class LifeFragment extends BaseFragment {

    public static LifeFragment newInstance(String s){
        LifeFragment homeFragment = new LifeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BaseFragment.FRAGMENT_ARG_PARAM1,s);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }


    @Override
    public int getLayout() {
        return R.layout.fragment_life;
    }

    @Override
    public void initializing(Bundle savedInstanceState) {

    }

}
