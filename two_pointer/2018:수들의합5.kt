package two_pointer

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().trim().toInt()
    var ans = 1L
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        var s = 1
        var e = 1
        var sum = 0
        while (e in s..n) {
            when {
                sum < n -> sum += (e++)
                sum > n -> sum -= (s++)
                sum == n -> {
                    ans+=1L
                    sum+=(e++)
                }
            }
        }
        w.write("$ans")
        w.flush()
    }
}