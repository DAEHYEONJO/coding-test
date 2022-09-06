package greedy

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val q = PriorityQueue<Int>()
    var ans = 0
    repeat(n){
        q.add(readLine().toInt())
    }
    while (q.size!=1){
        val a = q.poll()
        val b = q.poll()
        ans+=a+b
        q.add(a+b)
    }
    println(ans)
}