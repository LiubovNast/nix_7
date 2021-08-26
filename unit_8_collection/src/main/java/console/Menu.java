package console;

import static console.Console.getInt;
import static console.Console.printMessage;

public class Menu {

    private static final String STRING_SEPARATOR = "--------------------------------------------------------";

    public void mathSetMenu() {
        printMessage("===MathSet<Number>===");
        printMessage("=====================");
        while (true) {
            printMessage(STRING_SEPARATOR);

            printMessage(STRING_SEPARATOR);
            printMessage("What you want to do ?");
            printMessage("1 - ;");
            printMessage("2 - ;");
            printMessage("3 - ;");
            printMessage("4 - ;");
            printMessage("0 - .");
            printMessage(STRING_SEPARATOR);

            int input = getInt();
            switch (input) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

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
