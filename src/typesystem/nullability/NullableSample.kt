package typesystem.nullability

import java.util.*

/** 어떤 타입이든 타입 이름 뒤에 물음표를 붙이면 그 타입의 변수나 프로퍼티에 null 참조를 저장할 수 있다는 뜻이다. */

// if 검사를 통해 null 값 다루기
fun strLenSafe(s: String?): Int =
    if (s != null) s.length else 0 // 엘비스 연산자를 통해 간략화 가능

// Safe calls
fun printAllCaps(s: String?) {
    val allCaps: String? = s?.uppercase()
    println(allCaps)
}

class Employee(val name: String, val manager: Employee?)

fun managerName(employee: Employee): String? = employee.manager?.name


class Address(val streetAddress: String, val zipCode: Int, val city: String, val country: String)

class Company(val name: String, val address: Address?)

class Person(val name: String, val company: Company?)

fun Person.countryName() : String {
    val country = this.company?.address?.country
    return if (country != null) country else "Unknown" // 엘비스 연산자를 통해 간략화 가능
}

fun printShippingLabel(person: Person) {
    val address = person.company?.address
        ?: throw IllegalArgumentException("No address")
    with(address) {
        println(streetAddress)
        println("$zipCode $city, $country")
    }
}

// Safe casts
class Person2(val firstName: String, val lastName: String) {
    override fun equals(other: Any?): Boolean {
        val otherPerson = other as? Person2 ?: return false

        return otherPerson.firstName == firstName &&
                otherPerson.lastName == lastName
    }

    override fun hashCode(): Int = firstName.hashCode() * 37 + lastName.hashCode()
}

fun main() {
    val x: String? = null
    println(strLenSafe(x))
    println(strLenSafe("abc"))

    printAllCaps("abc")
    printAllCaps(null)

    val ceo = Employee("Da Boss", null)
    val developer = Employee("Bob Smith", ceo)
    println(managerName(developer))
    println(managerName(ceo))

    val address = Address("Elsester. 47", 80687, "Munich", "Germany")
    val jetbrains = Company("JetBrains", address)
    val person = Person("Dmitry", jetbrains)

    printShippingLabel(person)
//    printShippingLabel(Person("Alexey", null))

    val p1 = Person2("Dmitry", "Jemerov")
    val p2 = Person2("Dmitry", "Jemerov")
    println(p1 == p2)
    println(p1.equals(42))
}