package typesystem.nullability

/**
 * let 함수를 사용하면 널이 될 수 있는 식을 더 쉽게 다룰 수 있다.
 * let 함수를 safe call과 함께 사용하면 원하는 식을 평가해서 결과가 널인지 검사한 다음에
 * 그 결과를 변수에 넣는 작업을 간단한 식을 사용해 한꺼번에 처리할 수 있다.
 *
 * let을 사용하는 가장 흔한 용례 : nullable value를 non-null value만 인자로 받는 함수에 넘기는 경우
 */

fun sendEmailTo(email: String) {
    println("Sending email to $email")
}

fun main() {
    var email: String? = "yole@example.com"
    email?.let { sendEmailTo(it) }
    email = null
    email?.let { sendEmailTo(it) }
}

