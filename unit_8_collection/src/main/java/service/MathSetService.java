package service;

import entity.MathSet;

import static console.Console.*;

public class MathSetService {

    private static final String STRING_SEPARATOR = "===================================";
    private static MathSet mathSet;

    public void create() {
        printMessage(STRING_SEPARATOR);
        printMessage("What kind of MathSet do you want create?");
        printMessage("1 - MathSet();");
        printMessage("2 - MathSet(int capacity);");
        printMessage("3 - MathSet(Number[] numbers);");
        printMessage("4 - MathSet(Number[] ... numbers).");
        printMessage(STRING_SEPARATOR);

        int choice = getInt();
        switch (choice) {
            case 1:
                mathSet = new MathSet();
                break;
            case 2:
                printMessage("Please, enter the capacity of:");
                int size = getInt();
                mathSet = new MathSet(size);
                break;
            case 3:
                printMessage("Please, enter numbers with space:");
                String numString = getString();
                Number[] numbers = getArrayFromString(numString);
                mathSet = new MathSet(numbers);
                break;
            case 4:
                Number[][] arrayOfNumbers = getArrayOfNumbers();
                mathSet = new MathSet(arrayOfNumbers);
                break;
            default:
                printMessage("Sorry, we don't have as this structure.");
        }
        mathSet.printMathSet();
    }

    public void add() {
    }

    private Number[][] getArrayOfNumbers() {
        Number[][] array = new Number[10][];
        int index = 0;
        printMessage("Please, input numbers with space and rows with 'enter':");
        printMessage("If you want to stop inputting, please enter - 'exit'");
        while (true) {
            String numString = getString();
            if (numString.equals("exit")) break;
            if (index >= array.length) array = changeArraySize(array);
            array[index] = getArrayFromString(numString);
            index++;
        }
        return array;
    }

    private Number[][] changeArraySize(Number[][] array) {
        int newSize = (int) ((array.length * 1.5) + 1);
        Number[][] newArray = new Number[newSize][];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                newArray[i][j] = array[i][j];
            }
        }
        return newArray;
    }

    private Number[] getArrayFromString(String numString) {
        boolean isDouble = false;
        String[] temp = numString.split(" ");
        Number[] numbers = new Number[temp.length];
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].matches("\\-?\\d+\\.\\d+")) {
                isDouble = true;
                break;
            }
        }
        for (int i = 0; i < temp.length; i++) {
            if (isDouble) {
                if (temp[i].matches("\\-?\\d+\\.?\\d*"))
                    numbers[i] = Double.parseDouble(temp[i]);
            } else if (temp[i].matches("\\-?\\d+")) numbers[i] = Integer.parseInt(temp[i]);
        }
        return numbers;
    }

    public void delete() {
    }

    public void sort() {
    }

    private static boolean isPositiveAnswer() {
        String answer = getString();
        if (answer.trim().equals("Y") || answer.trim().equals("y"))
            return true;
        else if (answer.trim().equals("N") || answer.trim().equals("n"))
            return false;
        else {
            printMessage("Please, enter correct answer.");
            return isPositiveAnswer();
        }
    }
}
