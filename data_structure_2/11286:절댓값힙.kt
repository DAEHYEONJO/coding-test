package data_structure_2

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w->
        val n = readLine().toInt()
        val pq = PriorityQueue<Pair<Int, Boolean>>(compareBy({it.first},{it.second}))//양수: true, 음수: false
        repeat(n){
            val num = readLine().toInt()
            when{
                num>0 ->{pq.add(Pair(kotlin.math.abs(num),true))}
                num<0 ->{pq.add(Pair(kotlin.math.abs(num),false))}
                num==0->{
                    val minPair = pq.poll() ?: Pair(0,true)
                    val num = if (minPair.second) minPair.first else -minPair.first
                    w.write("${num}\n")
                }
            }
        }
        w.flush()
    }

}