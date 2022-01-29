package highorder.declaration

import java.lang.StringBuilder

/*
// 하드 코딩을 통해 toString 사용 관례를 따르는 joinToString
fun <T> Collection<T>.jointToString(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element) // 기본 toString 메서드를 사용해 객체를 문자열로 반환
    }
    result.append(postfix)
    return result.toString()
}
*/

/** 함수 타입의 파라미터에 대한 디폴트 값 지정하기 */
//fun <T> Collection<T>.jointToString(
//    separator: String = ", ",
//    prefix: String = "",
//    postfix: String = "",
//    transform: (T) -> String = { it.toString() } // 함수 타입 파라미터를 선언하면서 람다를 디폴트 값으로 지정
//): String {
//    val result = StringBuilder(prefix)
//    for ((index, element) in this.withIndex()) {
//        if (index > 0) result.append(separator)
//        result.append(transform(element)) // "transform" 파라미터로 받은 함수를 호출
//    }
//    result.append(postfix)
//    return result.toString()
//}

fun main() {
    val letters = listOf("Alpha", "Beta")

    println(letters.jointToString()) // 디폴트 변환 함수 사용 -> "Alpha, Beta"

    println(letters.jointToString { it.toLowerCase() }) // 람다를 인자로 전달 -> "alpha, beta"

    // 이름 붙은 인자 구문을 사용해 람다를 포함하는 여러 인자를 전달한다. -> "ALPHA! BETA! "
    println(letters.jointToString(separator = "! ", postfix = "! ", transform = { it.toUpperCase() }))
}

/** 널이 될 수 있는 함수 타입 파라미터를 사용하기 */
fun <T> Collection<T>.jointToString(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = "",
    transform: ((T) -> String)? = null // 널이 될 수 있는 함수 타입의 파라미터를 선언
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        val str = transform?.invoke(element) // 안전 호출을 사용해 함수를 호출
            ?: element.toString() // 엘비스 연산자를 사용해 람다를 인자로 받지 않은 경우 처리
        result.append(str)
    }
    result.append(postfix)
    return result.toString()
}

