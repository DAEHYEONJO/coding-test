package dp

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, k) = readLine().trim().split(" ").map { it.toInt() }
    val wv = Array(n) {
        val st = StringTokenizer(readLine())
        Pair(st.nextToken().toInt(), st.nextToken().toInt())
    }.sortedWith(compareBy { it.first })//무게 작은순으로 sorting

    // dp[row][col] 의 의미는 다음과 같다 -> 최대 무게가 row 일때, col 번째 짐을 넣거나 넣지 않았을때의 최대 가치이다.
    val dp = Array(k + 1) { IntArray(n + 1) { 0 } }
    // dp 의 row 는 최대 무게 이다.
    // dp 의 col 은 (w, v) 순서이다
    // 즉, 입력으로 들어온 (w, v) 쌍을 무게가 작은것부터 sorting 하고 row (최대무게) 를 증가시키면서 col 번째 짐을 넣고, 넣지않고, 빼고 를 고려한다.
    for(i in wv.first().first until k+1){
        dp[i][1] = wv.first().second
    }
    // 첫번째 짐은 젤 가벼운 짐이다. 따라서 무게가 첫번째 짐 이상일땐, 모두 최대 v 만큼의 가치를 얻을 수 있다.

    BufferedWriter(OutputStreamWriter(System.`out`)).use { bw->
        for (col in 2 until n+1){
            val (w, v) = wv[col-1]
            for (row in wv.first().first until k+1){
                if (row-w>=0){
                    dp[row][col] = dp[row][col-1].coerceAtLeast(dp[row-w][col-1]+v)
                    // dp[row][col-1] 은 최대 무게 row 일 때, col 번째 짐을 넣지 않은 값이다.
                    // dp[row-w][col-1] 은 col 번째 짐을 넣기 위해 row 에서 현재 짐을 뺐을때, 이전 짐까지 넣는것을 고려한 최댓값이다.
                    // 따라서 dp[row-w][col-1] 은 현재 짐을 넣을 수 있는 상태이기에 현재 짐의 가치 v를 더할 수 있다.
                    // 둘중에 큰 값이 dp[row][col]의 값이다.
                }else{
                    // 현재 짐이 들어갈 수 없는 무게이므로, 현재 row 무게에 대하여 이전번째 짐을 고려했을 때 무게를 가져온다.
                    dp[row][col] = dp[row][col-1]

                }
            }
        }
        bw.write("${dp.last().last()}") // 결국 최대 k 무게에서 모든 짐을 넣거나 몇개의 짐을 골라 넣었을 때 최대 가치에 해당한다.
        bw.flush()
    }
}