package greedy

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val times = Array(n){readLine().trim().split(" ").map{it.toInt()}.toIntArray()}.sortedBy { it[0] }
    val pq = PriorityQueue<Int>().apply { add(times[0][1]) }
    var answer = 1
    var index = 1

    while (pq.isNotEmpty() && index < n){
        val minEndTime = pq.peek()
        val (curStartTime, curEndTime) = times[index++]
        if (minEndTime <= curStartTime){
            pq.poll()
        }else{
            answer+=1
        }
        pq.add(curEndTime)
    }
    println(answer)
}