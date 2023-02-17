package com.amano.moeconn.annotation;

import com.amano.moeconn.emnu.SysLogModuleEnum;
import com.amano.moeconn.emnu.SysLogOperTypeEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    String value() default "";

    SysLogModuleEnum module();

    SysLogOperTypeEnum type();
}