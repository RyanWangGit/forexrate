package com.calibre.forex.fetching.service;

import com.calibre.forex.fetching.constant.Constants;
import com.calibre.forex.fetching.model.Currency;
import com.calibre.forex.fetching.response.ResponseData;
import com.calibre.forex.fetching.response.ResponseEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class FetchingServiceTest {

    @Autowired
    private FetchingService fetchingService;
    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    void before(){
        Cache cache = cacheManager.getCache(Constants.FETCHING_CACHE_NAME);
        cache.evict(Constants.FETCHING_CACHE_KEY_OLD_RATE);
    }

    @Test
    void fetchForexRate() {
        ResponseData<String> responseData = fetchingService.fetchForexRate("EUR");
        assertEquals(ResponseEnum.DATA_SUCCESS.getCode(),responseData.getCode());
        responseData = fetchingService.fetchForexRate("AUDUSD");
        assertEquals(ResponseEnum.DATA_ERROR.getCode(),responseData.getCode());
    }

    @Test
    void fetchForexRateList() {
        Map<String, Currency> map = fetchingService.fetchForexRateList();
        assertEquals(1,map.size());
        String rateCode = map.get("EUR.FOREX").getCode();
        assertEquals("EUR.FOREX",rateCode);

    }

    @Test
    void getForexRateChanged() {
        Map<String, Currency> newRate = fetchingService.fetchForexRateList();
        boolean changed = fetchingService.getForexRateChanged(newRate);
        assertTrue(changed);
        changed = fetchingService.getForexRateChanged(newRate);
        assertFalse(changed);

    }
}