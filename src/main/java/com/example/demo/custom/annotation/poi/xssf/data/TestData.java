package com.example.demo.custom.annotation.poi.xssf.data;

import com.example.demo.custom.annotation.poi.xssf.AlignStyle;
import com.example.demo.custom.annotation.poi.xssf.annotation.Align;
import com.example.demo.custom.annotation.poi.xssf.annotation.Header;
import com.example.demo.custom.annotation.poi.xssf.annotation.Order;
import com.example.demo.custom.annotation.poi.xssf.annotation.XSSF;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author zyl
 * @date 2020/7/15 8:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
//@Header(name = "身份证号码", value = "aa")
public class TestData {

    @Align
    @Header("身份证号码")
    @Order(0)
    private String code;

    @Align
    @Header("姓名")
    @Order(0)
    private String name;

    @Align
    @Header("性别")
    @Order(0)
    private Integer gender;

    @Align(AlignStyle.LEFT)
    @Header("身高")
    @Order(0)
    private Float height;

    @Align(AlignStyle.RIGHT)
    @Header("工资")
    @Order(0)
    private BigDecimal salary;
}
