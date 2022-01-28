package conventions.delegate

/**
 * 지연 초기화(Lazy initialization)
 * : 객체의 일부분을 초기화하지 않고 남겨뒀다가 실제로 그 부분의 값이 필요한 경우 초기화할 때 흔히 쓰이는 패턴
 */

// 이메일 프로퍼티의 값을 최초로 사용할 떄 단 한 번만 이메일을 데이터베이스에서 가져오기
class Email { /* ... */ }

fun loadEmails(person: Person): List<Email> {
    println("${person.name}의 이메일을 가져옴")
    return listOf(/* ... */)
}


class Person(val name: String) {
    private var _emails: List<Email>? = null  // 데이터를 저장하고 emails의 위임 객체 역할을 하는 _emails 프로퍼티
    val emails: List<Email>
        get() {
            if (_emails == null) {
                _emails = loadEmails(this)  // 최초 접근 시 이메일을 가져옴
            }

            return _emails!!  // 저장해 둔 데이터가 있으면 그 데이터를 반환
        }
}

fun main() {
    val p = Person("Alice")
    p.emails // 최소로 emails를 읽을 때 단 한 번만 이메일을 가져온다.
    p.emails
}

/**
 * # 위의 코드에서는 "뒷받침하는 프로퍼티(backing property)"라는 기법을 사용한다.
 *
 * _emails라는 프로퍼티는 값을 저장하고, 다른 프로퍼티인 emails는 _emails라는 프로퍼티에 대한 읽기 연산을 제공한다.
 * _emails는 널이 될 수 있는 타입인 반면 emails는 널이 될 수 없는 타입이므로 프로퍼티를 두 개 사용해야 한다.
 * 이런 기법을 자주 사용하므로 잘 숙지해두자.
 *
 * 하지만 이런 코드를 만드는 건 약간 성가신데다, 이 구현은 스레드 안전하지 않아서 언제나 제대로 작동한다고 보장할 수 없다.
 * -> 코틀린의 더 나은 해법, "위임 프로퍼티"
 *
 * 위임 프로퍼티는 데이터를 저장할 때 쓰이는 뒷받침하는 프로퍼티와 값이 오직 한 번만 초기화됨을 보장하는 게터 로직을 함께 캡슐화해준다.
 * 예제와 같은 경우를 위한 위임 객체를 반환하는 표준 라이브러리 함수가 바로 lazy다.
 */

/*
class Person(val name: String) {
    val emails by lazy { loadEmails(this) }
}
 */

/**
 * lazy 함수는 코틀린 관례에 맞는 시그니처의 getValue 메소드가 들어있는 객체를 반환한다.
 * 따라서 lazy를 by 키워드와 함께 사용해 위임 프로퍼티를 만들 수 있다.
 * lazy 함수의 인자는 값을 초기화할 때 호출할 람다다.
 *
 * lazy 함수는 기본적으로 스레드 안전하다. 하지만 필요에 따라 동기화에 사용할 락을 lazy 함수에 전달할 수 있고,
 * 다중 스레드 환경에서 사용하지 않을 프로퍼티를 위해 lazy 함수가 동기화 하지 못하게 막을 수도 있다.
 */