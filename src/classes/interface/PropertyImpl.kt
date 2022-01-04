package classes.`interface`

/** 추상 프로퍼티 nickname 을 구현하는 방식들 */

class privateUser(override val nickname: String) : User // 주 생성자에 프로퍼티 직접 선언

class SubscribingUser(val email: String) : User {
    override val nickname: String
        get() = email.substringBefore('@') // 커스텀 게터
}

//class FacebookUser(val accountId: Int) : User {
//    override val nickname: String = getFaceBookName(accountId) // 프로퍼티 초기화 식
//}
