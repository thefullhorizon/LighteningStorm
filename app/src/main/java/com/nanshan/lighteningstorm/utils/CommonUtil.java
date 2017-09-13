package com.nanshan.lighteningstorm.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by nanshan on 9/13/2017.
 */

public class CommonUtil {

    public static void toast(Context context, String content){
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

}
