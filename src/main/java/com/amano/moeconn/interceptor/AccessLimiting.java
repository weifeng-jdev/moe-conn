package com.amano.moeconn.interceptor;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessLimiting {
    /**
     * 单位时间流量上限
     */
    long limitOnUnitTime();
    /**
     * 限流时间单位
     */
    TimeUnit timeUnit();
}
