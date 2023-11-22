package Lab3.Queue;

public class ArrayUpQueue<T> implements Queue<T> {
    private Object[] array;
    private int front;
    private int rear;
    private int size;

    public ArrayUpQueue(int capacity) {
        array = new Object[capacity];
        front = -1;
        rear = -1;
        size = 0;
    }

    @Override
    public void enqueue(T element) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }

        if (front == -1) {
            front = 0;
        }

        rear = (rear + 1) % array.length;
        array[rear] = element;
        size++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        T data = (T) array[front];
        front = (front + 1) % array.length;
        size--;

        if (isEmpty()) {
            front = rear = -1;
        }

        return data;
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