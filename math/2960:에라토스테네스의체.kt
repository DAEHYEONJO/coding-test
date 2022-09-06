package math

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val(n, k) = readLine().split(" ").map{it.toInt()}

    val isPrime = BooleanArray(n+1){true}
    isPrime[1] = false
    var check = 0
    for ( i in 2 until n+1){
        if (isPrime[i]){
            for (j in i until n+1 step i){
                if (isPrime[j]){
                    isPrime[j]=false
                    if (++check == k){
                        println(j)
                        return@with
                    }
                }
            }
        }
    }

}