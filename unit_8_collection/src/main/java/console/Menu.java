package console;

import service.MathSetService;

import static console.Console.getInt;
import static console.Console.printMessage;

public class Menu {

    private static final String STRING_SEPARATOR = "---------------------------------------------";

    public void mathSetMenu() {
        MathSetService service = new MathSetService();
        printMessage("===MathSet<Number>===");
        printMessage("=====================");
        while (true) {
            printMessage(STRING_SEPARATOR);
            printMessage("What you want to do?");
            printMessage("1 - create new MathSet;");
            printMessage("2 - add into MathSet;");
            printMessage("3 - delete from MathSet;");
            printMessage("4 - sort MathSet;");
            printMessage("0 - exit from app.");
            printMessage(STRING_SEPARATOR);

            int input = getInt();
            switch (input) {
                case 1:
                    service.create();
                    break;
                case 2:
                    service.add();
                    break;
                case 3:
                    service.delete();
                    break;
                case 4:
                    service.sort();
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
