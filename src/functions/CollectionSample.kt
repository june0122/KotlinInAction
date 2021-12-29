package functions

fun main() {
    val strings: List<String> = listOf("first", "second", "fourteenth")
    println(strings.last())

    val args: Array<String> = arrayOf("A", "B", "C")
    // * (spread 연산자) : 배열을 명시적으로 풀어서 배열의 각 원소가 인자로 전달되게 함
    val list = listOf("0", *args)
    println(list)

    parsePath("/Users/yole/kotlin-book/chapter.adoc")
    parsePathWithRegex("/Users/yole/kotlin-book/chapter.adoc")
}

fun parsePath(path: String) {
    val directory = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")
    val fileName = fullName.substringBeforeLast(".")
    val extension = fullName.substringAfterLast(".")
    println("Dir : $directory, name : $fileName, ext : $extension")
}

fun parsePathWithRegex(path: String) {
    val regex = """(.+)/(.+)\.(.+)""".toRegex()
    val matchResult = regex.matchEntire(path)
    if (matchResult != null) {
        val (directory, fileName, extension) = matchResult.destructured
        println("Dir : $directory, name : $fileName, ext : $extension")
    }
}