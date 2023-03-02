package com.study.datastructure.interface_form;

/**
 *
 * HashSet, LinkedHashSet, TreeSet 에 의해 구현
 *
 * @param <E> the type of elements in this Set
 */

public interface Set<E> {

    /**
     * 저장할 요소가 Set 에 없는 경우 요소를 추가합니다.
     *
     * @param e 집합에 추가할 요소
     * @return 저장할 요소가 Set 에 포함되지 않아 정상적으로 추가되었을 경우 {@code true}
     *         else, {@code false}
     */
    boolean add(E e);

    /**
     * 지정된 요소가 Set 에 있는 경우 해당 요소를 삭제합니다.
     *
     * @param o 집합에서 삭제할 특정 요소
     * @return 삭제할 요소가 Set 에 포함되어 있어 정상적으로 삭제되었을 경우 {@code true}
     *         else, {@code false}
     */
    boolean remove(Object o);

    /**
     * 현재 집합에 특정 요소의 포함 여부를 반환합니다.
     *
     * @param o 집합에서 찾을 특정 요소
     * @return Set 에 특정 요소가 포함되어 있을 경우 {@code true}
     *         else, {@code false}
     */
    boolean contains(Object o);

    /**
     * 지정된 객체와 현재 집합의 동일 여부를 반환합니다.
     *
     * @param o 집합과 배교할 객체
     * @return 비교할 집합과 동일한 경우 {@code true}
     *         else, {@code false}
     */
    boolean equals(Object o);

    /**
     * 현재 집합이 빈 상태(요소가 없는 상태)인지 여부를 반환합니다.
     *
     * @return 집합이 비어있는 경우 {@code true}
     *         else, {@code false}
     */
    boolean isEmpty();

    /**
     * 현재 집합의 요소의 개수를 반환합니다.
     * 만약 들어있는 요소가 {@code Integer.MAX_VALUE}를 넘을 경우 {@code Integer.MAX_VALUE}를 반환합니다.
     *
     * @return 집합에 들어있는 요소의 개수를 반환
     */
    int size();

    /**
     * 집합의 모든 요소를 제거합니다.
     */
    void clear();
}
