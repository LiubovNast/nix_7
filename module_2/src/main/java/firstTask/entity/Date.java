package firstTask.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Date {

    private int year;
    private int month;
    private int day;
    private boolean isLeapYear;
}
