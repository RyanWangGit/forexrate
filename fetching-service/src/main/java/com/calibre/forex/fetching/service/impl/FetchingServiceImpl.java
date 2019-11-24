package com.calibre.forex.fetching.service.impl;

import com.calibre.forex.fetching.constant.Constants;
import com.calibre.forex.fetching.model.Currency;
import com.calibre.forex.fetching.model.FetchingLink;
import com.calibre.forex.fetching.response.ResponseData;
import com.calibre.forex.fetching.response.ResponseEnum;
import com.calibre.forex.fetching.response.ResponseUtil;
import com.calibre.forex.fetching.service.FetchingService;
import com.calibre.forex.fetching.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FetchingServiceImpl implements FetchingService {
    private final Logger logger = LoggerFactory.getLogger(FetchingServiceImpl.class);
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private FetchingLink fetchingLink;
    @Autowired
    private CacheManager cacheManager;

    /**
     * Getting data from remote server
     * @param code forex code
     * @return ResoponseData
     */
    @Override
    public ResponseData<String> fetchForexRate(String code) {
        ResponseData<String> responseData = null;
        String url = fetchingLink.getFetchingLinks().get(code);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,String.class);
        int statusCodeValue = responseEntity.getStatusCodeValue();
        if (statusCodeValue == 200){
            responseData = ResponseUtil.success(responseEntity.getBody());
        }else{
            responseData = ResponseUtil.error(responseEntity.getBody());
        }
        return responseData;
    }

    /**
     * Getting data list from remote server
     * @param codes list of forex code
     * @return
     */
    @Override
    public List<ResponseData<String>> fetchForexRateList(List<String> codes) {
        List<ResponseData<String>> results = new ArrayList<>();
        ResponseData<String> responseData;
        for (String code: codes) {
            responseData = fetchForexRate(code);
            results.add(responseData);
        }
        return results;
    }

    /**
     * Getting data list from remote server
     * @return
     */
    @Override
    public Map<String, Currency> fetchForexRateList() {
        Map<String, Currency> resutls = new HashMap<>();
        List<String> codeList = new ArrayList<>(fetchingLink.getFetchingLinks().keySet());
        List<ResponseData<String>> dataList = fetchForexRateList(codeList);
        for (ResponseData<String> data: dataList) {
            int status = data.getCode();
            if(status == ResponseEnum.DATA_SUCCESS.getCode()) {
                Currency currency = JsonUtil.parseJson(data.getData(),Currency.class);
                logger.info("== Currency == {}",currency);
                resutls.put(currency.getCode(),currency);
            }
        }
        return resutls;
    }

    /**
     * To compare rateMap with data in cache, if they are same, return false, else return true;
     * @param rateMap new forex rate value
     * @return if they are same return false, if they are different, return true
     */
    @Override
    public boolean getForexRateChanged(Map<String, Currency> rateMap) {
        boolean changed = false;
        Cache cache = cacheManager.getCache(Constants.FETCHING_CACHE_NAME);
        Map oldRateMap = cache.get(Constants.FETCHING_CACHE_KEY_OLD_RATE, Map.class);
        if (oldRateMap != null){
            for (Object code: oldRateMap.keySet()) {
                BigDecimal oldClose = ((Currency)oldRateMap.get(code)).getClose();
                BigDecimal newClose = rateMap.get(code).getClose();
                if(!oldClose.equals(newClose)){
                    changed = true;
                    cache.put(Constants.FETCHING_CACHE_KEY_OLD_RATE,rateMap);
                    break;
                }
            }
        }else{
            changed = true;
            cache.put(Constants.FETCHING_CACHE_KEY_OLD_RATE,rateMap);
        }

        return changed;
    }
}
