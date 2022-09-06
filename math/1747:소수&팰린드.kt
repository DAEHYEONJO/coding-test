package math

import java.io.*
import kotlin.math.sqrt

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    var n = readLine().toInt()
    if (n==1) {
        println(2)
        return@with
    }
    fun isPrime(n: Int): Boolean{
        var start = 2
        while (start*start<=n){
            if (n.mod(start)==0) return false
            start+=1
        }
        return true
    }

    fun isPalindrome(n: Int): Boolean{
        val str = n.toString()
        return str.reversed() == str
    }

    while (true){
        if (isPrime(n++) && isPalindrome(n-1)){
            println(n-1)
            break
        }
    }

}