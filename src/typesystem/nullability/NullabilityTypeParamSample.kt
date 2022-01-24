package typesystem.nullability

// 널이 될 수 있는 타입 파라미터 다루기
/*
fun <T> printHashCode(t: T) {
    println(t?.hashCode())
}
*/

// 타입 파라미터에 대해 널이 될 수 있는 상항
fun <T: Any> printHashCode(t: T) { // 이제 T는 널이 될 수 없는 타입이다.
    println(t.hashCode())
}

fun main() {
//    printHashCode(null)
}