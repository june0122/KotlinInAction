package lambdas.scope

/**
 * 수신 객체 지정 람다(lambda with receiver) : with & apply
 * 수신 객체를 명시하지 않고 람다의 본문 안에서 다른 객체의 메소드를 호출할 수 있게 하는 것
 */

/** 1. with 함수 미사용 */
/*
fun alphabet(): String {
    val result = StringBuilder()
    for (letter in 'A'..'Z') {
        result.append(letter)
    }
    result.append("\nNow I know the alphabet!")
    return result.toString()
}
*/

/** 2. with 함수 사용 */
/*
fun alphabet(): String {
    val stringBuilder = StringBuilder()
    return with(stringBuilder) {  // 메소드를 호출하려는 수신 객체를 지정
        for (letter in 'A'..'Z') {
            this.append(letter)  // "this"를 명시해서 앞에서 지정한 수신 객체의 메서드를 호출
        }
        append("\nNow I know the alphabet!") // "this"를 생략하고 메서드를 호출
        this.toString() // 람다에서 값을 반환
    }
}
 */

/** 3. with와 식을 본문으로 하는 함수 활용 */
/*
fun alphabet() = with(StringBuilder()) {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow I know the alphabet!")
    toString()
}
 */

/**
 * with가 반환하는 값은 람다 코드를 실행한 결과이며, 그 결과는 람다 식의 본문에 있는 마지막 식의 값이다.
 * 하지만 때로는 람다의 결과 대신 수신 객체가 필요한 경우도 있다.
 * 그럴 때 apply 라이브러리 함수를 사용할 수 있다.
 *
 * apply 함수는 거의 with와 같다.
 * 유일한 차이란 apply는 항상 자신에게 전달된 객체(수신 객체)를 반환한다는 점뿐이다.
 */

/** 4. apply를 사용해 알파벳 만들기 */
/*
fun alphabet() = StringBuilder().apply {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow I know the alphabet!")
}.toString()
*/

/** apply 함수는 객체의 인스턴스를 만들면서 즉시 프로퍼티 중 일부를 초기화해야 하는 경우 유용하다. */

/** apply를 객체 초기화에 활용하는 예로 안드로이드의 TextView 컴포넌트를 만드면서 특성 중 일부를 설정해보자. */
/*
fun createViewWithCustomAttributes(context: Context) =
    TextView(context).apply {
        text = "Sample text"
        textSize = 20.0
        setPadding(10, 0, 0, 0)
    }
 */

/** 5. buildString 함수로 alphabet 함수를 더 단순화
 * StringBuilder 객체를 만드는 일과 toString을 호출해주는 일을 알아서 해줌
 */
fun alphabet() = buildString {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow I know the alphabet!")
}

fun main() {
    println(alphabet())
}