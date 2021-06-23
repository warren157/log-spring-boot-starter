package com.verymro.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author rui.wang
 * @version 1.0.0
 * @ClassName JYLog.java
 * @Description TODO
 * @createTime 2021年06月23日 08:10:00
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface JYLog {
    String desc();
}
