public class LinkedListDeque<T> {
    private int size;
    private Node sentFront;
    private Node sentBack;

    class Node {
        T data;
        Node prev;
        Node next;

        Node(T d, Node p, Node n) {
            data = d;
            prev = p;
            next = n;
        }
    }

    public LinkedListDeque() {
        size = 0;
        sentFront = new Node(null, null, null);
        sentBack = new Node(null, null, null);
        sentFront.next = sentBack;
        sentBack.prev = sentFront;
    }

    public void addFirst(T item) {
        size++;
        Node n = new Node(item, null, null);
        n.next = sentFront.next;
        n.prev = sentFront;
        sentFront.next = n;
        n.next.prev = n;
    }

    public void addLast(T item) {
        size++;
        Node n = new Node(item, null, null);
        n.next = sentBack;
        n.prev = sentBack.prev;
        sentBack.prev = n;
        n.prev.next = n;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (Node p = sentFront.next; p != sentBack; p = p.next) {
            System.out.print(p.data + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size--;
        T x = sentFront.next.data;
        sentFront.next = sentFront.next.next;
        sentFront.next.prev = sentFront;
        return x;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size--;
        T x = sentBack.prev.data;
        sentBack.prev = sentBack.prev.prev;
        sentBack.prev.next = sentBack;
        return x;
    }

    public T get(int i) {
        if (i >= size) {
            return null;
        }
        Node p;
        for (p = sentFront.next; i > 0; i--) {
            p = p.next;
        }
        return p.data;
    }

    public T getRecursive(int i) {
        if (i >= size) {
            return null;
        }
        return getRec(i, sentFront.next);
    }

    private T getRec(int i, Node p) {
        if (i == 0) {
            return p.data;
        }
        return getRec(i-1, p.next);
    }
}
