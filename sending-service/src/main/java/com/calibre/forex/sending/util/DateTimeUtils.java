package com.calibre.forex.sending.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class  DateTimeUtils {
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmm");

    /**
     * Get Current Date
     * @return String
     */
    public static String getCurrentDateStr() {
        return LocalDate.now().format(DATE_FORMATTER);
    }

    /**
     * Get Current Time
     * @return String
     */
    public static String getCurrentTimeStr() {
        return LocalDateTime.now().format(TIME_FORMATTER);
    }

    /**
     * Get Date String from parameter
     * @param localDateTime LocalDateTime
     * @return String
     */
    public static String getDateStr(LocalDateTime localDateTime) {
        return localDateTime.format(DATE_FORMATTER);
    }

    /**
     * Get time String from parameter
     * @param localDateTime LocalDateTime
     * @return String
     */
    public static String getTimeStr(LocalDateTime localDateTime) {
        return localDateTime.format(TIME_FORMATTER);
    }

    /**
     * Get date Stringvalue from timestamp value
     * @param timeStamp lang value
     * @return String value
     */
    public static String getDateString(long timeStamp) {
        Instant instant = Instant.ofEpochSecond(timeStamp);
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.format(DATE_FORMATTER);
    }

    /**
     * Get time String value from timestamp value
     * @param timeStamp long value
     * @return String value
     */
    public static String getTimeString(long timeStamp) {
        Instant instant = Instant.ofEpochSecond(timeStamp);
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.format(TIME_FORMATTER);
    }


}
