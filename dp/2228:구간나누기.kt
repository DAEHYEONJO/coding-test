package dp

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, m) = readLine().trim().split(" ").map { it.toInt() }
    val sum = IntArray(n + 1) { 0 }
    val dp = Array(m + 1) { IntArray(n + 1) { -3276900 } }
    //dp[m][n] => 첫번째 ~ n 번째 숫자 집합에서 겹치지 않고, 인접하지 않은 구간 m 개의 숫자들의 합의 최댓값
    for (i in 1 until n + 1) {
        sum[i] = sum[i - 1] + readLine().toInt()
    } // 구간합을 구하기 위하여 sumdp 를 셋팅한다 .

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        //1개의 구간으로 나누는 것은 n 개 숫자 집합에서 연속된 임의의 구간을 의미한다.
        for(i in 1 until n+1){
            var maxValue = dp[1][i-1]
            for(k in i-1 downTo 0){
                maxValue = maxValue.coerceAtLeast(sum[i]-sum[k])
            }
            dp[1][i] = maxValue
        }

        //row 는 구간의 수를 의미한다. 만약 x개의 숫자 집합을 2구간으로 나누려면 최소 3개의 숫자 집합이 필요하다
        //3 구간 -> 최소 5개(3 + 2) 숫자 필요
        //4 구간 -> 최소 7개(4 + 3) 숫자 필요
        //5 구간 -> 최소 9개(5 + 4) 숫자 필요
        for(row in 2 until m+1){
            for(col in row+row-1 until n+1){
                var maxValue = dp[row][col-1] // row 개의 구간으로 col 개의 숫자집합을 나누는데 col 번째 숫자가 row 번째 구간에 포함되지 않은경우 dp[row][col]의 최댓값
                for(k in col-2 downTo 0){//row 개의 구간으로 col 개의 숫자집합을 나누는데 col 번째 숫자가 row 번째 구간에 포함되는 경우 row 번째 구간의 시작 숫자를 바꿔가면서 최댓값을 구해준다.
                    maxValue = maxValue.coerceAtLeast(dp[row-1][k] + sum[col] - sum[k+1])
                }
                dp[row][col] = maxValue
            }
        }
        w.write("${dp.last().last()}")
        w.flush()
    }
}