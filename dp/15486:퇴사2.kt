package dp

import java.io.*

// dp[i] 는 i 번째 날 얻을 수 있는 최대 이익이다.
// preMax 는 i 번째날 포함된 이전 날에 종료되는 일들중 얻을 수 있는 최대 이익이다.
// 즉, i 번째날 얻을 수 있는 최대 이익과, i번째날 포함된 이전 날에 종료되는 일들중 얻을 수 있는 최대 이익에
// i 번째날 일을 했을때 비용을 더한것중 최댓값을 갱신해주면 정답이 된다.

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val arr = Array(n){readLine().trim().split(" ").map{it.toInt()}.toIntArray()}
    val dp = IntArray(n+1){0}
    var ans = 0
    var preMax = 0
    arr.forEachIndexed { index, v ->
        val ti = v[0]
        val pi = v[1]
        preMax = preMax.coerceAtLeast(dp[index])
        val end = ti+index
        if (end<=n){
            dp[end] = dp[end].coerceAtLeast(pi+preMax)
            ans = ans.coerceAtLeast(dp[end])
        }
    }
    println(dp.contentToString())
    println(ans)
}