package entity;

import java.util.Arrays;
import java.lang.Number;

import static console.Console.printMessage;

public class MathSet<Num extends Number & Comparable<Num>> {

    private static final int CAPACITY = 10;
    private int size;
    private Number[] mathSet;

    public Number[] getMathSet() {
        return mathSet;
    }

    public MathSet() {
        this.size = CAPACITY;
        mathSet = new Number[CAPACITY];
    }

    public MathSet(int size) {
        this.size = size;
        mathSet = new Number[size];
    }

    public MathSet(Number[] numbers) {
        this.size = numbers.length;
        mathSet = new Number[size];
        add(numbers);
    }

    public MathSet(Number[]... numbers) {
        Number[] arrayNumbers = getArray(numbers);
        mathSet = new MathSet(arrayNumbers).getMathSet();
    }

    public MathSet(MathSet<Num> numbers) {
        this.size = numbers.getSize();
        mathSet = numbers.getMathSet();
    }

    public MathSet(MathSet<Num>... numbers) {
        int curentSize = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == null) continue;
            curentSize += numbers[i].getSize();
        }
        this.size = curentSize;
        mathSet = new MathSet<Num>(curentSize).getMathSet();
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == null) continue;
            Number[] set = numbers[i].getMathSet();
            for (int j = 0; j < set.length; j++) {
                add(set[j]);
            }
        }
    }

    public int getSize() {
        return mathSet.length;
    }

    void add(Number n) {
        boolean isUnique = true;
        for (int i = 0; i < mathSet.length; i++) {
            if (mathSet[i] != null) {
                if (n.equals(mathSet[i])) {
                    mathSet[i] = n;
                    isUnique = false;
                    break;
                }
            }
        }
        if (isUnique) {
            int index = getNextIndex();
            if (getNextIndex() == mathSet.length) {
                mathSet = changeSetSize();
            }
            mathSet[index] = n;
        }
    }

    void add(Number... n) {
        for (int i = 0; i < n.length; i++) {
            add(n[i]);
        }
    }

    void join(MathSet<Num> ms) {
        Number[] numbers = getMathSet();
        for (int i = 0; i < numbers.length; i++) {
            add(numbers[i]);
        }
    }

    void join(MathSet<Num>... ms) {
        for (int i = 0; i < ms.length; i++) {
            join(ms[i]);
        }
    }

    void intersection(MathSet ms) {

    }

    void intersection(MathSet... ms) {

    }

    void sortDesc() {
        ininsertionSortDesc((Num[]) mathSet);
    }

    void sortDesc(int firstIndex, int lastIndex) {

    }

    void sortDesc(Number value) {
    }

    void sortAsc() {
        insertionSortAsc((Num[]) mathSet);
    }

    void sortAsc(int firstIndex, int lastIndex) {
    }

    void sortAsc(Number value) {
    }

    Number get(int index) {
        return mathSet[index];
    }

    Number getMax() {
        return null;
    }

    Number getMin() {
        return null;
    }

    Number getAverage() {
        return null;
    }

    Number getMedian() {
        return null;
    }

    Number[] toArray() {
        return getMathSet();
    }

    Number[] toArray(int firstIndex, int lastIndex) {
        Number[] array = new Number[lastIndex - firstIndex + 1];
        int j = 0;
        for (int i = firstIndex; i <= lastIndex; i++) {
            array[j] = mathSet[i];
            j++;
        }
        return array;
    }

    void clear() {
        for (int i = 0; i < mathSet.length; i++) {
            if (mathSet != null)
            mathSet[i] = null;
        }
    }

    void clear(int firstIndex, int lastIndex) {
        for (int i = firstIndex; i <= lastIndex; i++) {
            if (mathSet != null)
                mathSet[i] = null;
        }
    }

    void clear(Number[] numbers) {

    }

    public void printMathSet() {
        String result = "Your new MathSet is ";
        String set = "{";
        for (int i = 0; i < mathSet.length; i++) {
            if (mathSet[i] != null) {
                set += mathSet[i] + " ";
            }
        }
        result += set.trim().replaceAll(" ", ", ") + "}";
        printMessage(result);
    }

    private Number[] getArray(Number[]... numbers) {
        int curentSize = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] != null)
                curentSize += numbers[i].length;
        }
        Number[] temp = new Number[curentSize];
        int indexSet = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == null) continue;
            Number[] set = numbers[i];
            for (int j = 0; j < set.length; j++) {
                temp[indexSet + j] = set[j];
            }
            indexSet += set.length;
        }
        return temp;
    }

    private void ininsertionSortDesc(Num[] array) {
        for (int i = 1; i < array.length; i++) {
            Num temp = array[i];
            int j = i;
            while (j > 0 && array[j - 1].compareTo(temp) < 0) {
                array[j] = array[j - 1];
                --j;
            }
            array[j] = temp;
        }
    }

    private void insertionSortAsc(Num[] array) {
        for (int i = 1; i < array.length; i++) {
            Num temp = array[i];
            int j = i;
            while (j > 0 && array[j - 1].compareTo(temp) >= 0) {
                array[j] = array[j - 1];
                --j;
            }
            array[j] = temp;
        }
    }

    private Number[] changeSetSize() {
        int newSize = (int) ((mathSet.length * 1.5) + 1);
        Number[] newArray = new Number[newSize];
        for (int i = 0; i < mathSet.length; i++) {
            newArray[i] = mathSet[i];
        }
        return newArray;
    }

    private int getNextIndex() {
        for (int i = 0; i < mathSet.length; i++) {
            if (mathSet[i] == null) return i;
        }
        return mathSet.length;
    }

    private boolean isEmpty() {
        for (int i = 0; i < mathSet.length; i++) {
            if (mathSet[i] != null) return false;
        }
        return true;
    }
}
