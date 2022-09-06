package two_pointer

import java.io.*
import java.lang.StringBuilder
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val (n, m) = readLine().trim().split(" ").map{ it.toInt() }
    val a = readLine().trim().split(" ").map{ it.toInt() }.toIntArray()
    val b = readLine().trim().split(" ").map{ it.toInt() }.toIntArray()
    val sb = StringBuilder()
    var ia = 0
    var ib = 0
    while (ia<n && ib<m){
        sb.append(if (a[ia]<=b[ib]) a[ia++] else b[ib++]).append(" ")
    }
    while (ia<n){
        sb.append(a[ia++]).append(" ")
    }
    while (ib<m){
        sb.append(b[ib++]).append(" ")
    }
    println(sb.toString())
}