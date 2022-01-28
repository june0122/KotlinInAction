package conventions.delegate.impl.d

import conventions.delegate.impl.a.PropertyChangeAware
import conventions.delegate.impl.c.ObservableProperty
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * 관찰 가능한 프로퍼티 로직을 직접 작성하는 대신 코틀린 표준 라이브러리를 사용해도 된다.
 * 표준 라이브러리의 클래스에는 이미 ObservableProperty와 비슷한 클래스가 있다.
 * 다만 이 표준 라이브러리의 클래스는 PropertyChangeSupport와는 연결돼 있지 않다.
 * 따라서 프로퍼티 값의 변경을 통지할 때 PropertyChangeSupport를 사용하는 방법을 알려주는 람다를 그 표준 라이브러리 클래스에 넘겨야 한다.
 */

/** Delegates.observable을 사용해 프로퍼티 변경 통지 구현하기 */
class Person(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
    private val observer = { prop: KProperty<*>, oldValue: Int, newValue: Int ->
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }
    var age: Int by Delegates.observable(age, observer)
    var salary: Int by Delegates.observable(salary, observer)
}

/**
 * by 오른쪽에 있는 식이 꼭 새 인스턴스를 만들 필요는 없다. 함수 호출, 다른 프로퍼티, 다른 식 등이 by의 우항에 올 수 있다.
 * 다만 우항에 있는 식을 계산한 결과인 객체는 컴파일러가 호출할 수 있는 올바른 타입의 getValue와 setValue를 반드시 제공해야 한다.
 */