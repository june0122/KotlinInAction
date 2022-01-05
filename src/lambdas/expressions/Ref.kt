package lambdas.expressions

class Ref<T>(var value: T)

fun main() {
    val counter = Ref(0)
    val inc = { counter.value++ }
    run(inc)
    println(counter.value)

//    var counter = 0
//    val inc = { counter++ }
//    run(inc)
//    println(counter)
}