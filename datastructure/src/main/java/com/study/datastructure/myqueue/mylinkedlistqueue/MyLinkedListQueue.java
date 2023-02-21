package com.study.datastructure.myqueue.mylinkedlistqueue;

import com.study.datastructure.myqueue.Queue;

import java.util.NoSuchElementException;


public class MyLinkedListQueue<E> implements Queue<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public MyLinkedListQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public boolean offer(E data) {

        Node<E> newNode = new Node<>(data);

        if(size == 0) {
            head = newNode;
        } else {
            tail.next = newNode;
        }

        tail = newNode;
        size++;

        return true;
    }

    @Override
    public E poll() {

        if(size == 0) {
            return null;
        }

        E removedData = head.data;

        Node<E> next_Node = head.next;

        head.data = null;
        head.next = null;

        head = next_Node;
        size--;

        return removedData;
    }

    public E remove() {

        E removedData = poll();

        if(removedData == null) {
            throw new NoSuchElementException();
        }
        return removedData;
    }

    @Override
    public E peek() {

        if(size == 0) {
            return null;
        }
        return head.data;
    }

    public E element() {

        E peekData = peek();

        if(peekData == null) {
            throw new NoSuchElementException();
        }
        return peekData;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object data) {

        Node<E> temp = head;

        while (temp != null) {
            if(data.equals(temp.data)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public void clear() {

        Node<E> temp = head;

        while (temp != null) {

            Node<E> next_Node = temp.next;
            temp.data = null;
            temp.next = null;
            temp = next_Node;
        }
        size = 0;
        head = tail = null;
    }

    public static void main(String[] args) {

        MyLinkedListQueue<Integer> myLinkedListQueue = new MyLinkedListQueue<>();

        myLinkedListQueue.offer(0);
    }
}
