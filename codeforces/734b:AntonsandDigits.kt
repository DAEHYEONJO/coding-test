package codeforces

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val (k2, k3, k5, k6) = readLine().trim().split(" ").map{ it.toInt() }

    // 256을 최대한 많이 만들어야 한다.
    val count256 = (k2.coerceAtMost(k5)).coerceAtMost(k6)
    val diffK2Count = k2 - count256
    var count32 = 0
    if (diffK2Count>0){
        count32 = k3.coerceAtMost(diffK2Count)
    }


    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        w.write("${256*count256 + 32*count32}")
        w.flush()
    }
}