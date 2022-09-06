package greedy

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        val n = readLine().toInt()
        val gss = readLine().trim().split(" ").map { it.toLong() }.toLongArray().sortedArray()
        // gss 에서 한개 혹은 두개를 골랐을때 각 쌍의 합이 최소가 되도록 하고 싶다.

        val lastIndex = if (n.mod(2)==0) n-1 else n-2
        var result = -1L
        for (i in 0 until n/2){
            result = result.coerceAtLeast(gss[i]+gss[lastIndex-i])
        }
        result = result.coerceAtLeast(gss.last())
        w.write("$result")
        w.flush()
    }
}