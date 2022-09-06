package dp

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val t = readLine().toInt()
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        repeat(t) {
            val n = readLine().toInt()
            val coins = readLine().trim().split(" ").map{ it.toInt() }.toIntArray()
            val m = readLine().toInt()
            val dp = IntArray(m+1){ 0 }
            dp[0] = 1
            coins.forEach{ coin ->
                for(i in coin until m+1){
                    dp[i] += dp[i - coin]
                }
            }
            w.write("${dp.last()}\n")
        }
    }
}