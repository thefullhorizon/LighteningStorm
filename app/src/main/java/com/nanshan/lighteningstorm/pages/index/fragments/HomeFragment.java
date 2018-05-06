package com.nanshan.lighteningstorm.pages.index.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nanshan.lighteningstorm.R;
import com.nanshan.lighteningstorm.base.data.AssetInfoBean;
import com.nanshan.lighteningstorm.pages.channel_reading.ReadingActivity;
import com.nanshan.lighteningstorm.pages.widget.PieChatView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * Description: HomeFragment
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.assign_circle)
    PieChatView mAssignCircle;

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

        List<AssetInfoBean> data = new ArrayList<>();
        data.add(new AssetInfoBean("Work","#40c8f7",0));
        data.add(new AssetInfoBean("Fitness","#ff6b5d",0));
        data.add(new AssetInfoBean("IELTS","#29d96c",10));
        data.add(new AssetInfoBean("Reading","#007afa",20));
        mAssignCircle.setData(data);
        mAssignCircle.setTotalAsset("Reading");//暂时的借用这个位置，作为reading频道的入口
        mAssignCircle.startDraw();
        mAssignCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ReadingActivity.class));
            }
        });

    }


}
