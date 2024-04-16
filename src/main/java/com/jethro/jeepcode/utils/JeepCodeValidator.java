package com.jethro.jeepcode.utils;

public class JeepCodeValidator {
    public static boolean isValidJeepCode(String code) {
        String pattern = "\\d{2}[A-Z]";
        return code.matches(pattern);
    }
}
