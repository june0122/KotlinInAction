package conventions.delegate.impl.c

import conventions.delegate.impl.a.PropertyChangeAware
import java.beans.PropertyChangeSupport
import kotlin.reflect.KProperty

/** ObservalbeProperty를 프로퍼티 위임에 사용할 수 있게 변경 */
class ObservableProperty(var propValue: Int, val changeSupport: PropertyChangeSupport) {
    operator fun getValue(p: Person, prop: KProperty<*>): Int = propValue
    operator fun setValue(p: Person, prop: KProperty<*>, newValue: Int) {
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }
}

/** 위임 프로퍼티를 통해 프로퍼티 변경 통지 받기 */
class Person(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
    var age: Int by ObservableProperty(age, changeSupport)
    var salary: Int by ObservableProperty(salary, changeSupport)
}

/**
 * by 키워드를 통해 위임 객체를 지정하면 이전 예제에서 직접 코드를 짜야 했던 여러 작업을 코틀린 컴파일러가 자동으로 처리해준다.
 *
 * by 오른쪽에 오는 객체를 "위임 객체"라고 부른다.
 * 코틀린은 위임 객체를 감춰진 프로퍼티에 저장하고, 주 객체의 프로퍼티를 읽거나 쓸 때마다 위임 객체의 getValue와 setValue를 호출해준다.
 */

/** 프로퍼티 변경 통지를 직접 구현한 이전 Person 클래스 */
/*
class Person(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
    var age: Int = age
        set(newValue) {
            val oldValue = field  // 뒷받침하는 필드에 접근할 때 "field" 식별자를 사용
            field = newValue
            changeSupport.firePropertyChange(  // 프로퍼티 변경을 리스너에게 통지
                "age", oldValue, newValue
            )
        }

    var salary: Int = salary
        set(newValue) {
            val oldValue = field
            field = newValue
            changeSupport.firePropertyChange(
                "salary", oldValue, newValue
            )
        }
}
*/