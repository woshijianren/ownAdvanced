package com.example.demo.custom.annotation.poi.xssf.util;

import com.example.demo.custom.annotation.poi.xssf.annotation.XSSF;
import com.example.demo.custom.annotation.poi.xssf.data.TestData;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @Author: zyl
 * @Date: 2020/7/16 0:27
 */
public class AnnotationUtil {

    public List<Field> getFieldsAfterOrder() {
        Map<Integer, Field> fieldMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        Field[] declaredFields = TestData.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(XSSF.class)) {
                XSSF xssf = declaredField.getAnnotation(XSSF.class);
                fieldMap.put(xssf.index(), declaredField);
            }
        }
        List<Field> fields = new ArrayList<>(fieldMap.values());
        return fields;
    }

    public List<String> getHeader() {
        List<String> headerList = new ArrayList<>();
        List<Field> fields = getFieldsAfterOrder();
        for (Field field : fields) {
            if (field.isAnnotationPresent(XSSF.class)) {
                XSSF xssf = field.getAnnotation(XSSF.class);
                headerList.add(xssf.header());
            }
        }
        return headerList;
    }
}
