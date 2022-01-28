package conventions.delegate.map

/*
/** 값을 맵에 저장하는 프로퍼티 정의하기 */
class Person {
    // 추가 정보
    private val _attributes = hashMapOf<String, String>()
    fun setAttribute(attrName: String, value: String) {
        _attributes[attrName] = value
    }

    // 필수 정보
    val name: String
        get() = _attributes["name"]!!  // 수동으로 맵에서 정보를 꺼낸다.
}
*/

/** 값을 맵에 저장하는 위임 프로퍼티 사용하기 */
class Person {
    private val _attributes = hashMapOf<String, String>()
    fun setAttribute(attrName: String, value: String) {
        _attributes[attrName] = value
    }

    val name: String by _attributes  // 위임 프로퍼티로 맵을 사용
}

/**
 * 이런 코드가 작동하는 이유는 코틀린 stdlib가 Map과 MutableMap 인터페이스에 대해 getValue와 setValue 확장 함수를 제공하기 때문이다.
 */

fun main() {
    val p = Person()
    val data = mapOf("name" to "Dmitry", "company" to "JetBrains")
    for ((attrName, value) in data)
        p.setAttribute(attrName, value)

    println(p.name)  // Dmitry
}