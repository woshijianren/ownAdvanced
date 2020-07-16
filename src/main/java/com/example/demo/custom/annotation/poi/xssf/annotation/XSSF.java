package com.example.demo.custom.annotation.poi.xssf.annotation;

import com.example.demo.custom.annotation.poi.xssf.AlignStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zyl
 * @date 2020/7/15 15:14
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface XSSF {

    HorizontalAlignment align() default HorizontalAlignment.CENTER;
    int index();
    String header() default "";
}
