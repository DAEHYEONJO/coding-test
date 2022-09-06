package dp

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val s = readLine().trim().split(" ").map{it.toInt()}.toIntArray()
    val dp = IntArray(n){0}
    dp[0] = s[0]
    var ans = dp[0]
    for (i in 1 until n){
        var index = i-1
        var maxValue = 0
        while (index>=0){
            if (s[index]<s[i]) {
                maxValue = maxValue.coerceAtLeast(dp[index])
            }
            index-=1
        }
        dp[i]+=maxValue+s[i]
        ans = ans.coerceAtLeast(dp[i])
    }

    println(ans)
}