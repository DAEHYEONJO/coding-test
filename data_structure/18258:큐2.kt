package data_structure

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w->
        val n = readLine().toInt()
        val queue = LinkedList<Int>()

        repeat(n){
            val line = readLine().split(" ")
            when(line[0]){
                "push"->queue.add(line[1].toInt())
                "pop"->{
                    if (queue.isEmpty()) w.write("${-1}\n")
                    else w.write("${queue.pollFirst()}\n")
                }
                "size"-> w.write("${queue.size}\n")
                "empty"->{
                    if (queue.isEmpty()) w.write("${1}\n")
                    else w.write("${0}\n")
                }
                "front"->{
                    if (queue.isEmpty()) w.write("${-1}\n")
                    else w.write("${queue.peekFirst()}\n")
                }
                "back"->{
                    if (queue.isEmpty()) w.write("${-1}\n")
                    else w.write("${queue.peekLast()}\n")
                }
            }
        }
        w.flush()
    }
    this.close()
}