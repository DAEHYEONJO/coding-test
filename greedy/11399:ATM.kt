package greedy

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    var n = readLine().toInt()
    val pi = readLine().trim().split(" ").map{it.toInt()}.toIntArray().sortedArray()
    println(pi.fold(0L){ acc, value -> acc+(n--)*value })

}
