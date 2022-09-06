package greedy

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val drinks = readLine().trim().split(" ").map{it.toDouble()}.toDoubleArray().sortedArrayDescending()
    for (i in 1 until n){
        drinks[0]+=drinks[i]/2
    }
    println(drinks.first())
}