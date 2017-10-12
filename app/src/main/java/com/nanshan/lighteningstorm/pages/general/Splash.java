package com.nanshan.lighteningstorm.pages.general;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

import com.nanshan.lighteningstorm.R;
import com.nanshan.lighteningstorm.pages.index.Main;
import com.nanshan.lighteningstorm.utils.PageUtils;
import com.nanshan.lighteningstorm.utils.SPUtils;
import com.nanshan.lighteningstorm.widget.HalvettThinTV;

import butterknife.BindView;

/**
 * Created by NANSHAN on 1/16/17.
 */
public class Splash extends BaseActivity {

    @BindView(R.id.splash) RelativeLayout splash;

    @Override
    public int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initializing(Bundle savedInstanceState) {

        scaleAndAlphaAnimation(splash);

    }

    private void scaleAndAlphaAnimation(View view){

        ObjectAnimator animatorA = ObjectAnimator.ofFloat(view,"alpha",  0.8f, 1.0f);
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(view,"scaleX", 1.0f, 1.1f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(view,"scaleY", 1.0f, 1.1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(animatorA).with(animatorX).with(animatorY);
        animSet.setDuration(1 * 1000);
        animSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(SPUtils.getInstance().read(Welcome.HAVE_SHOW,false)){
                    PageUtils.pageTransition(Splash.this, Main.class);
                }else{
                    PageUtils.pageTransition(Splash.this, Welcome.class);
                }
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animSet.start();
    }


}
