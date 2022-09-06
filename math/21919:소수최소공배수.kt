package math

import java.io.*
import java.util.*
import kotlin.math.sqrt

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    var nums = readLine().split(" ").map { it.toInt() }.toSortedSet()
    val isPrime = BooleanArray(nums.last() + 1) { true }
    isPrime[1] = false
    for (i in 2 until sqrt(isPrime.size.toDouble()-1).toInt() + 1) {
        if (isPrime[i]) {
            for (j in i + i until isPrime.size step i) {
                isPrime[j] = false
            }
        }
    }

    val filtered = nums.toList().filter { isPrime[it] }


    if (filtered.isEmpty()) {
        println(-1)
    } else {
        var multiply = 1L
        filtered.forEach {
            multiply *= it.toLong()
        }
        println(multiply)
    }
}

