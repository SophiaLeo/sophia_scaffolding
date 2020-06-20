package com.scaffolding.sophia.common.log.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author LHL
 */
//元注解，定义注解被保留策略，一般有三种策略
//1、RetentionPolicy.SOURCE 注解只保留在源文件中，在编译成class文件的时候被遗弃
//2、RetentionPolicy.CLASS 注解被保留在class中，但是在jvm加载的时候北欧抛弃，这个是默认的声明周期
//3、RetentionPolicy.RUNTIME 注解在jvm加载的时候仍被保留
@Retention(RetentionPolicy.RUNTIME)
//定义了注解声明在哪些元素之前
@Target(ElementType.METHOD)
@Documented
public @interface SysLog {

    /**
     * 描述
     *
     * @return {String}
     */
    String value() default "";
}
