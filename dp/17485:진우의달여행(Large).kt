package dp

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->

        val (n, m) = readLine().trim().split(" ").map { it.toInt() }
        val arr = Array(n) { IntArray(m + 2) { 0 } }
        val dp = Array(3){ Array(n) { IntArray(m + 2) { 1000001 } } }
        val firstLine = StringTokenizer(readLine())

        repeat(m){ i ->
            val value = firstLine.nextToken().toInt()
            arr[0][i+1] = value
            repeat(3){ j ->
                dp[j][0][i+1] = value
            }
        }

        repeat(n-1){ row ->
            val st = StringTokenizer(readLine())
            repeat(m){ col ->
                arr[row+1][col+1] = st.nextToken().toInt()
            }
        }

        for(i in 1 until n){
            for(j in 1 until m+1){
                //왼쪽 위에서 (i,j)로 왔을 때 최솟값은 (i-1,j-1)로 (i-2,j-1)[윗방향] or (i-2, j)[오른쪽 위 방향] 에서 왔을 때 값에 (i,j)의 arr 값을 더한것에 해당한다
                dp[0][i][j] = dp[1][i-1][j-1].coerceAtMost(dp[2][i-1][j-1]) + arr[i][j]
                //위에서 (i,j)로 왔을때 최솟값
                dp[1][i][j] = dp[0][i-1][j].coerceAtMost(dp[2][i-1][j]) + arr[i][j]
                //오른쪽 위에서 (i,j) 로 왔을때 최솟값
                dp[2][i][j] = dp[0][i-1][j+1].coerceAtMost(dp[1][i-1][j+1])+arr[i][j]
            }
        }

        var ans = 1000001
        for(i in 0 until 3){
            for(j in 1 until m+1){
                ans = ans.coerceAtMost(dp[i][n-1][j])
            }
        }
        w.write("$ans")
        w.flush()

    }
}