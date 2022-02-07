package generics.variance

/**
 * 반공변성: 뒤집힌 하위 타입 관계
 * 반공변성(contravariance)은 공변성과 반대되는 개념이다. 반공변 클래스의 하위 타입 관계는 공변 클래스의 경우와 반대다.
 */

interface Comparator<in T> {
    fun compare(e1: T, e2: T) { /* ... */
    } // T를 인 위치에 사용한다. 즉 이 메소드는 T 타입의 값을 소비하기만 한다.
}

fun main() {
    val anyComparator = Comparator<Any> { e1, e2 ->
        e1.hashCode() - e2.hashCode()
    }
    val strings: List<String> = listOf("...")
    strings.sortedWith(anyComparator) // 문자열과 같은 구체적인 타입의 객체를 비교하기 위해 모든 객체를 비교하는 Comparator를 사용할 수 있다.
}

/**
 * 타입 B가 타입 A의 하위 타입인 경우 Consumer<A>가 Consumer<B>의 하위 타입인 관계가 성립하면
 * 제네릭 클래스 Consumer<T>는 타입 인자 T에 대해 반공변이다.
 * 여기서 A와 B의 위치가 서로 뒤바뀐다는 점에 유의하라. 따라서 하위 타입 관계가 뒤집힌다고 말한다.
 */

/**
 * 클래스나 인터페이스가 어떤 타입 파라미터에 대해서는 공변적이면서 다른 타입 파라미터에 대해서는 반공변적일 수도 있다.
 * Function 인터페이스가 고전적인 예로, 다음 선언은 파라미터가 하나 뿐인 Function 인터페이스인 Fuction1이다.
 * 코틀린 표기에서 (P) -> R은 Function1<P, R>을 더 알아보기 쉽게 적은 것일 뿐이다.
 */
interface Function1<in P, out R> {
    operator fun invoke(p: P): R
}

/*
fun enumerateCats(f: (Cat) -> Number) { /* ... */ }
fun Animal.getIndex(): Int = ...

>>> enumerateCats(Animal::getIndex) // Animal은 Cat의 상위 타입이며 int는 Number의 하위 타입이므로, 이 코드는 올바른 코틀린 식이다.
 */

/** 함수 타입 (T) -> R은 인자의 타입에 대해서는 반공변적이면서 반환 타입에 대해서는 공변적이다. */