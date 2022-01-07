package lambdas.expressions

/**
 *  멤버 참조는 그 멤버를 호출하는 람다와 같은 타입니다.
 *  함수 언어에서는 이런 경우를 에타 변환(eta conversion)이라 한다.
 *  에타 변환이란 함수 f와 람다 { x -> f(x) }를 서로 바꿔 쓰는 것을 뜻한다.
 */
val getAge1 = { person: Person -> person.age }
val getAge2 = Person::age

/** ------------------------------------------- */

// 최상위 함수
fun salute() = println("salute")
// run(::salute) // run은 인자로 받은 람다를 호출

/** ------------------------------------------- */

// 람다가 인자가 여럿인 다른 함수에게 작업을 위임하는 경우 람다를 정의하지 않고 직접 위임 함수에 대한 참조를 제공하면 편리하다.
val action = { person: Person, message: String -> // 이 람다는 sendEmail 함수에게 작업을 위임한다.
    sendEmail(person, message)
}

val nextAction = ::sendEmail // 람다 대신 멤버 참조를 쓸 수 있다.

fun sendEmail(person: Person, message: String) {
    // ..
}

/** ------------------------------------------- */

// 생성자 참조를 사용하면 클래스 생성 작업을 연기하거나 저장해둘 수 있다.

// data class Person(val name: String, val age: Int)
val createPerson = ::Person // Person의 인스턴스를 만드는 동작을 값으로 저장한다
val p = createPerson("Alice", 29)

// 확장 함수도 멤버 함수와 똑같은 방식으로 참조할 수 있다.
fun Person.isAdult() = age >= 21
val predicate = Person::isAdult

/** ------------------------------------------- */

/**
 * 바운드 멤버 참조를 사용하면 멤버 참조를 생성할 때 클래스 인스턴스를 함께 저장한 다음 나중에 그 인스턴스에 대해 멤버를 호출해준다.
 * 따라서 호출 시 수신 대상 객체를 별도로 지정해 줄 필요가 없다.
 *
 * personsAgeFunction은 인자가 하나이지만, dmitrysAgeFunction은 인자가 없는 함수라는 점을 유의
 */

val person = Person("Dmitry", 34)
val personsAgeFunction = Person::age
// println(personsAgeFunction(person))

val dmitrysAgeFunction = p::age // 코틀린 1.1부터 지원하는 바운드 멤버 참조
// println(dmitrysAgeFunction())