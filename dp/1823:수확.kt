package dp

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val v = IntArray(1) { 0 } + IntArray(n) { readLine().toInt() }
    // dp[y][x] 는 y~x 의 벼가 남았을 때, 수확의 최댓값이다.
    val dp = Array(n + 1) { IntArray(n + 1) { 0 } }
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->

        // bottom -> up 방식이다.
        // 1~1 만 남았을 때, 수확의 최댓값은 첫번째를 젤 마지막에 수확하는 것이다 => v[1]*n
        // 2~2 만 남았을 때, 수확의 최댓값은 두번째를 젤 마지막에 수확하는 것이다 => v[2]*n
        // 1~2 만 남았을 때, 수확의 최댓값은 max(dp[1][1] + v[2]*(n-1), dp[2][2] + v[1]*(n-1)) = max( v[1]*n + v[2]*(n-1), v[2]*n + v[1]*(n-1) )
        // 3~3 만 남았을 때, 수확의 최댓값은 v[3]*nㅈ
        // 2~3 만 남았을 때, 수확의 최댓값 = max( dp[2][2] + v[3]*(n-1) = v[2]*n + v[3]*(n-1),
        //                          =       dp[3][3] + v[2]*(n-1) = v[3]*n + v[2]*(n-1) )
        // 1~3 만 남았을 때, 수확의 최댓값 = max( dp[1][2] + v[3]*(n-2) =  max( v[1]*n + v[2]*(n-1), v[2]*n + v[1]*(n-1) ) + v[3]*(n-2),
        //                                  = max(v[1]*n + v[2]*(n-1) + v[3]*(n-2), v[2]*n + v[1]*(n-1) + v[3]*(n-2)),
        //                                  dp[2][3] + v[1]*(n-2) )
        // 이런식으로 쭉 가서 dp[1][n] => 1~n의 벼가 남았을 때, 수확의 최댓값을 출력하면 된다.
        // 이전에 사용했던 결과값을 계속 재사용하는 memoization 기법이다.
        // dp[y][x] = max ( dp[y+1][x] + v[y] * k , dp[y][x-1] + v[x]*k )
        for (x in 1 until n + 1) {
            dp[x][x] = v[x] * n
            var k = n - 1
            for (y in x - 1 downTo 1) {
                dp[y][x] = (dp[y + 1][x] + v[y] * k).coerceAtLeast(dp[y][x - 1] + v[x] * k)
                k--
            }
        }

        w.write("${dp[1][n]}")
        w.flush()
    }
}