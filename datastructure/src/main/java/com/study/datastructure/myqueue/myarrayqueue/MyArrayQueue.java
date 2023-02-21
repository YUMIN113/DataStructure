package com.study.datastructure.myqueue.myarrayqueue;

import com.study.datastructure.myqueue.Queue;

import java.util.NoSuchElementException;

public class MyArrayQueue<E> implements Queue<E> {

    private static final int DEFAULT_CAPACITY = 64;

    private Object[] arr;
    private int size;

    private int front;
    private int rear;

    // 용적 할당 X
    public MyArrayQueue() {
        this.arr = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    // 용적 할당 O
    public MyArrayQueue(int capacity) {
        this.arr = new Object[capacity];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    private void resizeArr(int newCapacity) {

        int arrayCapacity = arr.length;
        Object[] newArr = new Object[newCapacity];

        for(int i = 1, j = front + 1; i <= size; i++, j++) {
            newArr[i] = arr[j % arrayCapacity];
        }

        this.arr = null;
        this.arr = newArr;

        front = 0;
        rear = size;

    }

    @Override
    public boolean offer(E value) {

        if((rear + 1) % arr.length == front) {
            int newCapacity = arr.length * 2;
            resizeArr(newCapacity);
        }

        rear = (rear + 1) % arr.length;

        arr[rear] = value;
        size++;

        return true;
    }

    // poll() 은 삭제 할 요소가 없다면 'null' 반환
    @Override
    public E poll() {

        if(size == 0) {
            return null;
        }

        front = (front + 1) % arr.length;

        @SuppressWarnings("unchecked")
        E item = (E) arr[front]; // 반환할 데이터 임시 저장

        arr[front] = null;
        size--;

        if(arr.length > DEFAULT_CAPACITY && size < (arr.length / 4)) {
            resizeArr(Math.max(DEFAULT_CAPACITY, arr.length / 2));
        }

        return item;
    }

    // remove() 는 삭제 할 요소가 없다면 NoSuchElementException() 예외를 던진다.
    public E remove() {

        E item = poll();

        if(item == null) {
            throw new NoSuchElementException();
        }

        return item;
    }

    // peek() 은 삭제 할 요소가 없다면 null 반환
    @Override
    public E peek() {

        if(size == 0) {
            return null;
        }

        @SuppressWarnings("unchecked")
        E item = (E) arr[(front + 1) % arr.length];
        return item;
    }

    // element() 는 삭제 할 요소가 없다면 NoSuchElementException() 예외를 던진다.
    public E element() {

       E item = peek();

       if(item == null) {
           throw new NoSuchElementException();
       }

       return item;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object value) {

        int start = (front +1) % arr.length;

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
            sb.append("[]");
            return String.valueOf(sb);
        }

        int start = (front + 1) % arr.length;

        for(int i = 0, idx = start; i < size; i++, idx = (idx + 1) % arr.length) {
            sb.append(arr[idx]);
            sb.append("\t");
        }

        return "[ " + String.valueOf(sb) + " ]";
    }

    public static void main(String[] args) {

        MyArrayQueue<Integer> myArrayQueue = new MyArrayQueue<>(4);

        myArrayQueue.offer(1);
        myArrayQueue.offer(2);
        System.out.println(myArrayQueue.toString());

        myArrayQueue.poll();

    }
}
