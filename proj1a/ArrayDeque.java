public class ArrayDeque<T> {
    private T[] items;
    private int nextFirst, nextLast;
    private int size;
    private static int ORIGIN = 8;
    private static int THESHOLD = 16;
    private static int FACTOR = 2;
    private static double RATIO = 0.25;

    public ArrayDeque() {
        items = (T[]) new Object[ORIGIN];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    private void resize() {
        T[] newItems = (T[]) new Object[items.length * FACTOR];
        migrate(newItems);
    }

    private void shrink() {
        T[] newItems = (T[]) new Object[items.length / FACTOR];
        migrate(newItems);
    }

    private void migrate(T[] newItems) {
        for (int i = 0; i < size; i++) {
            newItems[i] = items[(nextFirst+1+i) % items.length];
        }
        nextFirst = newItems.length - 1;
        nextLast = size;
        items = newItems;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize();
        }
        items[nextFirst] = item;
        int len = items.length;
        nextFirst = (nextFirst - 1 + len) % len;
        size++;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize();
        }
        items[nextLast] = item;
        int len = items.length;
        nextLast = (nextLast + 1) % len;
        size++;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public int len() {
        return items.length;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[(nextFirst+1+i) % items.length]);
        }
        System.out.println();
    }

    private void ratioCheck() {
        if ((double)size/items.length < RATIO && items.length >= THESHOLD) {
            shrink();
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        nextFirst = (nextFirst+1) % items.length;
        T temp = items[nextFirst];
        items[nextFirst] = null;
        size--;
        ratioCheck();
        return temp;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        nextLast = (nextLast-1+items.length) % items.length;
        T temp = items[nextLast];
        items[nextLast] = null;
        size--;
        ratioCheck();
        return temp;
    }

    public T get(int i) {
        return items[(nextFirst+1+i) % items.length];
    }
}
