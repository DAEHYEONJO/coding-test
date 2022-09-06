package codeforces

import java.io.*
import java.util.*

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val a = readLine().trim().split(" ").map { it.toLong() }.toLongArray().sortedArray()

/*
    println(a.contentToString())
    println()
*/

    val dp = Array(2) { LongArray(n) { 0L } }
    //dp[0][i] -> i 번째 숫자를 안골랐을때 점수의 최댓값
    //dp[1][i] -> i 번째 숫자를 골랐을때 점수의 최댓값
    dp[1][0] = a[0]

    for(i in 1 until n){
        if (a[i-1] == a[i]){
            dp[0][i] = dp[0][i-1]
            dp[1][i] = dp[1][i-1] + a[i]
        }else{
            dp[0][i] = (dp[0][i-1]).coerceAtLeast(dp[1][i-1])
            if (a[i-1]+1 == a[i]){ // 한개 차이 나는 경우
                dp[1][i] = dp[0][i-1] + a[i]
            }else{
                dp[1][i] = dp[0][i] + a[i]
            }
        }

        /*repeat(2){
            println(dp[it].contentToString())
        }
        println()*/
    }

    println(dp[0].last().coerceAtLeast(dp[1].last()))

}
