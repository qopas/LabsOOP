package Lab3.Stack;

public interface Stack<T> {
    void push(T element);
    T pop();
    T peek();
    boolean isEmpty();
    boolean isFull();
    int size();
}
