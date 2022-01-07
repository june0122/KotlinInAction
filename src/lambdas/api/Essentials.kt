package lambdas.api

data class Person(val name: String, val age: Int)

// 참/거짓을 반환하는 함수를 술어(predicate)라고 한다
public inline fun <T> Iterable<T>.filter(predicate: (T) -> Boolean): List<T> {
    return filterTo(ArrayList<T>(), predicate)
}

fun main() {
    val list = listOf(1, 2, 3, 4)
    println(list.filter { it % 2 == 0 })

    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    println(people.filter { it.age > 30 })

    println(list.map { it * it })

    println(people.map { it.name })
    println(people.map(Person::name)) // 위 코드를 멤버 참조를 이용해 작성

    // 30살 이상인 사람의 이름을 호출
    people.filter { it.age > 30 }.map(Person::age)

    // 가장 나이가 많은 사람의 이름
    people.filter { it.age == people.maxByOrNull(Person::age)!!.age }
    // 최댓값 구하는 작업을 반복하는 단점을 개선한 코드
    val maxAge = people.maxByOrNull { it.age }!!.age
    people.filter { it.age == maxAge }

    val numbers = mapOf(0 to "zero", 1 to "one")
    println(numbers.mapValues { it.value.uppercase() })
}

