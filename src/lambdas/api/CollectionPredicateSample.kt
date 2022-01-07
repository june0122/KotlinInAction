package lambdas.api

val canBeInClub27 = { p: Person -> p.age <= 27 }

fun main() {
    val people = listOf(Person("Alice", 27), Person("Bob", 31))
    println(people.all(canBeInClub27)) // 모든 원소가 술어를 만족하는지
    println(people.any(canBeInClub27)) // 술어를 만족하는 원소가 하나라도 있는지

    /**
     * 함수를 적재적소에 사용하기 : count와 size
     *
     * count를 사용하지 않고 필터링한 결과의 크기를 가져오는 경우가 있다.
     * 하지만 이렇게 처리할 경우 조건을 만족하는 모든 원소가 들어가는 중간 컬렉션이 생긴다.
     * 반면 count는 조건을 만족하는 원소의 개수만을 추적하지 조건을 만족하는 원소를 따로 저장하지 않는다.
     * 따라서 count가 더 효율적이다.
     */

    println(people.count(canBeInClub27))
    println(people.filter(canBeInClub27).size)


    // 술어를 만족하는 원소를 하나만 찾고 싶으면 find 함수를 사용한다.
    println(people.find(canBeInClub27))
    // find는 firstOrNull과 같으며, 조건을 만족하는 원소가 없으면 null이 나온다는 사실을 더 명확히 할 때 쓸 수 있다.
    println(people.firstOrNull(canBeInClub27))
}