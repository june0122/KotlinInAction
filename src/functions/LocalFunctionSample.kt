package functions

class User(val id: Int, val name: String, val address: String)


/** 1. 코드 중복 예제 */
//fun saveUser(user: User) {
//    if (user.name.isEmpty()) {
//        throw IllegalArgumentException(
//            "Can't save user ${user.id}: empty Name"
//        )
//    }
//
//    if (user.address.isEmpty()) {
//        throw IllegalArgumentException(
//            "Can't save user ${user.id}: empty Address"
//        )
//    }
//
//    // user를 DB에 저장
//}


/** 2. 로컬 함수로 코드 중복 줄이기 */
//fun saveUser(user: User) {
//    fun validate(
//        user: User,
//        value: String,
//        fieldName: String
//    ) {
//        if (value.isEmpty()) {
//            throw IllegalArgumentException(
//                "Can't save user ${user.id}: empty $fieldName"
//            )
//        }
//    }
//
//    validate(user, user.name, "Name")
//    validate(user, user.address, "Address")
//
//    // user를 DB에 저장
//}

/** 3. 불필요한 User 파라미터 줄이기 */
// 로컬 함수는 자신이 속한 바깥 함수의 모든 파라미터와 변수를 사용할 수 있다.
//fun saveUser(user: User) {
//    fun validate(value: String, fieldName: String) {
//        if (value.isEmpty()) {
//            throw IllegalArgumentException(
//                "Can't save user ${user.id}: " +
//                        "empty $fieldName"
//            )
//        }
//    }
//
//    validate(user.name, "Name")
//    validate(user.address, "Address")
//
//    // user를 DB에 저장
//}

/** 4. 검증 로직을 확장 함수로 추출 */

fun User.validateBeforeSave() {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException(
                "Can't save user $id: empty $fieldName"
            )
        }
    }
    validate(name, "Name")
    validate(address, "Address")

}

fun saveUser(user: User) {
    user.validateBeforeSave()
    // user를 DB에 저장
}

fun main() {
    saveUser(User(1, "KAMIYU", "AEUG"))
    saveUser(User(2, "AMURO", ""))
}