package com.zx.inject_api;


/**
 * @author 周旭
 * @company 伊柯夫
 * @e-mail 374952705@qq.com
 * @time 2019/11/18
 * @descripe
 */


public interface IViewInjector<T> {
    /**
     * 通过source.findViewById()
     *
     * @param target 泛型参数，调用类 activity、fragment等
     * @param source Activity、View
     */
    void inject(T target, Object source);
}
