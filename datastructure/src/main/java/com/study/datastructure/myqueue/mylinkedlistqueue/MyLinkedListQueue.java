package com.study.datastructure.myqueue.mylinkedlistqueue;

import com.study.datastructure.interface_form.Queue;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;


public class MyLinkedListQueue<E> implements Queue<E>, Cloneable {

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

    public Object[] toArray() {

        Object[] arr = new Object[size];

        int i = 0;

        for(Node<E> temp = head; temp != null; temp = temp.next) {
            arr[i++] = temp.data;
        }
        return arr;
    }

    public <T> T[] toArray(T[] a) {

        if(a.length < size) {
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
        }

        Object[] arr = a;

            int i = 0;

            for(Node<E> temp = head; temp != null; temp = temp.next) {

                arr[i++] = temp.data;
            }
            return (T[]) arr;
    }

    public Object clone() throws CloneNotSupportedException {

        MyLinkedListQueue<E> clone = (MyLinkedListQueue<E>) super.clone();

        clone.head = null;
        clone.tail = null;
        clone.size = 0;

        Node<E> temp = head;

        while(temp != null) {
            clone.offer(temp.data);
            temp = temp.next;
        }
        return clone;
    }


    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        if(size == 0) {
            return "[]";
        }

        for(Node<E> temp = head; temp != null; temp = temp.next) {
            sb.append(temp.data + " ");
        }
        return String.valueOf(sb);
    }

    public static void main(String[] args) throws CloneNotSupportedException {

        MyLinkedListQueue<Integer> myLinkedListQueue = new MyLinkedListQueue<>();

        myLinkedListQueue.offer(0);
        myLinkedListQueue.offer(1);
        myLinkedListQueue.offer(2);
        myLinkedListQueue.offer(3);

        System.out.println(myLinkedListQueue.toString());

        // toArray(T[] a) Test
        Integer[] a = new Integer[10];

        Integer[] arr = myLinkedListQueue.toArray(a);

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < arr.length; i++) {
            sb.append(arr[i] + " ");
        }

        System.out.println(sb);

        System.out.println(arr.length);



        // clone() Test
        MyLinkedListQueue<Integer> clone = (MyLinkedListQueue<Integer>) myLinkedListQueue.clone();

        System.out.println(clone.toString());



        // Shallow Copy , Deep Copy Test
        MyLinkedListQueue<Integer> original = new MyLinkedListQueue<>();

        original.offer(10);
        original.offer(20);

        MyLinkedListQueue<Integer> copy = original; // Shallow Copy

        MyLinkedListQueue<Integer> deepClone = (MyLinkedListQueue<Integer>) original.clone(); // Deep Copy

        copy.offer(30);
        deepClone.offer(40);

        System.out.println("==== Shallow Copy , Deep Copy ====");
        System.out.println(copy.toString());
        System.out.println(deepClone.toString());
        System.out.println(original.toString());

        // Shallow Copy 로 인해 copy 에 값을 추가하면 original 도 변경된다.(original 에도 영향을 준다.)
        // 하지만, Deep Copy 는 deepClone 에 값을 추가해도 original 에 영향을 주지 않는다.

    }
}
