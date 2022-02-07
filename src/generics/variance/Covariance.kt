package generics.variance

/**
 * 공변성: 하위 타입 관계를 유지
 * 코틀린에서 제네릭 클래스가 타입 파라미터에 대해 공변적임을 표시하려면 타입 파라미터 이름 앞에 out을 넣어야 한다.
 */

interface Producer<out T> { // 클래스가 T에 대해 공변적이라고 선언한다.
    fun produce(): T
}

/** 무공변 컬렉션 역할을 하는 클래스 정의 */
/*
open class Animal {
    fun feed() { /* ... */ }
}

class Herd<T : Animal> {
    val size: Int
        get() = /* ... */
    operator fun get(i: Int): T { /* ... */ }
}

fun feedAll(animals: Herd<Animal>) {
    for (i in 0 until animals.size) {
        animals[i].feed()
    }
}
 */

/** 무공변 컬렉션 역할을 하는 클래스 사용하기 */
/*
class Cat: Animal() {
    fun cleanLitter() { /* ... */ }
}

fun takeCareOfCats(cats: Herd<Cat>) {
    for (i in 0 until cats.size) {
        cats[i].cleanLitter()
        // feedAll(cats) // Error: inferred type is Herd<Cat>, but Herd<Animal> was expected
    }
}
 */

/** 공변적 컬렉션 역할을 하는 클래스 사용하기 */
/*
class Herd<out T : Animal> { // T는 이제 공변적이다.
    /* ... */
}

fun takeCareOfCats(cats: Herd<Cat>) {
    for (i in 0 until cats.size) {
        cats[i].cleanLitter()
        feedAll(cats) // 캐스팅을 할 필요가 없음
    }
}
 */

/**
 * 클래스 멤버를 선언할 때 타입 파라미터를 사용할 수 있는 지점은 모두 인과 아웃 위치로 나뉜다.
 * T라는 타입 파라미터를 선언하고 T를 사용하는 함수가 멤버로 있는 클래스를 생각해보자.
 * T가 함수의 반환 타입에 쓰인다면 T는 "아웃" 위치에 있다. 그 함수는 T 타입의 값을 생산(produce)한다.
 * T가 함수 파라미터 타입에 쓰인다면 T는 "인" 위치에 있다. 그런 함수는 T 타입의 값을 소비(consume)한다.
 */

/** 함수 파라미터 타입은 "인" 위치, 함수 반환 타입은 "아웃" 위치에 있다. */
interface Transformer<T> {
    fun transform(t: T): T
}

/**
 * 타입 파라미터 T에 붙은 out 키워드의 두 가지 의미
 * 1. 공변성: 하위 타입 관계가 유지된다.
 * 2. 사용 제한: T를 "아웃" 위치에서만 사용할 수 있다.
 */

interface MutableList<T> : List<T>, MutableCollection<T> { // MutableList는 T에 대해 공변적일 수 없다.
    override fun add(element: T): Boolean { // T가 인 위치에 쓰이기 때문이다.
        TODO("Not yet implemented")
    }
}

/**
 * 생성자 파라미터는 인이나 아웃 어느 쪽도 아니라는 사실에 유의하라.
 * 타입 파라미터가 out이라 해도 그 타입을 여전히 생성자 파라미터 선언에 사용할 수 있다.
 */
// class Herd<out T : Animal>(vararg animals: T) { /* ... */ }

/**
 * ★★★ 중요 ★★★
 * " 변성은 코드에서 위험할 여지가 있는 메서드를 호출할 수 없게 만듦으로써
 * 제네릭 타입의 인스턴스 역할을 하는 클래스 인스턴스를 잘못 사용하는 일이 없게 방지하는 역할을 한다. "
 *
 * 생성자는 (인스턴스를 생성한 뒤) 나중에 호출할 수 잇는 메서드가 아니다. 따라서 생성자는 위험할 여지가 없다.
 *
 * 하지만 val이나 var 키워드를 생성자 파라미터에 적는다면 게터나 (변경 가능한 프로퍼티의 경우) 세터를 정의하는 것과 같다.
 * 따라서 읽기 전용 프로퍼티는 아웃 위치, 변경 가능 프로퍼티는 아웃과 인 위치 모두에 해당한다.
 */

// 여기서는 T 타입인 leadAnimal 프로퍼티가 인 위치에 있기 때문에 T를 out으로 표시할 수 없다.
// class Herd<T : Animal>(var leadAnimal: T, vararg animals: T) { /* ... */ }

/**
 * 이런 위치 규칙은 오직 외부에서 볼 수 있는 (public, protected, internal) 클래스 API에만 적용할 수 있다.
 * 비공개(private) 메서드의 파라미터는 인도 아니고 아웃도 아닌 위치다.
 * 변성 규칙은 클래스 외부의 사용자가 클래스를 잘못 사용하는 일을 막기위한 것이므로 클래스 내부 구현에는 적용되지 않는다.
 */