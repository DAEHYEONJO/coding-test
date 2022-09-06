package data_structure

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        val n = readLine().toInt()
        val deque = ArrayDeque<Int>()
        repeat(n){
            val line = readLine().split(" ")
            when(line[0]){
                "push_front"->{
                    deque.addFirst(line[1].toInt())
                }
                "push_back"->{
                    deque.addLast(line[1].toInt())
                }
                "pop_front"->{
                    if (deque.isEmpty()) w.write("${-1}\n")
                    else w.write("${deque.pollFirst()}\n")
                }
                "pop_back"->{
                    if (deque.isEmpty()) w.write("${-1}\n")
                    else w.write("${deque.pollLast()}\n")
                }
                "size"->{
                    w.write("${deque.size}\n")
                }
                "empty"->{
                    if (deque.isEmpty()) w.write("${1}\n")
                    else w.write("${0}\n")
                }
                "front"->{
                    if (deque.isEmpty()) w.write("${-1}\n")
                    else w.write("${deque.peekFirst()}\n")
                }
                "back"->{
                    if (deque.isEmpty()) w.write("${-1}\n")
                    else w.write("${deque.peekLast()}\n")
                }
            }
        }
        w.flush()
    }
}