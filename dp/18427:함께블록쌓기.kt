package dp

import java.io.*
import java.util.*

// 학생 n 명
// 학생마다 최대 m 개 블록 있고 한명의 학생이 갖고 있는 모든 블록의 높이는 다름
// 각 학생들이 가진 블록을 차례대로 사용하여 바닥에서부터 쌓아올려 탑을 만들것임
// 어떤 학생의 블록은 사용안해도됨
// 학생당 최대 1개 블록 사용 가능
// 높이가 h 인 탑을 만드는 경우의 수 구하기

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val (n, m, h) = readLine().trim().split(" ").map { it.toInt() }
    val blocks = Array(n) { LinkedList<Int>() }
    repeat(n) { student ->
        val st = StringTokenizer(readLine())
        while (st.hasMoreTokens()) {
            blocks[student].add(st.nextToken().toInt())
        }
    }

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        val dp = Array(n + 1) { IntArray(h + 1) { 0 } }
        dp[0][0] = 1
        for (i in 1 until n + 1) {
            for(j in 0 until h+1){
                for(block in blocks[i-1]){
                    val beforeBlock = j-block
                    if (beforeBlock < 0) continue
                    dp[i][j] += dp[i-1][beforeBlock]
                    dp[i][j] %= 10007
                }
                dp[i][j] += dp[i-1][j]
                dp[i][j] %= 10007
            }
            //w.write("${dp[i].contentToString()}\n")
        }
        w.write("${dp[n][h]}")
        w.flush()
    }

}