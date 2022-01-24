package typesystem.nullability

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MyService {
    fun performAction(): String = "foo"
}

/*
class MyTest {
    private var myService: MyService? = null
    @Before fun setUp() {
        myService = MyService()
    }
    @Test fun testAction() {
        Assert.assertEquals("foo",
            myService!!.performAction()) // 반드시 널 가능성에 신경 써야 한다. !! 또는 ?을 꼭 써야 함.
    }
}
*/

class MyTest {
    private lateinit var myService: MyService // 초기화하지 않고 널이 될 수 없는 프로퍼티를 선언

    @Before fun setUp() {
        myService = MyService()
    }

    @Test fun testAction() {
        Assert.assertEquals("foo",
            myService.performAction()) // 널 검사를 수행하지 않고 프로퍼티를 사용
    }
}

/**
 * 나중에 초기화하는 프로퍼티는 항상 var여야 한다.
 * val 프로퍼티는 final 필드로 컴파일되며, 생성자 안에서 반드시 초기화해야 한다.
 * 따라서 생성자 밖에서 초기화해야 하는 나중에 초기화하는 프로퍼티는 항상 var여야 한다.
 */