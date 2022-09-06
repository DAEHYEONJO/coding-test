package codeforces

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    var (n, k) = readLine().trim().split(" ").map{ it.toInt() }
    val prefixSum = DoubleArray(n+1){0.0}
    val st = StringTokenizer(readLine())
    repeat(n){ i ->
        prefixSum[i+1] += prefixSum[i] + st.nextToken().toDouble()
    }
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        var max = 0.0
        while(k<=n){
            var end = k
            var start = 0
            var sum = 0.0
            while(end<n+1){
                sum = sum.coerceAtLeast(prefixSum[end++]-prefixSum[start++])
            }
            max = max.coerceAtLeast(sum.div(k))
            k++
        }
        w.write("${max}\n")
        w.flush()

    }
}