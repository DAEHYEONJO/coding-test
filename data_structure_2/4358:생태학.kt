package data_structure_2

import java.io.*
import java.util.*
import kotlin.math.round

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val hm = HashMap<String, Int>()

    var line : String? = null
    var count = 0

    while(readLine().also{line = it}!=null){
        line?.let { hm.run { put(it,getOrDefault(it,1)+1) } }
        count++
    }
    val list = hm.toList().sortedBy { it.first }
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        list.forEach {
            w.write("${it.first} ${String.format("%.4f",it.second/count.toDouble()*100)}\n")
        }
        w.flush()
    }
}