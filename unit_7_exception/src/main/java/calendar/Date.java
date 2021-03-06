package calendar;

public class Date {

    private int ms;
    private int sec;
    private int min;
    private int hour;
    private int day;
    private int month;
    private int year;
    private int typeOutput;
    private int typeInput;
    private boolean leapYear;
    private static final int DATE_TYPE = 1;
    private static final int START_OF_CALENDAR = 0;
    private static final int FIRST_DATE = 1;

    public Date() {
        this.ms = START_OF_CALENDAR;
        this.sec = START_OF_CALENDAR;
        this.min = START_OF_CALENDAR;
        this.hour = START_OF_CALENDAR;
        this.day = FIRST_DATE;
        this.month = START_OF_CALENDAR;
        this.year = START_OF_CALENDAR;
        this.typeInput = DATE_TYPE;
        this.typeOutput = DATE_TYPE;
        this.leapYear = false;
    }

    public boolean isLeapYear() {
        return leapYear;
    }

    public void setLeapYear(boolean leapYear) {
        this.leapYear = leapYear;
    }

    public int getMs() {
        return ms;
    }

    public void setMs(int ms) {
        this.ms = ms;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTypeOutput() {
        return typeOutput;
    }

    public void setTypeOutput(int typeOutput) {
        this.typeOutput = typeOutput;
    }

    public int getTypeInput() {
        return typeInput;
    }

    public void setTypeInput(int typeInput) {
        this.typeInput = typeInput;
    }
}
