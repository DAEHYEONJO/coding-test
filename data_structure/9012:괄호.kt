package data_structure

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){

    val n = readLine().toInt()

    repeat(n){
        val line = readLine().trim()
        if (isVPS(line)) println("YES")
        else println("NO")
    }
}

fun isVPS(line: String): Boolean {
    val stack = LinkedList<Char>()
    line.forEach{ c->
        when(c){
            '('->stack.add(c)
            ')'->{
                if (stack.isNotEmpty()) stack.pollLast()
                else return false
            }
        }
    }
    if(stack.isNotEmpty()) return false
    return true
}