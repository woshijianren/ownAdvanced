package com.example.demo.custom.annotation.poi.xssf.annotation;

import com.example.demo.custom.annotation.poi.xssf.AlignStyle;

/**
 * @author zyl
 * @date 2020/7/15 15:14
 */
public @interface XSSF {

    AlignStyle value() default AlignStyle.CENTER;
    int order();
    String name() default "";
}
