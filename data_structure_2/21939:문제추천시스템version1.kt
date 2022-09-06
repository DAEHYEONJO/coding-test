package data_structure_2

import java.io.*
import java.util.*

data class Problem(val level: Int, val num: Int)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        //난이도, 문제번호
        val n = readLine().toInt()
        val treeSet = TreeSet<Problem>(compareBy({it.level},{it.num}))
        val map = HashMap<Int, Int>()

        repeat(n){
            val(num, level) = readLine().split(" ").map{it.toInt()}
            treeSet.add(Problem(level = level, num = num))
            map[num] = level
        }
        //w.write("$treeSet\n")
        val m = readLine().toInt()
        repeat(m){
            val line = readLine().split(" ")
            when(line[0]){
                "add"->{
                    val num = line[1].toInt()
                    val level = line[2].toInt()
                    treeSet.add(Problem(level = level, num = num))
                    map[num] = level
                    //w.write("add $treeSet\n")
                }
                "recommend"->{
                    when(line[1]){
                        "1"->w.write("${treeSet.last().num}\n")
                        "-1"->w.write("${treeSet.first().num}\n")
                    }
                    //w.write("rec $treeSet\n")
                }
                "solved"->{
                    val level = map.remove(line[1].toInt())
                    treeSet.remove(Problem(level = level!!, num = line[1].toInt()))
                    //w.write("sol $treeSet\n")
                }
            }
        }
        w.flush()
    }

}