package com.study.datastructure.myslinkedlist;

public class Node<E> {

    E data;
    Node<E> next; // 다음 노드객체를 가리키는 레퍼런스 변수

    public Node(E data) {
        this.data = data;
        this.next = null;
    }

    @Override
    public String toString() {
        return String.valueOf(this.data);
    }
}
