package conventions.comparison

/**
 * 자바에서 정렬이나 최댓값, 최솟값 등 값을 비교해야 하는 알고리즘에 사용할 클래스는 Comparable 인터페이스를 구현해야 한다.
 * Comparable에 들어있는 compareTo 메서드는 한 객체와 다른 객체의 크기를 비교해 정수로 나타내준다.
 *
 * 자바에는 이 메서드를 짧게 호출할 방법이 없지만 코틀린은 compareTo 메서드를 호출하는 관례를 제공한다.
 * 비교 연산자는 compareTo 호출로 컴파일 된다 : [ a >= b ]  ->  [ a.compareTo(b) >= 0 ]
 */

class Person(
    val firstName: String, val lastName: String
) : Comparable<Person> {
    override fun compareTo(other: Person): Int {
        // compareValuesBy 함수: 두 객체와 여러 비교 함수를 인자로 받는다.
        return compareValuesBy(this, other, Person::lastName, Person::firstName)
    }
}

/** 필드를 직접 비교하면 코드는 조금 더 복잡해지지만 비교 속도는 훨씬 더 빨라진다는 사실을 기억하자. */

fun main() {
    println("abc" < "bac") // true
}
