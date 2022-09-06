package greedy

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val k = readLine().toInt()
    val pos = readLine().trim().split(" ").map{ it.toInt() }.toIntArray().sortedArray()
    val diff = IntArray(n-1){0}
    for (i in 1 until n){
        diff[i-1] = pos[i]-pos[i-1]
    }
    diff.sort()
    var result = 0L
    for (i in 0 until n-k){
        result+=diff[i]
    }
    println(result)
}