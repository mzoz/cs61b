package synthesizer;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T>  {
    private int first;
    private int last;
    private T[] rb;

    public ArrayRingBuffer(int capacity) {
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    private class RingBufferIterator implements Iterator<T> {
        int i = first;
        int n = fillCount;

        @Override
        public boolean hasNext() {
            return n != 0;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            n--;
            int k = ++i % capacity;
            return rb[k];
        }
    }

    /* Test Iterable and Iterator implementation*/
    public static void main(String[] args) {
        ArrayRingBuffer<Double> bf = new ArrayRingBuffer<>(10);
        Random r = new Random();
        while (!bf.isFull()) {
            bf.enqueue(r.nextDouble());
        }
        for (double d : bf) {
            System.out.println(d);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new RingBufferIterator();
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last = (last + 1) % capacity;
        fillCount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T x = rb[first];
        rb[first] = null;
        first = (first + 1) % capacity;
        fillCount--;
        return x;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return rb[first];
    }
}
