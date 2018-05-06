package com.nanshan.lighteningstorm.pages.utils;

import android.widget.Toast;

import com.nanshan.lighteningstorm.config.MyApplication;

/**
 * Created by nanshan on 9/13/2017.
 */

public class CommonUtil {

    public static void toast(String content){
        Toast.makeText(MyApplication.getInstance(), content, Toast.LENGTH_SHORT).show();
    }

}
