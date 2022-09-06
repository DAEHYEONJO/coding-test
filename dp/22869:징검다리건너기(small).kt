package dp

import java.io.*
import kotlin.math.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    // 항상 오른쪽으로만 이동가능하다.
    // i번째 돌에서 j(i < j)번째 돌로 이동할 때 (j - i) × (1 + |A_{i} - A_{j}|) 만큼 힘을 쓴다.
    // 돌을 한번 건너갈 때마다 쓸 수 있는 힘은 최대 $K$이다.
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        val(n, k) = readLine().trim().split(" ").map{ it.toInt() }
        val a = readLine().trim().split(" ").map{ it.toInt() }.toIntArray()
        val dp = IntArray(n){5000001}
        dp[0] = 0
        //power 의 최댓값은 (5000-0)*(1+999) = 5000*1000 => int로 충분함
        for(i in 0 until n-1){
            if (dp[i] <= k){ // 도달할 수 있는 징검다리에 대해서 i번 다리 다음 다리들을 최솟값으로 갱신해주기
                for (j in i+1 until n){
                    val power = (j-i)*(1+abs(a[i] - a[j]))
                    dp[j] = dp[j].coerceAtMost(power)
                }
            }
        }
        w.write(if (dp[n-1]<=k) "YES" else "NO" )
    }




}