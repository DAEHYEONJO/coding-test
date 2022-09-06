package data_structure_2

import java.io.*
import java.util.*
import kotlin.collections.HashSet

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val t = readLine().toInt()

    repeat(t){
        val n = readLine().toInt()
        val treeMap = TreeMap<Int, Int>()
        repeat(n){
            val(c, i)=readLine().split(" ")
            when(c){
                "I"->{
                    treeMap.run{
                        put(i.toInt(),getOrDefault(i.toInt(),0)+1)
                    }
                }
                "D"->{
                    if (treeMap.isNotEmpty()){
                        val key = if (i=="1") treeMap.lastKey() else treeMap.firstKey()
                        if (treeMap[key] == 1) treeMap.remove(key)
                        else treeMap[key] = treeMap[key]!!.minus(1)
                    }

                }
            }
        }
        if (treeMap.isEmpty()) println("EMPTY")
        else println("${treeMap.lastKey()} ${treeMap.firstKey()}")
    }
}