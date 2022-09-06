package math

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val (n1, n2) = readLine().split(" ").map{ it.toInt() }
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        val gcd = getGCD(n1, n2)
        w.write("${gcd}\n${n1*n2/gcd}")
    }
}

tailrec fun getGCD(a : Int, b: Int):Int{
    val remain = a.mod(b)
    if (remain == 0) return b
    return getGCD(b, remain)
}