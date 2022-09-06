package data_structure_2

import java.io.*
import java.util.*
import kotlin.math.abs
import kotlin.math.ceil

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        val t = readLine().toInt()
        val minHeap = PriorityQueue<Int>()
        val maxHeap = PriorityQueue<Int>(reverseOrder())
        var standard = 0
        val sb = ArrayList<String>()
        fun calcMedian(value: Int){
            if (minHeap.isEmpty() && maxHeap.isEmpty()){
                maxHeap.add(value)
                standard = value
                return
            }
            if (value < standard){
                maxHeap.add(value)
            }else{
                minHeap.add(value)
            }

            if (minHeap.size > maxHeap.size){
                maxHeap.add(minHeap.poll())
                standard = maxHeap.peek()
            }else if(maxHeap.size > minHeap.size){
                minHeap.add(maxHeap.poll())
                standard = minHeap.peek()
            }
        }
        repeat(t){
            val n = readLine().toInt()
            sb.clear()
            minHeap.clear()
            maxHeap.clear()
            repeat(ceil(n.div(10.0)).toInt()) {
                val line = readLine().split(" ").map{it.toInt()}.toIntArray()
                line.forEachIndexed { index, i ->
                    calcMedian(i)
                    if (index%2 == 0){
                        sb.add(standard.toString())
                    }
                }
            }
            w.write("${sb.size}\n")
            for (index in 1  until sb.size+1){
                w.write("${sb[index-1]} ")
                if (index % 10 == 0 || index == sb.size){
                    w.write("\n")
                    w.flush()
                }
            }
        }
    }
}
