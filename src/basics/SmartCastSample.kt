package basics

interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

//fun eval(e: Expr): Int {
//    if (e is Num) { // is로 원하는 타입인지를 검사하고 나면 굳이 변수를 원하는 타입으로 캐스팅하지 않아도 된다
//        val n = e as Num // 타입 변환 불필요
//        return n.value
//    }
//
//    if (e is Sum) {
//        return eval(e.right) + eval(e.left) // IDE는 배경색으로 스마트캐스트 표시
//    }
//
//    throw IllegalArgumentException("Unknown expression")
//}

fun eval(e: Expr): Int =
    when(e) {
        is Num -> e.value
        is Sum -> eval(e.right) + eval(e.left)
        else -> throw IllegalArgumentException("Unknown expression")
    }

fun main() {
    println(eval(Sum(Sum(Num(1), Num(2)), Num(4)))) // 7
}