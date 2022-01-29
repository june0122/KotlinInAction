package highorder.declaration

/**
 * 람다를 활용한 중복 제거
 */

/** 사이트 방문 데이터 정의 */
data class SiteVisit(
    val path: String,
    val duration: Double,
    val os: OS
)

enum class OS { WINDOW, LINUX, MAC, IOS, ANDROID }

val log = listOf(
    SiteVisit("/", 34.0, OS.WINDOW),
    SiteVisit("/", 22.0, OS.MAC),
    SiteVisit("/login", 12.0, OS.WINDOW),
    SiteVisit("/signup", 8.0, OS.IOS),
    SiteVisit("/", 16.3, OS.ANDROID)
)

// 윈도우 OS 사용자 평균 방문 시간 출력: 사이트 방문 데이터를 하드 코딩한 필터를 사용해 분석하기
val averageWindowsDuration = log
    .filter { it.os == OS.WINDOW }
    .map(SiteVisit::duration)
    .average()

// 특정 OS의 사용자 평균 방문 시간 출력: 일반 함수를 통해 중복 제거
fun List<SiteVisit>.averageDurationFor(os: OS) = // 중복 코드를 별도 함수로 추출
    filter { it.os == os }.map(SiteVisit::duration).average()

// 모바일 디바이스 사용자의 평균 방문 시간 출력: 복잡하게 하드코딩한 필터를 사용
val averageMobileDuration = log
    .filter { it.os in setOf(OS.IOS, OS.ANDROID) }
    .map(SiteVisit::duration)
    .average()

// 고차 함수를 사용해 중복 제거
fun List<SiteVisit>.averageDurationFor(predicate: (SiteVisit) -> Boolean) =
    filter(predicate).map(SiteVisit::duration).average()

fun main() {
    println(averageWindowsDuration) // 23.0

    println(log.averageDurationFor(OS.WINDOW)) // 23.0
    println(log.averageDurationFor(OS.MAC)) // 22.0

    println(averageMobileDuration) // 12.15

    println(log.averageDurationFor { it.os in setOf(OS.IOS, OS.ANDROID) }) // 12.15
    println(log.averageDurationFor { it.os == OS.IOS && it.path == "/signup" }) // 8.0
}

/**
 * 일부 잘 알려진 디자인 패턴을 함수 타입과 람다 식을 사용해 단순화 할 수 있다.
 *
 * 전략 패턴을 생각해보자.
 * - 람다식이 없다면 인터페이스를 선언하고 구현 클래스를 통해 전략을 정의해야 한다.
 * - 함수 타입을 언어가 지원하면 일반 함수 타입을 사용해 전략을 표현할 수 있고 경우에 따라 다른 람다 식을 넘김으로써 여러 전략 전달이 가능
 */