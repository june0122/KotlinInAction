package generics.typeparam

/*
/** 아무런 상한을 정하지 않은 타입 파라미터는 결과적으로 Any?를 상한으로 정한 파라미터와 같다 */
// process 함수에서 value 파라미터의 타입 T에는 물음표(?)가 붙어있지 않지만 실제로는 T에 해당하는 타입 인자로 널이 될 수 있는 타입을 넘길 수도 있다.
class Processor<T> {
    fun process(value: T) {
        value?.hashCode() // "value"는 널이 될 수 있다. 따라서 안전한 호출을 사용해야 한다.
    }
}

fun main() {
    // Processor 클래스를 널이 될 수 있는 타입을 사용해 인스턴스화
    val nullableStringProcessor = Processor<String?>() // 널이 될 수 있는 타입인 String?이 T를 대신한다.
    nullableStringProcessor.process(null) // 이 코드는 잘 컴파일되며 "null"이 "value" 인자로 지정된다.
}
 */

/**
 * 항상 널이 될 수 없는 타입만 타입 인자로 받게 만들려면 타입 파라미터에 제약을 가해야 한다.
 * 널 가능성을 제외한 아무런 제약도 필요 없다면 Any? 대신에 Any를 상한으로 사용하라.
 */
class Processor<T: Any> { // 널이 될 수 없는 타입 상한을 지정한다.
    fun process(value: T) {
        value.hashCode() // T 타입의 "value"는 "null"이 될 수 없다.
    }
}

fun main() {
    // Kotlin: Type argument is not within its bounds: should be subtype of 'Any'
    // val nullableStringProcessor = Processor<String?>()
}

/**
 * 타입 파라미터를 널이 될 수 없는 타입으로 제약하기만 하면 타입 인자로 널이 될 수 있는 타입이 들어오는 일을 막을 수 있다는 점을 기억하라.
 * 따라서 Any를 사용하지 않고 다른 널이 될 수 없는 타입을 사용해 상한을 정해도 된다.
 */