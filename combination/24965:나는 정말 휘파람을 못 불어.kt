package combination

import java.io.*

fun Char.isCharFind(c: Char): Int = if (this == c) 1 else 0

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val modNum = 1000000007L
    val n = readLine().toInt()
    val str = readLine().trim().toCharArray()
    var answer = 0L

    val wCountPrefixSum = IntArray(n + 1)
    val eCountPrefixSum = IntArray(n + 1)
    val powArray = LongArray(n + 1)
    powArray[0] = 1

    for (i in 1 until n + 1) {
        wCountPrefixSum[i] = wCountPrefixSum[i - 1] + str[i - 1].isCharFind('W')
        eCountPrefixSum[i] = eCountPrefixSum[i - 1] + str[i - 1].isCharFind('E')
        powArray[i] = (powArray[i - 1] * 2) % modNum
    }

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        for (i in 1 until n + 1) {
            if (str[i - 1] == 'H') {
                val wCount = wCountPrefixSum[i]
                val eCount = eCountPrefixSum[n] - eCountPrefixSum[i - 1]
                val curCount: Long = wCount.toLong() * (powArray[eCount] - 1L - eCount.toLong())
                answer += curCount
                answer %= modNum
            }
        }

        w.write("${answer % modNum}")
        w.flush()
    }
    close()
}