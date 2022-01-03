package classes.constructor

//class User constructor(_nickname: String) { // 파라미터가 하나만 있는 주 생성자
//    val nickname: String
//    // 초기화 블록
//    init {
//        nickname = _nickname
//    }
//}

//class User(_nickname: String) { // 파라미터가 하나뿐인 주 생성자
//    val nickname = _nickname // 프로퍼티를 주 생성자의 파라미터로 초기화
//}

//class User(val nickname: String) // "val"은 이 파라미터에 상응하는 프로퍼티가 생성된다는 뜻이다.

/** ----- 위의 세 가지 User 선언은 모두 같다. ----- */

//class User(val nickname: String = "default", val isSubscribed: Boolean = true) // 생성자 파라미터에 대한 디폴트 값을 제공

/**
 * 161p. 모든 생성자 파라미터에 디폴트 값을 지정하면 컴파일러가 자동으로 파라미터가 없는 생성자를 만들어준다.
 * 그렇게 자동으로 만들어진 파라미터 없는 생성자는 디폴트 값을 사용해 클래스를 초기화한다.
 * 의존관계 주입(DI) 프레임워크 등 자바 라이브러리 중에는 파라미터가 없는 생성자를 통해 객체를 생성해야만
 * 라이브러리 사용이 가능한 경우가 있는데, 코틀린이 제공하는 파라미터 없는 생성자는 그런 라이브러리와의 통합을 쉽게 해준다.
 */

open class User(val nickname: String) {}
class TwitterUser(nickname: String): User(nickname) {}

open class Button {}
class RadioButton: Button() {}

class Secretive private constructor() {} // 클래스 외부에서 인스턴스화 하지 못하게 모든 생성자를 private 로!

