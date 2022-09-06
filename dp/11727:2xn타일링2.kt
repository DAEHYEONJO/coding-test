package dp

import java.io.*

// n == 1 일땐 1가지
// n == 2 일땐 3가지
// n>=3 일 때, 2*n 타일링시 오른쪽에 ㅣ or || or = 가 올 수 있다.
// 오른쪽에 | 가 오는경우 2*(n-1) 타일링에 해당함.
// 오른쪽에 || or = 가 오는 경우 2*(n-2) 타일링에 해당함.
// 따라서 점화식은 f[n] = f[n-1] + 2*f[n-2] 에 해당한다.
fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val dp = IntArray(n+1){0}
    dp[1] = 1
    if (n>=2){
        dp[2] = 3
        for(i in 3 until n+1){
            dp[i] = (dp[i-1]+2*dp[i-2]).mod(10007)
        }
    }
    println(dp.last())
}