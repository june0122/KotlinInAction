package conventions.destructuring

import conventions.arithmetic.Point

/**
 * 구조 분해 선언: 복합적인 값을 분해해서 여러 다른 변수를 한꺼번에 초기화할 수 있다.
 *
 * data 클래스의 주 생성자에 들어있는 프로퍼티에 대해서는 컴파일러가 자동으로 componentN 함수를 만들어준다.
 */

fun main() {
    val p = Point(10, 20)
    val (x, y) = p

    val a = p.component1()
    val b = p.component2()

    println("$x, $y")
    println("$a, $b")
}

/** 데이터 타입이 아닌 클래스에서 componentN 함수를 구현하는 방법 */
class Point(val x: Int, val y: Int) {
    operator fun component1() = x
    operator fun component2() = y
}

/** 구조 분해 선언을 사용해 여러 값 반환하기 */
data class NameComponents(val name: String, val extension: String)

/*
fun splitFilename(fullName: String): NameComponents {
    val result = fullName.split('.', limit = 2)
    return NameComponents(result[0], result[1])
}
*/

/*
    val (name, ext) = splitFilename("example.kt")
    println(name) // example
    println(ext) // kt
*/

/**
 * 배열이나 컬렉션에도 componentN 함수가 있으므로 위 예제를 더 개선할 수 있다.
 * 크기가 정해진 컬렉션을 다루는 경우 구조 분해가 특히 더 유용하다.
 */
fun splitFilename(fullName: String): NameComponents {
    val (name, extension) = fullName.split('.', limit = 2)
    return NameComponents(name, extension)
}

/**
 * 물론 무한히 componentN을 선언할 수는 없으며, 코틀린 표준 라이브러리에서는 맨 앞의 다섯 원소에 대한 componentN을 제공한다.
 *
 * 표준 라이브러리의 Pair나 Triple 클래스를 사용하면 함수에서 여러 값을 더 간단하게 반환할 수 있다.
 * Pair와 Triple은 그 안에 담겨있는 원소의 의미를 말해주지 않으므로 경우에 따라 가독성이 떨어질 수 있는 반면,
 * 직접 클래스를 작성할 필요가 없으므로 코드는 더 단순해진다.
 */


/** 구조 분해 선언을 사용해 맵 이터레이션하기 */
fun printEntries(map: Map<String, String>) {
    for((key, value) in map) {
        println("$key -> $value")
    }
}

/*
val map = mapOf("Oracle" to "Java", "JetBrains" to "Kotlin")
printEntries(map)
*/

// Oracle -> Java
// JetBrains -> Kotlin

/**
 * 위 예제는 두 가지 코틀린 관례를 활용한다.
 * 1. 객체를 이터레이션하는 관례
 * 2. 구조 분해 선언
 *
 * 코틀린 표준 라이브러리에는 맵에 대한 확장 함수로 iterator가 들어있다. 그 iterator는 맵 원소에 대한 이터레이터를 반환한다.
 * 따라서 자바와 달리 코틀린에서는 맵을 직접 이터레이션할 수 있다.
 *
 * 또한 코틀린 라이브러리는 Map.Entry에 대한 확장 함수로 component1과 component2를 제공한다.
 */

/** 위의 printEntries 내부 루프는 이런 확장 함수를 사용하는 다음 코드와 같다. */
/*
for (entry in map.entries) {
    val key = entry.component1()
    val value = entry.component2()
    // ...
}
*/