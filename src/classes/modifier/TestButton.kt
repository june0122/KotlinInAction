package classes.modifier

class TestButton: RichButton(), Clickable {
    // 코틀린의 클래스와 메소드는 기본적으로 final 이므로 disable()은 오버라이드 불가
    // override fun disable() {}
    override fun animate() {}
    override fun click() {}
}