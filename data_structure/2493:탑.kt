package data_structure

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        val n = readLine().toInt()
        val tops = readLine().split(" ").map{it.toInt()}.toIntArray()
        //현재 탑 왼쪽에 나보다 큰 숫자가 언제 최초로 등장하는지가 관심시다.
        val stack = Stack<Pair<Int, Int>>()

        tops.forEachIndexed { index, top ->
            if (stack.isEmpty()){
                w.write("0 ")
            }else{
                val stackTop = stack.peek()
                if(stackTop.first > top){
                    w.write("${stackTop.second} ")
                }else{
                    while (stack.isNotEmpty() && stack.peek().first<top){
                        stack.pop()
                    }
                    if (stack.isNotEmpty()){
                        w.write("${stack.peek().second} ")
                    }else{
                        w.write("0 ")
                    }

                }
            }
            stack.push(Pair(top, index+1))
        }
        w.flush()
    }
}