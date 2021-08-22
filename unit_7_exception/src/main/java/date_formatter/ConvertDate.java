package date_formatter;

public class ConvertDate {

    private static final int MILLISECONDS_IN_SECOND = 1000;
    private static final int SECONDS_IN_MINUTE = 60;
    private static final int MINUTES_IN_HOUR = 60;
    private static final int HOURS_IN_DAY = 24;
    private static final int DAYS_IN_FEBRUARY = 28;
    private static final int DAYS_IN_LONG_MONTH = 31;
    private static final int DAYS_IN_SHORT_MONTH = 30;
    private static final int MONTHS_IN_YEAR = 12;
    private static final int DAYS_IN_YEAR = 365;

    public long convertMillisecondsToSeconds (long ms) {
        return ms / MILLISECONDS_IN_SECOND;
    }

    public static long convertSecondsToMinutes (long sec) {
        return sec / SECONDS_IN_MINUTE;
    }

    public static long convertMinutesToHours (long min) {
        return min / MINUTES_IN_HOUR;
    }

    public static long convertHoursToDays (long hour) {
        return hour/ HOURS_IN_DAY;
    }

    public static long convertDaysToMonths (long days) {
        return days / DAYS_IN_SHORT_MONTH;
    }

    public static long convertMonthsToYears (long month) {
        return month / MONTHS_IN_YEAR;
    }

    public static long convertSecondsToMilliseconds (long sec) {
        return sec * MILLISECONDS_IN_SECOND;
    }

    public static long convertMinutesToSeconds (long min) {
        return min * SECONDS_IN_MINUTE;
    }

    public static long convertHoursToMinutes (long hours) {
        return hours * MINUTES_IN_HOUR;
    }

    public static long convertDaysToHours (long days) {
        return days * HOURS_IN_DAY;
    }

    public static long convertMonthsToDays (long months) {
        return months * DAYS_IN_SHORT_MONTH;
    }

    public static long convertYearsToMonths(long year) {
        return year * MONTHS_IN_YEAR;
    }
}
