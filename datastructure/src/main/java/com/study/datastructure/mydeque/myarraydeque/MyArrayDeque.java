package com.study.datastructure.mydeque.myarraydeque;

import com.study.datastructure.interface_form.Queue;


import java.util.NoSuchElementException;

public class MyArrayDeque<E> implements Queue<E> {

    private static final int DEFAULT_CAPACITY = 64;

    private Object[] arr;
    private int size;

    private int front;
    private int rear;

    public MyArrayDeque() {
        this.arr = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    public MyArrayDeque(int capacity) {
        this.arr = new Object[capacity];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }


    // 동적 할당
    private void reSize(int newCapacity) {

        int arrCapacity = arr.length;

        Object[] newArr = new Object[newCapacity];

        for(int i = 1, j = front + 1; i <= size; i++, j++) {
            newArr[i] = arr[j % arrCapacity];
        }

        this.arr = null;
        arr = newArr;

        front = 0;
        rear = size;
    }


    public boolean offerLast(E item) {

        if((rear + 1) % arr.length == front) {
            reSize(arr.length * 2);
        }

        rear = (rear + 1) % arr.length;

        arr[rear] = item;
        size++;

        return true;
    }


    @Override
    public boolean offer(E item) {
        return offerLast(item);
    }


    public boolean offerFirst(E item) {

        if (((front - 1) + arr.length) % arr.length == rear) {
            reSize(arr.length * 2);
        }

        arr[front] = item;
        front = ((front - 1) + arr.length) % arr.length;
        size++;

        return true;
    }


    public E pollFirst() {

        if(size == 0) {
            return null;
        }

        front = (front + 1) % arr.length;
        E removedItem = (E) arr[front];
        arr[front] = null;
        size--;

        if(arr.length > DEFAULT_CAPACITY && size < (arr.length / 4)) {
            this.reSize(Math.max(DEFAULT_CAPACITY, arr.length / 2));
        }
        return removedItem;
    }


    @Override
    public E poll() {
        return pollFirst();
    }


    public E remove() {
        return removeFirst();
    }


    public E removeFirst() {

        E removedItem = pollFirst();

        if (removedItem == null) {
            throw new NoSuchElementException();
        }
        return removedItem;
    }


    public E pollLast() {

        if (size == 0) {
            return null;
        }

        E removedItem = (E) arr[rear];
        arr[rear] = null;
        rear = ((rear - 1) + arr.length) % arr.length;

        size--;

        if(arr.length >DEFAULT_CAPACITY && size < (arr.length / 4)) {
            this.reSize(Math.max(DEFAULT_CAPACITY, arr.length / 2));
        }
        return removedItem;
    }


    public E removeLast() {
        E removedItem = this.pollLast();

        if (removedItem == null) {
            throw new NoSuchElementException();
        }
        return removedItem;
    }


   public E peekFirst() {

        if (size == 0) {
            return null;
        }

        E peekFirstItem = (E) arr[(front + 1) % arr.length];

        return peekFirstItem;
   }


    @Override
    public E peek() {
        return this.peekFirst();
    }


    public E peekLast() {

        if (size == 0) {
            return null;
        }

        E peekLastItem = (E) arr[rear];

        return peekLastItem;
    }


    public E getFirst() {

        E peekFirstItem = peekFirst();

        if (peekFirstItem == null) {
            throw new NoSuchElementException();
        }

        return peekFirstItem;
    }


    public E element() {
        return getFirst();
    }


    public E getLast() {

        E peekLastItem = peekLast();

        if (peekLastItem == null) {
            throw new NoSuchElementException();
        }
        return peekLastItem;
    }


    public int size() {
        return size;
    }


    public boolean isEmpty() {
        return size == 0;
    }


    public boolean contains(Object value) {

        int start = (front + 1) % arr.length;

        for(int i = 0, idx = start; i < size; i++, idx = (idx + 1) % arr.length) {
            if(arr[idx].equals(value)) {
                return true;
            }
        }
        return false;
    }


    public void clear() {

        for(int i = 0; i < arr.length; i++) {
            arr[i] = null;
        }
        front = rear = size = 0;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        if(size == 0) {
            return "[]";
        }

        int start = (front + 1) % arr.length;
        for(int i = 0, idx = start; i < size; i++, idx = (idx + 1) % arr.length) {
            sb.append(arr[idx] + " ");
        }

        return "[" + sb + "]";
    }

    public static void main(String[] args) {

        MyArrayDeque<Integer> myArrayDeque = new MyArrayDeque<>(3);

        myArrayDeque.offerFirst(0);
        myArrayDeque.offerFirst(1);
        myArrayDeque.offerFirst(2);
        myArrayDeque.offerFirst(3);
        myArrayDeque.offer(4);

        System.out.println(myArrayDeque.toString());

        myArrayDeque.poll();
        System.out.println(myArrayDeque.toString());

        myArrayDeque.pollLast();
        System.out.println(myArrayDeque.toString());


        System.out.println(myArrayDeque.peek());
        System.out.println(myArrayDeque.peekLast());

        System.out.println(myArrayDeque.isEmpty());

        System.out.println(myArrayDeque.contains(6));

        myArrayDeque.clear();

        System.out.println(myArrayDeque.toString());
        System.out.println(myArrayDeque.arr.length);

        System.out.println(myArrayDeque.removeLast());
    }
}
