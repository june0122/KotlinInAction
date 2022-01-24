package typesystem.collections

import java.io.BufferedReader
import java.io.StringReader

/*
fun readNumbers(reader: BufferedReader): List<Int?> {
    val result = ArrayList<Int?>() // 널이 될 수 있는 Int값으로 이뤄진 리스트를 만든다.
    for (line in reader.lineSequence()) {
        try {
            val number = line.toInt()
            result.add(number) // 정수(널이 아닌 값)를 리스트에 추가한다.
        }
        catch (e: NumberFormatException) {
            result.add(null) // 현재 줄을 파싱할 수 없으므로 리스트에 널을 추가한다.
        }
    }
    return result
}
*/

/** String.toIntOrNull을 사용해 위 예제를 간략화 */
fun readNumbers(reader: BufferedReader): List<Int?> {
    val result = ArrayList<Int?>() // 널이 될 수 있는 Int값으로 이뤄진 리스트를 만든다.
    for (line in reader.lineSequence()) {
        val number = line.toIntOrNull()
            result.add(number)
    }
    return result
}

/** 널이 될 수 있는 값으로 이뤄진 컬렉션 다루기 */
/*
fun addValidNumbers(numbers: List<Int?>) {
    var sumOfValidNumbers = 0
    var invalidNumbers = 0
    for (number in numbers) {
        if (number != null) {
            sumOfValidNumbers += number
        } else {
            invalidNumbers++
        }
    }
    println("Sum of valid numbers: $sumOfValidNumbers")
    println("Invalid numbers: $invalidNumbers")
}
*/

/** filterNotNull을 이용해 위의 예제를 간략화 */
fun addValidNumbers(numbers: List<Int?>) {
    val validNumbers = numbers.filterNotNull()
    println("Sum of valid numbers: ${validNumbers.sum()}")
    println("Invalid numbers: ${numbers.size - validNumbers.size}")
}

fun main() {
    val reader = BufferedReader(StringReader("1\nabc\n42"))
    val numbers = readNumbers(reader)
    addValidNumbers(numbers)
}