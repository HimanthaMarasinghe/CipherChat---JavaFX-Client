package com.mahk.cipherchatjavafx_client.util;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    public static String formatedTime(String isoTime, boolean format12) {
        OffsetDateTime dateTime = OffsetDateTime.parse(isoTime);
        if(format12){
            return dateTime.format(DateTimeFormatter.ofPattern("h:mm a"));
        } else {
            return dateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        }
    }
}
