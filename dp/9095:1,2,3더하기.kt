package dp

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        val t = readLine().toInt()
        repeat(t){
            when (val n = readLine().toInt()) {
                1 -> w.write("1\n")
                2 -> w.write("2\n")
                3 -> w.write("4\n")
                else -> {
                    val dp = IntArray(n+1){0}
                    dp[1] = 1
                    dp[2] = 2
                    dp[3] = 4
                    for (i in 4 until n+1){
                        dp[i] += dp[i-1]+dp[i-2]+dp[i-3]
                    }
                    w.write("${dp.last()}\n")
                }
            }
        }
        w.flush()
    }
}