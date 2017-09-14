package com.nanshan.lighteningstorm.pages.index;

import android.os.Bundle;

import com.nanshan.lighteningstorm.R;
import com.nanshan.lighteningstorm.data.domain.AssetInfoBean;
import com.nanshan.lighteningstorm.pages.general.BaseActivity;
import com.nanshan.lighteningstorm.widget.PieChatView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Main extends BaseActivity {

    @BindView(R.id.assign_circle) PieChatView mAssignCircle;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initializing(Bundle savedInstanceState) {


        List<AssetInfoBean> data = new ArrayList<>();
        data.add(new AssetInfoBean("Work","#40c8f7",0));
        data.add(new AssetInfoBean("Fitness","#ff6b5d",0));
        data.add(new AssetInfoBean("IELTS","#29d96c",10));
        data.add(new AssetInfoBean("Reading","#007afa",20));
        mAssignCircle.setData(data);
        mAssignCircle.setTotalAsset("100.00");
        mAssignCircle.startDraw();

    }



}
