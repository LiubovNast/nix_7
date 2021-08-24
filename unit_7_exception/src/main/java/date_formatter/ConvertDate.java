package date_formatter;

import calendar.Date;

public class ConvertDate {

    private static final int MILLISECONDS_IN_SECOND = 1000;
    private static final int SECONDS_IN_MINUTE = 60;
    private static final int MINUTES_IN_HOUR = 60;
    private static final int HOURS_IN_DAY = 24;
    private static final int DAYS_IN_FEBRUARY = 28;
    private static final int DAYS_IN_LEAP_FEBRUARY = 29;
    private static final int DAYS_IN_LONG_MONTH = 31;
    private static final int DAYS_IN_SHORT_MONTH = 30;
    private static final int DAYS_IN_YEAR = 365;
    private static final int DAYS_IN_LEAP_YEAR = 366;
    private static final int ID_FEBRUARY = 1;
    private static final int ID_APRIL = 3;
    private static final int ID_JUNE = 5;
    private static final int ID_SEPTEMBER = 8;
    private static final int ID_NOVEMBER = 10;

    public static long convertMillisecondsToSeconds(long ms) {
        return ms / MILLISECONDS_IN_SECOND;
    }

    public static long convertMillisecondsToSeconds(long ms, Date date) {
        date.setMs((int) (ms % MILLISECONDS_IN_SECOND));
        return convertMillisecondsToSeconds(ms);
    }

    public static long convertMillisecondsToMinutes(long ms) {
        long seconds = convertMillisecondsToSeconds(ms);
        return convertSecondsToMinutes(seconds);
    }

    public static long convertMillisecondsToHours(long ms) {
        long minutes = convertMillisecondsToMinutes(ms);
        return convertMinutesToHours(minutes);
    }

    public static long convertMillisecondsToDays(long ms) {
        long hours = convertMillisecondsToHours(ms);
        return convertHoursToDays(hours);
    }

    public static long convertMillisecondsToYears(long ms) {
        long days = convertMillisecondsToDays(ms);
        return convertDaysToYears(days);
    }

    private static long convertSecondsToMinutes(long sec) {
        return sec / SECONDS_IN_MINUTE;
    }

    public static long convertSecondsToMinutes(long sec, Date date) {
        date.setSec((int) (sec % SECONDS_IN_MINUTE));
        return convertSecondsToMinutes(sec);
    }

    private static long convertMinutesToHours(long min) {
        return min / MINUTES_IN_HOUR;
    }

    public static long convertMinutesToHours(long min, Date date) {
        date.setMin((int) (min % MINUTES_IN_HOUR));
        return convertMinutesToHours(min);
    }

    private static long convertHoursToDays(long hour) {
        return hour / HOURS_IN_DAY;
    }

    public static long convertHoursToDays(long hour, Date date) {
        date.setHour((int) (hour % HOURS_IN_DAY));
        return convertHoursToDays(hour);
    }

    private static long convertDaysToYears(long days) {
        int year = 0;
        while (true) {
            if (isLeap(year)) {
                if (days > DAYS_IN_LEAP_YEAR) {
                    days -= DAYS_IN_LEAP_YEAR;
                    year++;
                } else return year;
            } else if (days > DAYS_IN_YEAR) {
                days -= DAYS_IN_YEAR;
                year++;
            } else return year;
        }
    }

    public static long getDaysAfterConvertToYears(long days, Date date) {
        long years = convertDaysToYears(days);
        date.setYear((int) years);
        if (isLeap(years)) date.setLeapYear(true);
        days -= convertYearsToDays(date);
        return days;
    }

    public static int convertDaysToMonths(long days, Date date) {
        int month = 0;
        while (true) {
            if (month == ID_FEBRUARY) {
                if (date.isLeapYear()) {
                    if (days > DAYS_IN_LEAP_FEBRUARY) {
                        days -= DAYS_IN_LEAP_FEBRUARY;
                        month++;
                    } else {
                        date.setDay((int) days);
                        return month;
                    }
                } else if (days > DAYS_IN_FEBRUARY) {
                    days -= DAYS_IN_FEBRUARY;
                    month++;
                } else {
                    date.setDay((int) days);
                    return month;
                }
            } else if (month == ID_APRIL || month == ID_JUNE || month == ID_SEPTEMBER || month == ID_NOVEMBER) {
                if (days > DAYS_IN_SHORT_MONTH) {
                    days -= DAYS_IN_SHORT_MONTH;
                    month++;
                } else {
                    date.setDay((int) days);
                    return month;
                }
            } else if (days > DAYS_IN_LONG_MONTH) {
                days -= DAYS_IN_LONG_MONTH;
                month++;
            } else {
                date.setDay((int) days);
                return month;
            }
        }
    }

    public static long convertSecondsToMilliseconds(long sec) {
        return sec * MILLISECONDS_IN_SECOND;
    }

    public static long convertMinutesToMilliseconds(long min) {
        long seconds = convertMinutesToSeconds(min);
        return convertSecondsToMilliseconds(seconds);
    }

    public static long convertHoursToMilliseconds(long hours) {
        long minutes = convertHoursToMinutes(hours);
        return convertMinutesToMilliseconds(minutes);
    }

    public static long convertDaysToMilliseconds(long days) {
        long hours = convertDaysToHours(days);
        return convertHoursToMilliseconds(hours);
    }

    public static long convertYearsToMilliseconds(long years) {
        long days = getDaysFromYears((int) years);
        return convertDaysToMilliseconds(days);
    }

    public static long convertMinutesToSeconds(long min) {
        return min * SECONDS_IN_MINUTE;
    }

    public static long convertHoursToMinutes(long hours) {
        return hours * MINUTES_IN_HOUR;
    }

    public static long convertDaysToHours(long days) {
        return days * HOURS_IN_DAY;
    }

    public static long convertMonthsToDays(Date date) {
        long days = 0L;
        int month = date.getMonth();
        for (int i = 0; i < month; i++) {
            if (i == ID_FEBRUARY) {
                if (date.isLeapYear()) {
                    days += DAYS_IN_LEAP_FEBRUARY;
                } else days += DAYS_IN_FEBRUARY;
            } else if (i == ID_APRIL || i == ID_JUNE || i == ID_SEPTEMBER || i == ID_NOVEMBER) {
                days += DAYS_IN_SHORT_MONTH;
            } else days += DAYS_IN_LONG_MONTH;
        }
        return days;
    }

    public static long convertYearsToDays(Date date) {
        int year = date.getYear();
        return getDaysFromYears(year);
    }

    private static long getDaysFromYears(int year) {
        long days = 0L;
        for (int i = 0; i < year; i++) {
            if (isLeap(i)) {
                days += DAYS_IN_LEAP_YEAR;
            } else days += DAYS_IN_YEAR;
        }
        return days;
    }

    private static boolean isLeap(long year) {
        return (year % 100 == 0 && year % 400 == 0) || year % 4 == 0;
    }
}
