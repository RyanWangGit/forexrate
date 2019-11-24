package com.calibre.forex.fetching;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FetchingServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FetchingServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
