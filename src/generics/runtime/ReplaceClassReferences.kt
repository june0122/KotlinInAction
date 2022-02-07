package generics.runtime

/**
 * 실체화한 타입 파라미터로 클래스 참조 대신(Replacing class references with reified type parameters)
 */

/** 표준 자바 API인 ServiceLoader를 사용해 서비스를 읽어 들이기 */
//val serviceImpl = ServiceLoader.load(Service::class.java)

/** 구체화한 타입 파라미터를 사용해 다시 작성하기 */
// val serviceImpl = loadService<Service>()

/*
// 클래스를 타입 인자로 지정하면 ::class.java라고 쓰는 경우보다 더 이해하기 쉽다.
inline fun <reified T> loadService() { // 타입 파라미터를 reified로 표시
    return ServiceLoader.load(T::class.java) // T::class로 타입 파라미터의 클래스를 가져온다.
}
 */

/**
 * 안드로이드의 startActivity 함수 간단하게 만들기
 *
 * 안드로이드 개발자라면 더 익숙한 예제가 바로 액티비티를 표시하는 과정이다.
 * 액티비티 클래스를 java.lang.Class로 전달하는 대신 실체화한 타입 파라미터를 사용할 수 있다.
 */
/*
inline fun <reified T: Activity> Context.startActivity() { // 타입 파라미터를 reified로 표시
    val intent = Intent(this, T::class.java) // T::class로 타입 파라미터의 클래스를 가져온다.
    startActivity(intent)
}

startActivity<DetailActivity>() // 액티비티를 표시하기 위해 메서드를 호출
 */