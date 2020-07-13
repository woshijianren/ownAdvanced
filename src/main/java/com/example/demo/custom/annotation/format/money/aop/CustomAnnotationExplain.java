package com.example.demo.custom.annotation.format.money.aop;

import com.example.demo.custom.annotation.format.money.annotation.JsonSplicing;
import com.example.demo.custom.annotation.format.money.annotation.NoUnitFormatMoney;
import com.example.demo.custom.annotation.format.money.annotation.TenThousandFormatMoney;
import com.example.demo.custom.annotation.format.money.util.FormatMoneyUtil;
import com.google.gson.Gson;
import javafx.scene.chart.ValueAxis;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author zyl
 * @date 2020/7/11 13:38
 */
@Aspect
@Component
public class CustomAnnotationExplain {

    @Pointcut("execution(* com.example.demo.custom.annotation.format.money.data..*.parseTransfer(..))")
    public void customMoneyFormat() {}

    @Around("customMoneyFormat()")
    public String customAnnotationExplain(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("aaa");
        Map<String, String> jsonMap = new HashMap<>();
        List<String> fieldNameList = null;
        Object target = pjp.getTarget();
        Class<?> clazz = target.getClass();
        if (clazz.isAnnotationPresent(JsonSplicing.class)) {
            fieldNameList = new ArrayList<>();
        }
        for (Field declaredField : clazz.getDeclaredFields()) {
            String fieldName = declaredField.getName();
            Annotation[] declaredAnnotations = declaredField.getDeclaredAnnotations();
            if (declaredAnnotations.length > 0) {
                for (Annotation declaredAnnotation : declaredField.getDeclaredAnnotations()) {
                    if (declaredAnnotation.annotationType().equals(TenThousandFormatMoney.class)) {
                        jsonMap.put(fieldName, tenThousandFormatMoney(target, Objects.requireNonNull(getMethod(clazz, fieldName))));
                    } else if (declaredAnnotation.annotationType().equals(NoUnitFormatMoney.class)) {
                        jsonMap.put(fieldName, noUnitFormatMoney(target, Objects.requireNonNull(getMethod(clazz, fieldName))));
                    } else {
                        if (fieldNameList != null) {
                            fieldNameList.add(fieldName);
                        }
                    }
                }
            } else {
                if (fieldNameList != null) {
                    fieldNameList.add(fieldName);
                }
            }

        }
        pjp.proceed();
        // 默认写了@NoUnitFormatMoney和@TenThousandFormatMoney的字段都是要进行json拼接的
        if (fieldNameList != null && fieldNameList.size() > 0) {
            String[] value = clazz.getDeclaredAnnotation(JsonSplicing.class).value();
            fieldNameList.removeAll(Arrays.asList(value));
            if (fieldNameList.size() > 0) {
                fieldNameList.forEach(fieldName -> forJson(jsonMap, target, clazz, fieldName));
            }
        }
        if (jsonMap.size() > 0) {
            return new Gson().toJson(jsonMap);
        }
        return null;
    }

    private String tenThousandFormatMoney(Object object, Method method) {
        try {
            return FormatMoneyUtil.tenThousandThreePartsFormat((BigDecimal) method.invoke(object));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String noUnitFormatMoney(Object object, Method method) {
        try {
            return FormatMoneyUtil.noUnitThreePartsFormat((BigDecimal) method.invoke(object));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void forJson(Map<String, String> map, Object object, Class clazz, String fieldName) {
        try {
            map.put(fieldName, String.valueOf(Objects.requireNonNull(getMethod(clazz, fieldName)).invoke(object)));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private Method getMethod(Class clazz, String fieldName) {
        try {
            return clazz.getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Pointcut("execution(* com.example.demo.custom.annotation.format.money.aop.*.*(..))")
    public void customMoneyFormat1() {}

    @Around("customMoneyFormat1()")
    public Object p(ProceedingJoinPoint pjp) throws Throwable {
        pjp.proceed();
        System.out.println("aa");
        return null;
    }
    public void parseTransfer() {
        System.out.println("dd");
    }
}
