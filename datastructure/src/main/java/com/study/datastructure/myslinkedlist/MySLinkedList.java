package com.study.datastructure.myslinkedlist;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

public class MySLinkedList<E> implements List<E>, Cloneable {

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

    public void addLast(E value) {
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

        Node<E> prev_Node = search(index - 1);
        Node<E> next_Node = search(index);
        Node<E> newNode = new Node<E>(value);

        prev_Node.next = newNode;
        newNode.next = next_Node;
        size++;

    }

    // head 삭제
    public E remove() {

       if(head == null) {
           throw new NoSuchElementException();
       }

       // 삭제된 노드의 데이터를 반환하기 위한 임시 변수
       E removedElement = head.data;

       Node<E> next_Node = head.next;

       head.next = null;
       head.data = null;

       head = next_Node;
       size--;


       if(size == 0) {
           tail = null;
       }

       return removedElement;
    }

    @Override
    public E remove(int index) {

        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if(index == 0) {
            return remove();
        }

        Node<E> prev_Node = search(index - 1);
        Node<E> removedNode = prev_Node.next;
        Node<E> next_Node = removedNode.next;

        E removedElement = removedNode.data;

        prev_Node.next = next_Node;

        if(prev_Node.next == null) {
            tail = prev_Node;
        }

        removedNode.data = null;
        removedNode.next = null;
        size--;

        return removedElement;

    }

    @Override
    public boolean remove(Object value) {

        Node<E> temp = head;
        Node<E> prev_Node = head;
        boolean hasValue = false;

        // 동일한 data 가 있는지 조회, 있다면, hasValue = true;
        while (temp != null) {
            if(value.equals(temp.data)) {
                hasValue = true;
                break;
            }
            prev_Node = temp;
            temp = temp.next;
        }

        // 동일한 data 가 있다면, 해당 로직 실행
        while (hasValue) {

                if(value.equals(head.data)) {
                    remove();
                    return true;
                }

                if(value.equals(tail.data)) {
                    prev_Node.next = null;
                    tail = prev_Node;
                    break;
                }

                prev_Node.next = temp.next;
                break;

            }


        if(hasValue == false) {
            return false;
        }

        temp.data = null;
        temp.next = null;
        size--;

        return true;
    }

    @Override
    public E get(int index) {
        return search(index).data;
    }

    @Override
    public void set(int index, E value) {
        search(index).data = null;
        search(index).data = value;
    }

    @Override
    public boolean contains(Object value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int indexOf(Object value) {

        Node<E> temp = head;
        int index = 0;

        while(temp != null) {
            if(value.equals(temp.data)) {
                return index;
            }

            temp = temp.next;
            index++;
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {

        Node<E> temp = head;

        while(temp != null) {

            Node<E> next_Node = temp.next;

            temp.data = null;
            temp.next = null;

            temp = next_Node;
        }
        head = tail = null;
        size = 0;
    }


    public Object clone() throws CloneNotSupportedException {

        MySLinkedList<E> clone = (MySLinkedList<E>) super.clone(); // Deep Copy, 새로운 변수 생성

        clone.head = null;
        clone.tail = null;
        clone.size = 0;

        for(Node<E> temp = head; temp != null; temp = temp.next) {
            clone.addLast(temp.data);
        }
        return clone;
    }

    public Object[] toArray() {

        Object[] arr = new Object[size];

        int i = 0;

        for(Node<E> temp = head; temp != null; temp = temp.next) {
            arr[i++] = temp.data;
        }
        return arr;
    }

    // 특정 타입의 배열을 돌려받기 위한 toArray(T[] a)
    @SuppressWarnings("unchedcked")
    public <T> T[] toArray(T[] a) {

        // 제네릭 타입. 타입 미정. reflection 사용하여 배열 생성
        // 배열을 다시 생성해야 하는 상황인데, T 타입으로는 배열을 생성할 수 없다.
        if(a.length < size) {
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
        }

        // a.length < size 인 경우, 새로운 배열을 생성해야 한다.
        // a.length > size 인 경우, 원래 배열을 사용한다.

        Object[] arr = a; // 어떤 타입의 데이터도 담을 수 있기 때문에 Object 로 배열 생성

        int i = 0;

        for(Node<E> temp = head; temp != null; temp = temp.next) {
            arr[i++] = temp.data;
        }
        return (T[]) arr;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();

        if(head == null) {
            return "[]";
        }

        Node<E> temp = head;

        while (temp.next != null) {

            sb.append(temp.data);
            temp = temp.next;
            sb.append("\t");
        }

        sb.append(temp.data);

        return "[" + sb + "]";
    }

    public static void main(String[] args) {

        MySLinkedList<String> mySLinkedList = new MySLinkedList<>();

        mySLinkedList.add("하나");
        mySLinkedList.add("둘");
        mySLinkedList.add("셋");
        mySLinkedList.add("넷");
        mySLinkedList.add("다섯");

        System.out.println(mySLinkedList.toString());

        System.out.println(mySLinkedList.remove("하나"));

        System.out.println(mySLinkedList.toString());

        System.out.println(mySLinkedList.remove("넷"));

        System.out.println(mySLinkedList.toString());

        System.out.println(mySLinkedList.size());

    }
}
