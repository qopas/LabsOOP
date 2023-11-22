package Lab3.Test;
import Lab3.Queue.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    @Test
    void testLinkedQueue() {
        Queue<Integer> linkedQueue = new LinkedQueue<>();
        assertTrue(linkedQueue.isEmpty());
        assertFalse(linkedQueue.isFull());
        assertEquals(0, linkedQueue.size());

        linkedQueue.enqueue(1);
        linkedQueue.enqueue(2);
        linkedQueue.enqueue(3);

        assertEquals(3, linkedQueue.size());
        assertFalse(linkedQueue.isEmpty());
        assertFalse(linkedQueue.isFull());

        assertEquals(1, linkedQueue.dequeue());
        assertEquals(2, linkedQueue.dequeue());

        assertEquals(1, linkedQueue.size());
        assertFalse(linkedQueue.isEmpty());
        assertFalse(linkedQueue.isFull());

        linkedQueue.enqueue(4);
        linkedQueue.enqueue(5);
        linkedQueue.enqueue(6);
        linkedQueue.enqueue(7);

        assertEquals(5, linkedQueue.size());
        assertFalse(linkedQueue.isEmpty());
        assertTrue(linkedQueue.isFull());


        assertEquals(3, linkedQueue.dequeue());
        assertEquals(4, linkedQueue.dequeue());
        assertEquals(5, linkedQueue.dequeue());
        assertEquals(6, linkedQueue.dequeue());
        assertEquals(7, linkedQueue.dequeue());

        assertEquals(0, linkedQueue.size());
        assertTrue(linkedQueue.isEmpty());
        assertFalse(linkedQueue.isFull());


        assertThrows(IllegalStateException.class, linkedQueue::dequeue);
    }

    @Test
    void testArrayDownQueue() {
        Queue<Integer> arrayDownQueue = new ArrayDownQueue<>(5);

        assertTrue(arrayDownQueue.isEmpty());
        assertFalse(arrayDownQueue.isFull());
        assertEquals(0, arrayDownQueue.size());


        arrayDownQueue.enqueue(1);
        arrayDownQueue.enqueue(2);
        arrayDownQueue.enqueue(3);

        assertEquals(3, arrayDownQueue.size());
        assertFalse(arrayDownQueue.isEmpty());
        assertFalse(arrayDownQueue.isFull());


        assertEquals(1, arrayDownQueue.dequeue());
        assertEquals(2, arrayDownQueue.dequeue());

        assertEquals(1, arrayDownQueue.size());
        assertFalse(arrayDownQueue.isEmpty());
        assertFalse(arrayDownQueue.isFull());


        arrayDownQueue.enqueue(4);
        arrayDownQueue.enqueue(5);
        arrayDownQueue.enqueue(6);
        arrayDownQueue.enqueue(7);

        assertEquals(5, arrayDownQueue.size());
        assertFalse(arrayDownQueue.isEmpty());
        assertTrue(arrayDownQueue.isFull());


        assertEquals(3, arrayDownQueue.dequeue());
        assertEquals(4, arrayDownQueue.dequeue());
        assertEquals(5, arrayDownQueue.dequeue());
        assertEquals(6, arrayDownQueue.dequeue());
        assertEquals(7, arrayDownQueue.dequeue());

        assertEquals(0, arrayDownQueue.size());
        assertTrue(arrayDownQueue.isEmpty());
        assertFalse(arrayDownQueue.isFull());


        assertThrows(IllegalStateException.class, arrayDownQueue::dequeue);
    }

    @Test
    void testArrayUpQueue() {
        Queue<Integer> arrayUpQueue = new ArrayUpQueue<>(5);

        assertTrue(arrayUpQueue.isEmpty());
        assertFalse(arrayUpQueue.isFull());
        assertEquals(0, arrayUpQueue.size());


        arrayUpQueue.enqueue(1);
        arrayUpQueue.enqueue(2);
        arrayUpQueue.enqueue(3);

        assertEquals(3, arrayUpQueue.size());
        assertFalse(arrayUpQueue.isEmpty());
        assertFalse(arrayUpQueue.isFull());


        assertEquals(1, arrayUpQueue.dequeue());
        assertEquals(2, arrayUpQueue.dequeue());

        assertEquals(1, arrayUpQueue.size());
        assertFalse(arrayUpQueue.isEmpty());
        assertFalse(arrayUpQueue.isFull());
        arrayUpQueue.enqueue(4);
        arrayUpQueue.enqueue(5);
        arrayUpQueue.enqueue(6);
        arrayUpQueue.enqueue(7);

        assertEquals(5, arrayUpQueue.size());
        assertFalse(arrayUpQueue.isEmpty());
        assertTrue(arrayUpQueue.isFull());


        assertEquals(3, arrayUpQueue.dequeue());
        assertEquals(4, arrayUpQueue.dequeue());
        assertEquals(5, arrayUpQueue.dequeue());
        assertEquals(6, arrayUpQueue.dequeue());
        assertEquals(7, arrayUpQueue.dequeue());

        assertEquals(0, arrayUpQueue.size());
        assertTrue(arrayUpQueue.isEmpty());
        assertFalse(arrayUpQueue.isFull());


        assertThrows(IllegalStateException.class, arrayUpQueue::dequeue);
    }
}