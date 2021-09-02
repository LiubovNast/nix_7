package date_formatter;

import calendar.Date;
import exception.NotFindDateFormat;

public class FormatDate {

    private static final int FIRST_TYPE = 1;
    private static final int SECOND_TYPE = 2;
    private static final int THIRD_TYPE = 3;
    private static final int FORTH_TYPE = 4;

    public static String[] checkCorrectInputAndGetArrayFromString(String inputDate, int typeInput) {
        switch (typeInput) {
            case FIRST_TYPE:
                if (inputDate.matches("[0-3]?[0-9]?/[0-1]?[0-9]?/[0-9]{0,2}"))
                    return inputDate.trim().split("/");
                break;
            case SECOND_TYPE:
                if (inputDate.matches("[1]?[0-9]?/[1-3]?[0-9]?/[0-9]{0,4}"))
                    return inputDate.trim().split("/");
                break;
            case THIRD_TYPE:
                if (inputDate.matches("[A-Z]{3,10}-[1-3]?[0-9]?-[0-9]{0,2}"))
                    return inputDate.trim().split("-");
                break;
            case FORTH_TYPE:
                if (inputDate.matches("[0-3]?[0-9]?-\\w{3,10}-\\d{0,4}\\s[0-2]?[0-9]?:[0-2]?[0-9]?:?[0-2]?[0-9]?:?[0-9]{0,3}"))
                    return getArrayWithHoursAndMinutes(inputDate.trim().split("-"));
                else if (inputDate.matches("\\d{0,4}\\s[0-2]?[0-9]?:[0-2]?[0-9]?:?[0-2]?[0-9]?:?[0-9]{0,3}"))
                    return getArrayWithoutDaysAndMonths(inputDate.trim().split(" "));
                break;
        }
        return null;
    }

    private static String[] getArrayWithoutDaysAndMonths(String[] arrayDate) {
        String[] allDate;
        String[] hoursAndMin = arrayDate[1].split(":");
        if (hoursAndMin.length == 3) {
            allDate = new String[6];
        } else if (hoursAndMin.length == 4) {
            allDate = new String[7];
        } else allDate = new String[5];
        allDate[0] = "1";
        allDate[1] = "JANUARY";
        allDate[2] = arrayDate[0];
        allDate[3] = hoursAndMin[0];
        allDate[4] = hoursAndMin[1];
        if (allDate.length >= 6) allDate[5] = hoursAndMin[2];
        if (allDate.length == 7) allDate[6] = hoursAndMin[3];
        return allDate;
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
