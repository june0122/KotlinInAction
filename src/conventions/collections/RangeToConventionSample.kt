package conventions.collections

import java.time.LocalDate

/**
 * rangeTo 함수는 Comparable에 대한 확장 함수
 */

fun main() {
    val now = LocalDate.now()
    val vacation = now..now.plusDays(10) // now.rangeTo(now.plusDays(10))
    println(now.plusWeeks(1) in vacation)
}