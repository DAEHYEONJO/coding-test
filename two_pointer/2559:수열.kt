package two_pointer

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val(n, k) = readLine().trim().split(" ").map{ it.toInt() }
    val st = StringTokenizer(readLine())
    val prefixSum = IntArray(n+1){0}
    repeat(n){ i ->
        prefixSum[i+1] = prefixSum[i] + st.nextToken().toInt()
    }

    var ans = Int.MIN_VALUE
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->

        for (i in 0 .. n-k){
            ans = ans.coerceAtLeast(prefixSum[i+k]-prefixSum[i])
        }
        w.write("${ans}\n")
        w.flush()
    }
}