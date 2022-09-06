package codeforces

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val prefixSum = LongArray(n + 1) { 0L }
    val st = StringTokenizer(readLine())
    repeat(n) { i ->
        prefixSum[i + 1] = prefixSum[i] + st.nextToken().toLong()
    }
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        var ans = 0L
        var count1 = 0L
        if ((prefixSum.last()) % 3L == 0L) {
            val sum1of3 = prefixSum[n]/3
            for( i in 1 until n){
                if (prefixSum[i] == sum1of3*2){
                    ans+=count1
                }
                if (prefixSum[i] == sum1of3){
                    count1+=1L
                }
            }
        }

        w.write("$ans\n")
        w.flush()
    }
}