package service;

import calendar.Date;
import date_formatter.FormatDate;
import exception.NotFindDateFormat;

import static console.Console.getString;
import static console.Console.printMessage;
import static date_formatter.ConvertDate.*;
import static service.DateService.printDate;
import static service.DateService.readDate;

public class MenuService {

    private static final Date date = new Date();

    public static void changeInputtingFormat() {
        printMessage("choose format for input:\n" +
                "    dd/mm/yy\n" +
                "    m/d/yyyy\n" +
                "    mmm-d-yy\n" +
                "    dd-mmm-yyyy 00:00");
        String inputFormat = getString();
        try {
            FormatDate.inputDateFormat(inputFormat, date);
            FormatDate.outputDateFormat(inputFormat, date);
        } catch (NotFindDateFormat e) {
            printMessage(e.getMessage());
        }
    }

    public static void changeOutputtingFormat() {
        printMessage("choose format for output:\n" +
                "    dd/mm/yy\n" +
                "    m/d/yyyy\n" +
                "    mmm-d-yy\n" +
                "    dd-mmm-yyyy 00:00");
        String outputFormat = getString();
        try {
            FormatDate.outputDateFormat(outputFormat, date);
        } catch (NotFindDateFormat e) {
            printMessage(e.getMessage());
        }
    }

    public static void findDifferenceBetweenTwoDates() {
        try {
            printMessage("Enter first date:");
            Date firstDate = readDate(date);
            printDate(firstDate, date.getTypeOutput());
            printMessage("Enter second date:");
            Date secondDate = readDate(date);
            printDate(secondDate, date.getTypeOutput());
            long dif = dif(firstDate, secondDate);
            printMessage("Difference between two dates - " + dif);
        } catch (NotFindDateFormat e) {
            e.printStackTrace();
        }
    }

    private static long dif(Date firstInput, Date secondInput) {
        Date first, second;
        if (firstInput.compareTo(secondInput) < 0) {
            second = firstInput;
            first = secondInput;
        } else {
            first = firstInput;
            second = secondInput;
        }
        long year = first.getYear() - second.getYear();
        long month = first.getMonth() - second.getMonth() + convertYearsToMonths(year);
        long day = first.getDay() - second.getDay() + convertMonthsToDays(month);
        long hour = first.getHour() - second.getHour() + convertDaysToHours(day);
        long minute = first.getMin() - second.getMin() + convertHoursToMinutes(hour);
        long seconds = first.getSec() - second.getSec() + convertMinutesToSeconds(minute);
        return first.getMs() - second.getMs() + convertSecondsToMilliseconds(seconds);
    }

    public static void addingToDate() {
    }

    public static void subtractingFromDate() {
    }

    public static void compareListOFDates() {
    }
}
