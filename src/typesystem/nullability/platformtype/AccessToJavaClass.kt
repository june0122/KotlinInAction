package typesystem.nullability.platformtype

import lambdas.expressions.person

/*
fun yellAt(person: Person) {
    println(person.name.uppercase() + "!!!")
}
*/

fun yellAt(person: Person) {
    println((person.name ?: "Anyone").uppercase() + "!!!")
}

class StringPrinter: StringProcessor {
    override fun process(value: String) {
        println(value)
    }
}

class NullableStringPrinter: StringProcessor {
    override fun process(value: String?) {
        if (value != null) {
            println(value)
        }
    }
}

fun main() {
    yellAt(Person(null))
}