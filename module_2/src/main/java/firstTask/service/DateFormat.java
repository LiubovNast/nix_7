package firstTask.service;

import firstTask.entity.Date;

public class DateFormat {

    private static final int DAYS_IN_FEBRUARY = 28;
    private static final int DAYS_IN_LEAP_FEBRUARY = 29;
    private static final int DAYS_IN_LONG_MONTH = 31;
    private static final int DAYS_IN_SHORT_MONTH = 30;
    private static final int ID_FEBRUARY = 2;
    private static final int ID_APRIL = 4;
    private static final int ID_JUNE = 6;
    private static final int ID_SEPTEMBER = 9;
    private static final int ID_NOVEMBER = 11;
    private static Date date;

    public static boolean checkDateFormat(String date) {
        return date.matches("^[0-9]{1,4}\\/[0-9]{2}\\/[0-9]{2}$") ||
                date.matches("^[0-9]{2}\\/[0-9]{2}\\/[0-9]{1,4}$") ||
                date.matches("^[0-9]{2}\\-[0-9]{2}\\-[0-9]{1,4}$");
    }

    public static Date getDate(String string) {
        int year = 0, month = 0, day = 0;
        date = new Date();
        if (string.matches("^[0-9]{1,4}\\/[0-9]{2}\\/[0-9]{2}$")) {
            String[] temp = string.trim().split("\\/");
            year = Integer.parseInt(temp[0]);
            month = Integer.parseInt(temp[1]);
            day = Integer.parseInt(temp[2]);
        } else if (string.matches("^[0-9]{2}\\/[0-9]{2}\\/[0-9]{1,4}$")) {
            String[] temp = string.trim().split("\\/");
            day = Integer.parseInt(temp[0]);
            month = Integer.parseInt(temp[1]);
            year = Integer.parseInt(temp[2]);
        } else if (string.matches("^[0-9]{2}\\-[0-9]{2}\\-[0-9]{1,4}$")) {
            String[] temp = string.trim().split("\\-");
            month = Integer.parseInt(temp[0]);
            day = Integer.parseInt(temp[1]);
            year = Integer.parseInt(temp[2]);
        }
        if (checkYear(year)) {
            date.setYear(year);
            date.setLeapYear(isLeap(year));
        } else return null;
        if (checkMonth(month)) {
            date.setMonth(month);
        } else return null;
        if (checkDay(day)) {
            date.setDay(day);
        } else return null;
        return date;
    }

    private static boolean checkDay(int day) {
        if (day < 1) {
            return false;
        }
        int month = date.getMonth();
        if (month == ID_FEBRUARY) {
            if (date.isLeapYear()) {
                return day <= DAYS_IN_LEAP_FEBRUARY;
            } else return day <= DAYS_IN_FEBRUARY;
        } else if (month == ID_APRIL || month == ID_JUNE || month == ID_SEPTEMBER || month == ID_NOVEMBER) {
            return day <= DAYS_IN_SHORT_MONTH;
        } else return day <= DAYS_IN_LONG_MONTH;
    }

    private static boolean checkMonth(int month) {
        return month > 0 && month < 13;
    }

    private static boolean checkYear(int year) {
        return year >= 0;
    }

    private static boolean isLeap(int year) {
        return (year % 100 == 0 && year % 400 == 0) || year % 4 == 0;
    }
}
