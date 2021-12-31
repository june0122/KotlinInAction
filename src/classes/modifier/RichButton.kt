package classes.modifier

open class RichButton: Clickable { // 이 클래스는 열려 있으므로 다른 클래스가 이 클래스를 상속 가능
    fun disable() { } // 이 함수는 파이널이므로 하위 클래스가 오버라이드 불가
    open fun animate() { } // 이 함수는 열려있으므로 하위 클래스에서 이 메소드를 오버라이드 가능
    override fun click() { } // 오버라이드한 메소드는 기본적으로 열려있다.
//    final override fun click() { } // final 키워드를 통해 오버라이드를 금지
}