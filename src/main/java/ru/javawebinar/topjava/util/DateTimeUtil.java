package ru.javawebinar.topjava.util;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static <T extends Comparable> boolean isBetween(T value, T startValue, T endValue) {
        return value.compareTo(startValue) >= 0 && value.compareTo(endValue) <= 0;
    }

    public static LocalDate parseToLocalDate(HttpServletRequest request, String parameter) {
        try {
            return LocalDate.parse(request.getParameter(parameter));
        } catch (Exception e) {
            return parameter.equals("fromDate") ? LocalDate.MIN : LocalDate.MAX;
        }
    }

    public static LocalTime parseToLocalTime(HttpServletRequest request, String parameter) {
        try {
            return LocalTime.parse(request.getParameter(parameter));
        } catch (DateTimeParseException e) {
            return parameter.equals("fromTime") ? LocalTime.MIN : LocalTime.MAX;
        }
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}
