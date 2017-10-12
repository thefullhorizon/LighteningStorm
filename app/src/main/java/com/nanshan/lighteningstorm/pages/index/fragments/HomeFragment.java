package com.nanshan.lighteningstorm.pages.index.fragments;

import android.os.Bundle;
import com.nanshan.lighteningstorm.R;
import com.nanshan.lighteningstorm.data.domain.AssetInfoBean;
import com.nanshan.lighteningstorm.widget.PieChatView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * Created by Kevin on 2016/11/28.
 * Blog:http://blog.csdn.net/student9128
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
        mAssignCircle.setTotalAsset("XXX");
        mAssignCircle.startDraw();

    }


}
