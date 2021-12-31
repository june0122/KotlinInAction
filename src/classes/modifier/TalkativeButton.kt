package classes.modifier

internal open class TalkativeButton : Focusable {
    private fun yell() = println("Hey!")
    protected fun whisper() = println("Let's talk!")
}

/**
 * @see [Why does Kotlin prohibit exposing restricted-visibility types?](https://discuss.kotlinlang.org/t/why-does-kotlin-prohibit-exposing-restricted-visibility-types/7047/6)
 *
 */

//class FooButton : TalkativeButton() { }

//fun TalkativeButton.giveSpeech() {
//    yell()
//    whisper()
//}