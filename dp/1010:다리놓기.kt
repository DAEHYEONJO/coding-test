package dp

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        val t = readLine().toInt()
        val dp = Array(31){LongArray(31){1L} }
        for (i in 1 until 31){
            dp[i][1] = i.toLong()
        }

        for (i in 3 until 31){
            for (j in 2 until i){
                dp[i][j] = dp[i-1][j-1]+dp[i-1][j]
            }
        }

        repeat(t){
            val st = StringTokenizer(readLine())
            val n = st.nextToken().toInt()
            val m = st.nextToken().toInt()
            w.write("${dp[m][n]}\n")
        }
        w.flush()
        this.close()
    }
}
