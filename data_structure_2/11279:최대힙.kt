package data_structure_2

import java.io.*
import java.util.PriorityQueue

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w->
        val n = readLine().toInt()
        val pq = PriorityQueue<Int>(reverseOrder())
        repeat(n){
            val num = readLine().toInt()
            when{
                num>0 ->{pq.add(num)}
                num==0->{
                    val maxNum = pq.poll() ?: 0
                    w.write("${maxNum}\n")
                }
            }
        }
        w.flush()
    }

}