package generics.variance

/**
 * 사용 지점 변성: 타입이 언급되는 지점에서 변성 지정
 *
 * 클래스를 선언하면서 변성을 지정하면 그 클래스를 사용하는 모든 장소에 변성 지정자가 영향을 끼치므로 편리하다.
 * 이런 방식을 선언 지점 변성(declaration site variance)이라 부른다.
 *
 * 자바의 와일드카드 타입(? extends나 ? super)에서 알 수 있듯 자바는 변성을 다른 방식으로 다룬다.
 * 자바에서는 타입 파라미터가 있는 타입을 사용할 때마다 해당 타입 파라미터를 하위 타입이나 상위 타입 중 어떤 타입으로 대치할 수 있는지 명시해야 한다.
 * 이런 방식을 사용 지점 변성(use-site variance)이라 부른다.
 */

/**
 * 코틀린도 사용 지점 변성을 지원한다. 따라서 클래스 안에서 어떤 타입 파라미터가 공변적이거나 반공변적인지 선언할 수 없는 경우에도
 * 특정 타입 파라미터가 나타나는 지점에서 변성을 정할 수 있다.
 *
 * MutableList와 같은 상당수의 인터페이스는 타입 파라미터로 지정된 타입을 소비하는 동시에 생산할 수 있기 때문에 일반적으로 공변적이지도 반공변적이지도 않다.
 * 하지만 그런 인터페이스 타입의 변수가 한 함수 안에서 생산자나 소비자 중 단 한 가지 역할만을 담당하는 경우가 자주 있다.
 */

import kotlin.collections.MutableList

/* 무공변 파라미터 타입을 사용하는 데이터 복사 함수 */
fun <T> copyData(source: MutableList<T>, destination: MutableList<T>) {
    for (item in source) {
        destination.add(item)
    }
}

/* 타입 파라미터가 둘인 데이터 복사 함수 */
fun <T : R, R> copySubTypeData(
    source: MutableList<T>, // // source 원소 타입은 destination 원소 타입의 하위 타입이어야 한다.
    destination: MutableList<R>
) {
    for (item in source) {
        destination.add(item)
    }
}

fun main() {
    val ints = mutableListOf(1, 2, 3)
    val anyItems = mutableListOf<Any>()

//    copyData(ints, anyItems)
    copySubTypeData(ints, anyItems) // int가 Any의 하위 타입이므로 이 함수를 호출할 수 있다.
}

/**
 * 두 타입 파라미터는 원본과 대상 리스트의 원소 타입을 표현한다.
 * 한 리스트에서 다른 리스트로 원소를 복사할 수 있으려면 원본 리스트 원소 타입은 대상 리스트 원소 타입의 하위 타입이어야 한다.
 *
 * 하지만 코틀린에는 이를 더 우아하게 표현할 수 있는 방법이 있다.
 * 함수 구현이 아웃 위치(또는 인 위치)에 있는 타입 파라미터를 사용하는 메서드만 호출한다면
 * 그런 정보를 바탕으로 함수 정의 시 타입 파라미터에 변성 변경자를 추가할 수 있다.
 */

/* out-프로젝션 타입 파라미터를 사용하는 데이터 복사 함수 */
fun <T> copyDataWithOutProjection(
    source: MutableList<out T>, // out 키워드를 타입을 사용하는 위치 앞에 붙이면 T 타입을 in 위치에 사용하는 메서드를 호출하지 않는다는 뜻이다.
    destination: MutableList<T>
) {
    for (item in source) {
        destination.add(item)
    }
}

/**
 * 타입 선언에서 타입 파라미터를 사용하는 위치라면 어디에나 변성 변경자를 붙일 수 있다.
 * 따라서 파라미터 타입, 로컬 변수 타입, 함수 반환 타입 등에 타입 파라미터가 쓰이는 경우, in이나 out 변경자를 붙일 수 있다.
 * 이때 타입 프로젝션(type projection)이 일어난다.
 *
 * 즉 source를 일반적인 MutableList가 아니라 MutableList를 프로젝션을 한(제약을 가한) 타입으로 만든다.
 * 이 경우 copyDataWithOutProjection 함수는 MutableList의 메서드 중에서 반환 타입으로 타입 파라미터 T를 사용하는 메서드만 호출할 수 있다.
 * 컴파일러는 타입 파라미터 T를 함수 인자 타입으로 사용하지 못하게 막는다.
 */

/*
>>> val list: MutableList<out Number> = /* ... */
>>> list.add(42)
Error: Out-projected type 'MutableList<out Number>' prohibits the use of 'fun add(element: E): Boolean'
 */

/* in-프로젝션 타입 파라미터를 사용하는 데이터 복사 함수 */
fun <T> copyDataWithInProjection(
    source: MutableList<T>,
    destination: MutableList<in T> // 원본 리스트 원소 타입의 상위 타입을 대상 리스트 원소 타입으로 허용한다.
) {
    for (item in source) {
        destination.add(item)
    }
}

/**
 * 코틀린의 사용 지점 변성 선언은 자바의 한정 와일드카드(bounded wildcard)와 똑같다.
 * 코틀린 MutableList<out T>는 자바 MutableList<? extends T>와 같고
 * 코틀린 MutableList<in T>는 자바 MutableList<? super T>어ㅣ 깉디/
 */