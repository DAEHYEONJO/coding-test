package dp

import java.io.*
// dp[k] 는 k 번째 계단에서의 최댓값을 의미한다.
// dp[0] = s[0]
// dp[1] = s[0]+s[1]
// dp[2] = max(s[0],s[1]) + s[2] -> s[2]는 무조건 밟아야 하는데 3개 연속은 못밟으므로 s[0] 과 s[1] 중 큰 값을 더해준다
// dp[3] = max(dp[0] + s[2], dp[1]) + s[3]
// dp[3]의 경우 o x o o 또는 o o x o 중 큰값을 의미한다.
// dp[4] = max(dp[1] + s[3], dp[2]) + s[4]
// dp[4]의 경우 o o x o o 또는 o x o x o 또는 x o o x o 중 큰 값을 의미한다.
// 즉 k >=3 이후의 점화식은 dp[k] = max(dp[k-3] + s[k-1], dp[k-2]) + s[k] 이다.
fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val s = IntArray(n){readLine().toInt()}
    var ans = 0
    when{
        n<=2->{ans = s.sum()}
        n==3->{ans = s[0].coerceAtMost(s[1]) + s.last()}
        else->{
            val dp = IntArray(n){0}
            dp[0] = s[0]
            dp[1] = s[0]+s[1]
            dp[2] = s[0].coerceAtLeast(s[1])+s[2]
            for (i in 3 until n){
                dp[i] = (dp[i-3]+s[i-1]).coerceAtLeast(dp[i-2]) + s[i]
            }
            ans = dp.last()
        }
    }
    println(ans)
}