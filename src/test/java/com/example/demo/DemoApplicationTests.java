package com.example.demo;

import com.example.demo.custom.annotation.format.money.aop.CustomAnnotationExplain;
import com.example.demo.custom.annotation.format.money.data.MoneyData;
import com.example.demo.custom.annotation.format.money.service.DoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testBigDecimalToString() {
//        BigDecimal zero = new BigDecimal("0.00");
//        BigDecimal zero1 = new BigDecimal(0);
//
//        System.out.println(zero);
//        System.out.println(zero1);
//
//        System.out.println(zero.toPlainString());
//        System.out.println(zero1.toPlainString());

//        BigDecimal zero2 = new BigDecimal("12.12");
//        BigDecimal zero21 = new BigDecimal(12.12);
//
//        // 12.12
//        System.out.println(zero2);
//        // 12.1199999999999992184029906638897955417633056640625
//        System.out.println(zero21);
//        // 12.12
//        System.out.println(zero2.toPlainString());
//        // 12.1199999999999992184029906638897955417633056640625
//        System.out.println(zero21.toPlainString());

//        BigDecimal zero3 = new BigDecimal("12.0");
//        BigDecimal zero31 = new BigDecimal(12.00);
//
//        System.out.println(zero3.divide(new BigDecimal(3.2)));
//        System.out.println(zero31.divide(new BigDecimal(3.2)));
//        // 12.12
//        System.out.println(zero3);
//        // 12.1199999999999992184029906638897955417633056640625
//        System.out.println(zero31);
//        // 12.12
//        System.out.println(zero3.toPlainString());
//        // 12.1199999999999992184029906638897955417633056640625
//        System.out.println(zero31.toPlainString());

        System.out.println(new BigDecimal(1212121212).divide(new BigDecimal(3.000), 2, BigDecimal.ROUND_HALF_UP));

        System.out.println(new DecimalFormat(",###.000").format(12345.1212d));

        System.out.println(new DecimalFormat(",###.0000").format(new BigDecimal("12222222212312312312312312312312321.2233333333333333333333333333333333333333333333333222222")));



    }

    @Autowired
    private DoService doService;
    @Test
    public void testAround() {
        doService.calculateData();
    }
    @Autowired
    private MoneyData data;
    @Test
    public void test1() {
        data = new MoneyData("左亚利", new BigDecimal("11000.123"), "左亚利1", new BigDecimal("11000.234"),
                new BigDecimal("33333.333"), new BigDecimal("11000.123"), new BigDecimal("11000.123"),
                new BigDecimal("3333.333"), new BigDecimal("11000.123"), new BigDecimal("11000.123"));
        data.parseTransfer();
    }
    @Autowired
    private CustomAnnotationExplain annotationExplain;
    @Test
    public void test2() {
        annotationExplain.parseTransfer();
    }

    @Test
    public void test() {
        System.out.println(new DecimalFormat("#,##0.00").format(new BigDecimal(0.33)));
    }
}
