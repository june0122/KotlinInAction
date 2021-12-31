package classes.nested

class Outer {
    inner class Inner {
        fun getOuterReference(): Outer = this@Outer
    }
}