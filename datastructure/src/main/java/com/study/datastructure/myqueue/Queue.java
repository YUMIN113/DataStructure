package com.study.datastructure.myqueue;

public interface Queue<E> {

    /**
     * Queue 가장 마지막에 요소를 추가합니다.
     *
     * @param e Queue 에 추가할 요소
     * @return Queue 에 요소가 정상적으로 추가되었을 경우 true 반환
     */
    boolean offer(E e);

    /**
     * Queue 의 첫 번째 요소를 삭제하고 삭제 된 요소를 반환합니다.
     *
     * @return 삭제 된 요소 반환
     */
    E poll();

    /**
     * Queue 의 첫 번째 요소를 반환합니다.
     * @return Queue 의 첫 번째 요소 반환
     */
    E peek();
}
