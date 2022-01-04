package classes.`object`

/** 동반 객체를 일반 객체처럼 사용하기 */
class Human(val name: String) {
    companion object Loader {
        fun fromJSON(jsonText: String): Human =
            Human(
                jsonText.substringAfter("'").substringBefore("'")
            )
    }
}

fun main() {
    val human = Human.Loader.fromJSON("{name: 'Dmitry'}")
    println(human.name)

    val human2 = Human.fromJSON("{name: 'Brent'}")
    println(human2.name)
}
