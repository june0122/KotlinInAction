package conventions.arithmetic

import java.math.BigDecimal

/**
 * 단항 연산자 오버로딩
 * 단항 연산자를 오버로딩하기 위해 사용하는 함수는 인자를 취하지 않는다.
 */
operator fun Point.unaryMinus() : Point {
    return Point(-x, -y)
}

operator fun BigDecimal.inc() = this + BigDecimal.ONE

fun main() {
    val p = Point(10, 20)
    println(-p)

    var bd = BigDecimal.ZERO
    println(bd++) // 0
    println(++bd) // 2
}