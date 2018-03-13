import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void testEmpty() {
        ArrayDeque L = new ArrayDeque();
        assertEquals(0, L.size());
    }

    @Test
    public void testVariableLength() {
        ArrayDeque L = new ArrayDeque();
        for (int i = 0; i < 150; i++) {
            L.addFirst(i);
            L.addLast(i);
        }
        assertEquals(300, L.size());
        for (int i = 0; i < 86; i++) {
            L.removeFirst();
            L.removeLast();
        }
        assertEquals(128, L.size());
        assertEquals(512, L.len());
        L.removeLast();
        L.removeFirst();
        assertEquals(126, L.size());
        assertEquals(256, L.len());
        for (int i = 0; i < 100; i++) {
            L.removeFirst();
            L.removeLast();
        }
        assertEquals(8, L.len());
    }
}
