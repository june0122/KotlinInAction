package highorder.controlflow

/** 람다 안의 return문: 람다를 둘러싼 함수로부터 반환 */

/** 일반 루프 안에서 return 사용하기 */
data class Person(val name: String, val age: Int)

val people = listOf(Person("Alice", 29), Person("Bob", 31))

fun lookForAlice(people: List<Person>) {
    for (person in people) {
        if (person.name == "Alice") {
            println("Found!")
            return
        }
    }
    println("Alice is not found")
}

/** forEach에 전달된 람다에서 return 사용 */
fun lookForAliceByForEach(people: List<Person>) {
    people.forEach {
        if (it.name == "Alice") {
            println("Found!")
            return
        }
    }
    println("Alice is not found")
}

fun main() {
    lookForAlice(people)
    lookForAliceByForEach(people)
}

/**
 * 람다 안에서 return을 사용하면 람다로부터만 반환되는 게 아니라 그 람다를 호출하는 함수가 실행을 끝내고 반환된다.
 * 그렇게 자신을 둘러싸고 있는 블록보다 더 바깥에 있는 다른 블록을 반환하게 만드는 return문을 넌로컬(non-local) return이라 부른다.
 *
 * 이렇게 return이 바깥쪽 함수를 반환시킬 수 있는 때는 람다를 인자로 받는 함수가 인라인 함수인 경우뿐이다.
 * 위의 forEach를 사용한 코드에서 forEach는 인라인 함수이므로 람다 본문과 함께 인라이닝된다.
 * 따라서 return 식이 바깥쪽 함수(여기선 lookForAliceByForEach)를 반환시키도록 쉽게 컴파일할 수 있다.
 *
 * 하지만 인라이닝되지 않는 함수에 전달되는 람다 안에서 return을 사용할 수는 없다.
 * 인라이닝되지 않는 함수는 람다를 변수에 저장할 수 있고, 바깥쪽 함수로부터 반환된 뒤에 저장해 둔 람다가 호출될 수도 있다.
 * 그런 경우 람다 안의 return이 실행되는 시점이 바깥쪽 함수를 반환시키기엔 너무 늦은 시점일 수도 있다.
 */