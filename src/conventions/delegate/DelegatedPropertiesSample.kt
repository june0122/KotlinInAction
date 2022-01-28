package conventions.delegate

/**
 * # 위임 프로퍼티(delegated property)
 *
 * 위임 : 객체가 직접 작업을 수행하지 않고 다른 도우미 객체가 그 작업을 처리하게 맡기는 디자인 패턴
 *       이때 작업을 처리하는 도우미 객체를 위임 객체라고 부른다.
 *
 * 클래스 위임과는 다르게, 여기서는 위임 패턴을 프로퍼티에 적용해서 접근자 기능을 도우미 객체가 수행하게 위임한다.
 * 도우미 객체를 직접 작성할 수도 있지만, 더 나은 방법은 코틀린 언어가 제공하는 기능을 활용하는 것이다.
 */

/**
 * # 위임 프로퍼티의 일반적인 문법
 *
 * 아래의 p 프로퍼티는 접근자 로직을 다른 객체에 위임한다. 여기서는 Delegate 클래스의 인스턴스를 위임 객체로 사용한다.
 * by 뒤에 있는 식을 계산에서 위임에 쓰일 객체를 얻는다.
 */
/*
class Foo {
    var p: Type by Delegate()
}
*/

/**
 * 다음과 같이 컴파일러는 숨겨진 도우미 프로퍼티를 만들고 그 프로퍼티를 위임 객체의 인스턴스로 초기화 한다.
 * p 프로퍼티는 바로 그 위임 객체에게 자신의 작업을 위임한다.
 */
/*
class Foo {
    private val delegate = Delegate()
    var p: Type
        set(value: Type) = delegate.setValue(..., value)
        get() = delegate.getValue(...)
}
*/

/*
// 단순화된 Delegate 클래스
class Delegate {
    operator fun getValue(...) { ... }  // getValue는 게터를 구현하는 로직을 담는다.
    operator fun setValue(..., value: Type) { ... }  // setValue 메서드는 세터를 구현하는 로직을 담는다.
}

class Foo {
    var p: Type by Delegate() // by 키워드는 프로퍼티와 위임 객체를 연결한다.
}

fun main() {
    val foo = Foo()
    val oldValue = foo.p  // foo.p라는 프로퍼티 호출은 내부에서 delegate.getValue(...)를 호출
    foo.p = newValue  // 프로퍼티 값을 변경하는 문장은 내부에서 delegate.setValue(..., newValue)를 호출
}
*/

/**
 * foo.p는 일반 프로퍼티처럼 쓸 수 있고, 일반 프로퍼티 같아 보인다. 하지만 실제로 p의 게터나 세터는 Delegate 타입의 위임 프로퍼티 객체에 있는 메서드를 호출한다.
 */