package deque;

public class LinkedListDeque<T> {
    private class Node {
        private Node prev;
        private T item;
        private Node next;

        private Node(T i, Node n) {
            item = i;
            next = n;
        }
    }
    private Node sentinal;
    private int size;

    public LinkedListDeque() {
        sentinal = new Node(null,null);
        sentinal.prev = sentinal;
        sentinal.next = sentinal;
        size = 0;
    }

    public void addFirst(T item) {
        Node first = sentinal.next;
        first.prev = new Node(item,first);
        sentinal.next = first.prev;
        first.prev.prev = sentinal;
        size++;
    }

    public void addLast(T item) {
        Node last = sentinal.prev;
        last.next = new Node(item, sentinal);
        sentinal.prev = last.next;
        last.next.prev = last;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int x = 0; x < size; x++) {
            System.out.print(get(x) + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node rNode = sentinal.next;
        sentinal.next = rNode.next;
        rNode.next.prev = sentinal;
        T rItem = rNode.item;
        rNode.item = null;
        size--;
        return rItem;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node mNode = sentinal.prev;
        sentinal.prev = mNode.prev;
        mNode.prev.next = sentinal;
        T mItem = mNode.item;
        mNode.item = null;
        size--;
        return mItem;
    }

    public T get(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        int i = 0;
        for (Node x = sentinal.next; x != null; x = x.next) {
            if (i != index) {
                i++;
            } else {
                return x.item;
            }
        }
        return null;
    }

    public T getRecursive(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        return getRecur(index, sentinal.next);
    }

    private T getRecur(int index, Node x) {
        if (index == 0) {
            return x.item;
        }
        return getRecur(index - 1, x.next);
    }
}