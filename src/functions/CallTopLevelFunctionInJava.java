package functions;

import java.util.ArrayList;

public class CallTopLevelFunctionInJava {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
//        JoinKt.joinToString(list, ",", ",", "");
        System.out.println(
                StringFunctions.joinToString(list, ", ", "", "")
        );
    }
}
