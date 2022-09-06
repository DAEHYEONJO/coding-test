package dp

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val dp = IntArray(n+1){1000000}
    dp[n] = 0
    for (i in n downTo 1){
        if (i.mod(3)==0){
            dp[i/3] = dp[i/3].coerceAtMost(dp[i]+1)
        }
        if (i.mod(2)==0){
            dp[i/2] = dp[i/2].coerceAtMost(dp[i]+1)
        }
        dp[i-1] = dp[i-1].coerceAtMost(dp[i]+1)
    }
    println(dp[1])
}