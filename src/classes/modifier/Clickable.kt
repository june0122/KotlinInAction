package classes.modifier

/**
 * 코틀린의 인터페이스는 자바 8 인터페이스와 비슷하다.
 * 코틀린 인터페이스 안에는 추상 메소드뿐 아니라 구현이 있는 메소드도 정의할 수 있다.
 * (자바 8의 디폴트 메소드와 비슷하다.)
 * 하지만 인터페이스에는 아무런 상태(필드)도 들어갈 수 없다.
 */
interface Clickable {
    fun click() // 일반 메소드 선언
    fun showOff() = println("I'm clickable!") // 디폴트 구현이 있는 메소드, 자바 8과 달리 default 키워드가 필요 없다.
}