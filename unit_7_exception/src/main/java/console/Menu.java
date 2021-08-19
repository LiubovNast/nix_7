package console;

import static console.Console.getInt;
import static console.Console.printMessage;

/*
  находить разницу между датами в миллисекундах, секундах, минутах, часах, днях и годах;
• прибавлять к дате миллисекунды, секунды, минуты, часы, дни и года;
• вычитать из даты миллисекунды, секунды, минуты, часы, дни и года;
• сравнивать перечень дат по убыванию и возрастанию;


 */

public class Menu {

    public void calendarMenu() {

        printMessage("==Calendar Application==");
        printMessage("=======================");
        while (true) {
            printMessage("--------------------------------------------------------");
            printMessage("What you want to do with date?");
            printMessage("1 - change format inputting date;");
            printMessage("2 - change format outputting date;");
            printMessage("3 - find the odds between dates;");
            printMessage("4 - add to date;");
            printMessage("5 - subtract from date;");
            printMessage("6 - compare list of dates by;");
            printMessage("0 - exit from Calendar Application.");
            printMessage("--------------------------------------------------------");

            int input = getInt();
            switch (input) {
                case 1:
                    printMessage("1 - change format inputting date;");
                    break;
                case 2:
                    printMessage("2 - change format outputting date;");
                    break;
                case 3:
                    printMessage("3 - find the odds between dates;");
                    break;
                case 4:
                    printMessage("4 - add to date;");
                    break;
                case 5:
                    printMessage("5 - subtract from date;");
                    break;
                case 6:
                    printMessage("6 - compare list of dates by;");
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
