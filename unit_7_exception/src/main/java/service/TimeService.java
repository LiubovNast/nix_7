package service;

import calendar.Date;
import calendar.Time;

import static date_formatter.ConvertDate.*;

public class TimeService {

    public static Time getTime(Date date) {
        Time timeInMs = new Time();
        long days = date.getDay() + convertYearsToDays(date) + convertMonthsToDays(date);
        long hours = convertDaysToHours(days) + date.getHour();
        long minutes = convertHoursToMinutes(hours) + date.getMin();
        long seconds = convertMinutesToSeconds(minutes) + date.getSec();
        long milliseconds = convertSecondsToMilliseconds(seconds) + date.getMs();
        timeInMs.setMs(milliseconds);
        return timeInMs;
    }

    public static Date getDateFromTime(Time time) {
        Date date = new Date();
        long ms = time.getMs();
        long seconds = convertMillisecondsToSeconds(ms, date);
        long minutes = convertSecondsToMinutes(seconds, date);
        long hours = convertMinutesToHours(minutes, date);
        long days = convertHoursToDays(hours, date);
        days = getDaysAfterConvertToYears(days, date);
        int months = convertDaysToMonths(days, date);
        date.setMonth(months);
        return date;
    }
}
