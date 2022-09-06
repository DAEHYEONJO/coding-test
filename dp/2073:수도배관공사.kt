package dp

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){

    //7 ≤ D ≤ 100,000
    //1 ≤ P ≤ 350
    //Li와 Ci는 모두 2^23보다 작거나 같은 양의 정수이다
    //거리(D), 마을수(P)
    val (d, p) = readLine().trim().split(" ").map{ it.toInt() }
    //dp[i] 는 길이 i 일때 수도관 용량의 최댓값이다.
    val dp = IntArray(d+1){0}
    dp[0] = Int.MAX_VALUE
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        repeat(p){
            val st = StringTokenizer(readLine())
            val l = st.nextToken().toInt()//4
            val c = st.nextToken().toInt()
            for (i in d-l downTo 0){//d-l = 3
                val useByLLength = i+l//7
                //i 길이에 l을 더해서 만들 수 있는 i+l 이라는 길이의 파이프는 dp[i] 와 c 중 min 값에 해당하고 dp[i+l] 에 있는 값과
                //앞에서 계산한 min(dp[i], c) 중 더 큰 값을 넣는다.
                //i 는 큰값에서 작은 값으로 진행한다.
                //왜냐하면 작은값에서 큰값으로 진행하는 경우 똑같은 막대기를 2번 이상 중복해서 사용할 수 있기 때문이다.
                // ex) l = 1, l = 4 인 막대기를 사용해서 l = 2 짜리 막대기를 만들 수 있다고 해버기리 때문
                //큰 값에서 작은 값으로 진행하게 되면 입력 l = 1, i = 1 인 경우
                //dp[i]는 이미 다른 값으로 갱신되어 있거나 아무런 값으로 갱신되어있지 않다.
                //이미 다른 값으로 갱신 되어 있는경우가 문제가 되는 원인이였다.
                //l 로 주어진 막대기와 dp[i]에 있는 막대기는 다른 막대기 이므로 길이 2짜리 막대기를 만드는데 문제가 없다.
                dp[useByLLength] = (dp[i].coerceAtMost(c)).coerceAtLeast(dp[useByLLength])
            }

        }
        w.write("${dp.last()}")
        w.flush()
    }
}