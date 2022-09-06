package math

import java.io.*
import java.util.*
import kotlin.math.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val nums = readLine().split(" ").map { it.toInt() }.toIntArray().sortedArray()
    val isPrime = BooleanArray(nums.last()+1){true}
    isPrime[1] = false

    for (i in 2 until sqrt(nums.last().toDouble()).toInt()+1){
        if (isPrime[i]){
            for (j in i+i until nums.last()+1 step i){
                isPrime[j] = false
            }
        }
    }

    println(nums.filter { isPrime[it] }.count())
}