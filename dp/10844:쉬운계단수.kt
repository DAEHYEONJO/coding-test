package dp

import java.io.*
//dp 배열에서 row: 자릿수, col: 끝자리를 의미한다.
//ex) dp[3][2] 가 의미하는 것은 3자리 숫자중 끝자리가 2인 것의 수다.
//N = 1 인 수들은 1, 2, 3, 4, ... ,9 이렇게 9 개다.
//N = 2 인 수는 10,12,21,23,32,34, ... , 89,98
//N >= 2 부터 끝자리가 0인 수는 N-1자리인 수들에서 끝자리가 1인 수로부터 파생된다.
//끝자리가 1인 수는 N-1자리인 수들에서 끝자리가 0인 수와 2인 수에서부터 파생된다.
//끝자리가 2인 수는 N-1자리인 수들에서 끝자리가 1인 수와 3인 수에서부터 파생된다.
//...
//끝자리가 8인 수도 동일하다.
//끝자리 9인 수는 N-1자리인 수들에서 끝자리가 8인 수로부터 파생된다.
fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val dp = Array(n+1){LongArray(10){0L} }
    // row: 자릿수, col: 끝자리수
    for (i in 1 until 10){
        dp[1][i] = 1L
    }

    for(i in 2 until n+1){
        for (j in 0 until 10){
            when(j){
                0 -> dp[i][j] = (dp[i-1][j+1]).mod(1000000000L)
                9 -> dp[i][j] = (dp[i-1][j-1]).mod(1000000000L)
                else -> dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]).mod(1000000000L)
            }
        }
    }
    var sum = 0L
    for (i in 0 until 10){
        sum+=dp.last()[i].mod(1000000000L)
    }
    println(sum.mod(1000000000L))

}