package greedy

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val w = readLine().trim().split(" ").map{ it.toInt() }.toIntArray().sortedArray()

    //s = 1, w[i] = 1 -> 2만들수있.
    //s = 2, w[i] = 1 -> 3만들수있,
    //s = 2, w[i] = 2 -> 1+2, 2+2가능.
    //s = 2, w[i] = 3 -> 1, 2, 3, 1+3, 2+3
    //s = 2, w[i] = 4 -> 1,2,4,1+4,2+4 (3은 못만듬..)
    var sum = 0
    for (i in 0 until n){
        //println("$i ${prefixSum[i]} ${w[i-1]}")
        if (sum+2<=w[i]) {
            break
        }
        sum+=w[i]
    }
    println(sum+1)


}