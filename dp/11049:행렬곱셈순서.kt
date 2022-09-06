package dp

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val m = Array(n) {
        val st = StringTokenizer(readLine())
        Pair(st.nextToken().toInt(), st.nextToken().toInt())
    }
    //dp[row][col] 은 입력으로 주어진 행렬의 순서에서 row번째 행렬에서부터 col번째 행렬까지의 곱의 최솟값을 갖고 있다.
    val dp = Array(n) { IntArray(n) { Int.MAX_VALUE } }

    BufferedWriter(OutputStreamWriter(System.`out`)).use { bw ->

        for( i in 0 until n ){
            dp[i][i] = 0 // i번째 행렬 ~ i번째 행렬 까지의 곱은 0이다.
        }

        //dp[i][j] = dp[i][j].coerceAtMost(dp[i][k] + dp[k+1][j] + m[i].first * m[k+1].first * m[j].second)
        // (i + 1) <= k <= (j - 1)
        // n -> 5 인 경우 계산 순서
        // 0 1 5 8 10
        // x 0 2 6 9
        // x x 0 3 7
        // x x x 0 4
        // x x x x 0
        // 10 번째 칸은 0번째 행렬 ~ n-1 번째 행렬의 곱의 최소다. 즉, 우리가 구하고자 하는 답이다.
        // 10 번째 칸을 구하기 위해서는 8번째 칸의 값과 9번째 칸의 값 포함 왼쪽 아래의 값을 알고 있어야 한다.
        // 8, 9 번째 칸을 구하기 위해서는 5, 6, 7 번째 칸의 값 포함 왼쪽 아래의 값을 알고 있어야 한다.
        //
        for(d in 0 until n){
            for(i in 0 until n-d){
                val j = i + d
                for(k in i until j){
                    dp[i][j] = dp[i][j].coerceAtMost(dp[i][k] + dp[k+1][j] + m[i].first * m[k+1].first * m[j].second)
                    // (i번째 ~ k 번째 행렬 곱의 최소) + (k+1 번째 행렬 ~ j 번째 행렬 곱의 최소) + (row_i * row_k+1 * col_j)
                    // k 는 어느때가 최소일지 모른다. 따라서 모든 경우를 다 구해보는 것이다.
                    // 행렬의 곱의 최소를 구할 때 M1 * M2 * M3 * M4 를 예시로 들자면 곱하기 3개중 하나를 기준으로 왼쪽, 오른쪽 행렬을 다 계산하고 선택한 곱하기를 마지막에 처리한다는 뜻이다.
                }
            }
        }

        bw.write("${dp[0][n-1]}")
        bw.flush()
    }
}