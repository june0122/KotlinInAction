package classes.`object`


data class Person(val name: String) {
    // 클래스 안에 객체를 선언하여도 바깥 클래스의 인스턴스마다 중첩된 객체 선언에 해당하는 인스턴스가 하나씩 따로 생기는 것이 아니다.
    object NameComparator : Comparator<Person> {
        override fun compare(p1: Person, p2: Person): Int =
            p1.name.compareTo(p2.name)
    }
}

fun main() {
    val persons = listOf(Person("Bob"), Person("Alice"))
    println(persons.sortedWith(Person.NameComparator))
}
