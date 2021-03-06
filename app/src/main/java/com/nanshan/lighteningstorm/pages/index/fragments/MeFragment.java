package com.nanshan.lighteningstorm.pages.index.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nanshan.lighteningstorm.R;

import butterknife.BindView;
import butterknife.OnClick;


/**
 *
 */

public class MeFragment extends BaseFragment {

    @BindView(R.id.cardview_omrs)
    CardView cvOMRS;
    @BindView(R.id.tv_home_omrs) TextView tvOMRS;

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

        tvOMRS.setText("OMRS");
    }


    @OnClick(R.id.cardview_omrs)
    public void cl_cardview_omrs(){

    }

}
