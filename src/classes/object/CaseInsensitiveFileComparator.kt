package classes.`object`

import java.io.File

/**
 * 싱글톤의 단점
 * 1. 객체 생성을 제어할 수 없고, 생성자 파라미터를 지정할 수 없다.
 * 2. 위의 이유로 단위테스트를 하거나 소프트웨어 시스템의 설정이 달라질 때 객체를 대체하거나 객체의 의존관계를 바꿀 수 없다.
 */

object CaseInsensitiveFileComparator: Comparator<File> {
    override fun compare(f1: File, f2: File): Int {
        return f1.path.compareTo(f2.path, ignoreCase = true)
    }
}

fun main() {
    println(CaseInsensitiveFileComparator.compare(File("/User"), File("/user")))

    val files = listOf(File("/Z"), File("/a"))
    println(files.sortedWith(CaseInsensitiveFileComparator))
}