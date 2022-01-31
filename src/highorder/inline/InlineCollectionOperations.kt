package highorder.inline

/**
 * 컬렉션 연산 인라이닝
 */

data class Person(val name: String, val age: Int)

val people = listOf(Person("Alice", 29), Person("Bob", 31))

fun main() {
    /** 람다를 사용해 컬렉션 거르기 */
    println(people.filter { it.age < 30 })

    /** 직접 컬렉션 거르기  */
    val result = mutableListOf<Person>()
    for (person in people) {
        if (person.age < 30) result.add(person)
    }
    println(result)
}

/**
 * 코틀린의 filter 함수는 인라인 함수다.
 * 따라서 filter 함수의 바이트코드는 그 함수에 전달된 람다 본문의 바이트코드와 함께 filter를 호출한 위치에 들어간다.
 * 그 결과 앞 예제에서 filter를 써서 생긴 바이트코드와 뒤 예제에서 생긴 바이트코드는 거의 같다.
 * 그러므로 코틀린다운 연산을 컬렉션에 대해 안전하게 사용할 수 있고, 코틀린이 제공하는 함수 인라이닝을 믿고 성능에 신경 쓰지 않아도 된다.
 */

// println(people.filter { it.age > 30 }.map(Person::name)) // Bob

/**
 * filter와 map을 연쇄해서 사용하면?
 *
 * 위 예제는 람다 식과 멤버 참조를 사용한다. filter와 map은 둘 다 인라인 함수다. 따라서 두 함수의 본문은 인라이닝되며, 추가 객체나 클래스 생성은 없다.
 * 하지만 이 코드는 리스트를 걸러낸 결과를 저장하는 중간 리스트를 만든다.
 * filter 함수에서 만들어진 코드는 원소를 그 중간 리스트에 추가하고, map 함수에서 만들어진 코드는 그 중간 리스트를 읽어서 사용한다.
 *
 * 처리할 원소가 많아지면 중간 리스트를 사용하는 부가 비용도 걱정할 만큼 커진다.
 * 이 문제는 "asSequence"를 통해 리스트 대신 시퀀스를 사용하면 중간 리스트로 인한 부가 비용은 줄어든다.
 * 이때 각 중간 시퀀스는 람다를 필드에 저장하는 객체로 표현되며, 최종 연산은 중간 시퀀스에 있는 여러 람다를 연쇄 호출한다.
 * 따라서 시퀀스는 (람다를 저장해야 하므로) 람다를 인라인하지 않는다. 그러므로 지연 계산을 통해 성능을 향상시키려는 이유로 모든 컬렉션 연산에 asSequence를 붙여서는 안 된다.
 * 시퀀스 연산에서는 람다가 인라이닝되지 않기 때문에 크기가 작은 컬렉션은 오히려 일반 컬렉션 연산이 더 성능이 나을 수도 있다.
 * 시퀀스를 통해 성능을 향상시킬 수 있는 경우는 컬렉션 크기가 큰 경우뿐이다.
 */
