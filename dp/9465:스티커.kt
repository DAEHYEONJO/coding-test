package dp

import java.io.*

// n == 1
// a[0][0] or a[1][0] 중 큰것이 답이다.
// n == 2
// a[0][0]를 선택한 경우 -> a[1][1] 을 선택해야 한다
// a[1][0]를 선택한 경우 -> a[0][1] 을 선택해야 한다
// 두가지를 더한것중 큰것이 답이다
// n == 3
// a[0][0]->a[1][1]->a[0][2]
// a[0][0]->a[0][2]
// a[1][0]->a[0][2]
// a[1][0]->a[0][1]->a[1][2]
// a[1][0]->a[1][2]
// a[0][0]->a[1][2]
// 위 네가지중 큰 것이 답이다.
// 즉, 점화식은, n>=3부터 다음과 같다.
// dp[0][i] = max(dp[0][i-2], dp[1][i-1], dp[1][i-2])+a[0][i]
// dp[1][i] = max(dp[1][i-2], dp[0][i-1], dp[0][i-2])+a[1][i]
// 답은 max(dp[0][i], dp[1][i])다.
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        val t = readLine().toInt()
        repeat(t) {
            val n = readLine().toInt()
            val a = Array(2) { readLine().trim().split(" ").map { it.toInt() }.toIntArray() }
            if (n >= 2) {
                a[0][1] += a[1][0]
                a[1][1] += a[0][0]
                for (i in 2 until n) {
                    val max1 = a[0][i - 2].coerceAtLeast(a[1][i - 2])
                    a[0][i] += max1.coerceAtLeast(a[1][i - 1])
                    a[1][i] += max1.coerceAtLeast(a[0][i - 1])
                }
            }
            w.write("${a[0].last().coerceAtLeast(a[1].last())}\n")

        }
        w.flush()
    }
}