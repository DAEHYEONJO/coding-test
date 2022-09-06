package data_structure_2

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val(n, m) = readLine().split(" ").map{it.toInt()}
    val hs = HashSet<String>(n)
    repeat(n){
        hs.add(readLine().trim())
    }
    var count = 0
    repeat(m){
        if (hs.contains(readLine().trim())){
            count++
        }
    }
    println(count)
}