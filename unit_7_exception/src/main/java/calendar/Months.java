package calendar;

import exception.NotFindDateFormat;

public enum Months {

    JANUARY,
    FEBRUARY,
    MARCH,
    APRIL,
    MAY,
    JUNE,
    JULY,
    AUGUST,
    SEPTEMBER,
    OCTOBER,
    NOVEMBER,
    DECEMBER;

    public static int getNumber(String month) throws NotFindDateFormat {
        for (int i = 0; i < Months.values().length; i++) {
            if (Months.values()[i] == Months.valueOf(month))
                return i;
        }
        throw new NotFindDateFormat("Illegal month");
    }
}
