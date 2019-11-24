package com.calibre.forex.fetching.service;

import com.calibre.forex.fetching.model.Currency;

import java.util.List;
import java.util.Map;

public interface RabbitService {

    public void sendChangedForexRate(Map<String, Currency> rateMap);
}
