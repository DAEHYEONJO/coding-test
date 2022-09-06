package two_pointer

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val prefixSum = IntArray(n + 1) { 0 }
    repeat(n) {
        prefixSum[it + 1] = prefixSum[it] + readLine().toInt()
    }

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        //w.write("${prefixSum.contentToString()}\n")
        var ans = 0
        var s = 0
        var e = 1
        while(s<e && e<n+1){
            val sigae = prefixSum[e]-prefixSum[s]
            val bansigae = prefixSum[n] - sigae
            if (sigae>=bansigae){
                s++
            }else{
                e++
            }
            ans = ans.coerceAtLeast(sigae.coerceAtMost(bansigae))
        }
        w.write("$ans")
        w.flush()
    }
}