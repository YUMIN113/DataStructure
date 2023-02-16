package com.study.datastructure.myslinkedlist;

public class MySLinkedList<E> implements List<E> {

    private Node<E> head; // 노드의 첫 부분

    private Node<E> tail; // 노드의 마지막 부분

    private int size; // 요소 개수

    public MySLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // 특정 위치의 노드를 반환하는 메서드
    private Node<E> search(int index) {

        if(index < 0 || index >=size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> x = head;
        for(int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    public void addFirst(E value) {
        Node<E> newNode = new Node<E>(value);
        newNode.next = head;
        head = newNode;
        size++;

        if(head.next == null) {
            tail = head;
        }
    }

    @Override
    public boolean add(E value) {
        addLast(value);
        return true;
    }

    private void addLast(E value) {
        Node<E> newNode = new Node<E>(value);

        if(size == 0) {
            addFirst(value);
            return;
        }

        tail.next = newNode;
        tail = newNode;
        size++;
    }

    @Override
    public void add(int index, E value) {

        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if(index == 0) {
            addFirst(value);
            return;
        }

        if(index == size) {
            addLast(value);
            return;
        }

        Node<E> newNode = new Node<E>(value);
        search(index - 1).next = newNode;
        newNode.next = search(index);
        size++;

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public boolean remove(Object value) {
        return false;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public void set(int index, E value) {

    }

    @Override
    public boolean contains(Object value) {
        return false;
    }

    @Override
    public int indexOf(Object value) {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }
}
