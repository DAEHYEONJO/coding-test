package data_structure_2

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        val(n, m) = readLine().split(" ").map{it.toInt()}
        val hm = HashMap<String, Int>(n)
        val array = Array< String>(n){""}
        repeat(n){
            val name = readLine().trim()
            hm[name] = it + 1
            array[it] = name
        }
        repeat(m){
            val quiz = readLine().trim()
            if (quiz[0].isDigit()){
                w.write("${array[quiz.toInt()-1]}\n")
            }else{
                w.write("${hm[quiz]}\n")
            }
        }
        w.flush()
    }
}