package com.calibre.forex.fetching.model;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "forex.fetching")
@Component
public class FetchingLink {
    private final Logger logger = LoggerFactory.getLogger(FetchingLink.class);
    private String baseUrl;
    private String codes;
    private String apiToken;

    public Logger getLogger() {
        return logger;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    /**
     * To generate the fetch link
     * @param code forex code
     * @return fetch link
     */
    @Cacheable(value = "fetchingCache", key = "#code")
    public String  genFetchingLink(String code){
        logger.debug("Getting link from genFetchingLink, Not in Cache");
        StringBuffer stringBuffer = new StringBuffer(getBaseUrl());
        stringBuffer.append(code)
                .append(".FOREX")
                .append("?api_token=")
                .append(getApiToken()).append("&fmt=json");
        return stringBuffer.toString();
    }

    /**
     * To generate the fetch links according to forex codes in properties
     * @return HashMap, code as key, link as value
     */
    @Cacheable(value = "fetchingCache", key="#root.target.getCodes()")
    public Map<String,String> getFetchingLinks(){
        logger.debug("Getting link from getFetchingLinks, Not in Cache");
        Map<String,String> map = new HashMap<>();
        String[] arrCode = getCodes().split(",");
        String link = null;
        for (String code: arrCode) {
            link = genFetchingLink(code);
            map.put(code,link);
        }
        return map;
    }


}
