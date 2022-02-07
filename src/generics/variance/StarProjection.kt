package generics.variance

import java.util.*
import kotlin.collections.MutableList

/** 스타 프로젝션: 타입 인자 대신 * 사용 */

fun main() {
    val list: MutableList<Any?> = mutableListOf('a', 1, "qwe")
    val chars = mutableListOf('a', 'b', 'c')
    val unknownElements: MutableList<*> = // MutableList<*>는 MutableList<Any?>와 같지 않다.
        if (Random().nextBoolean()) list else chars
    // unknownElements.add(42) // 컴파일러는 이 메소드 호출을 금지한다.
    // Error: Out-projected type 'MutableList<*>' prohibits the use of 'fun add(element: E): Boolean'
    println(unknownElements.first()) // 원소를 가져와도 안전하다. first()는 Any? 타입의 원소를 반환한다.
}

/**
 * 왜 컴파일러가 MutableList<*>를 아웃 프로젝션 타입으로 인식할까? 이 맥락에서 MutableList<*>는 MutableList<out Any?>처럼 동작한다.
 * 어떤 리스트의 원소 타입을 모르더라도 그 리스트에서 안전하게 Any? 타입의 원소를 꺼내올 수는 있지만(Any?는 모든 코틀린 타입의 상위 타입입다),
 * 타입을 모르는 리스트에 원소를 마음대로 넣을 수는 없다. 자바 와일드카드에 대해 이야기하자면 코틀린의 MyType<*>는 자바의 MyType<?>에 대응한다.
 *
 * Consumer<in T>와 같은 반공변 타입 파라미터에 대한 스타 프로젝션은 <in Nothing>과 동등하다.
 * 결과적으로 그런 스타 프로젝션에서는 T가 시그니처에 들어가 있는 메서드를 호출할 수 없다.
 * 타입 파라미터가 반공변이라면 제네릭 클래스는 소비자 역할을 하는데, 우리는 그 클래스가 정확히 T의 어떤 부분을 사용할지 알 수 없다.
 * 따라서 반공변 클래스에 무언가를 소비하게 넘겨서는 안 된다.
 */

/**
 * 타입 파라미터를 시그니처에서 전혀 언급하지 않거나 데이터를 읽기는 하지만 그 타입에는 관심이 없는 경우와 같이 타입 인자 정보가 중요하지 않을 때
 * 스타 프로젝션 구문을 사용할 수 있다.
 */

fun printFirst(list: List<*>) { // 모든 리스트를 인자로 받을 수 있다.
    if (list.isNotEmpty()) { // isNotEmpty()에서는 제네릭 타입 파라미터를 사용하지 않는다.
        println(list.first()) // first()는 이제 Any?를 반환하지만 여기서는 그 타입만으로 충분하다.
    }
}

// printFirst(listOf("Svetlana", "Dmitry"))

/* 스타 프로젝션 우회하기: 제네릭 타입 파라미터를 도입 */
fun <T> printFirstByGenericTypeParam(list: List<T>) {
    if (list.isNotEmpty()) {
        println(list.first())
    }
}

/**
 * 스타 프로젝션을 쓰는 족이 더 간결하지만 제네릭 타입 파라미터가 어떤 타입인지 굳이 알 필요가 없을 때만 스타 프로젝션을 사용할 수 있다.
 * 스타 프로젝션을 사용할 때는 값을 만들어내는 메서드만 호출할 수 있고 그 값의 타입에는 신경을 쓰지 말아야 한다.
 */
