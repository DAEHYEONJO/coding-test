package dp

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    // 2개 돌 건너는 점프(매우 큰 점프): 1번의 기회 -> K 만큼의 에너지가 소비됨
    // 큰 점프: 1개 돌 건너뜀 -> jumps[i][1] 만큼의 에너지가 소비됨
    // 작은 점프: 다음돌로 건너뜀 -> jumps[i][0] 만큼의 에너지가 소비됨
    // 산삼을 얻기 위한 에너지의 최솟값을 구하기
    // 첫번째 돌에서 시작한다.
    val n = readLine().toInt()
    val jumps = Array(n-1) {  readLine().trim().split(" ").map { it.toInt() }.toIntArray() }
    val k = readLine().toInt()
    var ans = Int.MAX_VALUE

    // N = 20 이므로 완전탐색이 가능하다.
    /*fun dfs(rock: Int, energy: Int, isSupered: Boolean){
        if (rock>n) return
        else if (rock == n){
            ans = ans.coerceAtMost(energy)
            return
        }
        dfs(rock+1, energy+jumps[rock][0],isSupered)
        dfs(rock+2, energy+jumps[rock][1],isSupered)
        if (!isSupered){
            dfs(rock+3, energy+k,true)
        }
        return
    }
    dfs(1, 0, false)*/

    //dp 방법은 다음과 같다.
    //dp를 2차원 배열로 만들어줄 수 있다
    //dp[i][0]는 매우 큰 점프를 한번도 하지 않은 경우다.
    //dp[i][1]은 매우 큰 점프를 했을때 비용을 고려한 것이다.
    //dp[i][1]세팅 방법은 현재 돌에서 매우 큰 점프를 했을때 i+3번째의 비용과, 이전에 매우 큰 점프를 했을때 비용중 작은 값으로 세팅해주면 된다.
    val dp = Array(n + 3) { IntArray(2){100001} }
    dp[1][0] = 0
    dp[1][1] = 0
    for (i in 1 until n) {
        for (j in 0 until 2){
            dp[i + 1][j] = dp[i + 1][j].coerceAtMost(dp[i][j] + jumps[i-1][0])
            dp[i + 2][j] = dp[i + 2][j].coerceAtMost(dp[i][j] + jumps[i-1][1])
        }
        dp[i+3][1] = dp[i+3][1].coerceAtMost(dp[i][0] + k)
    }
    println(dp[n].minOrNull()!!)

}