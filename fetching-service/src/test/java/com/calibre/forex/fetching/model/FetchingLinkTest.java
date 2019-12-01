package com.calibre.forex.fetching.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class FetchingLinkTest {

    @Autowired
    private FetchingLink fetchingLink;
    private final String TEST_URL = "https://eodhistoricaldata.com/api/real-time/EUR.FOREX?api_token=OeAFFmMliFG5orCUuwAKQ8l4WWFQ67YX&fmt=json";

    @Test
    void genFetchingLink(){
        String link = fetchingLink.genFetchingLink("EUR");
        assertEquals(TEST_URL,link);
    }

    @Test
    void getFetchingLinks() {
        Map<String, String> fetchingLinks = fetchingLink.getFetchingLinks();
        assertNull(fetchingLinks.get("USD"));
        assertNotNull(fetchingLinks.get("EUR"));
        assertEquals(TEST_URL,fetchingLinks.get("EUR"));
    }
}