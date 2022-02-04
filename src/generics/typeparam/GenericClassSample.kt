package generics.typeparam

interface List<T> { // List 인터페이스에 T라는 타입 파라미터를 정의한다.
    operator fun get(index: Int): T // 인터페이스 안에서 T를 일반 타입처럼 사용 가능
    // ...
}

/**
 * 제네릭 클래스를 확장하는 클래스(또는 제네릭 인터페이스를 구현하는 클래스)를 정의하려면 기반 타입의 제네릭 파라미터에 대해 타입 인자를 지정해야 한다.
 * 이때 구체적인 타입을 넘길 수도 있고 (하위 클래스도 제네릭 클래스라면) 타입 파라미터로 받은 타입을 넘길 수도 있다.
 */

/*
class StringList: List<String> {
    override fun get(index: Int): String = ...
}

class ArrayList<T>: List<T> {
    override fun get(index: Int): T {
        // ...
    }
}
 */