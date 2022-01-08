package lambdas.api

import java.io.File

/**
 * map이나 filter와 같은 컬렉션 함수는 결과 컬렉션을 즉시(eagerly) 생성한다. -> 즉시 계산 컬렉션
 * 이는 컬렉션 함수를 연쇄하면 매 단계마다 계산 중간 결과를 새로운 컬렉션에 임시로 담는다는 말이다.
 * 시퀀스(sequence)를 사용하면 중간 임시 컬렉션을 사용하지 않고도 컬렉션 연산을 연쇄할 수 있다.
 */

fun main() {
    val people = listOf(
        Person("Alice", 31), Person("Alfred", 28), Person("Bob", 29), Person("Carol", 31),
        Person("Dan", 31), Person("Dan", 24), Person("Edward", 29), Person("Frank", 21)
    )

    people.map(Person::name).filter { it.startsWith("A") }

    people.asSequence()                      // 원본 컬렉션을 시퀀스로 변환한다.
        .map(Person::name)                   // 시퀀스도 컬렉션과 똑같은 API를 제공
        .filter { it.startsWith("A") } // 시퀀스도 컬렉션과 똑같은 API를 제공
        .toList()                            // 결과 시퀀스를 다시 리스트로 변환한다.

    /**
     * 전체 연산을 수행한 결과는 같지만 시퀀스를 이용하면 중관 결과를 저장하는 컬렉션이 생기지 않기 때문에
     * 원소가 많은 경우 성능이 눈에 띄게 좋아진다.
     */

    listOf(1, 2, 3, 4).asSequence()
        .map { print("map($it) "); it * it }
        .filter { print("filter($it) "); it % 2 == 0 } // 최종 연산이 없어 아무 내용도 출력되지 않음

    // map과 filter 변환이 늦춰져서 결과를 얻을 필요가 있을 때(최종 연산이 호출될 때) 적용된다.

    listOf(1, 2, 3, 4).asSequence()
        .map { print("map($it) "); it * it }
        .filter { print("filter($it) "); it % 2 == 0 }
        .toList() // 최종 연산을 호출하면 연기됐던 모든 계산이 수행된다.

    /**
     * 직접 연산을 구현한다면 map 함수를 각 원소에 대해 먼저 수행해서 새 시퀀스를 얻고, 그 시퀀스에 대해 다시 filter를 수행
     * -> 컬렉션에 대한 map과 filter는 이와 같은 방식으로 작동
     *
     * 하지만 시퀀스에 대한 map과 filter는 그렇지 않다. 시퀀스의 경우 모든 연산은 각 원소에 대해 순차적으로 적용된다.
     */

    println(
        listOf(1, 2, 3, 4).asSequence()
            .map { it * it }.find { it > 3 }
    )

    /** ----------------------------- */

    // 시퀀스 만들기 -> generateSequence 함수
    val naturalNumbers = generateSequence(0) { it + 1 }
    val numbersTo100 = naturalNumbers.takeWhile { it <= 100 }
    println(numbersTo100.sum()) // 모든 지연 연산은 "sum"의 결과를 계산할 때 수행된다.


}

fun File.isInsideHiddenDirectory() =
    generateSequence(this) { it.parentFile }.any { it.isHidden }

// val file = File("/Users/svtk/.HiddenDir/a.txt")
// println(file.isInsideHiddenDirectory())