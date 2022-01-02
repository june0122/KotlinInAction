package lambdas

// 1. 변수나 데이터 구조에 할당
val function = { println("first-class test") }

// 2. 함수의 인자로 전달
fun function2(f: () -> Unit) {
    f.invoke()
}

// 3. 함수의 반환값으로 전달
fun function3() : () -> Unit {
    return function
}

fun main() {
    val a = function
    a.invoke()

    function2(a)

    function3().invoke()


}

//fun findAlice() = findPerson { it.name == "Alice"}
//fun findBob() = findPerson { it.name == "Bob"}





