package classes.`object`

// 비즈니스 로직 모듈
class Plant(val name: String, val color: String) {
    companion object {
    }
}

// 클라이언트/서버 통신 모듈
fun Plant.Companion.fromJSON(json: String): Plant { // 확장 함수 선언
    // ...
    return Plant("Sample", "Code")
}

fun main() {
    val json = "Sample"
    val p = Plant.fromJSON(json)
}