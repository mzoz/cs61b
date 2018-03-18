public interface Deque<Item> {
    void addFirst(Item t);

    void addLast(Item t);

    boolean isEmpty();

    int size();

    void printDeque();

    Item removeFirst();

    Item removeLast();

    Item get(int index);
}
