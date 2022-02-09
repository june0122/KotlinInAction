package annotations

import java.text.SimpleDateFormat
import java.util.*
import kotlin.reflect.KClass

/**
 * 애노테이션을 활용한 JSON 직렬화 제어
 *
 * 직렬화(serialization)는 객체를 저장장치에 저장하거나 네트워크를 통해 전송하기 위해 텍스트나 이진 형식으로 변환하는 것이다.
 * 반대 과정인 역직렬화(desrialization)는 텍스트나 이진 형식으로 저장된 데이터로부터 원래의 객체를 만들어낸다.
 *
 * 직렬화에 자주 쓰이는 형식에 JSON이 있다. 자바와 JSON을 변환할 때 자주 쓰이는 라이브러리로는 잭슨(Jackson)과 지슨(GSON)이 있다.
 */

/* 메타애노테이션: 애노테이션 클래스에 적용할 수 있는 애노테이션 */
@Target(AnnotationTarget.PROPERTY) // @Target 메타애노테이션은 애노테이션을 적용할 수 있는 요소의 유형을 지정한다.
annotation class JsonExclude

annotation class JsonName(val name: String)

@Target(AnnotationTarget.ANNOTATION_CLASS) // 메타애노테이션을 직접 만들려면 ANNOTATION_CLASS를 대상으로 지정하라.
annotation class BindingAnnotation

@BindingAnnotation
annotation class MyBinding

// KClass의 타입 파라미터는 이 KClass의 인스턴스가 가리키는 코틀린 타입을 지정한다.
annotation class DeserializeInterface(val targetClass: KClass<out Any>)

/**
 * KClass의 타입 파라미터를 쓸 때 out 변경자 없이 KClass<Any>라고 쓰면 DeserializeInterface에게 CompanyImpl::class를 인자로 넘길 수 없고,
 * 오직 Any::class만 넘길 수 있다.
 *
 * 반면 out 키워드가 있으면 모든 코틀린 타입 T에 대해 KClass<T>가 KClass<out Any>의 하위 타입이 된다(공변성).
 */

interface Company {
    val name: String
}

data class CompanyImpl(override val name: String) : Company
data class Person(
    val name: String,
    @DeserializeInterface(CompanyImpl::class) val company: Company,
    @CustomSerializer(DateSerializer::class) val birthDate: Date
)

/* 애노테이션 파라미터로 제네릭 클래스 받기 */

interface ValueSerializer<T> {
    fun toJsonValue(value: T): Any?
    fun fromJsonValue(jsonValue: Any?): T
}

annotation class CustomSerializer(
    val serializerClass: KClass<out ValueSerializer<*>>
)

object DateSerializer : ValueSerializer<Date> {
    private val dateFormat = SimpleDateFormat("dd-mm-yyyy")

    override fun toJsonValue(value: Date): Any? =
        dateFormat.format(value)

    override fun fromJsonValue(jsonValue: Any?): Date =
        dateFormat.parse(jsonValue as String)
}

/**
 * 클래스를 애노테이션 인자로 받아야 할 때 사용하는 패턴
 * - 클래스를 인자로 받을 경우: 애노테이션 파라미터 타입에 KClass<out 허용할 클래스 이름>
 * - 제네릭 클래스를 인자로 받을 경우: KClass<out 허용할 클래스 이름<*>>
 */