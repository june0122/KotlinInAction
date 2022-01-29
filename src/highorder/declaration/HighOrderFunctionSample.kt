package highorder.declaration

/**
 * 고차 함수: 람다나 함수 참조를 인자로 넘길 수 있거나 반환하는 함수
 */

/** 함수 타입 */
fun main() {
    val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y } // Int 파라미터 2개를 받아서 Int 값을 반환하는 함수
    val action: () -> Unit = { println(42) } // 아무 인자도 받지 않고 아무 값도 반환하지 않는 함수

    var canReturnNull: (Int, Int) -> Int? = { x, y -> null }
    var funOrNull: ((Int, Int) -> Int)? = null

    println("ab1c".filter { it in 'a'..'z' })
}

/** 파라미터 이름과 함수 타입 : 함수 타입에서 파라미터 이름을 지정 */
fun performRequest(
    url: String,
    callback: (code: Int, content: String) -> Unit // 함수 타입의 각 파라미터에 이름을 붙인다.
) {
    /* ... */
}

/*
    val url = "http://kotl.in"
    performRequest(url) { code, content -> /* ... */ } // API에서 제공하는 이름을 람다에 사용할 수 있다.
    performRequest(url) { code, page -> /* ... */ } // 하지만 그냥 원하는 다른 이름을 붙여도 된다.
*/

/** 인자로 받은 함수 호출 */
fun twoAndThree(operation: (Int, Int) -> Int) { // 함수 타입인 파라미터를 선언한다.
    val result = operation(2, 3) // 함수 타입인 파라미터를 호출한다.
    println("The result is $result")
}

/*
    twoAndThree { a, b -> a + b } // The result is 5
    twoAndThree { a, b -> a * b } // The result is 6
*/

// stlib 함수인 filter를 단순하게 구현
fun String.filter(predicate: (Char) -> Boolean): String {
    val sb = StringBuilder()
    for (index in 0 until length) {
        val element = get(index)
        if (predicate(element)) sb.append(element) // predicate 파라미터로 전달받은 함수를 호출
    }
    return sb.toString()
}

// println("ab1c".filter { it in 'a'..'z' }) // 람다를 predicate 파라미터로 전달
