package two_pointer

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        val (n, x) = readLine().trim().split(" ").map { it.toInt() }
        val prefixSum = IntArray(n + 1) { 0 }
        val st = StringTokenizer(readLine())

        repeat(n) { i ->
            prefixSum[i + 1] = prefixSum[i] + st.nextToken().toInt()
        }

        var start = 0
        var end = x
        var maxValue = 0
        var maxValueCount = 1
        while (end < n + 1) {
            val diff = prefixSum[end] - prefixSum[start]
            if (diff > maxValue) {
                maxValue = diff
                maxValueCount = 1
            } else if (diff == maxValue) {
                maxValue = diff
                maxValueCount += 1
            }
            start += 1
            end += 1
        }
        if (maxValue == 0) {
            w.write("SAD")
        } else {
            w.write("$maxValue\n$maxValueCount")
        }
        w.flush()
    }
}