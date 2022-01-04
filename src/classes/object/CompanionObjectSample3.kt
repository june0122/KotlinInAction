package classes.`object`

/** 동반 객체에서의 인터페이스 구현 */
interface JSONFactory<T> {
    fun fromJSON(jsonText: String): T
}

class Animal(val name: String) {
    companion object : JSONFactory<Animal> {
        override fun fromJSON(jsonText: String): Animal {
            // 구현 코드
            return Animal("Sample Code")
        }
    }
}

fun <T> loadFromJSON(factory: JSONFactory<T>): T {
    // 구현 코드
    return factory.fromJSON("Sample Code")
}

fun main() {
    // 동반 객체가 구현한 JSONFactory 인스턴스를 넘길 때 Animal 클래스의 이름을 사용
    // 동반 객체를 둘러싼 클래스를 넘겼다는 것에 유의
    loadFromJSON(Animal)
}