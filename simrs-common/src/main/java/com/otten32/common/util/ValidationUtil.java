package com.otten32.common.util;

import java.util.regex.Pattern;

public class ValidationUtil {
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String PHONE_PATTERN = "^[0-9]{10,15}$";
    private static final String NIK_PATTERN = "^[0-9]{16}$";

    public static boolean isValidEmail(String email) {
        return email != null && Pattern.matches(EMAIL_PATTERN, email);
    }

    public static boolean isValidPhone(String phone) {
        return phone != null && Pattern.matches(PHONE_PATTERN, phone);
    }

    public static boolean isValidNIK(String nik) {
        return nik != null && Pattern.matches(NIK_PATTERN, nik);
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}
