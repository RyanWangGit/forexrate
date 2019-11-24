package com.calibre.forex.fetching.service;

import com.calibre.forex.fetching.model.Currency;
import com.calibre.forex.fetching.response.ResponseData;

import java.util.List;
import java.util.Map;

public interface FetchingService {

    /**
     * Getting data from remote server
     * @param code forex code
     * @return
     */
    public ResponseData<String> fetchForexRate(String code);

    /**
     * Getting data list from remote server
     * @param codes list of forex code
     * @return
     */
    public List<ResponseData<String>> fetchForexRateList(List<String> codes);

    /**
     * Getting data list from remote server
     * @return
     */
    public Map<String, Currency> fetchForexRateList();

    /**
     * Getting forex rate if data has any changes
     * @return
     */
    public boolean getForexRateChanged(Map<String, Currency> rateMap);
}
