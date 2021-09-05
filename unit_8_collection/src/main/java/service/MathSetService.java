package service;

import entity.MathSet;

import java.util.Arrays;

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
        printMessage("6 - intersection many MathSets with your.");
        printMessage(STRING_SEPARATOR);

        if (mathSet == null) mathSet = new MathSet();
        MathSet current = mathSet;
        MathSet[] arrayOfMathSets;
        String numString;
        Number value;
        int choice = getInt();

        switch (choice) {
            case 1:
                printMessage("Please, enter number for adding to MathSet");
                numString = getString();
                value = getNumberFromString(numString);
                if (value != null) {
                    mathSet.add(value);
                }
                break;
            case 2:
                printMessage("Please, enter numbers with space for adding to MathSet");
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
        printMessage("6 - to sort ascending from value.");
        printMessage(STRING_SEPARATOR);

        Number value;
        int fromIndex, toIndex;
        int choice = getInt();
        String numberString;

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
                numberString = getString();
                value = getNumberFromString(numberString);
                if (value != null) {
                    mathSet.sortDesc(value);
                }
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
                value = getNumberFromString(numberString);
                if (value != null) {
                    mathSet.sortAsc(value);
                }
                break;
            default:
                printMessage("Sorry, we don't have this kind of sorting.");
        }

        printMessage("Result of action:");
        mathSet.printMathSet();
    }

    public void get() {
        printMessage(STRING_SEPARATOR);
        printMessage("What you want to get from MathSet?");
        printMessage("1 - to get number with index;");
        printMessage("2 - to get max value from MathSet;");
        printMessage("3 - to get min value from MathSet;");
        printMessage("4 - to get average value from MathSet;");
        printMessage("5 - to get median value from MathSet;");
        printMessage("6 - to get array from MathSet;");
        printMessage("7 - to get array from MathSet with from to indexes.");
        printMessage(STRING_SEPARATOR);

        if (mathSet == null) mathSet = new MathSet();
        Number[] array;
        Number value;
        int choice = getInt();

        switch (choice) {
            case 1:
                printMessage("Please, enter index:");
                int index = getInt();
                value = mathSet.get(index);
                if (value == null) {
                    printMessage("We don't have value with this index.");
                } else {
                    printMessage("This value is: " + value);
                }
                break;
            case 2:
                Number max = mathSet.getMax();
                printMessage("Max value is: " + max);
                break;
            case 3:
                Number min = mathSet.getMin();
                printMessage("Min value is: " + min);
                break;
            case 4:
                Number average = mathSet.getAverage();
                printMessage("Average value is: " + average);
                break;
            case 5:
                Number median = mathSet.getMedian();
                printMessage("Median value is: " + median);
                break;
            case 6:
                array = mathSet.toArray();
                printMessage(Arrays.toString(array));
                break;
            case 7:
                printMessage("Please, enter index from:");
                int fromIndex = getInt();
                printMessage("Please, enter index to:");
                int toIndex = getInt();
                array = mathSet.toArray(fromIndex, toIndex);
                printMessage(Arrays.toString(array));
                break;
            default:
                printMessage("Sorry, we don't have this action.");
        }
    }

    public void delete() {
        printMessage(STRING_SEPARATOR);
        printMessage("How do you want clear this MathSet?");
        printMessage("1 - clear all from MathSet();");
        printMessage("2 - clear some numbers from MathSet;");
        printMessage("3 - cut indexes from MathSet from to;");
        printMessage(STRING_SEPARATOR);

        int choice = getInt();
        switch (choice) {
            case 1:
                mathSet.clear();
                break;
            case 2:
                printMessage("Please, enter numbers with space:");
                String string = getString();
                Number[] numbers = getArrayFromString(string);
                mathSet.clear(numbers);
                break;
            case 3:
                printMessage("Please, enter index from:");
                int fromIndex = getInt();
                printMessage("Please, enter index to:");
                int toIndex = getInt();
                mathSet.cut(fromIndex, toIndex);
                break;
            default:
                printMessage("Sorry, we don't have this kind of deleting.");
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

    private Number getNumberFromString(String string) {
        Number value = null;
        if (string.matches("\\-?\\d+\\.\\d+")) {
            value = Double.parseDouble(string);
        } else if (string.matches("\\-?\\d+")) {
            value = Integer.parseInt(string);
        } else printMessage("You enter invalid number: '" + string + "', we cannot add it to MathSet.");
        return value;
    }

    private Number[] getArrayFromString(String numString) {
        String[] temp = numString.split(" ");
        Number[] numbers = new Number[temp.length];
        Number value;
        for (int i = 0; i < temp.length; i++) {
            value = getNumberFromString(temp[i]);
            if (value != null) {
                numbers[i] = value;
            }
        }
        return numbers;
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
