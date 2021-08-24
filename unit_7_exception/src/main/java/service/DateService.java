package service;

import calendar.Date;
import calendar.Months;
import exception.NotFindDateFormat;

import static calendar.Months.getNumber;
import static date_formatter.FormatDate.checkCorrectInputAndGetArrayFromString;

public class DateService {

    private static final int FIRST_TYPE = 1;
    private static final int SECOND_TYPE = 2;
    private static final int THIRD_TYPE = 3;
    private static final int FORTH_TYPE = 4;

    public static Date readDate(String stringDate, Date date) throws NotFindDateFormat {
        Date current = new Date();
        current.setTypeInput(date.getTypeInput());
        current.setTypeOutput(date.getTypeOutput());
        String[] arrayDate = checkCorrectInputAndGetArrayFromString(stringDate, date.getTypeInput());
        if (arrayDate != null) {
            current = createDate(arrayDate, current);
        } else throw new NotFindDateFormat("Illegal input");
        return current;
    }

    private static Date createDate(String[] arrayDate, Date date) throws NotFindDateFormat {
        date.setYear(readYear(arrayDate, date));
        date.setMonth(readMonth(arrayDate, date));
        date.setDay(readDay(arrayDate, date));
        if (date.getTypeInput() == FORTH_TYPE) {
            date.setHour(readHour(arrayDate));
            date.setMin(readMinute(arrayDate));
        }
        if (arrayDate.length >= 6) {
            date.setSec(readSeconds(arrayDate));
        }
        if (arrayDate.length == 7) {
            date.setMs(readMilliseconds(arrayDate));
        }
        return date;
    }

    private static int readMilliseconds(String[] arrayDate) throws NotFindDateFormat {
        int sec = arrayDate[6].equals("") ? 0 : Integer.parseInt(arrayDate[6]);
        if (sec < 0 || sec > 999) {
            throw new NotFindDateFormat("Illegal format for seconds");
        }
        return sec;
    }

    private static int readSeconds(String[] arrayDate) throws NotFindDateFormat {
        int sec = arrayDate[5].equals("") ? 0 : Integer.parseInt(arrayDate[5]);
        if (sec < 0 || sec > 59) {
            throw new NotFindDateFormat("Illegal format for seconds");
        }
        return sec;
    }

    private static int readMinute(String[] arrayDate) throws NotFindDateFormat {
        int min = arrayDate[4].equals("") ? 0 : Integer.parseInt(arrayDate[4]);
        if (min < 0 || min > 59) {
            throw new NotFindDateFormat("Illegal format for minutes");
        }
        return min;
    }

    private static int readHour(String[] arrayDate) throws NotFindDateFormat {
        int hour = arrayDate[3].equals("") ? 0 : Integer.parseInt(arrayDate[3]);
        if (hour < 0 || hour > 23) {
            throw new NotFindDateFormat("Illegal format for hours");
        }
        return hour;
    }

    private static int readDay(String[] arrayDate, Date date) throws NotFindDateFormat {
        int day;
        if (date.getTypeInput() == THIRD_TYPE || date.getTypeInput() == SECOND_TYPE) {
            day = arrayDate[1].equals("") ? 1 : Integer.parseInt(arrayDate[1]);
        } else day = arrayDate[0].equals("") ? 1 : Integer.parseInt(arrayDate[0]);
        if (day < 1) throw new NotFindDateFormat("Illegal date");
        if (date.getMonth() == 1) {
            if (date.isLeapYear()) {
                if (day > 29) throw new NotFindDateFormat("In February in leap-year can be only 29 days");
            }
            if (day > 28) throw new NotFindDateFormat("In February can be only 28 days");
        } else if (date.getMonth() == 3 || date.getMonth() == 5 || date.getMonth() == 8 || date.getMonth() == 10) {
            if (day > 30) throw new NotFindDateFormat("In this month can be only 30 days");
        } else {
            if (day > 31) throw new NotFindDateFormat("In this can be only 31 days");
        }
        return day;
    }

    private static int readMonth(String[] arrayDate, Date date) throws NotFindDateFormat {
        int month;
        int dateInput = date.getTypeInput();
        if (dateInput == FIRST_TYPE || dateInput == SECOND_TYPE) {
            if (dateInput == FIRST_TYPE) month = arrayDate[1].equals("") ? 0 : Integer.parseInt(arrayDate[1]) - 1;
            else month = arrayDate[0].equals("") ? 0 : Integer.parseInt(arrayDate[0]) - 1;
            if (month < 0 || month > 11) throw new NotFindDateFormat("Indefinite month");
        } else if (dateInput == THIRD_TYPE) {
            month = arrayDate[0].equals("") ? 0 : getNumber(arrayDate[0].toUpperCase());
        } else month = arrayDate[1].equals("") ? 0 : getNumber(arrayDate[1].toUpperCase());
        return month;
    }

    private static int readYear(String[] arrayDate, Date date) {
        int year = (arrayDate.length < 3 ? 0 : Integer.parseInt(arrayDate[2]));
        if ((year % 100 == 0 && year % 400 == 0) || year % 4 == 0) {
            date.setLeapYear(true);
        }
        return year;
    }

    public static void printDate(Date date, int outputType) {
        switch (outputType) {
            case FIRST_TYPE:
                System.out.printf("%02d/%02d/%02d\n", date.getDay(), date.getMonth() + 1, date.getYear() % 100);
                break;
            case SECOND_TYPE:
                System.out.printf("%d/%d/%04d\n", date.getMonth() + 1, date.getDay(), date.getYear());
                break;
            case THIRD_TYPE:
                System.out.printf("%s %2d %02d\n", Months.values()[date.getMonth()].toString(), date.getDay(), date.getYear() % 100);
                break;
            case FORTH_TYPE:
                System.out.printf("%02d %s %04d %02d:%02d\n", date.getDay(), Months.values()[date.getMonth()].toString(),
                        date.getYear(), date.getHour(), date.getMin());
                break;
        }
    }
}
