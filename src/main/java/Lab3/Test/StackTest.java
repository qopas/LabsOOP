package Lab3.Test;

import Lab3.Stack.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    void testLinkedStack() {
        Stack<Integer> linkedStack = new LinkedStack<>();

        assertTrue(linkedStack.isEmpty());
        assertFalse(linkedStack.isFull());
        assertEquals(0, linkedStack.size());


        linkedStack.push(1);
        linkedStack.push(2);
        linkedStack.push(3);

        assertEquals(3, linkedStack.size());
        assertFalse(linkedStack.isEmpty());
        assertFalse(linkedStack.isFull());


        assertEquals(3, linkedStack.pop());
        assertEquals(2, linkedStack.pop());

        assertEquals(1, linkedStack.size());
        assertFalse(linkedStack.isEmpty());
        assertFalse(linkedStack.isFull());


        linkedStack.push(4);
        linkedStack.push(5);
        linkedStack.push(6);
        linkedStack.push(7);

        assertEquals(5, linkedStack.size());
        assertFalse(linkedStack.isEmpty());
        assertTrue(linkedStack.isFull());


        assertEquals(7, linkedStack.pop());
        assertEquals(6, linkedStack.pop());
        assertEquals(5, linkedStack.pop());
        assertEquals(4, linkedStack.pop());
        assertEquals(1, linkedStack.pop());

        assertEquals(0, linkedStack.size());
        assertTrue(linkedStack.isEmpty());
        assertFalse(linkedStack.isFull());


        assertThrows(IllegalStateException.class, linkedStack::pop);
    }

    @Test
    void testArrayDownStack() {
        Stack<Integer> arrayDownStack = new ArrayDownStack<>(5);

        assertTrue(arrayDownStack.isEmpty());
        assertFalse(arrayDownStack.isFull());
        assertEquals(0, arrayDownStack.size());


        arrayDownStack.push(1);
        arrayDownStack.push(2);
        arrayDownStack.push(3);

        assertEquals(3, arrayDownStack.size());
        assertFalse(arrayDownStack.isEmpty());
        assertFalse(arrayDownStack.isFull());


        assertEquals(3, arrayDownStack.pop());
        assertEquals(2, arrayDownStack.pop());

        assertEquals(1, arrayDownStack.size());
        assertFalse(arrayDownStack.isEmpty());
        assertFalse(arrayDownStack.isFull());


        arrayDownStack.push(4);
        arrayDownStack.push(5);
        arrayDownStack.push(6);
        arrayDownStack.push(7);

        assertEquals(5, arrayDownStack.size());
        assertFalse(arrayDownStack.isEmpty());
        assertTrue(arrayDownStack.isFull());


        assertEquals(7, arrayDownStack.pop());
        assertEquals(6, arrayDownStack.pop());
        assertEquals(5, arrayDownStack.pop());
        assertEquals(4, arrayDownStack.pop());
        assertEquals(1, arrayDownStack.pop());

        assertEquals(0, arrayDownStack.size());
        assertTrue(arrayDownStack.isEmpty());
        assertFalse(arrayDownStack.isFull());


        assertThrows(IllegalStateException.class, arrayDownStack::pop);
    }

    @Test
    void testArrayUpStack() {
        Stack<Integer> arrayUpStack = new ArrayUpStack<>(5);

        assertTrue(arrayUpStack.isEmpty());
        assertFalse(arrayUpStack.isFull());
        assertEquals(0, arrayUpStack.size());


        arrayUpStack.push(1);
        arrayUpStack.push(2);
        arrayUpStack.push(3);

        assertEquals(3, arrayUpStack.size());
        assertFalse(arrayUpStack.isEmpty());
        assertFalse(arrayUpStack.isFull());

        assertEquals(3, arrayUpStack.pop());
        assertEquals(2, arrayUpStack.pop());

        assertEquals(1, arrayUpStack.size());
        assertFalse(arrayUpStack.isEmpty());
        assertFalse(arrayUpStack.isFull());

        arrayUpStack.push(4);
        arrayUpStack.push(5);
        arrayUpStack.push(6);
        arrayUpStack.push(7);

        assertEquals(5, arrayUpStack.size());
        assertFalse(arrayUpStack.isEmpty());
        assertTrue(arrayUpStack.isFull());

        assertEquals(7, arrayUpStack.pop());
        assertEquals(6, arrayUpStack.pop());
        assertEquals(5, arrayUpStack.pop());
        assertEquals(4, arrayUpStack.pop());
        assertEquals(1, arrayUpStack.pop());

        assertEquals(0, arrayUpStack.size());
        assertTrue(arrayUpStack.isEmpty());
        assertFalse(arrayUpStack.isFull());

        assertThrows(IllegalStateException.class, arrayUpStack::pop);
    }
}