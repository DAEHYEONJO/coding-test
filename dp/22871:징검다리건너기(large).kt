package dp

import java.io.*
import kotlin.math.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val a = readLine().trim().split(" ").map { it.toLong() }.toLongArray()
    val dp = LongArray(n) { Long.MAX_VALUE }
    dp[0] = 0L
    //N<=5,000
    //ai<=1,000,000
    //dp[j] 는 j 번째까지 오는 파워중 최솟값을 의미한다
    //dp[j] 의 최솟값 갱신을 위해서 i (0~j-1) 까지 돌을 건너면서 dp[i]와 i->j까지 건너는 파워 중 더 큰 값을 K의 최댓값으로 갱신해준다.
    //dp[j]는 갱신된 K 의 최댓값과 dp[j] 중에서 작은걸로 갱신해준다.
    //이 문제는 결국 i->j 로 가면서 i까지 오는데 걸린 최솟값과 i->j 로 가는데 걸리는 비용 두개 중 최댓값을 찾는다. 왜냐하면 돌을 건널때마다 쓸 수 있는 힘은 최대 K 이기 때문에.
    //i->j로 가는데 발생하는 최댓값들중의 최솟값을 찾는 문제다.

    for (j in 1 until n) {
        for (i in 0 until j) {
            val power = ((j - i) * (1 + abs(a[i] - a[j]))).coerceAtLeast(dp[i])
            dp[j] = dp[j].coerceAtMost(power)
        }
    }
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        w.write("${dp.last()}")
        w.flush()
    }
}