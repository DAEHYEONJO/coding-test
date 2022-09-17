package dp

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val grapes = intArrayOf(0) + IntArray(n) {
        readLine().toInt()
    }

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