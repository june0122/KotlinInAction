package classes.nested

class Button: View {
    val num = 10

    override fun getCurrentState(): State = ButtonState()

    override fun restoreState(state: State) { /*...*/ }

    class ButtonState: State { /*...*/ }
}