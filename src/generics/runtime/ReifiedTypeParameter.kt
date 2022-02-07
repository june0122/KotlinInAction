package generics.runtime

/**
 * 코틀린 제네릭 타입의 타입 인자 정보는 실행 타임에 지워진다.
 * 하지만 인라인 함수의 타입 파라미터는 실체화되므로 실행 시점에 인라인 함수의 타입 인자를 알 수 있다.
 *
 * 어떤 함수에 inline 키워드를 붙이면 컴파일러는 그 함수를 호출한 식을 모두 함수 본문으로 바꾼다.
 * 함수가 람다를 인자로 사용하는 경우 그 함수를 인라인 함수로 만들면 람다 코드도 함께 인라이닝되고,
 * 그에 따라 무명 클래스와 객체가 생성되지 않아서 성능이 더 좋아질 수 있다.
 */

/** 실체화한 타입 파라미터를 사용하는 함수 정의하기 */
inline fun <reified T> isA(value: Any) = value is T // reified를 통해 이제는 이 코드가 컴파일된다.

/*
println(isA<String>("abc")) // true
println(isA<String>(123)) // false
 */

/**
 * filterIsInstance 표준 라이브러리 함수 사용하기
 * 실체화한 타입 파라미터를 활용하는 가장 간단한 예제 중 하나는 표준 라이브러리 함수인 filterIsInstance다.
 * 이 함수는 인자로 받은 컬렉션의 원소 중에서 타입 인자로 지정한 클래스의 인스턴스만을 모아서 만든 리스트를 반환한다.
 */
val items = listOf("one", 2, "three")
// println(items.filterIsInstance<String>()) // [one, three]

/** filterIsInstance를 간단하게 정리한 버전 */
inline fun <reified T> Iterable<*>.filterIsInstance(): List<T> { // reified 키워드는 이 타입 파라미터가 실행 시점에 지워지지 않음을 표시한다.
    val destination = mutableListOf<T>()
    for (element in this) {
        if (element is T) { // 각 원소가 타입 인자로 지정한 클래스의 인스턴스인지 검사할 수 있다.
            destination.add(element)
        }
    }
    return destination
}

/**
 * 인라인 함수에서만 실체화한 타입을 쓸 수 있는 이유
 *
 * 컴파일러는 인라인 함수의 본문을 구현한 바이트코드를 그 함수가 호출되는 모든 지점에 삽입한다.
 * 컴파일러는 실체화한 타입 인자를 사용해 인라인 함수를 호출하는 각 부분의 정확한 타입 인자를 알 수 있다.
 * 따라서 컴파일러는 타입 인자로 쓰인 구체적인 클래스를 참조하는 바이트코드를 생성해 삽입할 수 있다.
 */

/*
// 결과적으로 위의 filterIsInstance<String> 호출은 다음과 동등한 코드를 만들어낸다.
for (element in this) {
    if (element is String) {
        destination.add(element)
    }
}
 */

/**
 * 타입 파라미터가 아니라 구체적인 타입을 사용하므로 만들어진 바이트코드는 실행 시점에 벌어지는 타입 소거의 영향을 받지 않는다.
 *
 * 자바 코드에서는 reified 타입 파라미터를 사용하는 inline 함수를 호출할 수 없다는 점을 기억하라.
 * 자바에서는 코틀린 인라인 함수를 다른 보통 함수처럼 호출한다. 그런 경우 인라인 함수를 호출해도 실제로 인라이닝 되지는 않는다.
 */

/**
 * 인라인 함수를 사용하는 경우
 *
 * 1. 함수의 파라미터 중에 함수 타입인 파라미터가 잇고 그 파라미터에 해당하는 인자(람다)를 함께 인라이닝함으로써 얻는 이익이 더 큰 경우 => 성능 향상
 * 2. 실체화한 타입 파라미터를 사용하기 위해
 *
 * 성능을 좋게하려면 인라인 함수의 크기를 계속 관찰해야 한다. 함수가 커지면 실체화한 타입에 의존하지 않는 부분을 별도의 일반 함수로 뽑아내는 편이 낫다.
 */