package dp

import java.io.*

// dp[i-2]+g[i] -> 퐁 ~ 당 (0 x 0) -> 현재꺼 먹고 현재꺼 전꺼 안먹고 현재꺼 전전꺼 먹은거
// dp[i-3]+g[i-1]+g[i] -> 연속 2개 현재 포함
// dp[i-1] -> 현재 이전꺼 연속 2개
// 이 셋중 max 가 dp[i]다
// dp[i]란 i 번째까지 마실 수 있는 포도주의 max 량이다.

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val g = IntArray(n + 1) { 0 }
    repeat(n) {
        g[it + 1] = readLine().toInt()
    }
    val dp = IntArray(n + 1) { 0 }
    dp[0] = 0
    dp[1] = g[1]
    if (n >= 2) {
        dp[2] = g[1] + g[2]
        for (i in 3 until n + 1) {
            val max = (dp[i - 2] + g[i]).coerceAtLeast(dp[i - 3] + g[i - 1] + g[i])
            dp[i] = max.coerceAtLeast(dp[i - 1])
        }
    }
    println(dp.last())
}