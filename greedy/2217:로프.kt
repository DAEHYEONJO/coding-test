package greedy

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    var n = readLine().toInt()
    val lWeight = IntArray(n){readLine().toInt()}.sortedArray()
    var result = 0
    lWeight.forEach{
        result = result.coerceAtLeast(it*(n--))
    }
    println(result)
}
