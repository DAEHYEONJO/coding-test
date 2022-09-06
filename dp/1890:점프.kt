package dp

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val table = Array(n){readLine().trim().split(" ").map { it.toInt() }.toIntArray()}
    val dp = Array(n){LongArray(n){0L} }
    dp[0][0] = 1L
    val DY = intArrayOf(0, 1) //오, 아
    val DX = intArrayOf(1, 0)

    for(i in 0 until n){
        for(j in 0 until n){
            if (i==n-1 && j==n-1) {
                break
            }
            for ((dy, dx) in DY.zip(DX)){
                val newY = i + dy*table[i][j]
                val newX = j + dx*table[i][j]
                if (newY in (0 until n) && newX in (0 until n)){
                    dp[newY][newX]+=dp[i][j]
                }
            }
        }
    }
    println(dp.last().last())
}