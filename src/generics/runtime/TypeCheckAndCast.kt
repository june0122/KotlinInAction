package generics.runtime

/**
 * JVM의 제네릭스는 보통 타입 소거(type eraser)를 사용해 구현된다.
 * 이는 실행 시점에 제네릭 클래스의 인스턴스에 타입 인자 정보가 들어있지 않다는 뜻이다.
 *
 * 코틀린 타입 소거는 함수를 inline으로 선언함으로써 타입 인자가 지워지지 않게 할 수 있다 (코틀린에서 이를 실체화 reify 라고 부른다).
 */

/**
 * 자바와 마찬가지로 코틀린 제네릭 타입 인자 정보는 런타임에 지워진다.
 * 이는 제네릭 클래스 인스턴스가 그 인스턴스를 생성할 때 쓰인 타입 인자에 대한 정보를 유지하지 않는다는 뜻이다.
 * 예) List<String> 객체를 만들고 그 안에 문자열을 여럿 넣더라도 실행 시점에는 그 객체를 오직 List로만 볼 수 있다.
 *    그 List 객체가 어떤 타입의 원소를 저장하는지 실행 시점에는 알 수 없다.
 */

// 실행 시점에 각 객체는 단지 List일 뿐이다.
val list1: List<String> = listOf("a", "b")
val list2: List<Int> = listOf(1, 2, 3)

/**
 * 타입 소거로 인해 생기는 한계점
 * 타입 인자를 따로 저장하지 않기 때문에 실행 시점에 타입 인자 검사 불가
 *
 * 다만 저장해야하는 타입 정보의 크기가 줄어들어서 전반적인 메모리 사용량이 줄어든다는 제네릭 타입 소거 나름의 장점이 있다.
 */

// ERROR: Cannot check for instance of erased type
// if (value is List<String>) { ... }

/** 제네릭 타입으로 타입 캐스팅하기 */
fun printSum(c: Collection<*>) {
    val intList = c as? List<Int> // 실행 시점에는 제네릭 타입의 타입 인자를 알 수 없으므로 캐스팅은 항상 성공한다. -> unchecked cast 경고
        ?: throw IllegalArgumentException("List is expected")
    println(intList.sum())
}

/** 알려진 타입 인자를 사용해 타입 검사하기 */
// 코틀린 컴파일러는 컴파일 시점에 타입 정보가 주어진 경우에는 is 검사를 수행할 수 있다.
fun printSum2(c: Collection<Int>) {
    if (c is List<Int>) { // 이 검사는 올바르다.
        println(c.sum())
    }
}

fun main() {
    printSum(listOf(1, 2, 3)) // 6
    printSum(setOf(1, 2, 3)) // IllegalArgumentException: List is expected
    printSum(listOf("a", "b", "c")) // ClassCastException: String cannot be cast to Number
}