package highorder.declaration

/** 함수를 반환하는 함수 정의 */
enum class Delivery { STANDARD, EXPEDITED }

class Order(val itemCount: Int)

fun getShippingCostCalculator(delivery: Delivery): (Order) -> Double { // 함수를 반환하는 함수 선언
    if (delivery == Delivery.EXPEDITED) {
        return { order -> 6 + 2.1 * order.itemCount }
    }
    return { order -> 1.2 * order.itemCount }
}

/*
    val calculator = getShippingCostCalculator(Delivery.EXPEDITED)
    println("Shipping costs ${calculator(Order(3))}")
*/

/** 함수를 반환하는 함수를 UI 코드에서 정의 */
data class Person(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String?
)

class ContactListFilters {
    var prefix: String = ""
    var onlyWithPhoneNumber: Boolean = false

    fun getPredicate(): (Person) -> Boolean { // 함수를 반환하는 함수를 정의
        val startWithPrefix = { p: Person ->
            p.firstName.startsWith(prefix) || p.lastName.startsWith(prefix)
        }

        if (!onlyWithPhoneNumber) {
            return startWithPrefix // 함수 타입의 변수를 반환
        }

        return { startWithPrefix(it) && it.phoneNumber != null } // 람다를 반환
    }
}

fun main() {
    val contacts = listOf(
        Person("Dmitry", "Jemerov", "123-4567"),
        Person("Svetlana", "Isakova", null)
    )
    val contactListFilters = ContactListFilters()
    with(contactListFilters) {
        prefix = "Dm"
        onlyWithPhoneNumber = true
    }

    // getPredicate이 반환한 함수를 filter에게 인자로 넘긴다.
    println(contacts.filter(contactListFilters.getPredicate())) // [Person(firstName=Dmitry, lastName=Jemerov, phoneNumber=123-4567)]
}