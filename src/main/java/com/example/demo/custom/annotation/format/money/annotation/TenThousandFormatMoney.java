package com.example.demo.custom.annotation.format.money.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 三位分节法（转为万元，小数点2位四舍五入）
 * @author zyl
 * @date 2020/7/11 10:46
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TenThousandFormatMoney {
}
