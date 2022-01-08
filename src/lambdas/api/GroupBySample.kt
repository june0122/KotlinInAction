package lambdas.api

fun main() {
    val people = listOf(
        Person("Alice", 31), Person("Bob", 29), Person("Carol", 31),
        Person("Dan", 31), Person("Dan", 24), Person("Edward", 29), Person("Frank", 21)
    )

    println(people.groupBy { it.age })

    val list = listOf("a", "ab", "b")
    println(list.groupBy(String::first))

    println(list.groupBy(String::first).mapKeys { it.key.uppercase() })
    println(list.groupBy(String::first).mapValues { it.key.uppercase() })
}