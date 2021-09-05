package entity;

import java.lang.Number;

import static console.Console.printMessage;

public class MathSet<Num extends Number & Comparable<Num>> {

    private static final int CAPACITY = 10;
    private int size;
    private Number[] mathSet;

    private Number[] getMathSet() {
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
        getArrayWithoutNull(mathSet);
        return mathSet.length;
    }

    void add(Number n) {
        boolean isUnique = true;
        for (int i = 0; i < mathSet.length; i++) {
            if (!isEmpty() && mathSet[i] != null) {
                if (numberComparator((Num) n, (Num) mathSet[i]) == 0) {
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
                        if (numberComparator((Num) second[j], (Num) first[i]) == 0) {
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
        if (mathSet == null) return;
        insertionSortDesc((Num[]) mathSet);
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        if (firstIndex > lastIndex) {
            int temp = firstIndex;
            firstIndex = lastIndex;
            lastIndex = temp;
        }
        if (isCorrectIndexes(firstIndex, lastIndex)) {
            if (firstIndex == lastIndex) {
                return;
            }
            Number[][] arrayOfSets = splitArray(firstIndex, lastIndex, mathSet);
            insertionSortDesc((Num[]) arrayOfSets[1]);
            mathSet = new MathSet(arrayOfSets).getMathSet();
        }
    }

    public void sortDesc(Number value) {
        int index = getIndex(value);
        if (index == mathSet.length) {
            printMessage("We don't have this value.");
        } else {
            sortDesc(index, mathSet.length - 1);
        }
    }

    public void sortAsc() {
        if (mathSet == null) return;
        insertionSortAsc((Num[]) mathSet);
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        if (firstIndex > lastIndex) {
            int temp = firstIndex;
            firstIndex = lastIndex;
            lastIndex = temp;
        }
        if (isCorrectIndexes(firstIndex, lastIndex)) {
            if (firstIndex == lastIndex) {
                return;
            }
            Number[][] arrayOfSets = splitArray(firstIndex, lastIndex, mathSet);
            insertionSortAsc((Num[]) arrayOfSets[1]);
            mathSet = new MathSet(arrayOfSets).getMathSet();
        }
    }

    public void sortAsc(Number value) {
        int index = getIndex(value);
        if (index == mathSet.length) {
            printMessage("We don't have this value.");
        } else {
            sortAsc(index, mathSet.length - 1);
        }
    }

    public Number get(int index) {
        if (isCorrectIndex(index)) {
            Number value = mathSet[index];
            return mathSet[index];
        }
        return null;
    }

    public Number getMax() {
        Number[] array = getArrayWithoutNull(mathSet);
        Number max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (numberComparator((Num) array[i], (Num) max) > 0) {
                max = array[i];
            }
        }
        return max;
    }

    public Number getMin() {
        Number[] array = getArrayWithoutNull(mathSet);
        Number min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (numberComparator((Num) array[i], (Num) min) < 0) {
                min = array[i];
            }
        }
        return min;
    }

    public Number getAverage() {
        Number[] array = getArrayWithoutNull(mathSet);
        double sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i].doubleValue();
        }
        double result = sum / array.length;
        return result;
    }

    public Number getMedian() {
        Number[] array = getArrayWithoutNull(mathSet);
        insertionSortDesc((Num[]) array);
        int halfArray = array.length / 2;
        if (array.length % 2 != 0) {
            return array[halfArray];
        } else {
            double a = array[halfArray].doubleValue();
            double b = array[halfArray - 1].doubleValue();
            return (a + b) / 2;
        }
    }

    public Number[] toArray() {
        return getMathSet();
    }

    public Number[] toArray(int firstIndex, int lastIndex) {
        Number[] array = new Number[lastIndex - firstIndex + 1];
        int j = 0;
        for (int i = firstIndex; i <= lastIndex; i++) {
            array[j] = mathSet[i];
            j++;
        }
        return array;
    }

    public MathSet cut(int firstIndex, int lastIndex) {
        Number[][] arrayOfSets = new Number[3][];
        if (firstIndex > lastIndex) {
            int temp = firstIndex;
            firstIndex = lastIndex;
            lastIndex = temp;
        }
        if (isCorrectIndexes(firstIndex, lastIndex)) {
            arrayOfSets = splitArray(firstIndex, lastIndex, mathSet);
        }
        return new MathSet(arrayOfSets[0], arrayOfSets[2]);
    }

    public void clear() {
        for (int i = 0; i < mathSet.length; i++) {
            if (mathSet != null)
                mathSet[i] = null;
        }
    }

    public void clear(Number[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            int index = getIndex(numbers[i]);
            if (index != mathSet.length) {
                mathSet[index] = null;
            } else {
                printMessage("This number: " + numbers[i] + " does not exist in MathSet.");
                return;
            }
        }
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

    private boolean isCorrectIndex(int index) {
        if (index < 0) {
            printMessage("Your index: " + index + " - is out of bounds of MathSet.");
            return false;
        } else if (index >= mathSet.length) {
            printMessage("Your index: " + index + " - is out of bounds of MathSet.");
            return false;
        }
        return true;
    }

    private boolean isCorrectIndexes(int firstIndex, int lastIndex) {
        if (firstIndex < 0) {
            printMessage("Your index: " + firstIndex + " - is out of bounds of MathSet.");
            return false;
        } else if (lastIndex >= mathSet.length) {
            printMessage("Your index: " + lastIndex + " - is out of bounds of MathSet.");
            return false;
        }
        return true;
    }

    private int getIndex(Number value) {
        for (int i = 0; i < mathSet.length; i++) {
            if (mathSet[i] != null) {
                if (numberComparator((Num) value, (Num) mathSet[i]) == 0) {
                    return i;
                }
            }
        }
        return mathSet.length;
    }

    private Number[][] splitArray(int firstIndex, int lastIndex, Number[] numbers) {
        Number[][] splitArray = new Number[3][];
        Number[] firstPart, middle, lastPart;
        firstPart = new Number[firstIndex + 1];
        middle = new Number[lastIndex - firstIndex + 1];
        lastPart = new Number[numbers.length - lastIndex];

        for (int i = 0; i < numbers.length; ) {
            if (i < firstIndex) {
                int fIndex = 0;
                while (i < firstIndex) {
                    firstPart[fIndex] = numbers[i];
                    i++;
                    fIndex++;
                }
            }
            if (i == firstIndex) {
                int mIndex = 0;
                while (i <= lastIndex) {
                    middle[mIndex] = numbers[i];
                    mIndex++;
                    i++;
                }
            }
            if (i > lastIndex) {
                int lIndex = 0;
                while (i < numbers.length) {
                    lastPart[lIndex] = numbers[i];
                    i++;
                    lIndex++;
                }
            }
        }
        splitArray[0] = firstPart;
        splitArray[1] = middle;
        splitArray[2] = lastPart;
        return splitArray;
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
                compare = numberComparator(array[j - 1], temp);
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
                compare = numberComparator(array[j - 1], temp);
                if (compare >= 0) {
                    array[j] = array[j - 1];
                } else break;
                j--;
            }
            array[j] = temp;
        }
    }

    private int numberComparator(Num first, Num second) {
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
