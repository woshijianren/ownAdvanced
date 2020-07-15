package com.example.demo;

import com.example.demo.custom.annotation.format.money.aop.CustomAnnotationExplain;
import com.example.demo.custom.annotation.format.money.data.MoneyData;
import com.example.demo.custom.annotation.format.money.service.DoService;
import com.example.demo.custom.annotation.poi.xssf.annotation.Header;
import com.example.demo.custom.annotation.poi.xssf.annotation.Order;
import com.example.demo.custom.annotation.poi.xssf.annotation.XSSF;
import com.example.demo.custom.annotation.poi.xssf.data.TestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
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

    @Test
    public void test3() {
        Map<Integer, String> headers = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        Field[] declaredFields = TestData.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(Header.class) && declaredField.isAnnotationPresent(Order.class)) {
                headers.put(declaredField.getDeclaredAnnotation(Order.class).value(), declaredField.getDeclaredAnnotation(Header.class).value());
            }
        }
        headers.values().forEach(System.out::println);
    }

    @Test
    public void test4() {
        Map<Integer, String> headers = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        Field[] declaredFields = TestData.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(Header.class) && declaredField.isAnnotationPresent(Order.class)) {
                headers.put(declaredField.getDeclaredAnnotation(Order.class).value(), declaredField.getDeclaredAnnotation(Header.class).value());
            }
        }
        headers.values().forEach(System.out::println);
    }

    @Test
    public void test10() {

    }
}
