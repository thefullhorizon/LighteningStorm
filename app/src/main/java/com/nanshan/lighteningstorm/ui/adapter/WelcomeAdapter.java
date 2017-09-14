package com.nanshan.lighteningstorm.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nanshan.lighteningstorm.R;
import com.nanshan.lighteningstorm.config.MyApplication;
import com.nanshan.lighteningstorm.pages.events.CommonEvent;
import com.nanshan.lighteningstorm.pages.events.OttoBus;
import com.nanshan.lighteningstorm.widget.HalvettThinTV;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nanshan on 9/13/2017.
 */

public class WelcomeAdapter extends PagerAdapter {

    private int size;
    private String[] instructions = {"keep focusing","Keep it up","Keep output"};
    private int[] mVImageResIds = new int[]{R.mipmap.splash_bg,R.mipmap.splash_bg,R.mipmap.splash_bg};
    private List<View> mViews;

    public WelcomeAdapter() {
        size = instructions.length;
        mViews = new ArrayList<>(size);
        View vGoIndexBtn;
        LayoutInflater li = LayoutInflater.from(MyApplication.getInstance());
        for(int i=0;i< size;i++){
            View view = li.inflate(R.layout.welcome_item,null);
            ((HalvettThinTV)view.findViewById(R.id.iv_text)).setText(instructions[i]);
            ((ImageView)view.findViewById(R.id.iv_image)).setImageResource(mVImageResIds[i]);
            if(i == size - 1){
                vGoIndexBtn = view.findViewById(R.id.tv_go_index);
                vGoIndexBtn.setVisibility(View.VISIBLE);
                vGoIndexBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OttoBus.getInstance().getBus().post(CommonEvent.TOMIANFROMWELCOM);
                    }
                });
            }
            mViews.add(view);
        }
    }

    @Override
    public int getCount() {
        return size;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViews.get(position));
        return mViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position));
    }

    public List<View> getmViews() {
        return mViews;
    }

}
