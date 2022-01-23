package lambdas.funinterface

/**
 * 코틀린 1.4부터 코틀린 인터페이스도 SAM 변환을 지원
 *
 * SAM 변환을 지원할 인터페이스에 fun 키워드를 추가하여 인터페이스가 함수형 인터페이스임을 명시해야만
 * SAM 변환을 지원하며, 이 키워드가 없는 인터페이스에서는 여전히 SAM 변환을 지원하지 않는다.
 */

fun interface KRunnable {
    fun invoke()
}

fun interface IntPredicate {
    fun accept(i: Int): Boolean
}

// SAM 변환을 사용하지 않았을 때
/*
val isEven = object : IntPredicate {
    override fun accept(i: Int): Boolean {
        return i % 2 == 0
    }
}
*/

// SAM 변환 사용했을 때
val isEven = IntPredicate { i -> i % 2 == 0 }

fun main() {
    println("Is 7 even? : ${isEven.accept(7)}")
}