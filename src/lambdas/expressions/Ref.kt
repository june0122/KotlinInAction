package lambdas.expressions

class Ref<T>(var value: T)

fun main() {
    val counter = Ref(0)
    val inc = { counter.value++ }
    run(inc)
    println(counter.value)

//    var counter = 0
//    val inc = { counter++ }
//    run(inc)
//    println(counter)
}

/**
 * 자바에서는 파이널 변수만 포획할 수 있다.
 * 하지만 교묘한 속임수를 통해 변경 가능한 변수를 포획할 수 있다.
 * 그 속임수는 변경 가능한 변수를 저장하는 원소가 단 하나뿐인 배열을 선언하거나,
 * 변경 가능한 변수를 필드로 하는 클래스를 선언하는 것이다.
 * (안에 들어있는 원소는 변경 가능할지라도 배열이나 클래스의 인스턴스에 대한 참조를 final로 만들면 포획이 가능하다.)
 */

/*
final IntRef count = new IntRef();
count.element = 0;
view.setOnClickListener((OnClickListener)(new OnClickListener() {
   public final void onClick(View it) {
      int var10001 = count.element++;
   }
}));
*/

/*
final int[] count = {0};
binding.fab.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        count[0] = count[0] + 1;
    }
});
*/