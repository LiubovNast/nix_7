package date_formatter;

import calendar.Date;
import exception.NotFindDateFormat;

public class FormatDate {

    public static void outputDateFormat(String format, Date date) throws NotFindDateFormat {
        if (format.matches("[d]{2}/[m]{2}/[y]{2}")) {
            date.setTypeOutput(1);
        } else if (format.matches("[m]/[d]/[y]{4}")) {
            date.setTypeOutput(2);
        } else if (format.matches("[m]{3}-[d]-[y]{2}")) {
            date.setTypeOutput(3);
        } else if (format.matches("[d]{2}-[m]{3}-[y]{4} [0]{2}:[0]{2}")) {
            date.setTypeOutput(4);
        } else throw new NotFindDateFormat("Indefinite format");
    }

    public static void inputDateFormat(String format, Date date) throws NotFindDateFormat {
        if (format.matches("[d]{2}/[m]{2}/[y]{2}")) {
            date.setTypeInput(1);
        } else if (format.matches("[m]/[d]/[y]{4}")) {
            date.setTypeInput(2);
        } else if (format.matches("[m]{3}-[d]-[y]{2}")) {
            date.setTypeInput(3);
        } else if (format.matches("[d]{2}-[m]{3}-[y]{4} [0]{2}:[0]{2}")) {
            date.setTypeInput(4);
        } else throw new NotFindDateFormat("Indefinite format");
    }

    public static String[] checkCorrectInputAndGetArrayFromString(String inputDate, Date date) {
        switch (date.getTypeInput()) {
            case 1:
                if (inputDate.matches("[0-3]?[0-9]?/[0-1]?[0-9]?/[0-9]{0,2}"))
                    return inputDate.trim().split("/");
                break;
            case 2:
                if (inputDate.matches("[1]?[0-9]?/[1-3]?[0-9]?/[0-9]{0,4}"))
                    return inputDate.trim().split("/");
                break;
            case 3:
                if (inputDate.matches("[A-Z]{3,10}-[1-3]?[0-9]?-[0-9]{0,2}"))
                    return inputDate.trim().split("-");
                break;
            case 4:
                if (inputDate.matches("[0-3]?[0-9]?-\\w{3,10}-\\d{0,4}\\s[0-2]?[0-9]?:[0-2]?[0-9]?:?[0-2]?[0-9]?:?[0-9]{0,3}"))
                    return getArrayWithHoursAndMinutes(inputDate.trim().split("-"));
                break;
        }
        return null;
    }

    private static String[] getArrayWithHoursAndMinutes(String[] arrayDate) {
        String[] times = arrayDate[arrayDate.length - 1].split(" ");
        String[] hoursAndMin = times[1].split(":");
        String[] allDate;
        if (hoursAndMin.length == 3) {
            allDate = new String[6];
        } else if (hoursAndMin.length == 4) {
            allDate = new String[7];
        } else allDate = new String[5];
        allDate[0] = arrayDate[0];
        allDate[1] = arrayDate[1];
        allDate[2] = times[0];
        allDate[3] = hoursAndMin[0];
        allDate[4] = hoursAndMin[1];
        if (allDate.length >= 6) allDate[5] = hoursAndMin[2];
        if (allDate.length == 7) allDate[6] = hoursAndMin[3];
        return allDate;
    }
}
