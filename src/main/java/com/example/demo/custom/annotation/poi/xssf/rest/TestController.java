package com.example.demo.custom.annotation.poi.xssf.rest;

import com.example.demo.custom.annotation.poi.xssf.annotation.XSSF;
import com.example.demo.custom.annotation.poi.xssf.data.TestData;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
    public void export(HttpServletResponse response) throws IOException {

        TestData data = new TestData("362324200111113911", "做压力", 1, 175.34F, new BigDecimal("1100.00"));
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("diyige");
        XSSFRow headerRow = sheet.createRow(0);
        List<Field> fieldList = getAllField();
        List<String> headerList = getAllHeader(fieldList);
        // cellStyle要同源，来自于同一个XSSFWorkbook
        List<CellStyle> cellStyleList = getAllCellStyle(workbook, fieldList);
        List<Method> methodList = getAllMethod(fieldList, data.getClass());

        for (int i = 0; i < fieldList.size(); i ++) {
            XSSFCell cell = headerRow.createCell(i);
            cell.setCellValue(headerList.get(i));
            cell.setCellStyle(cellStyleList.get(i));
        }

        XSSFRow firstDataRow = sheet.createRow(1);
        for (int i = 0; i < fieldList.size(); i ++) {
            XSSFCell cell = firstDataRow.createCell(i);
            try {
                cell.setCellValue(methodList.get(i).invoke(data).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            cell.setCellStyle(cellStyleList.get(i));
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename=file.xlsx");
        workbook.write(response.getOutputStream());
//        Header header = TestData.class.getDeclaredAnnotation(Header.class);
//        System.out.println(header.value());
    }

//    private List<String> getHeaders() {
//        Map<Integer, String> headers = new TreeMap<>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1 - o2;
//            }
//        });
//        Field[] declaredFields = TestData.class.getDeclaredFields();
//        for (Field declaredField : declaredFields) {
//            if (declaredField.isAnnotationPresent(Header.class) && declaredField.isAnnotationPresent(Order.class)) {
//                headers.put(declaredField.getDeclaredAnnotation(Order.class).value(), declaredField.getDeclaredAnnotation(Header.class).value());
//            }
//        }
//        return new ArrayList<>(headers.values());
//    }

    private List<Field> getAllField() {
        Map<Integer, Field> fieldMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        Field[] declaredFields = TestData.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(Arrays.toString(declaredField.getDeclaredAnnotations()));
            if (declaredField.isAnnotationPresent(XSSF.class)) {
                XSSF xssf = declaredField.getDeclaredAnnotation(XSSF.class);
                fieldMap.put(xssf.index(), declaredField);
            }
        }
        return new ArrayList<>(fieldMap.values());
    }

    private List<String> getAllHeader(List<Field> fieldList) {
        List<String> headerList = new ArrayList<>();
        for (Field field : fieldList) {
            XSSF xssf = field.getDeclaredAnnotation(XSSF.class);
            headerList.add(xssf.header());
        }
        return headerList;
    }

    private List<CellStyle> getAllCellStyle(XSSFWorkbook workbook, List<Field> fieldList) {
        List<CellStyle> cellStyleList = new ArrayList<>();
        for (Field field : fieldList) {
            XSSF xssf = field.getDeclaredAnnotation(XSSF.class);
            HorizontalAlignment align = xssf.align();
//            XSSFCellStyle xssfCellStyle = new XSSFCellStyle(new StylesTable());
            XSSFCellStyle xssfCellStyle = workbook.createCellStyle();
            xssfCellStyle.setAlignment(align);
            cellStyleList.add(xssfCellStyle);
        }
        return cellStyleList;
    }

    private List<Method> getAllMethod(List<Field> fieldList, Class clazz) {
        List<Method> methodList = new ArrayList<>();
        for (Field field : fieldList) {
            methodList.add(splicePrefixMethod("get", field, clazz));
        }
        return methodList;
    }

    private Method splicePrefixMethod(String prefix, Field field, Class clazz) {
        String fieldName = field.getName();
        prefix += fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        try {
            return clazz.getDeclaredMethod(prefix);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
