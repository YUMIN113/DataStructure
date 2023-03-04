package com.study.datastructure.myset.myHashSet;

import com.study.datastructure.interface_form.Set;

import java.util.Objects;

public class MyHashSet<E> implements Set<E> {

    // 최소 기본 용적. 2^n 형태가 좋다.
    private final static int DEFAULT_CAPACITY = 1 << 4;

    // 3/4 이상 채워질 경우, resize
    private final static float LOAD_FACTOR = 0.75f;

    Node<E>[] table;
    private int size;

    public MyHashSet() {
        this.table = new Node[DEFAULT_CAPACITY];
        this.size = 0;
    }

    // 보조 해시 함수
    private static final int hash(Object key) {

        int hash;
        if (key == null) {
            return 0;
        } else {
            return Math.abs(hash = key.hashCode())^(hash >>> 16);
        }
    }

    @Override
    public boolean add(E e) {
        return add(hash(e), e) == null;
    }


    private E add(int hash, E key) {

        int index = hash % table.length;

        // table[index] 가 비었을 경우 새 노드 생성
        if (table[index] == null) {
            table[index] = new Node<E>(hash, key, null);
        } else {

            Node<E> temp = table[index];
            Node<E> prev = null;

            while (temp != null) {

                // 중복 허용하지 않는다.
                if ((temp.hash == hash) && (temp.key == key || temp.key.equals(key))) {
                    return key;
                }

                prev = temp;
                temp = temp.next;

            }

            // 마지막 노드에 새 노드를 연결한다.
            prev.next = new Node<E>(hash, key, null);

        }
        size++;

        // 데이터의 개수가 table 용적의 75% 이상이면 용적을 늘린다.
        if (size >= LOAD_FACTOR * table.length) {
            resize();
        }
        return null;
    }


    private void resize() {

        int newCapacity = table.length * 2;

        final Node<E>[] newTable = new Node[newCapacity];

        for(int i = 0; i < table.length; i++) {

            Node<E> value = table[i];

            if (value == null) {
                continue;
            }

            table[i] = null;

            Node<E> next_Node;

            while (value != null) {

                int index = value.hash % newTable.length;

                // 해시 충돌 발생할 경우
                if (newTable[index] != null) {

                    Node<E> tail = newTable[index];

                    // 같은 인덱스 내의 가장 마지막 노드로 이동
                    while (tail.next != null) {
                        tail = tail.next;
                    }

                    next_Node = value.next;
                    value.next = null;
                    tail.next = value;

                } else {

                    next_Node = value.next;
                    value.next = null;
                    newTable[index] = value;

                }
                value = next_Node;
            }
        }
        table = null;
        table = newTable;
    }


    @Override
    public boolean remove(Object o) {
        return remove(hash(o), o) != null;
    }


    private Object remove(int hash, Object key){

        int searchIndex = hash % table.length;

        Node<E> node = table[searchIndex];
        Node<E> removedNode = null;
        Node<E> prev_Node = null;

        if (node == null) {
            return null;
        }

        // 같은 인덱스에서 노드 탐색
        while (node != null) {

            if(node.hash == hash && (node.key == key || node.key.equals(key))) {

                removedNode = node;

                // 첫번째 노드인 경우(prev_Node 가 없는 경우)
                if (prev_Node == null) {
                    table[searchIndex] = node.next;
                } else {
                    prev_Node.next = node.next;
                }
                node = null;

                size--;
                break;
            }

            prev_Node = node;
            node = node.next;
        }
        return removedNode;
    }


    @Override
    public boolean contains(Object o) {

        int searchIndex = hash(o) % table.length;

        Node<E> node = table[searchIndex];

        while (node != null) {

            // 데이터 내용이 같더라도 hash 값이 다를 수 있다.
            // 하지만, 해당 메서드는 데이터 내용이 같은지많을 확인하고자 사용하는 메서드이기에
            // hash 값의 동일 여부를 별도의 조건으로 포함하지는 않았다.
            // Objects.equals()
            // public static boolean equals(Object a, Object b) {
            //     return (a == b) || (a != null && a.equals(b));
            // }
            if (Objects.equals(o, node.key)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        if (table != null && size > 0) {
            for (int i = 0; i < table.length; i++) {
                table[i] = null;
            }
            size = 0;
        }
    }

    public static void main(String[] args) {

        MyHashSet<String> myHashSet = new MyHashSet<>();

        myHashSet.add("안녕");
        myHashSet.add("하이");


    }
}
