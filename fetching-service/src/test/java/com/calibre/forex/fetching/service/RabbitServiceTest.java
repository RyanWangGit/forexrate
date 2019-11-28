package com.calibre.forex.fetching.service;

import com.calibre.forex.fetching.config.RabbitConfig;
import com.calibre.forex.fetching.model.Currency;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class RabbitServiceTest {
    private final Logger logger = LoggerFactory.getLogger(RabbitServiceTest.class);
    @Autowired
    private RabbitService rabbitService;
    @Autowired
    private FetchingService fetchingService;


    @Test
    void sendChangedForexRate() {
        Map<String, Currency> rateMap = fetchingService.fetchForexRateList();
        rabbitService.sendChangedForexRate(rateMap);
    }

}
