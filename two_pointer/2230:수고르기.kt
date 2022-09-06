package two_pointer

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val (n, m) = readLine().trim().split(" ").map{ it.toInt() }
    val a = LongArray(n) {
        readLine().trim().toLong()
    }.sortedArray()
    var s = 0
    var e = 0
    var ans = Long.MAX_VALUE
    while(e in s until n){
        when{
            a[e]-a[s]<m -> e++
            a[e]-a[s]>=m -> {
                ans = ans.coerceAtMost(a[e]-a[s])
                s++
            }
        }
    }
    println(ans)
}