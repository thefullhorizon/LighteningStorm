package com.nanshan.lighteningstorm.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by nanshan on 9/13/2017.
 */

public class PageUtils {

    public static void pageTransition(Context from, Class to){
        from.startActivity(new Intent(from, to));
    }


}
