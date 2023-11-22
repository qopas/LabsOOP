package Lab3.Stack;

import Lab3.LinkedList.Node;

public class LinkedStack<T> implements Stack<T> {
    private Node<T> top;
    private int size;


    @Override
    public void push(T element) {
        Node<T> newNode = new Node<>(element);
        newNode.setNext(top);
        top = newNode;
        size++;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }

        T data = top.getData();
        top = top.getNext();
        size--;

        return data;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }

        return top.getData();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == 5;
    }

    @Override
    public int size() {
        return size;
    }
}