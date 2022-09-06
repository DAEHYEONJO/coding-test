package data_structure

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    BufferedWriter(OutputStreamWriter(System.`out`)).use{
        val (n, k) = readLine().split(" ").map{it.toInt()}
        val list = LinkedList<Int>()
        repeat(n){
            list.add(it+1)
        }
        val result = StringBuilder()
        while(list.isNotEmpty()){
            repeat(k-1){
                val first = list.poll()
                list.add(first)
            }
            result.append("${list.poll()}, ")
        }
        result.run {
            deleteCharAt(lastIndex)
            deleteCharAt(lastIndex)
        }
        it.write("<$result>")
        it.flush()
    }
}