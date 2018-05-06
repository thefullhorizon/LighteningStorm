package com.nanshan.lighteningstorm.pages.general;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nanshan.lighteningstorm.R;
import com.nanshan.lighteningstorm.pages.events.CommonEvent;
import com.nanshan.lighteningstorm.pages.index.Main;
import com.nanshan.lighteningstorm.pages.ui.adapter.WelcomeAdapter;
import com.nanshan.lighteningstorm.pages.utils.PageUtils;
import com.nanshan.lighteningstorm.pages.utils.SPUtils;
import com.squareup.otto.Subscribe;

import butterknife.BindView;

public class Welcome extends BaseActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.vp_guide) ViewPager vpGuide;
    @BindView(R.id.ll_indicator) LinearLayout indicatorContainer;

    private ImageView[] mIvPointArray;
    private int number;
    private WelcomeAdapter mWecomeAdapter;

    public static String HAVE_SHOW  = "have_show";

    @Override
    public int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initializing(Bundle savedInstanceState) {

        SPUtils.getInstance().write(HAVE_SHOW,true);
        mWecomeAdapter = new WelcomeAdapter();
        vpGuide.setAdapter(mWecomeAdapter);
        vpGuide.addOnPageChangeListener(this);
        number = mWecomeAdapter.getCount();
        scaleAndAlphaAnimation( mWecomeAdapter.getmViews().get(0).findViewById(R.id.iv_text));
        initPoint(number);

    }

    /***
     * 初始化底部引导点
     */
    private void initPoint(int number){
        mIvPointArray = new ImageView[number];
        int margin = getResources().getDimensionPixelOffset(R.dimen.guide_point_padding);
        int size = getResources().getDimensionPixelSize(R.dimen.guide_point_size);
        ImageView ivPoint;
        for (int i = 0; i< number; i++){
            ivPoint = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size,size);
            params.setMargins(margin,0,margin,0);
            ivPoint.setLayoutParams(params);
            mIvPointArray[i] = ivPoint;
            if (i == 0){//默认指示第一页
                ivPoint.setBackgroundResource(R.drawable.red_point_bg);
            }else{
                ivPoint.setBackgroundResource(R.drawable.gray_point_bg);
            }
            indicatorContainer.addView(mIvPointArray[i]);
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        scaleAndAlphaAnimation( mWecomeAdapter.getmViews().get(position).findViewById(R.id.iv_text));
        for(int i = 0 ; i < number; i++){
            mIvPointArray[i].setBackgroundResource(position == i ? R.drawable.red_point_bg : R.drawable.gray_point_bg);
        }
        indicatorContainer.setVisibility(position == number - 1 ? View.GONE : View.VISIBLE);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void scaleAndAlphaAnimation(View view){

        ObjectAnimator animatorA = ObjectAnimator.ofFloat(view,"alpha",  0.1f, 1f);
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(view,"scaleX", 1f, 1.5f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(view,"scaleY", 1f, 1.5f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(animatorA).with(animatorX).with(animatorY);
        animSet.setDuration(1000);
        animSet.start();
    }

    @Subscribe
    public void toMain(String event) {
        if (CommonEvent.TOMIANFROMWELCOM.equals(event)) {
            PageUtils.pageTransition(Welcome.this, Main.class);
            finish();
        }
    }

}
