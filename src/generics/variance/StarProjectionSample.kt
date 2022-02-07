package generics.variance

import kotlin.reflect.KClass

/**
 * 스타 프로젝션을 쓰는 방법과 스타 프로젝션 사용 시 빠지기 쉬운 함정을 보여주는 예제들
 */

/* 입력 검증을 위한 인터페이스 */
interface FieldValidator<in T> {
    fun validate(input: T): Boolean
}

object DefaultStringValidator : FieldValidator<String> {
    override fun validate(input: String): Boolean = input.isNotEmpty()
}

object DefaultIntValidator : FieldValidator<Int> {
    override fun validate(input: Int): Boolean = input >= 0
}

/**
 * 모든 검증기를 한 컨테이너에 넣고 입력 필드의 타입에 따라 적절한 검증기를 꺼내서 사용하는 경우를 생각해보자.
 * 맵에 검증기를 담으면 될 것이다. 모든 타입의 검증기를 맵에 넣을 수 있어야 하므로 KClass를 키로 하고
 * FieldValidator<*>를 값으로 하는 맵(FieldValidator<*>는 모든 타입의 검증기를 표현)을 선언한다.
 */
fun main() {
    val validators = mutableMapOf<KClass<*>, FieldValidator<*>>()
    validators[String::class] = DefaultStringValidator
    validators[Int::class] = DefaultIntValidator

    // validators[String::class]!!.validate("") // 맵에 저장된 값의 타입은 FieldValidator<*>이다.
    // Error: Out-projected type 'FieldValidator<*>' prohibits the use of 'fun validate(input: T): Boolean'

    /* 검증기를 가져오면서 명시적 타입 캐스팅 사용하기 */
    val stringValidator = validators[String::class] as FieldValidator<String> // 안전하지 않은 캐스팅 경고(unchecked cast)
    println(stringValidator.validate("")) // false

    /* 검증기를 잘못 가져온 경우 */
    val wrongStringValidator = validators[Int::class] // 검증기를 잘못 가져왔어도 컴파일과 타입 캐스팅시 아무 문제가 없다.
            as FieldValidator<String> // Warning: Unchecked cast를 표시하지만 컴파일은 된다.
    stringValidator.validate("") // 검증기를 사용해야 비로소 오류가 발생한다.
}

/* 검증기 컬렉션에 대한 접근 캡슐화하기 */
object Validators {
    private val validators = mutableMapOf<KClass<*>, FieldValidator<*>>()

    fun <T : Any> registerValidator(
        kClass: KClass<T>, fieldValidator: FieldValidator<T>
    ) {
        validators[kClass] = fieldValidator // 어떤 클래스와 검증기가 타입이 맞아 떨어지는 경우에만 그 클래스와 검증기 정보를 앱에 키/값 쌍으로 넣는다.
    }

    operator fun <T : Any> get(kClass: KClass<T>): FieldValidator<T> =
        validators[kClass] as? FieldValidator<T>
            ?: throw IllegalArgumentException("No validator for ${kClass.simpleName}")
}
/*
    Validators.registerValidator(String::class, DefaultStringValidator)
    Validators.registerValidator(Int::class, DefaultIntValidator)
    println(Validators[String::class].validate("Kotlin!")) // true
    println(Validators[Int::class].validate(42)) // true

    // println(Validators[String::class].validate(42))
    // The integer literal does not conform to the expected type String
 */

