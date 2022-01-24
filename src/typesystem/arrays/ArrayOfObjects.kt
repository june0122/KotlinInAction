package typesystem.arrays

/**
 * 다른 제네릭 타입에서처럼 배열 타입의 타입 인자도 항상 객체 타입이 된다.
 * 따라서 Array<Int> 같은 타입을 선언하면 그 배열은 박싱된 정수의 배열(자바 타입은 java.lang.Integer[])이다.
 * 박싱하지 않은 원시 타입의 배열이 필요하다면 그런 타입을 위한 특별한 배열 클래스를 사용해야 한다,
 *
 * -> 코틀린은 원씨 타입의 배열을 표현하는 별도 클래스를 각 원시 타입마다 하나씩 제공 (IntArray, ByteArray...)
 *    이 모든 타입은 자바 원시 타입 배열인 int[], byte[], char[] 등으로 컴파일된다.
 *    따라서 그런 배열의 값은 박싱하지 않고 가장 효율적인 방식으로 저장된다.
 */

fun main(args: Array<String>) {
    val letters = Array<String>(26) { i -> ('a' + i).toString() }
    println(letters.joinToString(""))

    // 컬렉션을 vararg 메소드에 넘기기
    val strings = listOf("a", "b", "c")
    println("%s/%s/%s".format(*strings.toTypedArray())) // vararg 인자를 넘기기 위해 스프레드 연산자(*)를 써야 한다

    val fiveZeros = IntArray(5)
    val fiveZerosToo = intArrayOf(0, 0, 0, 0, 0) // 팩토리 함수 사용

    val squares = IntArray(5) { i -> (i+1) * (i+1) }
    println(squares.joinToString())

    // 배열에 forEachIndexed 사용하기
    args.forEachIndexed { index, element ->
        println("Argument $index is: $element")
    }
}

