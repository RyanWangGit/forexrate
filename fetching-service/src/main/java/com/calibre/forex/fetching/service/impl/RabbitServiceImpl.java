package com.calibre.forex.fetching.service.impl;

import com.calibre.forex.fetching.config.RabbitConfig;
import com.calibre.forex.fetching.model.Currency;
import com.calibre.forex.fetching.service.RabbitService;
import com.calibre.forex.fetching.util.JsonUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RabbitServiceImpl implements RabbitService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void sendChangedForexRate(Map<String, Currency> rateMap) {
        String ratesJson = JsonUtil.toJsonStr(rateMap.values());
        rabbitTemplate.convertAndSend(RabbitConfig.FOREX_RATE_EXCHANGE,RabbitConfig.FOREX_RATE_ROUTING_KEY,ratesJson);
    }
}
