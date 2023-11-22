package Lab3.Queue;

interface Queue<T> {
    void enqueue(T element);
    T dequeue();
    boolean isEmpty();
    boolean isFull();
    int size();
}