package classes.data

data class ClientB(val name: String, val postalCode: Int)

class Client(val name: String, val postalCode: Int) {
    // 인스턴스의 문자열 표현 재정의
    override fun toString(): String = "Client(name=$name, postalCode=$postalCode)"
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Client) return false
        return name == other.name && postalCode == other.postalCode
    }
    override fun hashCode(): Int = name.hashCode() * 31 + postalCode
}

fun main() {
    val client1 = Client("홍길동", 4122)
    val client2 = Client("홍길동", 4122)

    println(client1)
    println(client1 == client2) // equals() 정의 후 true

    /**
     * JVM 언어에서는 hashCode가 지켜야 하는
     * "eqauls()가 true를 반환하는 두 객체는 반드시 같은 hashCode()를 반환해야 한다" 라는 제약이 있다.
     *
     * HashSet은 원소를 비교할 때 비용을 줄이기 위해 먼저 객체의 해시 코드를 비교하고
     * 해시 코드가 같은 경우에만 실제 값을 비교한다.
     */

    val processed = hashSetOf(Client("오현석", 4122))
    println(processed.contains(Client("오현석", 4122))) // hashCode() 미정의 시 false

    val lee = ClientB("임꺽정", 5322)
    println(lee.copy(postalCode = 2000))
}