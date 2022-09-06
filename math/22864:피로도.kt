package math

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val (a, b, c, m) = readLine().split(" ").map{ it.toInt() }
    var curP = 0
    var churiIll = 0
    var result = 0
    for ( i in 0 until 24){
        if (curP+a>m){
            curP-=c
            if (curP<0) curP = 0
        }
        else if (curP+a<=m){
            curP+=a
            churiIll+=b
        }
        result = result.coerceAtLeast(churiIll)

    }
    println(result)
}