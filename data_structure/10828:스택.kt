package data_structure

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val n = readLine().toInt()
    val stack = LinkedList<Int>()
    repeat(n){
        val line = readLine().split(" ")

        when(line[0]){
            "push"->{stack.add(line[1].toInt())}
            "pop"->{
                if (stack.isEmpty()) println(-1)
                else println(stack.pollLast())
            }
            "size"->{println(stack.size)}
            "empty"->{
                if (stack.isEmpty()) println(1)
                else println(0)
            }
            "top"->{
                if (stack.isEmpty()) println(-1)
                else println(stack.peekLast())
            }
        }
    }
}