package com.example.demo.custom.annotation.format.money.data;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.example.demo.custom.annotation.format.money.annotation.JsonSplicing;
import com.example.demo.custom.annotation.format.money.annotation.NoUnitFormatMoney;
import com.example.demo.custom.annotation.format.money.annotation.TenThousandFormatMoney;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author zyl
 * @date 2020/7/11 13:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSplicing({"voNotNeed1", "voNotNeed2"})
@Component
public class MoneyData extends BaseParse {

    private String voNotNeed1;
    private BigDecimal voNotNeed2;

    private String string1;
    private BigDecimal string2;

    @TenThousandFormatMoney
    private BigDecimal tenThousand1;
    @TenThousandFormatMoney
    private BigDecimal tenThousand2;
    @TenThousandFormatMoney
    private BigDecimal tenThousand3;

    @NoUnitFormatMoney
    private BigDecimal noUnit1;
    @NoUnitFormatMoney
    private BigDecimal noUnit2;
    @NoUnitFormatMoney
    private BigDecimal noUnit3;

    public void copy(MoneyData source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
