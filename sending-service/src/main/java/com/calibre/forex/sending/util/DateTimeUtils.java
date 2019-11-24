package com.calibre.forex.sending.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
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


}
