package typesystem.collections

/**
 * 코틀린 컬렉션과 자바 컬렉션을 나누는 가장 중요한 특성 하나:
 *      코틀린에서는 컬렉션 안의 데이터에 접근하는 인터페이스와 컬렉션 안의 데이터를 변경하는 인터페이스를 분리했다는 점
 *
 * Collection 인테페이스: 컬렉션 안 원소를 이터레이션하고, 컬렉션의 크기를 얻고, 어떤 값이 컬렉션 안에 들어있는지 검사,
 *                     컬렉션에서 데이터를 읽는 여러 다른 연산을 수행
 *
 * MutableCollection 인터페이스: 컬렉션의 데이터를 수정할 때 사용. 일반 인터페이스인 Collection을 확장하면서
 *                            컬렉션 내용을 변경하는 메서드를 더 제공
 */

fun <T> copyElements(source: Collection<T>, target: MutableCollection<T>) {
    for (item in source) {
        target.add(item) // 변경 가능한 target 컬렉션에 원소를 추가
    }
}

fun main() {
    val source: Collection<Int> = arrayListOf(3, 5, 7)
    val target: MutableCollection<Int> = arrayListOf(1)
    copyElements(source, target)
    println(target)
}