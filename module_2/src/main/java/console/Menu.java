package console;

import firstTask.Dates;
import secondTask.UniqueName;
import thirdTask.TheCheapestRoad;

import static console.Console.getInt;
import static console.Console.printMessage;

public class Menu {

    private static final String STRING_SEPARATOR = "---------------------------------------------";

    public void moduleMenu() {
        printMessage("======== Module #2 ========");
        printMessage("===========================");
        while (true) {
            printMessage(STRING_SEPARATOR);
            printMessage("This module consist with 3 tasks.");
            printMessage("Choose number of task to start it.");
            printMessage("If you want to start task about list of Dates, please, enter - 1;");
            printMessage("If you want to start task about unique name, please, enter - 2;");
            printMessage("If you want to start task about the cheapest road, please, enter - 3;");
            printMessage("0 - exit from app.");
            printMessage(STRING_SEPARATOR);

            int input = getInt();
            switch (input) {
                case 1:
                    Dates dates = new Dates();
                    dates.start();
                    break;
                case 2:
                    UniqueName uniqueName = new UniqueName();
                    uniqueName.start();
                    break;
                case 3:
                    TheCheapestRoad theCheapestRoad = new TheCheapestRoad();
                    theCheapestRoad.start();
                    break;
                case 0:
                    printMessage("Good bye!");
                    break;
                default:
                    printMessage("We don't have this number of task. Please, enter correct number.");
                    break;
            }
            if (input == 0) break;
        }
    }
}
