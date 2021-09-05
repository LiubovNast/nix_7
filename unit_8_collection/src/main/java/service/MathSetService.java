package service;

import entity.MathSet;

import static console.Console.*;

public class MathSetService {

    private static final String STRING_SEPARATOR = "===================================";
    private static MathSet mathSet;
    private static final int CAPACITY = 10;

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
        printMessage(STRING_SEPARATOR);
        printMessage("What you want to do with MathSet?");
        printMessage("1 - to add number;");
        printMessage("2 - to add array;");
        printMessage("3 - to join MathSet to your;");
        printMessage("4 - to join many MathSets to your;");
        printMessage("5 - intersection MathSet with your;");
        printMessage("6 - intersection many MathSets with your;");
        printMessage(STRING_SEPARATOR);

        if (mathSet == null) mathSet = new MathSet();
        MathSet current = mathSet;
        MathSet[] arrayOfMathSets;
        int choice = getInt();

        switch (choice) {
            case 1:
            case 2:
                printMessage("Please, enter number/numbers with space for adding to MathSet");
                String num = getString();
                Number[] numbers = getArrayFromString(num);
                mathSet.add(numbers);
                break;
            case 3:
                printMessage("Please, choose and input MathSet, which you want to join:");
                create();
                current.join(mathSet);
                mathSet = current;
                break;
            case 4:
                printMessage("Please, input MathSets, which you want to join:");
                arrayOfMathSets = getArrayOfMathSets();
                current.join(arrayOfMathSets);
                mathSet = current;
                break;
            case 5:
                printMessage("Please, choose and input MathSet, which you want intersection with:");
                create();
                current.intersection(mathSet);
                mathSet = current;
                break;
            case 6:
                printMessage("Please, input MathSets, which you want intersection with:");
                arrayOfMathSets = getArrayOfMathSets();
                current.intersection(arrayOfMathSets);
                mathSet = current;
                break;
            default:
                printMessage("Sorry, we don't have this action.");
        }
        printMessage("Result of action:");
        mathSet.printMathSet();
    }

    public void sort() {
        printMessage(STRING_SEPARATOR);
        printMessage("What you want to do with MathSet?");
        printMessage("1 - to sort descending;");
        printMessage("2 - to sort descending with index from to;");
        printMessage("3 - to sort descending from value;");
        printMessage("4 - to sort ascending;");
        printMessage("5 - to sort ascending with index from to;");
        printMessage("6 - to sort ascending from value;");
        printMessage(STRING_SEPARATOR);

        Number value = null;
        int fromIndex, toIndex;
        int choice = getInt();

        switch (choice) {
            case 1:
                mathSet.sortDesc();
                break;
            case 2:
                printMessage("Please, enter index from:");
                fromIndex = getInt();
                printMessage("Please, enter index to:");
                toIndex = getInt();
                mathSet.sortDesc(fromIndex, toIndex);
                break;
            case 3:
                printMessage("Please, enter value from:");
                String numberString = getString();
                if (numberString.matches("\\-?\\d+\\.\\d+"))
                    value = Double.parseDouble(numberString);
                else if (numberString.matches("\\-?\\d+")) value = Integer.parseInt(numberString);
                mathSet.sortDesc(value);
                break;
            case 4:
                mathSet.sortAsc();
                break;
            case 5:
                printMessage("Please, enter index from:");
                fromIndex = getInt();
                printMessage("Please, enter index to:");
                toIndex = getInt();
                mathSet.sortAsc(fromIndex, toIndex);
                break;
            case 6:
                printMessage("Please, enter value from:");
                numberString = getString();
                if (numberString.matches("\\-?\\d+\\.\\d+"))
                    value = Double.parseDouble(numberString);
                else if (numberString.matches("\\-?\\d+")) value = Integer.parseInt(numberString);
                mathSet.sortAsc(value);
                break;
            default:
                printMessage("Sorry, we don't have this kind of sorting.");
        }

        printMessage("Result of action:");
        mathSet.printMathSet();
    }

    private Number[][] getArrayOfNumbers() {
        Number[][] array = new Number[CAPACITY][];
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

    private MathSet[] getArrayOfMathSets() {
        MathSet[] sets = new MathSet[CAPACITY];
        int index = 0;
        while (true) {
            printMessage("Please, choose and add MathSet:");
            if (index >= sets.length) sets = changeArraySize(sets);
            create();
            sets[index] = mathSet;
            index++;
            printMessage("Do you want to stop create MathSets? (Y/N)");
            if (isPositiveAnswer()) break;
        }
        return sets;
    }

    private MathSet[] changeArraySize(MathSet[] sets) {
        int newSize = (int) ((sets.length * 1.5) + 1);
        MathSet[] newArray = new MathSet[newSize];
        for (int i = 0; i < sets.length; i++) {
            newArray[i] = sets[i];
        }
        return newArray;
    }
}
