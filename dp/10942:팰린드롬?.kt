package dp

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val a = readLine().trim().split(" ").map { it.toInt() }.toIntArray()
    val m = readLine().toInt()
    val dp = Array(n) { BooleanArray(n) { true } }


    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        for(i in n-2 downTo 0){
            for(j in n-1 downTo i+1){
                dp[i][j] = dp[i+1][j-1] && (a[i]==a[j])
            }
        }
        repeat(m) {
            var (n1, n2) = readLine().trim().split(" ").map { it.toInt() }
            if (n1 > n2) n1 = n2.also { n2 = n1 }
            w.write("${if (dp[n1 - 1][n2 - 1]) "1" else "0"}\n")
        }
        w.flush()
    }
}