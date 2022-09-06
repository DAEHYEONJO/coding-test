package dp

import java.io.*
import java.math.BigInteger

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val (n, m) = readLine().trim().split(" ").map{it.toInt()}
    val dp = Array(101){Array<BigInteger>(101){ BigInteger.ONE } }
    for(i in 1 until 101){
        dp[i][1] = BigInteger.valueOf(i.toLong())
    }

    for (i in 3 until 101){
        for(j in 2 until i){
            dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
        }
    }

    println(dp[n][m])
}