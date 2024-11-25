package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    @Override
    public int size() {
        return size;
    }

    private void resize(int cap) {
        T[] a = (T[]) new Object[cap];
        int i = cap / 4;
        System.arraycopy(items, nextFirst + 1, a, i, size);
        items = a;
        nextFirst = i - 1;
        nextLast = i + size;
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length - 2) {
            resize(items.length * 2);
        }
        items[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst--;
        }
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length - 2) {
            resize(items.length * 2);
        }
        items[nextLast] = item;
        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast++;
        }
        size++;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        nextFirst++;
        T item = items[nextFirst];
        items[nextFirst] = null;
        size--;
        return item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        nextLast--;
        T item = items[nextLast];
        items[nextLast] = null;
        size--;
        return item;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < items.length; i++) {
            System.out.print(items[i]+" ");
        }
        System.out.println();
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        int i = nextFirst + 1 + index;
        if (i >= items.length) {
            return items[i - items.length];
        }
        return items[i];
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int wizPos;
        private ArrayDequeIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T item = items[wizPos];
            wizPos++;
            return item;
        }
    }
}