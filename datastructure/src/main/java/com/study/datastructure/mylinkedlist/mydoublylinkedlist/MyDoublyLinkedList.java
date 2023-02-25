package com.study.datastructure.mylinkedlist.mydoublylinkedlist;

import com.study.datastructure.interface_form.List;

import java.util.NoSuchElementException;

public class MyDoublyLinkedList<E> implements List<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public MyDoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }


    // 특정 위치의 노드를 반환하는 메서드
    // 시작점이 tail 또는 head 이다.
    private Node<E> search(int index) {

        Node<E> searchNode;

        // size 가 후위 연산이기 때문에 index == size 에는 Node 가 없어 조회할 수 없다.
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        // tail 부터 접근
        // size 가 홀수인지 짝수인지 고려
        if(index >= size / 2) {

            searchNode = tail;

            for(int i = 1; i < size - index; i++ ) {
                searchNode = searchNode.prev;
            }
        } else {

            searchNode = head;

            for(int i = 0; i < index; i++) {
                searchNode = searchNode.next;
            }
        }
        return searchNode;
    }


    // 가장 앞부분으로 추가하는 메서드
    public void addFirst(E value) {

        Node<E> newNode = new Node<>(value);

        newNode.next = head;

        // size == 0 일 경우, 즉, head == null 인 상태일 때, prev 객체에 접근할 수 없다.
        // if(head.prev != null) 안된다. head 자체가 null 인데 prev 객체에 접근할 수 없다.
        if(head != null) {
            head.prev = newNode;
        }

        head = newNode;
        size++;

        if(head.next == null) {
            tail = head;
        }
    }


    public void addLast(E value) {

        Node<E> newNode = new Node<>(value);

        if(size == 0) {
            this.addFirst(value);
            return;
        }

        newNode.prev = tail;
        tail.next = newNode;
        tail = newNode;
        size++;

    }


    @Override
    public boolean add(E value) {
        this.addLast(value);
        return true;
    }


    @Override
    public void add(int index, E value) {

        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if(index == 0) {
            this.addFirst(value);
            return;
        }

        // size 가 후위 연산이기 때문에 index == size 가 가능하다.
        if(index == size) {
            this.addLast(value);
            return;
        }

            Node<E> prev_Node = search(index - 1);
            Node<E> next_Node = prev_Node.next;
            Node<E> newNode = new Node<>(value);

            // 링크 끊기
            prev_Node.next = null;
            next_Node.prev = null;

            // 링크 연결
            prev_Node.next = newNode;
            next_Node.prev = newNode;

            newNode.prev = prev_Node;
            newNode.next = next_Node;

            size++;
    }


    // head 삭제 메서드
    public E remove() {

        Node<E> next_Node = head.next;
        Node<E> removedHeadNode = head;
        E removedHeadData = removedHeadNode.data;

        if(removedHeadNode == null) {
            throw new NoSuchElementException();
        }

        // 기존 head 의 next 와 data 모두 삭제
        head.data = null;
        head.next = null;

        // Node 가 하나만 있을 경우, next_Node 는 null 이다.
        // 따라서, next_Node 의 prev 객체에 접근할 수 없기 때문에 next_Node 가 null 이 아닌 조건 하에서만 해당 로직이 실행되도록 했다.
        if(next_Node != null) {
            next_Node.prev = null;
        }

        head = next_Node;
        size--;

        if(size == 0) {
            tail = null;
        }
        return removedHeadData;
    }

    @Override
    public E remove(int index) {

        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if(index == 0) {
            E removedHeadData = head.data;
            this.remove();
            return removedHeadData;

        }

        Node<E> removedNode = search(index);
        Node<E> prev_Node = removedNode.prev;
        Node<E> next_Node = removedNode.next;

        E removedData = removedNode.data;

        prev_Node.next = null;

        // 삭제할 Node 데이터 모두 삭제
        removedNode.data = null;
        removedNode.prev = null;
        removedNode.next = null;

        // index 가 tail 이라면, next_Node 는 null 이다. 따라서 next_Node 의 prev 객체에 접근할 수 없다.
        // 따라서, next_Node != null 조건 하에서만 해당 로직이 실행되도록 했다.
        if(next_Node != null) {
            next_Node.prev = null;

            next_Node.prev = prev_Node;
            prev_Node.next = next_Node;
        }

        // 마지막 노드를 삭제할 경우 실행
        else {
            tail = prev_Node;
        }
            size--;

        return removedData;
    }


    @Override
    public boolean remove(Object value) {

        Node<E> temp = head;

        while (temp != null) {
            if(temp.data.equals(value)) {

                if (temp == head) {
                    this.remove();
                    return true;
                }

                Node<E> removedNode = temp;
                Node<E> prev_Node = removedNode.prev;
                Node<E> next_Node = removedNode.next;

                prev_Node.next = null;

                if (next_Node != null) {
                    next_Node.prev = null;
                    next_Node.prev = prev_Node;
                    prev_Node.next = next_Node;
                }

                removedNode.data = null;
                removedNode.prev = null;
                removedNode.next = null;

                if (next_Node == null) {
                    tail = prev_Node;
                }
                size--;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }


    @Override
    public E get(int index) {
        return search(index).data;
    }


    @Override
    public void set(int index, E value) {
        Node<E> replaceNode = search(index);
        replaceNode.data = null;
        replaceNode.data = value;
    }

    // head 부터 탐색
    @Override
    public int indexOf(Object value) {

        int index = 0;

        for(Node<E> temp = head; temp != null; temp = temp.next) {
            if (value.equals(temp.data)) {
                return index;
            }
            index++;
        }
        return -1;
    }


    // tail 부터 탐색
    public int lastIndexOf(Object value) {

        int index = size;

        for(Node<E> temp = tail; temp != null; temp = temp.prev) {
            index--;
            if (value.equals(temp.data)) {
                return index;
            }
        }
        return -1;
    }


    @Override
    public boolean contains(Object value) {
        return indexOf(value) != -1;
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

        while (temp != null) {

            Node<E> next_Node = temp.next;

            temp.data = null;
            temp.prev = null;
            temp.next = null;

            temp = next_Node;

        }
        head = tail = null;
        size = 0;
    }


    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

            Node<E> temp = head;

            while (temp != null) {
                sb.append(temp.data + " ");
                temp = temp.next;
            }

        if (head == null) {
            return "[]";
        }

        return "[" + sb + "]";
    }


    public static void main(String[] args) {

        MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<>();

        myDoublyLinkedList.add("0");
        System.out.println(myDoublyLinkedList.size);
        myDoublyLinkedList.add("1");

        myDoublyLinkedList.add(0, "2");

        System.out.println(myDoublyLinkedList.toString());

        System.out.println(myDoublyLinkedList.isEmpty());

        System.out.println(myDoublyLinkedList.contains("1"));

        System.out.println(myDoublyLinkedList.indexOf("1"));

        myDoublyLinkedList.clear();

        System.out.println(myDoublyLinkedList.toString());

        System.out.println(myDoublyLinkedList.isEmpty());
    }
}
