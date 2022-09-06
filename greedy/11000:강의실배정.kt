package greedy

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    var n = readLine().toInt()
    val classes = Array<IntArray>(n){readLine().trim().split(" ").map{it.toInt()}.toIntArray()}
        .sortedWith(compareBy({it[0]},{it[1]}))
    var answer = 1
    val times = PriorityQueue<Int>()
    times.add(classes[0][1])
    var index = 1
    while (times.isNotEmpty() && index < n){
        val curMinTime = times.peek()
        if (curMinTime <= classes[index][0]){
            times.poll()
        }else{
            answer+=1
        }
        times.add(classes[index][1])
        index+=1
    }

    println(answer)

}