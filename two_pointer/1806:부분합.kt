package two_pointer

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    var(n, s) = readLine().trim().split(" ").map{ it.toInt() }
    val dp = IntArray(n+1){0}
    val st = StringTokenizer(readLine())
    repeat(n){ i ->
        dp[i+1] = dp[i] + st.nextToken().toInt()
    }

    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        var start = 0
        var end = 1
        var ans = 100001
        while(end<n+1 && start < end){
            val diff = dp[end] - dp[start]
            diff.run {
                when{
                    this < s -> end+=1
                    this >= s -> {
                        ans = ans.coerceAtMost(end-start)
                        start +=1
                    }
                }
            }


        }
        w.write(if (ans==100001) "0" else ans.toString())
        w.flush()
    }
}