package Lab3.Stack;

public class ArrayUpStack<T> implements Stack<T> {
    private Object[] array;
    private int top;
    private int size;

    public ArrayUpStack(int capacity) {
        array = new Object[capacity];
        top = -1;
        size = 0;
    }

    @Override
    public void push(T element) {
        if (isFull()) {
            throw new IllegalStateException("Stack is full");
        }
        top = (top + 1) % array.length;
        array[top] = element;
        size++;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }

        T data = (T) array[top];
        top = (top - 1 + array.length) % array.length;
        size--;

        return data;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }

        return (T) array[top];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }

    @Override
    public int size() {
        return size;
    }
}