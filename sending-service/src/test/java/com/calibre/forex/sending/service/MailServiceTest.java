package com.calibre.forex.sending.service;

import com.calibre.forex.sending.model.Currency;
import com.calibre.forex.sending.model.Mail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class MailServiceTest {

    @Autowired
    private MailService mailService;
    @Autowired
    private CsvService csvService;
    @Autowired
    private Mail mail;
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
    void sendMailWithAttach() {
        String filePath = csvService.write(rates);
        mail.setSubject("About FOREX Rate updates");
        mail.setAttachPath(filePath);
        mailService.sendMailWithAttach(mail);
    }
}