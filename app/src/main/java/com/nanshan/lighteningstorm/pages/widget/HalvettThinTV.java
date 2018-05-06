package com.nanshan.lighteningstorm.pages.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.nanshan.lighteningstorm.config.MyApplication;

/**
 * Created by nanshan on 9/13/2017.
 */

public class HalvettThinTV extends AppCompatTextView {

    public final static String HALVETT_THIN_FONT_PATH = "fonts/halvettthin.ttf";

    public HalvettThinTV(Context context) {
        this(context,null);
    }

    public HalvettThinTV(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HalvettThinTV(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    public void setTypeface(Typeface tf) {
        super.setTypeface(MyApplication.getInstance().getHalvettThinFont());
    }

}
