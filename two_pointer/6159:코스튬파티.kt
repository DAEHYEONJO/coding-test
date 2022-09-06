package two_pointer

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val (N, S) = readLine().trim().split(" ").map{ it.toInt() }
    val l = LongArray(N){readLine().trim().toLong()}.sortedArray()

    var ans = 0L
    for(i in 0 until N){
        if (l[i] shl 1 > S) break
        var e = N-1
        while (l[i] + l[e] > S) e--
        ans+=(e-i).toLong()
    }
    println(ans)
}