package lambdas.funinterface


fun main() {
    val sample = JavaMethodSample()


    with(sample) {
        /**
         * 컴파일러가 자동으로 람다를 Runnable 인스턴스로 변환해준다.
         * 여기서 'Runnable 인스턴스'는 'Runnable을 구현한 무명 클래스의 인스턴스'를 뜻한다.
         * 이때 무명 클래스에 있는 유일한 추상 메소드를 구현할 때 람다 본문을 메소드 본문으로 사용한다. (Runnable의 추상 메소드 run)
         */
        postPoneComputation(1000) { println(42) } // 프로그램 전체에서 Runnable의 인스턴스는 단 하나만 만들어진다.

        /**
         * Runnable을 구현하는 무명 객체를 명시적으로 만들어 사용하기
         *
         * 하지만 람다와 무명 객체 사이에는 차이가 있다.
         * 객체를 명시적으로 선언 -> 메소드를 호출할 때마다 새로운 객체 생성
         * 람다 -> 정의가 들어있는 함수의 변수에 접근하지 않는 람다에 대응하는 무명 객체를 메소드를 호출할 때마다 반복 사용
         */
        postPoneComputation(1000, object : Runnable {
            override fun run() {
                println(42)
            }
        })

        // 명시적인 object 선언을 사용하면서 람다와 동일한 코드
        val runnable = Runnable { println(42) }
        fun handleComputation() {
            postPoneComputation(1000, runnable) // 모든 handleComputation 호출에 같은 객체를 사용
        }

        /**
         * 람다가 주변 영역의 변수를 포획한다면 매 호출마다 같은 인스턴스를 사용할 수 없다.
         * 그런 경우 컴파일러는 매번 주변 영역의 변수를 포획한 새로운 인스턴스를 생성해준다.
         */

        // id를 필드로 저장하는 새로운 Runnable 인스턴스를 매번 새로 만들어 사용한다.
        fun handleComputation2(id: String) {
            postPoneComputation(1000) { println(id) } // handleComputation2를 호출할 때마다 새로 Runnable 인스턴스 생성
        }
    }



}