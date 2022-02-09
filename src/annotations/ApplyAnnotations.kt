package annotations

import junit.framework.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

/**
 * 어떤 함수를 호출하려면 그 함수가 정의된 클래스∙함수∙파라미터 이름 등을 알아야만 했다.
 * 애노테이션과 리플렉션을 사용하면 그런 제약을 벗어나서 미리 알지 못하는 임의의 클래스를 다룰 수 있다.
 * - 애노테이션을 사용하면 라이브러리가 요구하는 의미를 클래스에게 부여할 수 있다.
 *  - 메타데이터를 선언에 추가하면 애노테이션을 처리하는 도구가 컴파일 시점이나 런타임에 적절한 처리를 해준다.
 * - 리플렉션을 사용하면 런타임에 컴파일러 내부 구조를 분석할 수 있다.
 */

class MyTest {
    @Test
    fun testTrue() {
        Assert.assertTrue(true)
    }

    @Deprecated("Use removeAt(index) instead", ReplaceWith("removeAt(index)"))
    fun remove(index: Int) { }
}

fun main() {
    val test = MyTest()
    test.remove(1) // ReplaceWith 파라미터를 통해 quick fix 제시
}

/**
 * 규칙을 지정하려면 공개(public) 필드나 메서드 앞에 @Rule을 붙여야 한다.
 * 하지만 코틀린 클래스의 folder라는 프로퍼티 앞에 @Rule을 붙이면 "The @Rule 'folder' must be public."라는 제이유닛 예외가 발생한다.
 * @Rule은 필드에 적용되지만 코틀린의 필드는 기본적으로 비공개이기 때문에 이런 예외가 생긴다.
 * @Rule 애노테이션을 정확한 대상에 적용하려면 다음과 같이 @get:Rule을 사용해야 한다.
 */

class HasTempFolder {
    @get:Rule // 프로퍼티가 아니라 게터에 애노테이션이 붙는다.
    val folder = TemporaryFolder()

    @Test
    fun testUsingTempFolder() {
        val createdFile = folder.newFile("myfile.txt")
        val createdFolder = folder.newFolder("subfolder")
        // ...
    }
}

/**
 * 자바에서 선언된 애노테이션을 사용해 프로퍼티에 애노테이션을 붙이는 경우 기본적으로 프로퍼티의 필드에 그 애노테이션이 붙는다.
 * 하지만 코틀린으로 애노테이션을 선언하면 프로퍼티에 직접 적용할 수 있는 애노테이션을 만들 수 있다.
 *
 * 사용 지점 대상을 지정할 때 지원하는 대상 목록은 다음과 같다.
 * - property: 프로퍼티 전체. 자바에서 선언된 애노테이션에는 이 사용 지점 대상을 사용할 수 없다.
 * - field: 프로퍼티에 의해 생성되는 (뒷받침하는) 필드
 * - get: 프로퍼티 게터
 * - set: 프로퍼티 세터
 * - receiver: 확장 함수나 프로퍼티의 수신 객체 파라미터
 * - param: 생성자 파라미터
 * - setparam: 새터 파라미터
 * - delegate: 위임 프로퍼티의 위임 인스턴스를 담아둔 필드
 * - file: 파일 안에 선언된 최상위 함수와 프로퍼티를 담아두는 클래스
 */

/**
 * 자바 API를 애노테이션으로 제어하기
 *
 * 코틀린은 코틀린으로 선언한 내용을 자바 바이트코드로 컴파일하는 방법과 코틀린 선언을 자바에 노출하는 방법을 제어하기 위한 애노테이션을 많이 제공한다.
 * 이런 애노테이션 중 일부는 자바 언어의 일부 키워드를 대신한다. 다음에 나열한 애노테이션을 사용하면 코틀린 선언을 자바에 노출시키는 방법을 변경할 수 있다.
 * - @JvmName은 코틀린 선언이 만들어내는 자바 필드나 메서드 이름을 변경한다.
 * - @JvmStatic을 메서드, 객체 선언, 동반 객체에 적용하면 그 요소가 자바 정적 메서드로 노출된다.
 * - @JvmOverloads를 사용하면 디폴트 파라미터 값이 있는 함수에 대해 컴파일러가 자동으로 오버로딩한 함수를 생성해준다.
 * - @JvmField를 프로퍼티에 사용하면 게터나 세터가 없는 공개된(public) 자바 필드로 프로퍼티를 노출시킨다.
 */







