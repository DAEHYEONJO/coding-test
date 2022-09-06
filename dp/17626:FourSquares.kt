package dp

import java.io.*
import kotlin.math.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    var n = readLine().toInt()
    val dp = IntArray(n+1){0}
    dp[1] = 1
    for (i in 1 until n+1){
        var j = 1
        var minNum = Int.MAX_VALUE
        while (j*j<=i){
            val k = i-j*j
            minNum = minNum.coerceAtMost(dp[k]+1)
            j+=1
        }
        dp[i] = minNum
    }
    println(dp.contentToString())
    println(dp[n])
}