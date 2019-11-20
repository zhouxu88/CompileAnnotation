package com.zx.inject_api;

import android.app.Activity;
import android.util.Log;
import android.view.View;


/**
 * @author 周旭
 * @company 伊柯夫
 * @e-mail 374952705@qq.com
 * @time 2019/11/18
 * @descripe
 */


public class ButterKnife {

    private static final String TAG = "ButterKnife";
    public static final String PROXY = "_ViewBinding";

    /**
     * Activity调用
     */
    public static void bind(Activity activity) {
        findProxyActivity(activity).inject(activity, activity);
    }

    /**
     * Fragment、Adapter调用
     *
     * @param object
     * @param view
     */
    public static void bind(Object object, View view) {
        findProxyActivity(object).inject(object, view);
    }


    /**
     * 根据使用注解的类和约定的命名规则，通过反射找到动态生成的代理类（处理注解逻辑）
     *
     * @param object 调用类对象
     */
    private static IViewInjector findProxyActivity(Object object) {
        String proxyClassName = object.getClass().getName() + PROXY;
        Log.e(TAG, "findProxyActivity: " + proxyClassName);
        Class<?> proxyClass = null;
        try {
            proxyClass = Class.forName(proxyClassName);
//            Constructor<?> constructor = proxyClass.getConstructor(object.getClass());
            return (IViewInjector) proxyClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
