package dp

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    //팰린드롬이란 원본 수열을 뒤집었을 때 똑같은 수열에 해당한다.
    //원본 수열과 뒤집은 수열의 Longest Common Subsequence 의 길이를 n 에서 뺀게 정답이다.
    //가장 긴 공통부분에 해당하지 않는 숫자를 어딘가 끼워넣으면 되기 때문이다.
    val n = readLine().toInt()
    val origin = IntArray(1) + readLine().split(" ").map{it.toInt()}.toIntArray()
    val dp = Array(n+1){IntArray(n+1){0} }

    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        for(i in 1 until n+1){
            for(j in 1 until n+1){
                if (origin[i] == origin[n+1-j]){
                    dp[i][j] = dp[i-1][j-1] + 1
                }else{
                    dp[i][j] = dp[i-1][j].coerceAtLeast(dp[i][j-1])
                }
            }
        }
        w.write("${n-dp[n][n]}")
        w.flush()
    }


}