package dp

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        val (n, k) = readLine().trim().split(" ").map { it.toInt() }
        val dp  = IntArray(k+1){Int.MAX_VALUE}
        val coins = HashSet<Int>()
        repeat(n){
            coins.add(readLine().toInt())
        }
        coins.forEach {
            if (it < k+1) dp[it] = 1
        }
        for (i in 1 until k+1){
            coins.forEach {
                val coin = it
                if (i-coin>=1 && dp[i-coin]!=Int.MAX_VALUE){
                    dp[i] = dp[i].coerceAtMost(dp[i-coin]+1)
                }
            }
        }
        w.write("${
            dp.last().let { 
                if (it!=Int.MAX_VALUE) it
                else -1
            }
        }")
    }

}