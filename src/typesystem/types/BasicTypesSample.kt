package typesystem.types

/**
 * 자바에서 Object가 클래스 계층의 최상위 타입
 * 코틀린에서는 Any 타입이 모든 널이 될 수 없는 타입의 조상 타입
 *
 * 자바에서는 참조 타입만 Object를 정점으로 하는 타입 계층에 포함되며, 원시 타입은 그런 계층에 들어있지 않다.
 * -> 이는 자바에서 Object 타입의 객체가 필요할 경우 원시 타입을 래퍼 타입으로 감싸야만 한다는 뜻이다.
 *
 * 하지만 코틀린에서는 Any가 Int 등의 원시 타입을 포함한 모든 타입의 조상 타입이다.
 */

fun main() {
    val answer: Any = 42
}

/** 함수의 반환 타입이 Unit이면 반환 타입 선언 없이 정의한 블록이 본문이 함수와 같다. */
//fun f(): Unit {}
//fun f() {}

/**
 * 코틀린의 Unit과 자바의 void의 다른 점
 * - Unit은 모든 기능을 갖는 일반적인 타입이며, void와 달리 Unit을 타입 인자로 사용 가능
 * - Unit 타입의 함수는 Unit 값을 묵시적으로 반환
 *
 * -> 이 두 특성은 제네릭 파라미터를 반환하는 함수를 오버라이드하면서 반환 타입으로 Unit을 쓸 때 유용
 */

interface Processor<T> {
    fun process(): T
}

class NoResultProcessor: Processor<Unit> {
    override fun process() { // Unit을 반환하지만 타입을 지정할 필요는 없다.
        // 코드

        // return을 명시할 필요가 없다 -> 컴파일러가 묵시적으로 return Unit을 넣어준다.
    }
}

/**
 * Nothing 타입: 이 함수는 결코 정상적으로 끝나지 않는다.
 *
 * 예1) 테스트 라이브러리들은 fail이라는 함수를 제공하는 경우가 많은데, 특별한 메시지가 들어있는 예외를 던져서 현재 테스트를 실패시킨다.
 * 예2) 무한 루프를 도는 함수도 정상적으로 끝나지 않는다.
 *
 * -> 위와 같은 함수를 호출하는 코드를 분석하는 경우 함수가 정상적으로 끝나지 않는다는 사실을 표현하기 위해
 *    코틀린에서는 Nothing이라는 특별한 반환 타입이 있어 유용하게 사용할 수 있다.
 *
 * Nothing 타입은 아무 값도 포함하지 않는다
 * -> 따라서 함수의 반환 타입이나 반환 타입으로 쓰일 타입 파라미터로만 쓸 수 있다.
 */

fun fail(message: String): Nothing {
    throw IllegalArgumentException(message)
}

// fail("Error occurred")
// java.lang.IllegalArgumentException: Error occurred