package conventions.collections

import java.time.LocalDate

operator fun ClosedRange<LocalDate>.iterator(): Iterator<LocalDate> =
    object : Iterator<LocalDate> { // 이 객체는 LocalDate 원소에 대한 Iterator를 구현한다.
        var current = start
        override fun hasNext(): Boolean =
            current <= endInclusive // compareTo 관례를 사용해 날짜를 비교

        override fun next(): LocalDate = current.apply {
            current = plusDays(1) // 현재 날짜를 1일 뒤로 변경
        }
    }

fun main() {
    val newYear = LocalDate.ofYearDay(2017, 2)
    val daysOff = newYear.minusDays(1)..newYear
    for (dayOff in daysOff) { println(dayOff) }
}