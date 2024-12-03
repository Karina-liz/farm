package com.app.farmacia.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Util {
    public static String localDateTimeToString(LocalDateTime date) {
        if (date == null) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        return date.format(formatter);
    }

    public static String localDateToString(LocalDate date) {
        if (date == null) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return date.format(formatter);
    }

    public static String toUpperString(String field) {
        if (StringUtils.isNoneBlank(field)) {
            return field.toUpperCase();
        }

        return field;
    }

    public static String toLowerString(String field) {
        if (StringUtils.isNoneBlank(field)) {
            return field.toLowerCase();
        }

        return field;
    }

    public static int defaultValue(Integer field, int defaultValue) {
        if (field == null) {
            return defaultValue;
        }

        return field;
    }

    public static boolean isValidFilter(String field) {
        if (field == null) {
            return false;
        }

        return !field.contains("'") && !field.contains(";");
    }
}
