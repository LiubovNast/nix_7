package entity;

public class MathSet<Number> {

    private static final int CAPACITY = 10;
    private int size;
    private Number[] mathSet;

    public Number[] getMathSet() {
        return mathSet;
    }

    public MathSet() {
        this.size = CAPACITY;
        mathSet = (Number[]) new Object[CAPACITY];
    }

    public MathSet(int size) {
        this.size = size;
        mathSet = (Number[]) new Object[size];;
    }

    public MathSet(Number[] numbers) {
        this.size = numbers.length;
        mathSet = numbers;
    }

    public MathSet(Number[]... numbers) {
        int curentSize = 0;
        for (int i = 0; i < numbers.length; i++) {
            curentSize += numbers[i].length;
        }
        this.size = curentSize;
        mathSet = (Number[]) new Object[curentSize];
    }

    public MathSet(MathSet numbers) {
        this.size = numbers.getSize();
        mathSet = (Number[]) numbers.getMathSet();
    }

    public MathSet(MathSet... numbers) {
        int curentSize = 0;
        for (int i = 0; i < numbers.length; i++) {
            curentSize += numbers[i].getSize();
        }
        this.size = curentSize;
        mathSet = (Number[]) new Object[curentSize];
    }

    public int getSize() {
        return size;
    }
    private int getIndex() {
        for (int i = 0; i < mathSet.length; i++) {
            if (mathSet[i] == null) return i;
        }
        return mathSet.length;
    }

    void add(Number n) {
        for (int i = 0; i < mathSet.length; i++) {
            if (n.equals(mathSet[i])) break;
        }
        int index = getIndex();
        if (getIndex() == mathSet.length) {
            mathSet = changeSetSize();
        }
        mathSet[index] = n;
    }

    private Number[] changeSetSize() {
        int newSize = (int) ((mathSet.length * 1.5) + 1);
        Number[] newArray = (Number[]) new Object[newSize];
        for (int i = 0; i < mathSet.length; i++) {
            newArray[i] = mathSet[i];
        }
        return newArray;
    }

    void add(Number... n) {
        for (int i = 0; i < n.length; i++) {
            add(n[i]);
        }
    }

    void join(MathSet ms) {
        Number[] numbers = getMathSet();
        for (int i = 0; i < numbers.length; i++) {
            add(numbers[i]);
        }
    }

    void join(MathSet... ms) {
        for (int i = 0; i < ms.length; i++) {
            join(ms[i]);
        }
    }

    void sortDesc() {
    }

    void sortDesc(int firstIndex, int lastIndex) {
    }

    void sortDesc(Number value) {
    }

    void sortAsc() {
    }

    void sortAsc(int firstIndex, int lastIndex) {
    }

    void sortAsc(Number value) {
    }

    Number get(int index) {
        return null;
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
        return null;
    }

    Number[] toArray(int firstIndex, int lastIndex) {
        return null;
    }

    void clear() {
    }

    void clear(int firstIndex, int lastIndex) {
    }

    void clear(Number[] numbers) {
    }
}
