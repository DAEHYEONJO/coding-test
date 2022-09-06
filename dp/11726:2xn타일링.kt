package dp

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()

    var a = 1
    var a2 = 1
    var a3 = a
    for (i in 2 until n+1){
        a3 = a
        a = a2
        a2 = (a+a3).mod(10007)
    }
    println(a2)
}