package com.zx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS) //注解生命周期是编译期，存活于.class文件，当jvm加载class时就不在了
@Target(ElementType.FIELD) //目标对象是变量
public @interface BindView {

    /**
     * @return 控件变量的resourceId
     */
    int value();
}
