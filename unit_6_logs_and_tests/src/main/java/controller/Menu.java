package controller;

import static controller.Console.getInt;
import static controller.Console.printMessage;

public class Menu {

    public void libraryMenu() {
        Actions action = new Actions();
        printMessage("==Welcome in Library!==");
        printMessage("=======================");
        while (true) {
            printMessage("--------------------------------------------------------");
            printMessage("We have some actions in Library. What do you want to do?");
            printMessage("1 - add a new book;");
            printMessage("2 - remove the book;");
            printMessage("3 - list of all books in Library;");
            printMessage("4 - list of all authors in Library;");
            printMessage("5 - to correct information about the book;");
            printMessage("6 - find a book by title;");
            printMessage("7 - find books by author;");
            printMessage("8 - find books by genre;");
            printMessage("0 - exit from Library.");
            printMessage("--------------------------------------------------------");

            int input = getInt();
            switch (input) {
                case 1:
                    action.addNewBook();
                    break;
                case 2:
                    action.removeBook();
                    break;
                case 3:
                    action.takeListAllBooks();
                    break;
                case 4:
                    action.takeListAllAuthors();
                    break;
                case 5:
                    action.changeInfoAboutBook();
                    break;
                case 6:
                    action.findBook();
                    break;
                case 7:
                    action.findAuthor();
                    break;
                case 8:
                    action.findGenre();
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
