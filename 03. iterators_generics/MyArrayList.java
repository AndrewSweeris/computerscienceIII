
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<E> implements Iterable<E> {

    private final int STARTSIZE = 10;
    private int size;       // the number of elements stored
    E[] ary;                // access modifier is package protected for testing purposes

    public MyArrayList() {    // start with a threshold/capacity of 10
        ary = (E[]) new Object[STARTSIZE];
        size = 0;
    }

    public boolean isEmpty() {	// is the list empty?
        return size == 0;
    }

    public int size() {         // the number of elements in the list
        return size;
    }

    // add the item to the end unless it's null and throw a NoSuchElementException
    public void add(E item) {
        if (item == null) {
            throw new java.util.NoSuchElementException();
        }
        if (size >= ary.length) {
            expand();
        }
        ary[size++] = item;
    }

    // add the item at the specified index
    // throw a NoSuchElementException if item is null
    // throw an IndexOutOfBounds exception if the index is invalid
    public void add(E item, int index) {
        // todo
        if (item == null) {
            throw new java.util.NoSuchElementException();
        }
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (size >= ary.length) {
            expand();
        }
        size++;
        E[] ary = (E[]) new Object[this.ary.length];
        for (int i = 0; i < index; i++) {
            ary[i] = this.ary[i];
        }
        ary[index] = item;
        for (int i = index; i < size; i++) {
            ary[i + 1] = this.ary[i];
        }
        this.ary = ary;
    }

    private void expand() {
        E[] ary = (E[]) new Object[this.ary.length * 2];
        for (int i = 0; i < size; i++) {
            ary[i] = this.ary[i];
        }
        this.ary = ary;
    }

    // remove and return the item at the index
    // throw an IndexOutOfBounds exception if the index is invalid
    public E remove(int index) {
        // todo

        E item = ary[index];

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (size-- <= ary.length / 4 && size >= STARTSIZE) {
            reduce();
        }
        for (int i = index; i < size; i++) {
            ary[i] = ary[i + 1];
        }
        return item;
    }

    private void reduce() {
        E[] ary = (E[]) new Object[this.ary.length / 2];
        for (int i = 0; i < size; i++) {
            ary[i] = this.ary[i];
        }
        this.ary = ary;
    }

    public E get(int index) {
        // todo
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return ary[index];
    }

    public void clear() {
        // todo
        ary = (E[]) new Object[10];
        size = 0;
    }

    @Override
    public Iterator<E> iterator() {         // return an iterator over items in order
        return new Itr();
    }

    private class Itr implements Iterator<E> {

        int index = 0;
        boolean called = false;

        // implemement this class
        @Override
        public boolean hasNext() {
            // todo
            return index < size;
        }

        @Override
        public void remove() {
            // todo
            if (called || index == 0) {
                throw new IllegalStateException();
            }
            called = true;

            if (size-- <= ary.length / 4 && size >= STARTSIZE) {
                reduce();
            }
            E[] temp = (E[]) new Object[ary.length];
            for (int i = index - 1; i < size; i++) {
                temp[i] = ary[i + 1];
            }
            for (int i = index - 1; i < ary.length; i++) {
                ary[i] = temp[i];
            }
            index--;
        }

        @Override
        public E next() {
            // todo
            called = false;

            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return ary[index++];
        }

    }

    // basic test cases
    // try adding your own thru JUnit
    public static void main(String[] args) {
        MyArrayList<String> test = new MyArrayList<>();
        test.add("Love");
        test.add("I", 0);
        test.add("Computer");
        test.add("Science");
        System.out.println(test.size());
        for (String item : test) {
            System.out.println(item);
        }
        test.remove(test.size() - 1);
        test.remove(2);
        test.remove(0);
        for (String item : test) {
            System.out.println(item);
        }

        test.remove(test.size() - 1);
        test.add("Iterators");
        test.add("Rock");
        for (String item : test) {
            System.out.println(item);
        }

        test.clear();

        System.out.println("After clearing, size is: " + test.size());
        for (int i = 0; i < 10; i++) {
            test.add("" + i);
        }

        Iterator<String> it = test.iterator();
        for (; it.hasNext();) {
            System.out.print(it.next() + " ");
        }

        try {
            it.next();
        } catch (Exception e) {
            System.out.println("\nNo more elements to iterate");
        }

        test.clear();
        it = test.iterator();
        for (int i = 0; i < 10; i++) {
            test.add("" + i);
        }
        it.next();
        it.remove();
        it.next();
        it.next();
        it.next();
        it.remove();
        System.out.println("" + java.util.Arrays.toString(test.ary));

        long start = System.currentTimeMillis();
        for (int i = 0; i < 2621440; i++) {
            test.add("how fast?");
        }
        for (int i = 0; i < 1310730; i++) {
            test.remove(test.size() - 1);
        }
        for (int j = 0; j < 1E8; j++) {
            for (int i = 0; i < 10; i++) {
                test.add("how fast?");
            }
            for (int i = 0; i < 10; i++) {
                test.remove(test.size() - 1);
            }
        }
        long stop = System.currentTimeMillis();
        System.out.println("My ArrayList: " + (stop - start) / 1000.0);

        java.util.ArrayList<String> list = new java.util.ArrayList<>();
        start = System.currentTimeMillis();
        for (int i = 0; i < 2621440; i++) {
            list.add("how fast?");
        }
        for (int i = 0; i < 1310730; i++) {
            list.remove(list.size() - 1);
        }
        for (int j = 0; j < 1E8; j++) {
            for (int i = 0; i < 10; i++) {
                list.add("how fast?");
            }
            for (int i = 0; i < 10; i++) {
                list.remove(list.size() - 1);
            }
        }
        stop = System.currentTimeMillis();
        System.out.println("Java's ArrayList: " + (stop - start) / 1000.0);
    }
}
