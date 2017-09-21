package com.nanshan.lighteningstorm.pages.index.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nanshan.lighteningstorm.R;
import com.nanshan.lighteningstorm.data.domain.AssetInfoBean;
import com.nanshan.lighteningstorm.widget.PieChatView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Kevin on 2016/11/28.
 * Blog:http://blog.csdn.net/student9128
 * Description: HomeFragment
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.assign_circle)
    PieChatView mAssignCircle;

    @BindView(R.id.cardview_omrs)
    CardView cvOMRS;
    @BindView(R.id.cardview_dts)
    CardView cvDts;

    @BindView(R.id.tv_home_omrs) TextView tvOMRS;
    @BindView(R.id.tv_home_dts) TextView tvDts;

    public static HomeFragment newInstance(String s){

        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BaseFragment.FRAGMENT_ARG_PARAM1,s);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }


    @Override
    public int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initializing(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String s = bundle.getString(BaseFragment.FRAGMENT_ARG_PARAM1);

        tvOMRS.setText("OMRS");
        tvDts.setText("DTS");

        List<AssetInfoBean> data = new ArrayList<>();
        data.add(new AssetInfoBean("Work","#40c8f7",0));
        data.add(new AssetInfoBean("Fitness","#ff6b5d",0));
        data.add(new AssetInfoBean("IELTS","#29d96c",10));
        data.add(new AssetInfoBean("Reading","#007afa",20));
        mAssignCircle.setData(data);
        mAssignCircle.setTotalAsset("100.00");
        mAssignCircle.startDraw();

    }

    @OnClick(R.id.cardview_omrs)
    public void cl_cardview_omrs(){

    }

    @OnClick(R.id.cardview_dts)
    public void cl_cardview_dts(){

    }




}
