package service;

import calendar.Date;
import calendar.Time;
import exception.IndefiniteInput;
import exception.NotFindDateFormat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static console.Console.*;
import static date_formatter.ConvertDate.*;
import static service.DateService.printDate;
import static service.DateService.readDate;
import static service.TimeService.getDateFromTime;
import static service.TimeService.getTime;

public class MenuService {

    private static final Date date = new Date();

    public static void changeFormat() {
        printMessage("Do you want to change input format? (Y,N)");
        if (isPositiveAnswer()) {
            changeInputtingFormat();
        }
        printMessage("Do you want to change output format? (Y,N)");
        if (isPositiveAnswer()) {
            changeOutputtingFormat();
        }
    }

    private static boolean isPositiveAnswer() {
        String answer = getString();
        if (answer.trim().equals("Y") || answer.trim().equals("y"))
            return true;
        else if (answer.trim().equals("N") || answer.trim().equals("n"))
            return false;
        else {
            printMessage("Please, enter correct answer.");
            return isPositiveAnswer();
        }
    }

    private static void changeInputtingFormat() {
        int inputFormat = chooseFormat();
        date.setTypeInput(inputFormat);
    }

    private static int chooseFormat() {
        printMessage("choose format for input/output:\n" +
                "1 - dd/mm/yy\n" +
                "2 - m/d/yyyy\n" +
                "3 - mmm-d-yy\n" +
                "4 - dd-mmm-yyyy 00:00");
        int choose = getInt();
        switch (choose) {
            case 1:
            case 2:
            case 3:
            case 4:
                return choose;
            default:
                printMessage("You enter indefinite format. Please, enter correct number for date:");
        }
        return chooseFormat();
    }

    private static void changeOutputtingFormat() {
        int outputFormat = chooseFormat();
        date.setTypeOutput(outputFormat);
    }

    public static void findDifferenceBetweenTwoDates() {
        try {
            printMessage("Enter first date:");
            String stringDate = getString();
            Date firstDate = readDate(stringDate, date);
            printMessage("Enter second date:");
            stringDate = getString();
            Date secondDate = readDate(stringDate, date);
            long dif = dif(firstDate, secondDate);
            printMessage("Difference between two dates - " + dif);
        } catch (IndefiniteInput | NotFindDateFormat e) {
            printMessage(e.getMessage());
        }
    }

    private static long dif(Date firstInput, Date secondInput) throws IndefiniteInput {
        Time first = getTime(firstInput);
        Time second = getTime(secondInput);
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
            default:
                throw new IndefiniteInput("You enter indefinite operation.");
        }
    }

    public static void addingToDate() {
        try {
            printMessage("Enter date:");
            String stringDate = getString();
            Date current = readDate(stringDate, date);
            Time time = getTime(current);
            printMessage("What do you want to add?");
            long number = getNumberForAction();
            long result = time.getMs() + number;
            time.setMs(result);
            printMessage("New date after adding:");
            printDate(getDateFromTime(time), date.getTypeOutput());
        } catch (NotFindDateFormat | IndefiniteInput e) {
            printMessage(e.getMessage());
        }
    }

    private static long getNumberForAction() throws IndefiniteInput {
        printMessage("1 - milliseconds;\n" +
                "2 - seconds;\n" +
                "3 - minutes\n" +
                "4 - hours;\n" +
                "5 - days;\n" +
                "6 - years.");
        int choice = getInt();
        long ms;
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
                ms = convertHoursToMilliseconds(hours);
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
            default:
                throw new IndefiniteInput("You enter indefinite operation.");
        }
        return ms;
    }


    public static void subtractingFromDate() {
        try {
            printMessage("Enter date:");
            String stringDate = getString();
            Date current = readDate(stringDate, date);
            Time time = getTime(current);
            printMessage("What do you want to subtract?");
            long number = getNumberForAction();
            long result = time.getMs() - number;
            time.setMs(result);
            printMessage("New date after subtracting:");
            printDate(getDateFromTime(time), date.getTypeOutput());
        } catch (NotFindDateFormat | IndefiniteInput e) {
            printMessage(e.getMessage());
        }
    }

    public static void compareListOFDates() {
        List<Time> times = new ArrayList<>();
        Time time;
        Date dateResult;
        int dateOutput = date.getTypeOutput();
        printMessage("Enter date to compare:");
        printMessage("If you want stop enter - 'exit'.");
        String stringDate = getString();
        while (!stringDate.equals("exit")) {
            try {
                time = getTime(readDate(stringDate, date));
                times.add(time);
                stringDate = getString();
            } catch (NotFindDateFormat e) {
                printMessage(e.getMessage());
            }
        }
        Collections.sort(times);
        printMessage("Do you want to sort ascending? (Y,N)");
        if (isPositiveAnswer()) {
            for (int i = 0; i < times.size(); i++) {
                dateResult = getDateFromTime(times.get(i));
                printDate(dateResult, dateOutput);
            }
        } else {
            for (int i = times.size() - 1; i >= 0; i--) {
                dateResult = getDateFromTime(times.get(i));
                printDate(dateResult, dateOutput);
            }
        }
    }
}
