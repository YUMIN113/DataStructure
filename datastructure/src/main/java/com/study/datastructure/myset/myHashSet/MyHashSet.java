package com.study.datastructure.myset.myHashSet;

import com.study.datastructure.interface_form.Set;

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
//            resize();
        }
        return null;
    }


    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void clear() {

    }
}
