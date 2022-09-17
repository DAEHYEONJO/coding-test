package dp

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val grapes = intArrayOf(0) + IntArray(n) {
        readLine().toInt()
    }
    // dp[i] -> i 번째 index 에서 max 값
    // 정의에 따르면
    // dp[0] = 0
    // dp[1] = g[1]
    // dp[2] = g[1] + g[2] 다.
    // 왜냐하면 최대 두개 연속해서 먹을수 있기 때문이다.
    // dp[3]
    // x o o -> g[2] + g[3] = dp[0] + g[2] + g[3] => i, i-1 번째 연속해서 선택하는 경우
    // o x o -> g[1] + g[3] = dp[1] + g[3] => i, i-2 번째를 선택한다. i-1번째는 건너뛴다
    // o o x -> g[1] + g[2] = dp[2] => i-2, i-1 번째를 연속하여 두번 선택하는 경우
    // x x o 와같이 한개만 선택하는것은 dp 배열의 정의에 의하여 모순이다. 3번째를 선택했을때 1번 또는 2번을 선택했을때 더 큰 값을 얻을수 있기 때문이다.

    // x o o x -> dp[3] => i-1, i-2 번째를 연속해서 두번 선택하는 경우
    // o x o o -> dp[1] + g[3] + g[4] => i, i-1 번째 연속해서 선택하는 경우
    // o o x o -> dp[2] + g[4] => i-2, i 번째를 선택한다. i-1번째는 건너뛴다.
    // dp[i] = maxOf(dp[i-1], dp[i-3] + grape[i-1] + grape[i], dp[i-2] + grape[i])

    val dp = IntArray(n + 1).apply {
        set(1, grapes[1])
    }

    if (n + 1 >= 3) {
        dp[2] = grapes[1] + grapes[2]
        for (i in 3 until n + 1) {
            dp[i] = maxOf(dp[i - 1], dp[i - 3] + grapes[i - 1] + grapes[i])
            dp[i] = maxOf(dp[i], dp[i - 2] + grapes[i])
        }
    }

    println(dp[n])


}