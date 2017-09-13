package com.nanshan.lighteningstorm.pages.general;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nanshan.lighteningstorm.R;
import com.nanshan.lighteningstorm.pages.events.OttoBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by nanshan on 9/13/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;

    public abstract int getLayout();
    public abstract void initializing(Bundle savedInstanceState);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayout() != 0) {
            setContentView(getLayout());
        }
        unbinder = ButterKnife.bind(this);
        initializing(savedInstanceState);

    }

    @Override
    protected void onStart() {
        super.onStart();

        OttoBus.getInstance().getBusInstance().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();

        OttoBus.getInstance().getBusInstance().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
