package math

import java.io.*
import java.util.*
import kotlin.math.*

// 두 수 가 서로소다 => 공약수가 1 뿐이다 => 두 수의 최대공약수가 1이다.
fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val nums = readLine().split(" ").map{it.toInt()}.toIntArray()
    val x = readLine().toInt()
    println(nums.filter { getGcd(x, it)==1 }.average())
}

tailrec fun getGcd(a: Int, b: Int): Int{
    val remainder = a.mod(b)
    if (remainder == 0) return b
    return getGcd(b, remainder)
}