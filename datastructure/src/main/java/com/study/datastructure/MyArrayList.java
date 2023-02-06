package com.study.datastructure;

public class MyArrayList<T> {

    private int size = 5; // 배열 size

    private int index = 0; // 배열 index

    private Object[] arr = new Object[size];


    // overloading_순차적으로 데이터 입력
    public void add(T data) {

        if(size == index) {
            arr = resizeArr();
        }

        arr[index++] = data;
    }


    // overloading_특정 index 에 데이터 입력
    public void add(int selectedIndex, T data) {

        if(size == index) {
            arr = resizeArr();
        }
        for(int i = (index - 1); i >= selectedIndex; i--) {
            arr[i + 1] = arr[i];
        }
        arr[selectedIndex] = data;
        index++;
    }


    // 배열 size 증가 로직
    private Object[] resizeArr() {

            Object[] tempArr = new Object[size * 2];
            for(int i = 0; i < arr.length; i++) {
                tempArr[i] = arr[i];
            }
            size *= 2;
            return tempArr;
    }


    // 특정 index 배열 값 찾기
    public Object get(int selectedIndex) {

        Object val = arr[selectedIndex];
        return val;
    }


    // 특정 index 배열 값 삭제
    public void remove(int selectedIndex) {

        arr[selectedIndex] = null;

        for(int i = selectedIndex; i < index - 1; i++) {
            arr[i] = arr[i + 1];
        }
        index--;
    }


    // 배열 복사
    public Object[] copyArr(Object[] arr) {

        Object[] copyArr = new Object[arr.length];
        for(int i = 0; i < arr.length; i++) {
            copyArr[i] = arr[i];
        }

        return copyArr;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < index; i++) {
            sb.append(arr[i] + "  ");
        }

        return sb.toString();
    }


    // 실행
    public static void main(String[] args) {

        MyArrayList<Object> myArrayList = new MyArrayList<>();

        myArrayList.add(1);
        myArrayList.add("안녕");
        myArrayList.add("hello");

        System.out.println(myArrayList.toString());

        myArrayList.add(1, "끼기");
        myArrayList.add(1, "두번째끼기");
        myArrayList.add(1, "세번째끼기");

        System.out.println(myArrayList.toString());

        myArrayList.remove(1);

        System.out.println(myArrayList.toString());

        Object val = myArrayList.get(3);

        System.out.println(val);

        Object[] arr = {1, 2, 3};
        Object[] copyArr = myArrayList.copyArr(arr);

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < copyArr.length; i++) {
            sb.append(copyArr[i] + " ");
        }

        System.out.println(sb);
    }
}
