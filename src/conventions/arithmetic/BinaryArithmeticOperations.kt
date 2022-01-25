package conventions.arithmetic

data class Point(val x: Int, val y: Int) {
    // 연산자를 오버로딩하는 함수 앞에는 꼭 "operator" 키워드가 있어야 한다.
    operator fun plus(other: Point): Point { // "plus"라는 이름의 연산자 함수를 정의
        return Point(x + other.x, y + other.y)
    }
}

/** 연산자를 멤버 함수로 만드는 대신 확장 함수로도 정의할 수 있다.
 *  외부 함수의 클래스에 대한 연산자를 정의할 때는 관례를 따르는 이름의 확장 함수로 구현하는 게 일반적 패턴
 */
operator fun Point.plus(other: Point) : Point {
    return Point(x + other.x, y + other.y)
}

operator fun Point.rem(other: Point) : Point {
    return Point(x % other.x, y % other.y)
}

/** 두 피연산자의 타입이 다른 연산자 정의 */
operator fun Point.times(scale: Double) : Point {
    return Point((x * scale).toInt(), (y * scale).toInt())
}

/** 결과 타입이 피연산자 타입과 다른 연산자 정의 */
operator fun Char.times(count: Int) : String {
    return toString().repeat(count) // this.toString().repeat(count)
}

fun main() {
    val p1 = Point(10, 20)
    val p2 = Point(30, 40)
    println(p1 + p2) // +로 계산하면 "plus" 함수가 호출된다.
    println(p1.plus(p2))

    println(p1 % p2)

    val p = Point(10, 20)
    // 코틀린 연산자가 자동으로 교환 법칙을 지원하지 않으므로 1.5 * p라고 쓰는 것은 따로 이에 대응하는 연산자 함수를 정의해야 한다.
    println(p * 1.5)

    println('a' * 3)
}