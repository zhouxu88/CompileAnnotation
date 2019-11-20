package com.zx.inject_api;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * @author 周旭
 * @company 伊柯夫
 * @e-mail 374952705@qq.com
 * @time 2019/11/18
 * @descripe
 */


public class ViewUtils {
    public static <T extends View> T findViewById(AppCompatActivity activity, int resourceId) {
        return (T) activity.findViewById(resourceId);
    }


    public static <T extends View> T findViewById(View view, int resourceId) {
        return (T) view.findViewById(resourceId);
    }
}
