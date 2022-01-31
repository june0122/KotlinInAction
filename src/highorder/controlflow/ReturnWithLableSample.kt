package highorder.controlflow

import highorder.inline.Person

/**
 * 람다로부터 반환: 레이블을 사용한 return
 *
 * 람다 식에서도 로컬 return을 사용할 수 있다. 람다 안에서 로컬 return은 for 루프의 break와 비슷한 역할을 한다.
 * 로컬 return은 람다의 실행을 끝내고 람다를 호출했던 코드의 실행을 계속 이어간다.
 * 로컬 return과 넌로컬 return을 구분하기 위해 레이블(lable)을 사용해야 한다.
 *
 * 참고로 람다 식에는 레이블이 2개 이상 붙을 수 없으니 유의해야 한다.
 */

/** 레이블을 통해 로컬 리턴 사용 */
fun lookForAliceWithLabel(people: List<Person>) {
    people.forEach label@{  // 람다 식 앞에 레이블을 붙인다.
        if (it.name == "Alice") return@label // return@label은 앞에서 정의한 레이블을 참조한다.
    }
    println("Alice might be somewhere") // 항상 이 줄이 출력된다.
}

/**
 *  함수 이름을 return 레이블로 사용하기
 *  : 람다에 레이블 붙여서 사용하는 대신 람다를 인자로 받는 인라인 함수의 이름을 return 뒤에 레이블로 사용해도 된다.
 */
fun lookForAliceWithFunctionNameLabel(people: List<Person>) {
    people.forEach{
        if (it.name == "Alice") return@forEach // return@forEach는 람다 식으로부터 반환시킨다.
    }
    println("Alice might be somewhere")
}

fun main() {
    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    lookForAliceWithLabel(people) // Alice might be somewhere

    /**
     * 레이블이 붙은 this 식
     * : 수신 객체 지정 람다 앞에 레이블을 붙인 경우 this 뒤에 그 레이블을 붙여서 묵시적인 컨텍스트 객체를 지정할 수 있다.
     */
    println(StringBuilder().apply sb@{ // this@sb를 통해 이 람다의 묵시적 수신 객체에 접근할 수 있다.
        listOf(1, 2, 3).apply { // this는 이 위치를 둘러싼 가장 안쪽 영역의 묵시적 수신 객체를 가리킨다.
            this@sb.append(this.toString()) // 모든 묵시적 수신 객체를 사용할 수 있다. 다만 바깥쪽 묵시적 수신 객체에 접근할 때는 레이블을 명시해야 한다.
        }
    })
}

/**
 * 하지만 넌로컬 반환문은 장황하고, 람다 안의 여러 위치에 return 식이 들어가야 하는 경우 사용하기 불편하다.
 * 코틀린은 코드 블록을 여기저기 전달하기 위한 다른 해법을 제공하며, 그 해법을 사용하면 넌로컬 반환문을 여럿 사용해야 하는 코드 블록을 쉽게 작성할 수 있다.
 * 바로 무명 함수가 그 해법이다.
 */