package two_pointer

import java.io.*
import java.util.*
import kotlin.math.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val m = readLine().toInt()
    val a = readLine().trim().split(" ").map{ it.toInt() }.toIntArray().sortedArray()
    var s = 0
    var e = n-1
    var ans = 0
    while(s<e){
        val sum = a[s] + a[e]
        when{
            sum == m ->{
                ans+=1
                s++
            }
            sum < m ->{
                s++
            }
            sum > m ->{
                e--
            }
        }

    }
    println(ans)
}