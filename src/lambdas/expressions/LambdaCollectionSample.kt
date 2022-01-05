package lambdas.expressions

fun main() {
    val people = listOf(Person("Alice", 29), Person("Bob", 31))

    /** 컬렉션을 직접 검색하기 */
    findTheOldest(people)
    /** 람다를 사용해 컬렉션 검색 */
    println(people.maxByOrNull { it.age })
    /** 멤버 참조(더블 콜론)를 사용해 컬렉션 검색 */
    println(people.maxByOrNull(Person::age)) // 단지 함수나 프로퍼티를 반환하는 역할을 수행하는 람다는 멤버 참조로 대치 가능
}

fun findTheOldest(people: List<Person>) {
    var maxAge = 0
    var theOldest: Person? = null
    for (person in people) {
        if (person.age > maxAge) {
            maxAge = person.age
            theOldest = person
        }
    }
    println(theOldest)
}