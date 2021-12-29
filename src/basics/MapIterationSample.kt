package basics

fun main() {
    val list = arrayListOf("10", "11", "1001")
    for ((index, element) in list.withIndex()) { // 구조 분해 구문
        println("$index: $element")
    }
}