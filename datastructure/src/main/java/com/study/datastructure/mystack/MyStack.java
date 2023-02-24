package com.study.datastructure.mystack;

public class MyStack<T> {

    private static int size = 6;
    private static int top = -1;
    private static Object[] arr = new Object[size];


    // push()
    public void push(Object data) {

        if (isFull()) {
            arr = resizeArr();
        }
        arr[++top] = data;
    }

    private boolean isFull() {
        return top == size - 1 ? true : false;
    }


    private Object[] resizeArr() {

        int newSize = size * 2;
        Object[] newArr = new Object[newSize];
        for(int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    // peek()
    public String peek() {
        if(!isEmpty()) {
            String peek = String.valueOf(arr[top]);
            return peek;
        }
        return "-1";
    }

    // pop() : 요소 반환 및 제거
    public String pop() {
        if(!isEmpty()) {
            return String.valueOf(arr[top--]);
            // 반환하고 제거 로직 구현은 힘들다. 따라서 출력 범위를 줄이는 것으로 제거를 구현
        }
        return "-1";
    }

    private boolean isEmpty() {
        return top == -1 ? true : false;
    }

    // search(Object o)
    public int search(Object data) {

        for(int i = 0; i <= top; i++) {
            if(data == arr[i]) {
                return (top - i) + 1;
            }
        }
        return -1;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i <= top; i++) {

            sb.append(arr[i] + " ");
        }

        return sb.toString();
    }


    public static void main(String[] args) {

        MyStack<Object> myStack = new MyStack<>();

        myStack.push(1);
        myStack.push(2);
        myStack.push("안녕");

        System.out.println(myStack.toString());

        System.out.println(myStack.peek());

        System.out.println(myStack.search("안녕"));

        System.out.println(myStack.pop());

        System.out.println(myStack.toString());

    }

}
