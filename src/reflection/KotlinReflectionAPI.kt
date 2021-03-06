package reflection

/**
 * 리플렉션: 런타임에 코틀린 객체 내부 관찰
 * 리플렉션은 런타임에 (동적으로) 객체의 프로퍼티와 메서드에 접근할 수 있게 해주는 방법이다.
 *
 * 보통의 객체의 메서드나 프로퍼티에 접근할 때는 프로그램 소스코드 안에 구체적인 선언이 있는 메서드나 프로퍼티 이름을 사용하며,
 * 컴파일러는 그런 이름이 실제로 가리키는 선언을 컴파일 시점에 (정적으로) 찾아내서 해당하는 선언이 실제 존재함을 보장한다.
 *
 * 하지만 타입과 관계없이 객체를 다뤄야 하거나 객체가 제공하는 메서드나 프로퍼티 이름을 오직 런타임에만 알 수 있는 경우가 있다.
 * JSON 직렬화 라이브러리가 그런 경우이며, 직렬화 라이브러리는 어떤 객체든 JSON으로 변환할 수 있어야 하고,
 * 런타임이 되기 전까지는 라이브러리가 직렬화할 프로퍼티나 클래스에 대한 정보를 알 수 없다. 이런 경우 리플렉션을 사용해야 한다.
 */

/**
 * 코틀린에서 리플렉션을 사용하려면 두 가지 서로 다른 리플렉션 API를 다뤄야 한다.
 * 1. 자바가 java.lang.reflect 패키지를 통해 제공하는 표준 리플렉션
 * 2. 코틀린이 kotlin.reflect 패키지를 통해 제공하는 코틀린 리플렉션 API
 */