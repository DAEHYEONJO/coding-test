package dp

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w->
        val n = readLine().toInt()
        val dp = IntArray(n+1){Int.MAX_VALUE}
        dp[3] = 1
        if (n>=5) dp[5] = 1
        for (i in 6 until n+1){
            val value = dp[i-3].coerceAtMost(dp[i-5])
            if (value!=Int.MAX_VALUE){
                dp[i] = value+1
            }
        }


        w.write("${if (dp[n]==Int.MAX_VALUE) -1 else dp[n]}")
        w.flush()
        this.close()
    }


}