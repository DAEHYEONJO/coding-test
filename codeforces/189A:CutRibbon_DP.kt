package codeforces

import java.io.*

fun main()  = with(BufferedReader(InputStreamReader(System.`in`))){
    val info = readLine().trim().split(" ").map{ it.toInt() }.toIntArray()
    val n = info[0]
    val dp = IntArray(n+1){0}
    for(i in 1 until 4){
        if (info[i]<=n) dp[info[i]] = 1
    }

    for(i in 1 until n+1){
        for(j in 1 until 4){
            val newRibbon = i + info[j]
            if (dp[i]!=0 && newRibbon <= n){
                dp[newRibbon]=dp[newRibbon].coerceAtLeast(dp[i]+1)
            }
        }
    }

    println(dp.last())


}