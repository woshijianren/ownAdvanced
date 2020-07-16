package com.example.demo.custom.annotation.poi.xssf.data;

import com.example.demo.custom.annotation.poi.xssf.AlignStyle;
import com.example.demo.custom.annotation.poi.xssf.annotation.Align;
import com.example.demo.custom.annotation.poi.xssf.annotation.Header;
import com.example.demo.custom.annotation.poi.xssf.annotation.Order;
import com.example.demo.custom.annotation.poi.xssf.annotation.XSSF;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
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
public class TestData {

    @XSSF(index = 0, header = "身份证号")
    private String code;

    @XSSF(index = 1, header = "姓名")
    private String name;

    @XSSF(index = 2, header = "性别")
    private Integer gender;

    @XSSF(index = 3, header = "身高")
    private Float height;

    @XSSF(index = 4, header = "工资", align = HorizontalAlignment.RIGHT)
    private BigDecimal salary;
}
