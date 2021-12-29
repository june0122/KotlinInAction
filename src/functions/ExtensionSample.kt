package functions

val String.lastChar: Char
    get() = get(length - 1)

var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value: Char) {
        this.setCharAt(length - 1, value)
    }

//fun String.lastChar(): Char = this.get(this.length - 1)
fun String.lastChar(): Char = get(length - 1) // 수신 객체 멤버에 this 없이 접근 가능