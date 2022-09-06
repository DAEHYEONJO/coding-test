package codeforces

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        var t = readLine().toInt()
        out@ while (t-- > 0) {
            val n = readLine().toInt()
            val st = StringTokenizer(readLine())
            val p = LongArray(n + 1) { 0L }
            repeat(n) { i ->
                p[i + 1] = p[i] + st.nextToken().toLong()
            }
            //w.write("${p.contentToString()}\n")
            val yasser = p.last()
            var min = Long.MAX_VALUE
            //문제가 되는 상황 -> adel 은 [1,n] 을 할 수 없음.
            //i == n 일때 min 값이 p[0] 일 때 문제가 생김.
            for (i in 1 until n) {
                min = min.coerceAtMost(p[i - 1])
                if (p[i] - min >= yasser) {
                    w.write("NO\n")
                    continue@out
                }
            }
            min = p[1]
            for(i in 2 until n+1){
                min = min.coerceAtMost(p[i - 1])
                if (p[i] - min >= yasser) {
                    w.write("NO\n")
                    continue@out
                }
            }
            w.write("YES\n")
        }

        w.flush()
    }
}