package com.calibre.forex.sending.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeUtilsTest {

    @Test
    void getDateStringFromTimeStamp() {
        long dateStamp = 1574219832L;
        String date = DateTimeUtils.getDateString(dateStamp);
        assertEquals("20191120", date);
    }

    @Test
    void getTimeStringFromTimeStamp() {
        long dateStamp = 1574219832L;
        String time = DateTimeUtils.getTimeString(dateStamp);
        assertEquals("1417", time);
    }

    @Test
    void getDateStringFromDateTime(){
        LocalDateTime localDateTime = LocalDateTime.of(2019, Month.NOVEMBER, 25, 17, 23, 52);
        String date = DateTimeUtils.getDateStr(localDateTime);
        assertEquals("20191125", date);
    }

    @Test
    void getTimeStringFromDateTime(){
        LocalDateTime localDateTime = LocalDateTime.of(2019, Month.NOVEMBER, 25, 17, 23, 52);
        String time = DateTimeUtils.getTimeStr(localDateTime);
        assertEquals("1723", time);
    }


}