package codeforces

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val ab = Array(1){intArrayOf(0,0)} + Array(n){
        readLine().trim().split(" ").map{it.toInt()}.toIntArray()
    }
    ab.sortBy{it.first()}
    val dp = IntArray(n+1){0}
    dp[1] = 1

    fun lowerBound (end : Int, target: Int): Int{

        var s = 1
        var e = end
        while(s<e){
            val mid = (s+e)/2
            when{
                ab[mid][0] < target -> s = mid + 1
                else -> e = mid
            }
        }
        return s -1
    }

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        var max = dp[1]
        for(i in 2 until n+1){
            val a = ab[i][0]
            val b = ab[i][1]
            val target = a-b
            if (target <= 0) dp[i] = 1
            if (target > 0) {
                val index = lowerBound(i, target)
                dp[i] = dp[index] + 1
                max = max.coerceAtLeast(dp[i])
            }
        }
        w.write("${n-max}")
        w.flush()
    }
}