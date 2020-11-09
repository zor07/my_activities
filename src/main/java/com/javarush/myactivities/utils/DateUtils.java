package com.javarush.myactivities.utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

public class DateUtils {

    public static LocalDate date2LocalDate(Date date) {
        return new Timestamp(date.getTime()).toLocalDateTime().toLocalDate();
    }

    public static java.sql.Date localDate2Date(LocalDate date) {
        return java.sql.Date.valueOf(date);
    }

}
