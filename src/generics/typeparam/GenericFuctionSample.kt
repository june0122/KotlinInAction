package generics.typeparam

/**
 * 제네릭스를 사용하면 "타입 파라미터(type parameter)"를 받는 타입을 정의할 수 있다.
 * ex) Map 클래스는 key 타입과 value 타입을 타입 파라미터로 받으므로 Map<K, V>가 된다.
 * 제네릭 타입의 인스턴스를 만들려면 타입 파라미터를 구체적인 "타입 인자(type argument)"로 치환해야 한다.
 * ex) Map과 같은 제네릭 클래스에 Map<String, Person>처럼 구체적인 타입을 타입 인자로 넘기면 타입을 인스턴스화할 수 있다.
 */

/**
 * 자바와 달리 코틀린에서는 제네릭 타입의 타입 인자를 프로그래머가 명시하거나 컴파일러가 추론할 수 있어야 한다.
 *
 * 자바는 맨 처음에 제네릭 지원이 없었고 자바 1.5에 뒤늦게 제네릭을 도입했기 때문에 이전 버전과 호환성을 유지하기 위해
 * 타입 인자가 없는 제네릭 타입(raw 타입)을 허용한다.
 * ex) 자바에서는 리스트 원소 타입을 지정하지 않고 List 타입의 변수를 선언할 수도 있다.
 *
 * 코틀린은 처음부터 제네릭을 도입했기 때문에 raw 타입을 지원하지 않고
 * 제네릭 타입 인자를 (프로그래머가 직접 정의하든, 타입 추론에 의해 자동으로 정의되든) 항상 정의해야 한다.
 */

/*
// fun 뒤에 함수의 타입 파라미터 T를 선언 -> 타입 파라미터가 수신 객체와 반환 타입에 사용
fun <T> List<T>.slice(indices: IntRange): List<T> {
    return listOf() // 코드 생략
}

// 제네릭 확장 프로퍼티
val <T> List<T>.penultimate: T // 모든 리스트 타입에 이 제네릭 확장 프로퍼티를 사용 가능
    get() = this[size - 2]

// println(listOf(1, 2, 3, 4).penultimate) // 이 호출에서 타입 파라미터 T는 Int로 추론된다.

 */


/**
 * 확장 프로퍼티만 제네릭하게 만들 수 있다.
 * 확장이 아닌 일반 프로퍼티는 타입 파라미터를 가질 수 없다. 클래스 프로퍼티에 여러 타입의 값을 저장할 수는 없으므로
 * 제네릭한 일반 프로퍼티는 말이 되지 않는다. 일반 프로퍼티를 제네릭하게 정의하면 컴파일러가 아래와 같은 오류를 표시한다.
 */

// Kotlin: Type parameter of a property must be used in its receiver type
// val <T> x: T = TODO()