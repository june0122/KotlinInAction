package classes.modifier

class Button: Clickable, Focusable {
    override fun click() = println("I was clicked")
    override fun showOff() {
        super<Clickable>.showOff()
//        super<Focusable>.showOff()
    }
}

fun main() {
    val button = Button()
    button.showOff()
    button.setFocus(true)
    button.click()
}