package com.example.demo.custom.annotation.format.money.service;

import com.example.demo.custom.annotation.format.money.data.MoneyData;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author zyl
 * @date 2020/7/11 15:19
 */
@Service
public class DoService {

    @Autowired
    private MoneyData moneyData;
    public void calculateData() {
        MoneyData moneyData1 = new MoneyData("左亚利", new BigDecimal("11000.123"), "左亚利1", new BigDecimal("11000.234"),
                new BigDecimal("3333.333"), new BigDecimal("11000.123"), new BigDecimal("11000.123"),
                new BigDecimal("3333.333"), new BigDecimal("11000.123"), new BigDecimal("11000.123"));
        moneyData.copy(moneyData1);
        System.out.println(moneyData);
        String s = moneyData.parseTransfer();
        System.out.println(s);
    }
}
