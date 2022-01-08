package lambdas.api

class Book(val title: String, val authors: List<String>)

fun main() {
    val books = listOf(
        Book("동물농장", listOf("조지 오웰")),
        Book("1984", listOf("조지 오웰")),
        Book("다정한 것이 살아남는다", listOf("브라이언 헤어", "버네사 우즈"))
    )

    println(books.flatMap { it.authors }.toSet()) // books 컬렉션에 있는 책을 쓴 모든 저저의 집합

    val strings = listOf("abc", "def")
    println(strings.flatMap { it.toList() })
}