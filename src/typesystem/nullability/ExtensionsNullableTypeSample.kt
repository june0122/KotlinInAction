package typesystem.nullability

fun verifyUserInput(input: String?) {
    if (input.isNullOrBlank()) { // safe call이 필요 없음
        println("Please fill in the required fields")
    }
}

fun String?.isNullOrBlank(): Boolean = // 널이 될 수 있는 String의 확장
    this == null || this.isBlank()

fun main() {
    verifyUserInput(" ")
    verifyUserInput(null)
}