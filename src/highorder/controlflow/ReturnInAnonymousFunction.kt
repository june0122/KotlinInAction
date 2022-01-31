package highorder.controlflow

/** 무명 함수: 기본적으로 로컬 return */

/** 무명 함수 안에서 return 사용하기 */
fun lookForAliceWithAnonymousFunc(people: List<Person>) {
    people.forEach(fun (person) { // 람다 식 대신 무명 함수를 사용
        if (person.name == "Alice") return // "return"은 가장 가까운 함수를 가리키는데 이 위치에서 가장 가까운 함수는 무명 함수다.
        println("${person.name} is not Alice")
    })
}

fun main() {
    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    lookForAliceWithAnonymousFunc(people)

    /** filter에 무명 함수 넘기기 */
    people.filter(fun (person): Boolean {
        return person.age < 30
    })

    /** 식이 본문인 무명 함수 사용하기 */
    people.filter(fun (person) = person.age < 30)
}

/**
 * 무명 함수 안에서 레이블이 붙지 않은 return 식은 무명 함수 자체를 반환시킬 뿐 무명 함수를 둘러싼 다른 함수를 반환시키지 않는다.
 */