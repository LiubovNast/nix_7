package service;

import calendar.Date;
import calendar.Time;
import date_formatter.FormatDate;
import exception.NotFindDateFormat;

import static console.Console.*;
import static date_formatter.ConvertDate.*;
import static service.DateService.printDate;
import static service.DateService.readDate;
import static service.TimeService.getDateFromTime;

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
            printMessage("Enter second date:");
            Date secondDate = readDate(date);
            long dif = dif(firstDate, secondDate);
            printMessage("Difference between two dates - " + dif);
        } catch (NotFindDateFormat e) {
            printMessage(e.getMessage());
        }
    }

    private static long dif(Date firstInput, Date secondInput) throws NotFindDateFormat {
        Time first = TimeService.getTime(firstInput);
        Time second = TimeService.getTime(secondInput);
        long result = first.getMs() - second.getMs();
        if (result < 0) result *= -1;
        printMessage("What output do you want?\n" +
                "1 - milliseconds;\n" +
                "2 - seconds;\n" +
                "3 - minutes\n" +
                "4 - hours;\n" +
                "5 - days;\n" +
                "6 - years.");
        int choice = getInt();
        switch (choice) {
            case 1:
                return result;
            case 2:
                return convertMillisecondsToSeconds(result);
            case 3:
                return convertMillisecondsToMinutes(result);
            case 4:
                return convertMillisecondsToHours(result);
            case 5:
                return convertMillisecondsToDays(result);
            case 6:
                return convertMillisecondsToYears(result);
        }
        throw new NotFindDateFormat("Indefinite input");
    }

    public static void addingToDate() {
        try {
            printMessage("Enter date:");
            Date current = readDate(date);
            Time time = TimeService.getTime(current);
            printMessage("What do you want to add?");
            long number = getNumberForAction();
            long result = time.getMs() + number;
            time.setMs(result);
            printDate(getDateFromTime(time), date.getTypeOutput());
        } catch (NotFindDateFormat e) {
            printMessage(e.getMessage());
        }
    }

    private static long getNumberForAction() {
        printMessage(
                "1 - milliseconds;\n" +
                        "2 - seconds;\n" +
                        "3 - minutes\n" +
                        "4 - hours;\n" +
                        "5 - days;\n" +
                        "6 - years.");
        int choice = getInt();
        long ms = 0L;
        switch (choice) {
            case 1:
                printMessage("Enter milliseconds:");
                ms = getInt();
                break;
            case 2:
                printMessage("Enter seconds:");
                long sec = getInt();
                ms = convertSecondsToMilliseconds(sec);
                break;
            case 3:
                printMessage("Enter minutes:");
                long minutes = getInt();
                ms = convertMinutesToMilliseconds(minutes);
                break;
            case 4:
                printMessage("Enter hours:");
                long hours = getInt();
                ms = convertHoursToMilliseconds(ms);
                break;
            case 5:
                printMessage("Enter days:");
                long days = getInt();
                ms = convertDaysToMilliseconds(days);
                break;
            case 6:
                printMessage("Enter years:");
                long years = getInt();
                ms = convertYearsToMilliseconds(years);
                break;
        }
        return ms;
    }


    public static void subtractingFromDate() {
        try {
            printMessage("Enter date:");
            Date current = readDate(date);
            Time time = TimeService.getTime(current);
            printMessage("What do you want to subtract?");
            long number = getNumberForAction();
            long result = time.getMs() - number;
            time.setMs(result);
            printDate(getDateFromTime(time), date.getTypeOutput());
        } catch (NotFindDateFormat e) {
            printMessage(e.getMessage());
        }
    }

    public static void compareListOFDates() {
    }
}
