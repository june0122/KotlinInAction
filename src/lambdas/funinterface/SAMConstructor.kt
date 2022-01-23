package lambdas.funinterface

/** SAM 생성자: 람다를 함수형 인터페이스의 인스턴스로 변환할 수 있게 컴파일러가 자동으로 생성한 함수
 *
 * 단, 코틀린 1.4 이전까지는 SAM 변환이 자바로 작성된 인터페이스에서만 동작하고,
 * 코틀린으로 작성된 인터페이스에서는 동작하지 않았다.
 *
 * 1.4 이후 코틀린 인터페이스도 SAM 변환을 지원
 * : SAM 변환을 지원할 인터페이스에 fun 키워드를 추가하여 인터페이스가 함수형 인터페이스임을 명시해야만
 * SAM 변환을 지원하며, fun 키워드가 없는 인터페이스에서는 여전히 SAM 변화 지원 X
 */

/*
fun createAllDoneRunnable() = object : Runnable {
    override fun run() {
        return println("All done!")
    }
}
*/

// SAM 생성자를 사용해 값 반환
fun createAllDoneRunnable() : Runnable {
    return Runnable { println("All done!") }
}

//fun createAllDoneRunnable() = Runnable { println("All done!") }

fun main() {
    createAllDoneRunnable().run()
}

/** SAM 생성자를 이용해 listener 인스턴스 재사용하기 */
//    val listener = OnClickListener { view ->
//        val text = when (view.id) {
//            R.id.button1 -> "First button"
//            R.id.button2 -> "Second button"
//            else -> "Unknown button"
//        }
//        toast(text)
//    }
//
//    button1.setOnClickListener(listener)
//    button2.setOnClickListener(listener)