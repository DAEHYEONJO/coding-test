package two_pointer

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, k) = readLine().trim().split(" ").map { it.toInt() }
    val st = StringTokenizer(readLine())
    val prefixCount = IntArray(n + 1) { 0 }
    repeat(n) { i ->
        if (st.nextToken().toInt() == 1) {
            prefixCount[i + 1] += 1
        }
        prefixCount[i + 1] += prefixCount[i]
    }

    var ans = Int.MAX_VALUE
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        var s = 0
        var e = 1

        while (s < e && e < n + 1) {
            val count = prefixCount[e] - prefixCount[s]
            when {
                count < k -> {
                    e++
                }
                count == k -> {
                    ans = ans.coerceAtMost(e-s)
                    s++
                }
            }
        }
        w.write("${if (ans==Int.MAX_VALUE) -1 else ans}")
        w.flush()
    }
}