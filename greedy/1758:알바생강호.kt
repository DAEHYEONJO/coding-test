package greedy

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val tips = IntArray(n){readLine().toInt()}.sortedArrayDescending()
    var result = 0L
    tips.forEachIndexed { index, value ->
        val curTip = (value - index).toLong()
        if (curTip>0) result += curTip
    }
    println(result)
}