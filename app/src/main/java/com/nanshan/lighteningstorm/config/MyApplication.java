package com.nanshan.lighteningstorm.config;

import android.app.Application;
import android.graphics.Typeface;

import com.nanshan.lighteningstorm.pages.widget.HalvettThinTV;

/**
 * Created by nanshan on 9/13/2017.
 */

public class MyApplication extends Application {

    private static MyApplication application = null;
    private Typeface HalvettThinfont;

    public static MyApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public Typeface getHalvettThinFont() {
        if (HalvettThinfont == null) {
            HalvettThinfont = Typeface.createFromAsset(getInstance().getAssets(), HalvettThinTV.HALVETT_THIN_FONT_PATH);
        }
        return HalvettThinfont;
    }


}
