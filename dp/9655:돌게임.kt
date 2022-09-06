package dp

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    if (n.mod(2)==0){
        println("CY")
    }else{
        println("SK")
    }
}