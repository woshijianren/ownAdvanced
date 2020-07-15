package com.example.demo.custom.annotation.poi.xssf.annotation;

import com.example.demo.custom.annotation.poi.xssf.AlignStyle;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zyl
 * @date 2020/7/15 8:44
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Align {

    AlignStyle value() default AlignStyle.CENTER;
}
