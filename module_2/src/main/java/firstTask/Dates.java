package firstTask;

import firstTask.entity.Date;
import firstTask.service.DateFormat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static console.Console.*;

public class Dates {

    private static final String FILE = "files/dates.txt";
    private static final String STRING_SEPARATOR = "---------------------------------------------";
    private String dates;
    private String[] arrayDates;

    public void start() {
        printMessage("======== Task #1 ========");
        printMessage("=========================");
        printMessage(STRING_SEPARATOR);
        printMessage("This task about converting dates in simple format of type \"yyyymmdd\".");
        printMessage("Do you want get dates from file: dates.txt? Please, enter - 1.");
        printMessage("Do you want enter dates in console?, Please, enter - 2.");
        printMessage(STRING_SEPARATOR);

        int input = getInt();
        switch (input) {
            case 1:
                try {
                    dates = Files.readString(Paths.get(FILE));
                    arrayDates = dates.split("\n");
                } catch (IOException e) {
                    printMessage(e.getMessage());
                }
                convertAndPrintDate(arrayDates);
                break;
            case 2:
                printMessage("Please, enter dates with space:");
                dates = getString();
                arrayDates = dates.split(" ");
                convertAndPrintDate(arrayDates);
                break;
            default:
                printMessage("Please, enter correct number");
                start();
                break;
        }
    }

    private void convertAndPrintDate(String[] dates) {
        for (String date : dates) {
            if (DateFormat.checkDateFormat(date.trim())) {
                Date correctDate = DateFormat.getDate(date.trim());
                if (correctDate != null) {
                    System.out.printf("%d%02d%02d\n", correctDate.getYear(), correctDate.getMonth(), correctDate.getDay());
                }
            }
        }
    }
}
