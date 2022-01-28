package conventions.delegate.impl.b

import conventions.delegate.impl.a.PropertyChangeAware
import java.beans.PropertyChangeSupport

/**
 * 도우미 클래스를 통해 프로퍼티 변경 통지 구현
 * -> 세터 코드의 중복이 많이 보이므로, 프로퍼티 값을 저장하고 필요에 따라 통지를 보내주는 클래스 추출
 */
class ObservableProperty(val propName: String, var propValue: Int, val changeSupport: PropertyChangeSupport) {
    fun getValue(): Int = propValue
    fun setValue(newValue: Int) {
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(propName, oldValue, newValue)
    }
}

class Person(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
    val _age = ObservableProperty("age", age, changeSupport)
    var age: Int
        get() = _age.getValue()
        set(value) {
            _age.setValue(value)
        }

    var _salary = ObservableProperty("salary", salary, changeSupport)
    var salary: Int
        get() = _salary.getValue()
        set(value) {
            _salary.setValue(value)
        }
}

/**
 * 이 코드는 코틀린의 위임이 실제로 작동하는 방식과 비슷하다.
 * 프로퍼티 값을 저장하고 그 값이 바뀌면 자동으로 변경 통지를 전달해주는 클래스를 만들었고, 로직의 중복을 상당 부분 제거했다.
 *
 * 하지만 아직도 각각의 프로퍼티마다 ObservableProperty를 만들고
 * 게터와 세터에서 ObservableProperty에 작업을 위임하는 준비 코드가 상당 부분 필요하다.
 *
 * 코틀린의 위임 프로퍼티 기능을 활용하면 이런 준비 코드를 없앨 수 있다.
 */