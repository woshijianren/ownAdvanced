package com.example.demo.custom.annotation.format.money;

import java.math.BigDecimal;

/**
 * 常数枚举
 * @author zyl
 * @date 2020/7/11 10:50
 */
public class Constant {

    /**
     * BigDecimal之所以用String的构造函数创建，是因为其他方法在某些情况下有可能造成精度损失
     * 至于String类型构造的目前没有遇到过这种问题
     */
    public final static BigDecimal TEN_THOUSAND = new BigDecimal("10000.00");
    public final static Integer SCALE = 2;

}
