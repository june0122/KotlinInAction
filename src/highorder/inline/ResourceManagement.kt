package highorder.inline

import java.io.BufferedReader
import java.io.FileReader

/** 자바 try-with-resource */
/*
static String readFirstLineFromFile(String path) throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
        return br.readLine();
    }
}
*/

/** use 함수를 자원 관리에 활용: 자바의 try-with-resource와 같은 기능을 제공 */
fun readFirstLineFromFile(path: String): String {
    BufferedReader(FileReader(path)).use { br -> // BufferedReader 객체를 만들고 "use" 함수를 호출하면서 파일에 대한 연산을 실행할 람다를 넘긴다.
        return br.readLine() // 자원(파일)에서 맨 처음 가져온 한 줄을 람다가 아닌 readFirstLineFromFile에서 반환
    }
}

/**
 * use 함수는 닫을 수 있는(closeable) 자원에 대한 확장 함수며, 람다를 인자로 받는다.
 * use는 람다를 호출한 다음에 자원을 닫아준다. 이때 람다가 정상 종료한 경우는 물론 람다 안에서 예외가 발생한 경우에도 자원을 확실히 닫는다.
 * 물론 use 함수도 inline 함수이며, 따라서 use를 사용해도 성능에는 영향이 없다.
 *
 * 람다의 본문 안에서 사용한 return은 넌로컬 return이다. 이 return문은 람다가 아니라 readFirstLineFromFile 함수를 끝내면서 값을 반환한다.
 */