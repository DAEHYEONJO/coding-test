package data_structure_2

import java.io.*
import java.util.*

data class P3(val classify: Int, val pNum: Int, val level: Int)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){

    val n = readLine().toInt()

    val classifyMap = HashMap<Int, TreeSet<P3>>()
    val levelMap = TreeMap<Int, TreeSet<Int>>()
    val numMap = HashMap<Int, P3>()

    fun addProperty(classify: Int, pNum: Int, level: Int){
        val p3 = P3(classify, pNum, level)
        classifyMap.run {
            put(classify, getOrDefault(classify, TreeSet<P3>(compareBy({it.level},{it.pNum}))).apply { add(p3) })
        }
        levelMap.run{
            put(level, getOrDefault(level, TreeSet<Int>()).apply{add(pNum)})
        }
        numMap[pNum] = p3
    }

    repeat(n){
        val (pNum, level, classify) = readLine().split(" ").map{it.toInt()}
        addProperty(classify, pNum, level)
    }
    val m = readLine().toInt()
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w->
        repeat(m){
            val line = readLine().split(" ")

            when(line[0]){
                "add"->{
                    val pNum = line[1].toInt()
                    val level = line[2].toInt()
                    val classify = line[3].toInt()
                    addProperty(classify, pNum, level)
                }
                "recommend"->{
                    val classify = line[1].toInt()
                    when(line[2]){
                        "1"->{classifyMap[classify]?.let { w.write("${it.last().pNum}\n") }}
                        "-1"->{classifyMap[classify]?.let { w.write("${it.first().pNum}\n") }}
                    }
                }
                "recommend2"->{
                    when(line[1]){
                        "1"->{w.write("${levelMap.lastEntry().value.last()}\n")}
                        "-1"->{w.write("${levelMap.firstEntry().value.first()}\n")}
                    }
                }
                "recommend3"->{
                    when(line[1]){
                        "1"->{
                            val filter = levelMap.filterKeys { it>=line[2].toInt() }
                            if (filter.isEmpty()) w.write("-1\n")
                            else w.write("${filter.entries.first().value.first()}\n")
                        }
                        "-1"->{
                            val filter = levelMap.filterKeys { it<line[2].toInt() }
                            if (filter.isEmpty()) w.write("-1\n")
                            else w.write("${filter.entries.last().value.last()}\n")
                        }
                    }
                }
                "solved"->{
                    val p3 = numMap.remove(line[1].toInt())
                    classifyMap[p3!!.classify]!!.remove(p3)
                    if (levelMap[p3.level]!!.size == 1){
                        levelMap.remove(p3.level)
                    }else{
                        levelMap[p3.level]!!.remove(p3.pNum)
                    }

                }
            }
        }
        w.flush()
    }

}