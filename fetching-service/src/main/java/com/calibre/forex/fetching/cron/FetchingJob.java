package com.calibre.forex.fetching.cron;

import com.calibre.forex.fetching.model.Currency;
import com.calibre.forex.fetching.model.FetchingLink;
import com.calibre.forex.fetching.service.FetchingService;
import com.calibre.forex.fetching.service.RabbitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FetchingJob {
    private final Logger logger = LoggerFactory.getLogger(FetchingJob.class);
    @Autowired
    private FetchingService fetchingService;
    @Autowired
    private FetchingLink fetchingLink;
    @Autowired
    private RabbitService rabbitService;

    @Scheduled(cron = "${app.cron.schedule}")
    public void fectchingData(){
        logger.info("Job Executing at {} ",System.currentTimeMillis());
        Map<String,Currency> rateMap = fetchingService.fetchForexRateList();
        boolean changed = fetchingService.getForexRateChanged(rateMap);
        if(changed){
            rabbitService.sendChangedForexRate(rateMap);
        }
    }
}
