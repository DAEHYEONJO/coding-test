package dp

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val f = IntArray(21){0}
    f[1] = 1

    val n = readLine().toInt()
    for (i in 2 .. n){
        f[i] = f[i-1] + f[i-2]
    }
    println(f[n])
}