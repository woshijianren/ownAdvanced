package com.example.demo.custom.annotation.poi.xssf.annotation;

import com.example.demo.custom.annotation.poi.xssf.AlignStyle;

/**
 * @author zyl
 * @date 2020/7/15 15:14
 */
public @interface XSSF {

    AlignStyle align() default AlignStyle.CENTER;
    int index();
    String header() default "";
}
