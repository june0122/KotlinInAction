package typesystem.collections;

import java.util.List;

interface DataParser<T> {
    void parseData(String input, List<T> output, List<String> errors);
}