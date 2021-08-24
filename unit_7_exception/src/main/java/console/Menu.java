package console;

import static console.Console.*;
import static service.MenuService.*;

public class Menu {

    private static final String STRING_SEPARATOR = "--------------------------------------------------------";

    public void calendarMenu() {
        printMessage("==Calendar Application==");
        printMessage("========================");
        while (true) {
            printMessage(STRING_SEPARATOR);
            changeFormat();
            printMessage(STRING_SEPARATOR);
            printMessage("What you want to do with date?");
            printMessage("1 - find the difference between dates;");
            printMessage("2 - add to date;");
            printMessage("3 - subtract from date;");
            printMessage("4 - compare list of dates by;");
            printMessage("0 - exit from Calendar Application.");
            printMessage(STRING_SEPARATOR);

            int input = getInt();
            switch (input) {
                case 1:
                    findDifferenceBetweenTwoDates();
                    break;
                case 2:
                    addingToDate();
                    break;
                case 3:
                    subtractingFromDate();
                    break;
                case 4:
                    compareListOFDates();
                    break;
                case 0:
                    printMessage("Good bye!");
                    break;
                default:
                    printMessage("We don't have this action. Please, enter correct number.");
                    break;
            }
            if (input == 0) break;
        }
    }
}
