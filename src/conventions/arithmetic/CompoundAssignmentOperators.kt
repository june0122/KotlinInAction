package conventions.arithmetic

/** 복합 대입 연산자 오버로딩 (+=, -=, ...) */

operator fun <T> MutableCollection<T>.plusAssign(element: T) {
    this.add(element)
}

fun main() {
    val numbers = ArrayList<Int>()
    numbers += 42
    println(numbers[0])

    /**
     * 코틀린 표준 라이브러리는 컬렉션에 대해 두 가지 접근 방법을 함께 제공한다.
     * +와 -는 항상 새로운 컬렉션을 반환
     * +=와 -= 연산자는 항상 변경 가능한 컬렉션에 작용해 메모리에 있는 객체 상태를 변화
     * 읽기 전용 컬렉션에서 +=와 -=는 변경을 적용한 복사본을 반환한다(따라서 var로 선언한 변수가 가리키는 읽기 전용 컬렉션에만 적용 가능)
     */
    val list = arrayListOf(1, 2)
    list += 3 // +=는 list를 변경한다.
    val newList = list + listOf(4, 5) // +는 두 리스트의 모든 원소를 포함하는 새로운 리스트를 반환
    println(list)
    println(newList)
}