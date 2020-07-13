package com.example.demo.custom.annotation.format.money.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zyl
 * @date 2020/7/11 14:10
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonSplicing {

    @AliasFor("value")
    String[] exclude() default {};

    @AliasFor("exclude")
    String[] value() default {};
}
