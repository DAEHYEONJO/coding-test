package dp

import java.io.*

//정사각형 조건을 만족하기 위해서는 (y, x) 기준 (y-1,x), (y, x-1), (y-1, x-1) 모두 1 로 차있어야 한다.
//따라서 dp[y][x]는 (y, x) 를 가장 오른쪽 아래 꼭짓점으로 하는 정사각형이 갖는 대 한변의 길이다.
//점화식은 dp[y][x] = min(dp[y-1][x], dp[y][x-1], dp[y-1][x-1]) + 1 이다.

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        val (n, m) = readLine().trim().split(" ").map { it.toInt() }
        val dp = Array(n+1) { IntArray(m+1) }
        repeat(n) { row ->
            var col = 1
            readLine().trim().forEach { c ->
                dp[row+1][col++] = c.digitToInt()
            }
        }
        dp.forEach{
            w.write("${it.contentToString()}\n")
        }
        w.newLine()
        var ans = 0
        for(i in 1 until n+1){
            for(j in 1 until m+1){
                if (dp[i][j] == 0) continue
                val minValue = (dp[i-1][j].coerceAtMost(dp[i][j-1])).coerceAtMost(dp[i-1][j-1])
                dp[i][j] = minValue + 1
                ans = ans.coerceAtLeast(dp[i][j])
            }
            w.write("${dp[i].contentToString()}\n")
        }
        w.write("${ans*ans}")
        w.flush()
    }
}