package com.example.demo.custom.annotation.poi.xssf.rest;

import com.example.demo.custom.annotation.poi.xssf.annotation.Header;
import com.example.demo.custom.annotation.poi.xssf.annotation.Order;
import com.example.demo.custom.annotation.poi.xssf.annotation.XSSF;
import com.example.demo.custom.annotation.poi.xssf.data.TestData;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author zyl
 * @date 2020/7/15 8:58
 */
@Controller
@RequestMapping(value = "/xssf")
public class TestController {

    @GetMapping("/export")
    public void export(HttpServletResponse response) {

//        TestData data = new TestData("362324200111113911", "做压力", 1, 175.34F, new BigDecimal("1100.00"));
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFRow firstRow = sheet.getRow(0);
        Header header = TestData.class.getDeclaredAnnotation(Header.class);
        System.out.println(header.value());
    }

    private List<String> getHeaders() {
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
        return new ArrayList<>(headers.values());
    }

    private List<Method> getAllGetMethod() {
        Method[] declaredMethods = TestData.class.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod.getName());
        }
        return null;
    }

    private List<Field> getAllField() {
        Field[] declaredFields = TestData.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(XSSF.class)) {

            }
        }
    }
}
