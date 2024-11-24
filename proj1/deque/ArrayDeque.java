package deque;

public class ArrayDeque<T> {
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

    public int size() {
        return size;
    }

    private void resize(int cap) {
        T[] a = (T[]) new Object[cap];
        int i = (cap - size) / 2;
        System.arraycopy(items, nextFirst + 1, a, i, size);
        items = a;
        nextFirst = i - 1;
        nextLast = i + size;
    }

    public void addFirst(T item) {
        items[nextFirst] = item;
        size++;
        nextFirst--;
        if (nextFirst == -1) {
            resize(size * 2);
        }
    }

    public void addLast(T item) {
        items[nextLast] = item;
        size++;
        if (nextLast == items.length) {
            resize(size * 2);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

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

    public void printDeque() {
        for (int i = 0; i < items.length; i++) {
            System.out.print(items[i]+" ");
        }
        System.out.println();
    }

    public T get(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        int i = nextFirst + 1 + index;
        return items[i];
    }
}