package basics

import basics.Color.*

enum class Color(
    val r: Int, val g: Int, val b: Int
) {
    RED(255, 0, 0), ORANGE(255, 163, 0),
    YELLOW(255, 255, 0), GREEN(0, 255, 0), BLUE(0, 0, 255),
    INDIGO(75, 0, 130), VIOLET(238, 130, 238);

    fun rgb() = (r * 256 + g) * 256 + b
}

// 함수 호출때마다 함수 인자로 주어진 두 색과 when의 분기 조건에 있는 두 색을 비교
// 그때마다 여러 Set 인스턴스를 생성하므로 자주 호출된다면 불필요한 가비지 객체가 늘어남
fun mix(c1: Color, c2: Color) =
    when (setOf(c1, c2)) {
        setOf(RED, YELLOW) -> ORANGE
        setOf(BLUE, YELLOW) -> GREEN
        setOf(BLUE, VIOLET) -> INDIGO
        else -> throw Exception("Dirty color")
    }

fun mixOptimized(c1: Color, c2: Color) =
    when {
        (c1 == RED && c2 == YELLOW) || (c1 == YELLOW && c2 == RED) ->
            ORANGE
        (c1 == YELLOW && c2 == BLUE) || (c1 == BLUE && c2 == YELLOW) ->
            GREEN
        (c1 == BLUE && c2 == VIOLET) || (c1 == VIOLET && c2 == BLUE) ->
            INDIGO
        else -> throw Exception("Dirty Color")
    }

fun getMnemonic(color: Color) =
    when (color) {
        RED -> "Richard" // 임포트한 enum 상수의 경우 이름만으로 사용도 가능
        Color.ORANGE -> "Of"
        Color.YELLOW -> "York"
        Color.GREEN -> "Gave"
        Color.BLUE -> "Battle"
        Color.INDIGO -> "In"
        Color.VIOLET -> "Vain"
    }

fun getWarmth(color: Color) = when (color) {
    RED, Color.ORANGE, Color.YELLOW -> "warm"
    Color.GREEN -> "neutral"
    Color.BLUE, Color.INDIGO, Color.VIOLET -> "cold"
}

fun main() {
    println(getMnemonic(Color.ORANGE))
    println(getWarmth(Color.ORANGE))
    println(mix(BLUE, YELLOW))
    println(mixOptimized(BLUE, YELLOW))
}