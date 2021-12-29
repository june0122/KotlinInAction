@file:JvmName("StringFunctions")

package functions

import java.lang.StringBuilder

const val UNIX_LINE_SEPERATOR = "\n"

fun <T> joinToString(collection: Collection<T>, separator: String, prefix: String, postfix: String): String {
    val result = StringBuilder(prefix)

    for ((index, element) in collection.withIndex()) {
        if (index > 0 ) result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}