package classes.sealed

/** 인터페이스 구현을 통해 식 표현 */

//interface Expr
//class Num(val value: Int) : Expr
//class Sum(val left: Expr, val right: Expr) : Expr
//
//fun eval(e: Expr): Int =
//    when(e) {
//        is Num -> e.value
//        is Sum -> eval(e.right) + eval(e.left)
//        else -> throw IllegalArgumentException("Unknown expression")
//    }
//
//fun main() {
//    println(eval(Sum(Sum(Num(1), Num(2)), Num(4)))) // 7
//}

/** sealed 클래스로 식 표현 */

sealed class Expr {
    class Num(val value: Int) : Expr()
    class Sum(val left: Expr, val right: Expr) : Expr()
}

fun eval(e: Expr): Int =
    when (e) {
        is Expr.Num -> e.value
        is Expr.Sum -> eval(e.right) + eval(e.left)
    }

fun main() {
    println(eval(Expr.Sum(Expr.Sum(Expr.Num(1), Expr.Num(2)), Expr.Num(4)))) // 7
}