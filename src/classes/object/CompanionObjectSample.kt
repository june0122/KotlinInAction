package classes.`object`

class A {
    companion object {
        fun bar() {
            println("Companion object called")
        }
    }
}

// A.bar()로 동반 객체 멤버 호출


/*
// 부 생성자가 여럿 있는 클래스
class User {
    val nickname: String
    constructor(email: String) {
        nickname = email.substringBefore('@')
    }
    constructor(facebookAccoutId: Int) {
        nickname = getFacebookName(facebookAccoutId)
    }
}

*/

/** 위의 로직을 표현하는 더 유용한 방법이 아래와 같이 클래스의 인스턴스를 생성하는 팩토리 메서드이다. */

class User private constructor(val nickname: String) {
    companion object {
        fun newSubscribingUser(email: String) = User(email.substringBefore('@'))

        /**
         * 페이스북 사용자 ID로 사용자를 만드는 팩토리 메서드
         * 팩토리 메서드는 목적에 따라 이름을 정할 수 있으므로 매우 유용하다.
         *
         * 하지만 클래스를 확장해야만 하는 경우에는 동반 객체 멤버를 하위 클래스에서 오버라이드 할 수 없으므로
         * 여러 생성자를 사용하는 편이 더 나은 해법이다.
         */
        fun newFacebookUser(accountId: Int) = User(getFacebookName(accountId))
    }
}


fun main() {
    val subscribingUser = User.newSubscribingUser("bob@gmail.com")
    val facebookUser = User.newFacebookUser(4)
    println(subscribingUser.nickname)
}

fun getFacebookName(id: Int): String {
    // 구현 코드
    return ""
}