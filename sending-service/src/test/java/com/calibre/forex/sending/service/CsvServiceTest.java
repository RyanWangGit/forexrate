package com.calibre.forex.sending.service;

import com.calibre.forex.sending.model.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CsvServiceTest {

    @Autowired
    private CsvService csvService;
    private List<Currency> rates;

    @BeforeEach
    void before(){
        rates = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Currency currency = new Currency();
            currency.setCode("EUR.FOREX");
            float val = 0.65f+i;
            currency.setClose(new BigDecimal(val));
            rates.add(currency);
        }
    }
    @Test
    void write() {
        csvService.write(rates);
    }
}