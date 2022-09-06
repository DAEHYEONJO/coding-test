package dp

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val s = readLine().trim()
    val m = readLine().toInt()
    val ax = HashMap<String, Int>()
    val dp = IntArray(s.length + 1) { 0 }

    repeat(m) { i ->
        val st = StringTokenizer(readLine())
        ax[st.nextToken()] = st.nextToken().toInt()
    }

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->

        var index = s.length - 1
        while (index >= 0) {
            val curChar = s[index]
            var maxValue = 1
            ax.entries.forEach { (key, value) ->
                val keyZeroChar = key[0]
                if (curChar == keyZeroChar) {
                    val keyLength = key.length
                    if (index + keyLength <= s.length) {
                        val subString = s.substring(index, index + keyLength)
                        if (subString == key) {
                            maxValue = maxValue.coerceAtLeast(value + dp[index + keyLength])
                        }
                    }

                }
            }
            dp[index] = (dp[index + 1] + 1).coerceAtLeast(maxValue)
            index -= 1
        }
        w.write("${dp[0]}")
        w.flush()
    }

}