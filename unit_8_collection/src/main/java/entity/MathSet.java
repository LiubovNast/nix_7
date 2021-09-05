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
            if (!isEmpty() && mathSet[i] != null) {
                if (numberComporator((Num) n, (Num) mathSet[i]) == 0) {
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

    public void add(Number... n) {
        for (int i = 0; i < n.length; i++) {
            if (n[i] != null) add(n[i]);
        }
    }

    public void join(MathSet<Num> ms) {
        Number[] first = this.getMathSet();
        first = getArrayWithoutNull(first);
        Number[] second = ms.getMathSet();
        second = getArrayWithoutNull(second);
        mathSet = new MathSet(first.length + second.length).getMathSet();
        add(first);
        add(second);
    }

    public void join(MathSet<Num>... ms) {
        for (int i = 0; i < ms.length; i++) {
            if (ms[i] != null) join(ms[i]);
        }
    }

    public void intersection(MathSet ms) {
        Number[] first = this.getMathSet();
        first = getArrayWithoutNull(first);
        Number[] second = ms.getMathSet();
        second = getArrayWithoutNull(second);
        int newSize = first.length < second.length ? first.length : second.length;
        mathSet = new MathSet(newSize).getMathSet();
        int k = 0;
        for (int j = 0; j < second.length; j++) {
            if (second[j] != null) {
                for (int i = 0; i < first.length; i++) {
                    if (first[i] != null) {
                        if (numberComporator((Num) second[j], (Num) first[i]) == 0) {
                            mathSet[k] = second[j];
                            k++;
                        }
                    }
                }
            }
        }
    }

    public void intersection(MathSet... ms) {
        for (int i = 0; i < ms.length; i++) {
            if (ms[i] != null) intersection(ms[i]);
        }
    }

    public void sortDesc() {
        insertionSortDesc((Num[]) mathSet);
    }

    public void sortDesc(int firstIndex, int lastIndex) {

    }

    public void sortDesc(Number value) {
    }

    public void sortAsc() {
        insertionSortAsc((Num[]) mathSet);
    }

    public void sortAsc(int firstIndex, int lastIndex) {
    }

    public void sortAsc(Number value) {
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

    private void insertionSortDesc(Num[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] == null) continue;
            Num temp = array[i];
            int j = i;
            while (true) {
                int compare = 0;
                if (j <= 0) break;
                compare = numberComporator(array[j - 1], temp);
                if (compare < 0) {
                    array[j] = array[j - 1];
                } else break;
                j--;
            }
            array[j] = temp;
        }
    }

    private void insertionSortAsc(Num[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] == null) continue;
            Num temp = array[i];
            int j = i;
            while (true) {
                int compare = 0;
                if (j <= 0) break;
                compare = numberComporator(array[j - 1], temp);
                if (compare >= 0) {
                    array[j] = array[j - 1];
                } else break;
                j--;
            }
            array[j] = temp;
        }
    }

    private int numberComporator(Num first, Num second) {
        int compare = 0;
        if (first.getClass() != second.getClass()) {
            double f = first.doubleValue();
            double s = second.doubleValue();
            if (f < s) compare = -1;
            else if (f > s) compare = 1;
        } else {
            compare = first.compareTo(second);
        }
        return compare;
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

    private Number[] getArrayWithoutNull(Number[] array) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) count++;
        }
        if (count != 0) {
            Number[] temp = array;
            array = new Number[count];
            int j = 0;
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] != null) {
                    array[j] = temp[i];
                    j++;
                }
            }
        } else array = new Number[1];
        return array;
    }
}
