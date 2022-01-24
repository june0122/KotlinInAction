package typesystem.collections

import typesystem.types.Person

class PersonParser: DataParser<Person> {
    override fun parseData(input: String, output: MutableList<Person>, errors: MutableList<String?>) {
        //...
    }
}