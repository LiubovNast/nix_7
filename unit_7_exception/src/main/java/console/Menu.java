package console;

import static console.Console.*;
import static service.MenuService.*;

public class Menu {

    public void calendarMenu() {
        printMessage("==Calendar Application==");
        printMessage("========================");
        while (true) {
            printMessage("--------------------------------------------------------");
            printMessage("Do you want to change input format? (Y,N)");
            if (isPositiveAnswer()) {
                changeInputtingFormat();
            }
            printMessage("Do you want to change output format? (Y,N)");
            if (isPositiveAnswer()) {
                changeOutputtingFormat();
            }

            printMessage("--------------------------------------------------------");
            printMessage("What you want to do with date?");
            printMessage("1 - find the difference between dates;");
            printMessage("2 - add to date;");
            printMessage("2 - subtract from date;");
            printMessage("4 - compare list of dates by;");
            printMessage("0 - exit from Calendar Application.");
            printMessage("--------------------------------------------------------");

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

    private boolean isPositiveAnswer() {
        String answer = getString();
        if (answer.trim().equals("Y") || answer.trim().equals("y"))
            return true;
        else if (answer.trim().equals("N") || answer.trim().equals("n"))
            return false;
        else {
            printMessage("Please, enter correct answer.");
            isPositiveAnswer();
        }
        return false;
    }
}
