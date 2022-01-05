package lambdas.expressions

fun main() {
    val sum = { x: Int, y: Int -> x + y }
    println(sum(1, 2))
    run { println(42) } // run은 인자로 받은 람다를 실행해주는 라이브러리 함수

    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    people.maxByOrNull { it.age }

    // 정식으로 작성한 람다
    people.maxByOrNull({ p: Person -> p.age })
    people.maxByOrNull() { p: Person -> p.age }
    people.maxByOrNull { p: Person -> p.age }
    people.maxByOrNull { p -> p.age } // 파라미터 타입 생략 (컴파일러가 추론)
    people.maxByOrNull { it.age }

//    val names = people.joinToString(separator = " ", transform = { p: Person -> p.name })
    val names = people.joinToString(" ") { p: Person -> p.name }
    println(names)
}