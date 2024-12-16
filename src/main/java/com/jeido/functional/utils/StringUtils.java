package com.jeido.functional.utils;

public class StringUtils {

    public static String capitalizeAll(String str) {
        String[] words = str.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(capitalize(word));
            sb.append(' ');
        }
        return sb.toString().trim();
    }

    public static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    public static String center(String string, int length) {
        return center(string, length, ' ');
    }

    public static String center(String string, int length, char c) {
        if (string.length() >= length) {
            return string;
        }

        int midLen = length / 2;
        int midStringLen = string.length() / 2;
        int start = midLen - midStringLen;
        int end = length - (start + string.length());

        return ("" + c).repeat(start) + string + ("" + c).repeat(end);
    }

    public static String left(String string, int length) {
        return left(string, length, ' ');
    }

    public static String left(String string, int length, char c) {
        if (string.length() >= length) {
            return string;
        }

        int end = length - (string.length());
        return string + ("" + c).repeat(end);
    }

    public static String right(String string, int length) {
        return right(string, length, ' ');
    }

    public static String right(String string, int length, char c) {
        if (string.length() >= length) {
            return string;
        }

        int start = length - string.length();
        return ("" + c).repeat(start) + string;
    }
}
