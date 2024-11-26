package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;
    public MaxArrayDeque(Comparator<T> c ) {
        comparator = c;
    }

    public T max() {
        return max(comparator);
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        int max = 0;
        for (int i = 0; i < size(); i++) {
            if (c.compare(get(i), get(max)) > 0) {
                max = i;
            }
        }
        return get(max);
    }
}