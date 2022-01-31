package highorder.inline

import sun.misc.Lock

/**
 * 인라인 함수: 람다의 부가 비용 없애기
 *
 * 코틀린이 보통 람다를 무명 클래스로 컴파일하지만 그렇다고 람다 식을 사용할 때마다 새로운 클래스가 만들어지지는 않는다.
 * 람다가 변수를 포획하면 람다가 생성되는 시점마다 새로운 무명 클래스 객체가 생기는데, 이런 경우 실행 시점에 무명 클래스 생성에 따른 부가 비용이 든다.
 * 따라서 람다를 사용하는 구현은 똑같은 작업을 수행하는 일반 함수를 사용한 구현보다 덜 효율적이다.
 *
 * 반복되는 코드를 별도의 라이브러리 함수로 빼내되 컴파일러가 자바의 일반 명령문만큼 효율적인 코드를 생성하게 만들 수 있다.
 * inline 변경자를 함수에 붙이면 컴파일러는 그 함수를 호출하는 모든 문장을 함수 본문에 해당하는 바이트코드로 바꿔치기 해준다.
 */

/**
 * 인라이닝이 작동하는 방식
 *
 * 어떤 함수를 inline으로 선언하면 그 함수의 본문이 인라인(inline)된다.
 * 다른 말로 하면 함수를 호출하는 코드를 함수를 '호출'하는 바이트코드 대신에 함수 '본문을 번역'한 바이트코드로 컴파일한다는 뜻이다.
 */

/** 인라인 함수 정의 */
inline fun <T> synchronized(lock: Lock, action: () -> T): T {
    lock.lock()
    try {
        return action()
    }
    finally {
        lock.unlock()
    }
}

fun main() {
    val l = Lock()
    synchronized(l) {
        // ...
    }

}

fun foo(l: Lock) {
    println("Before sync")
    synchronized(l) {
        println("Action")
    }
    println("After sync")
}

class LockOwner(val lock: Lock) {
    fun runUnderLock(body: () -> Unit) {
        synchronized(lock, body) // 람다 대신에 함수 타입인 변수를 인자로 넘긴다.
    }
}

/** noinline 변경자를 파라미터 이름 앞에 붙여서 인라이닝 금지할 수 있음 */
inline fun goo(inlined: () -> Unit, noinline notInlined: () -> Unit) {
    // ...
}