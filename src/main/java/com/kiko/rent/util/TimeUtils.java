package com.kiko.rent.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class TimeUtils {

    public static String timeSlotToString(Integer timeSlot, Long startingHour, Date date){

        LocalDateTime localDateTime = convertToLocalDateTimeViaInstant(date);
        localDateTime = localDateTime.plusHours(startingHour);
        localDateTime = localDateTime.plusMinutes(timeSlot*20);
        LocalDateTime endingTime = localDateTime.plusMinutes(20);
        return String.format("Starting at %s and ending at %s",localDateTime.toString(), endingTime.toString());
    }

    public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
