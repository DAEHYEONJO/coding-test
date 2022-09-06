package math

import java.io.*
import java.lang.Math.pow
import java.util.*
import kotlin.math.pow

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        val(n, b) = readLine().split(" ")
        w.write("${n.toInt(b.toInt())}")
    }

}