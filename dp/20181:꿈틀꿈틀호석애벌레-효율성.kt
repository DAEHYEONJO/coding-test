package dp

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, k) = readLine().trim().split(" ").map { it.toInt() }
    val p = LongArray(n + 1) { 0L }
    readLine().trim().split(" ").map { it.toLong() }.forEachIndexed { index, l ->
        p[index + 1] = p[index] + l
    }

    val dp = LongArray(n + 1) { 0L }
    var s = 0
    var e = 1
    var maxBeforeS = 0L
    var ans = 0L
    while (s <= e && e < n + 1) {
        val diff = p[e] - p[s]
        if (diff < k) e++
        else {
            dp[e] = dp[e].coerceAtLeast(maxBeforeS + (diff - k))
            ans = ans.coerceAtLeast(dp[e])
            s++
            maxBeforeS = maxBeforeS.coerceAtLeast(dp[s])
        }
    }
    println(ans)

}