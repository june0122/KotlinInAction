package generics.typeparam

import java.lang.Appendable

/**
 * 타입 파라미터 제약(type parameter constraint)은 클래스나 함수에 사용할 수 있는 타입 인자를 제한하는 기능이다.
 *
 * 어떤 타입을 제네릭 타입의 타입 파라미터에 대한 상한(upper bound)으로 지정하면 그 제네릭 타입을 인스턴스화할 때 사용하는 타입 인자는
 * 반드시 그 상한 타입이거나 그 상한 타입의 하위 타입이어야 한다.
 *
 * 제약을 가하려면 타입 파라미터 이름 뒤에 콜론(:)을 표시하고 그 뒤에 상한 타입을 적으면 된다.
 *
 * 코틀린에서는 예시로 fun <T : Number> List<T>.sum(): T
 * 자바에서는 <T extends Number> T sum (List<T> list) 처럼 extends를 써서 같은 개념을 표현한다.
 */

/**
 * 타입 파라미터 T에 대한 상한을 정하고 나면 T 타입의 값을 그 상한 타입의 값으로 취급 가능하다.
 * 예를 들어 상한 타입에 정의된 메서드를 T 타입 값에 대해 호출 가능하다.
 */
fun <T : Number> oneHalf(value: T): Double { // Number를 타입 파라미터 상한으로 정한다.
    return value.toDouble() / 2.0 // Number 클래스에 정의된 메서드를 호출
}

/** 타입 파라미터를 제약하는 함수 선언 */
fun <T : Comparable<T>> max(first: T, second: T): T { // 이 함수의 인자들은 비교 가능해야 한다.
    return if (first > second) first else second
}

/** 타입 파라미터에 여러 제약을 가하기 */
// 표준 StringBuilder나 java.nio.CharBuffer 클래스 등에 이 함수를 사용 가능
// 이 예제는 타입 인자가 CharSequence와 Appendable 인터페이스를 반드시 구현해야 한다는 사실을 표현한다.
// 이는 데이터에 접근하는 연산(endsWith)과 데이터를 변환하는 연산(append)을 T 타입의 값에게 수행할 수 있다는 뜻이다.
fun <T> ensureTailingPeriod(seq: T) where T : CharSequence, T : Appendable { // 타입 파라미터 제약 목록이다.
    if (!seq.endsWith('.')) { // CharSequence 인터페이스의 확장 함수 호출
        seq.append('.') // Appendable 인터페이스의 확장 함수 호출
    }
}

fun main() {
    println(oneHalf(3)) // 1.5

    println(max("kotlin", "java")) // 문자열은 알파벳 순으로 비교된다. -> kotlin

    val helloWorld = StringBuilder("Hello World")
    ensureTailingPeriod(helloWorld)
    println(helloWorld) // Hello World.
}

