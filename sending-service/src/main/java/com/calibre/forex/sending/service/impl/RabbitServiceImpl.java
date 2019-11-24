package com.calibre.forex.sending.service.impl;

import com.calibre.forex.sending.config.RabbitConfig;
import com.calibre.forex.sending.model.Currency;
import com.calibre.forex.sending.model.Mail;
import com.calibre.forex.sending.service.CsvService;
import com.calibre.forex.sending.service.MailService;
import com.calibre.forex.sending.service.RabbitService;
import com.calibre.forex.sending.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RabbitServiceImpl implements RabbitService {
    private final Logger logger = LoggerFactory.getLogger(RabbitServiceImpl.class);
    @Autowired
    private CsvService csvService;
    @Autowired
    private MailService mailService;
    @Autowired
    private Mail mail;

    @Override
    @RabbitListener(queues = RabbitConfig.FOREX_RATE_QUEUE)
    @RabbitHandler
    public void receiveForexRate(String ratesJson) {
        System.out.println("===rateJson :"+ratesJson);
        List<Currency> rateList = JsonUtil.parseJsonList(ratesJson, Currency.class);
        String attachPath = csvService.write(rateList);
        mail.setSubject("About FOREX Rate updates from Calibre");
        mail.setAttachPath(attachPath);
        mailService.sendMailWithAttach(mail);
        logger.info("===Mail has already sent===");
    }
}
