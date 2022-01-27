package conventions.comparison

class Point(val x: Int, val y: Int) {
    override fun equals(obj: Any?): Boolean { // Any에 정의된 메소드를 오버라이딩
        if (obj === this) return true // 최적화: 파라미터 this와 같은 객체인지 살펴본다.
        if (obj !is Point) return false // 파라미터 타입을 검사
        return obj.x == x && obj.y == y
    }
}

/**
 * equals 함수에는 override가 붙어있다. 다른 연산자 오버로딩 관례와 달리 equals는 Any에 정의된 메서드이므로 override가 필요하다.
 * 상위 클래스에서 정의된 메서드를 오버라이드한다는 사실을 알면 equals 앞에 operator를 붙이지 않는 이유를 알 수 있다.
 *
 * Any의 equals에는 operator가 붙어있지만 그 메서드를 오버라이드하는 하위 클래스의 메서드 앞에는 operator 변경자를 붙이지 않아도
 * 자동으로 상위 클래스의 operator 지정이 적용된다.
 *
 * 또한 Any에서 상속 받은 equals가 확장 함수보다 우선순위가 높기 때문에 equals를 확장 함수로 정의할 수는 없다는 사실에 유의하라.
 */

fun main() {
    println(Point(10, 20) == Point(10, 20))
    println(Point(10, 20) != Point(5, 5))
    println(null == Point(1, 2))
}