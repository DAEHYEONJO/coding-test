package two_pointer

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val (n, k) = readLine().trim().split(" ").map{ it.toInt() }
    val gx = Array<Pair<Int, Int>>(n){
        val st = StringTokenizer(readLine())
        Pair(st.nextToken().toInt(), st.nextToken().toInt())
    }.sortedBy { it.second }

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        var s = 0
        var e = 0
        var ans = 0
        var sum = 0
        while(e in s until n){
            val diff = gx[e].second - gx[s].second
            if (diff <= (k shl 1)){
                sum+=gx[e++].first
            }else{
                sum-=gx[s++].first
            }
            ans = ans.coerceAtLeast(sum)
        }
        w.write("$ans")
        w.flush()
    }

}