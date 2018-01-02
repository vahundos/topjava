package ru.javawebinar.topjava.util;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private static final DateTimeFormatter TO_DATE_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("[dd-MM-yyyy]")
            .appendPattern("[yyyy-MM-dd]")
            .toFormatter();

    private static final DateTimeFormatter TO_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static final LocalDate MIN_DATE = LocalDate.of(1, 1, 1);
    public static final LocalDate MAX_DATE = LocalDate.of(3000, 1, 1);

    private DateTimeUtil() {
    }

    public static <T extends Comparable<? super T>> boolean isBetween(T value, T start, T end) {
        return value.compareTo(start) >= 0 && value.compareTo(end) <= 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static LocalDate parseLocalDate(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        return LocalDate.parse(str, TO_DATE_FORMATTER);
    }

    public static LocalTime parseLocalTime(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        return LocalTime.parse(str, TO_TIME_FORMATTER);
    }
}
