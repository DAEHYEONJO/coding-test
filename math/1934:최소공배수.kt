package math

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        val n = readLine().toInt()
        repeat(n){
            val (a, b) = readLine().split(" ").map{ it.toInt() }
            w.write("${LCM(a,b)}\n")
        }
        w.flush()
    }
}

fun LCM(a: Int, b: Int):Int {
    val gcd = GCD(a,b)
    return a*b/gcd
}

tailrec fun GCD(a: Int, b: Int): Int{
    val remain = a.mod(b)
    if (remain == 0) return b
    return GCD(b, remain)
}