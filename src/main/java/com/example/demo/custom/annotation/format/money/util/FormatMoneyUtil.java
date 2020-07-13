package com.example.demo.custom.annotation.format.money.util;

import com.example.demo.custom.annotation.format.money.Constant;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author zyl
 * @date 2020/7/11 10:49
 */
public class FormatMoneyUtil {

    /**
     * 三位分节法，四舍五入保留两位小数，单位：万
     * @param money
     * @return
     */
    public static String tenThousandThreePartsFormat(BigDecimal money) {
        return new DecimalFormat("###,##0.00").format(money.divide(Constant.TEN_THOUSAND).setScale(Constant.SCALE, BigDecimal.ROUND_HALF_UP));
    }

    /**
     * 三位分节法，四舍五入保留两位小数，单位：1
     * @param money
     * @return
     */
    public static String noUnitThreePartsFormat(BigDecimal money) {
        return new DecimalFormat(",###.00").format(money.setScale(Constant.SCALE, BigDecimal.ROUND_HALF_UP));
    }


}
