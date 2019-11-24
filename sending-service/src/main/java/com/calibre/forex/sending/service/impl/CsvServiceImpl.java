package com.calibre.forex.sending.service.impl;

import com.calibre.forex.sending.model.Currency;
import com.calibre.forex.sending.util.DateTimeUtils;
import com.calibre.forex.sending.model.CsvFile;
import com.calibre.forex.sending.service.CsvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.math.RoundingMode;
import java.util.List;

@Service
public class CsvServiceImpl implements CsvService {
    private final Logger logger = LoggerFactory.getLogger(CsvServiceImpl.class);

    @Autowired
    private CsvFile csvFile;

    @Override
    public String write(List<Currency> rates) {
        String fullFilePath = genFullFilePath();
        logger.info("==== fullFilePath  {}",fullFilePath);
        String header = csvFile.getHeader();
        boolean fileExists = false;
        File attachFile = new File(fullFilePath);
        if(attachFile.exists()){
            fileExists = true;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(attachFile, true));){
            if(!fileExists){
                bw.write(header);
            }
            for (Currency rate: rates) {
                bw.newLine();
                int dotIndex = rate.getCode().indexOf(".");
                String code = rate.getCode().substring(0,dotIndex);
                bw.write(code);
                bw.write(",");
                bw.write(rate.getClose().setScale(2,RoundingMode.HALF_UP).toString());
            }
            bw.newLine();
            bw.flush();

        } catch (IOException e) {
            logger.error("Write CSV file error {0}",e);
        }

        return fullFilePath;
    }

    private String genFullFilePath(){
        StringBuffer stringBuffer = new StringBuffer();
        String path = System.getProperty("user.dir");
        System.out.println("=== path "+path);
        File filePath = new File(path,csvFile.getPath());
        if (!filePath.exists()) filePath.mkdir();
        stringBuffer.append(filePath.getAbsolutePath()).append("/");
        stringBuffer.append(csvFile.getPrefix());
        stringBuffer.append("_");
        String ymd = DateTimeUtils.getCurrentDateStr();
        String hm = DateTimeUtils.getCurrentTimeStr();
        stringBuffer.append(ymd).append("_");
        stringBuffer.append(hm).append(".csv");
        return stringBuffer.toString();
    }

}
