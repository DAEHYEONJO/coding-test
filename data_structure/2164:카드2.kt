package data_structure

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()

    val queue = LinkedList<Int>()
    repeat(n){queue.add(it+1)}
    while (queue.size!=1){
        queue.poll()
        val pushValue = queue.poll()
        queue.add(pushValue)
    }
    println(queue.poll())
}

