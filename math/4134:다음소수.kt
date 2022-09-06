package math

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w->
        val t = readLine().toInt()
        repeat(t){
            var num = readLine().trim().toLong()
            while (true){
                if (isPrime(num++)){
                    w.write("${num-1L}\n")
                    break
                }
            }


        }
    }
}

fun isPrime(a : Long): Boolean{
    if (a == 0L || a == 1L) return false
    var start = 2L
    while (start*start<=a){
        if (a.mod(start++)==0L) return false
    }
    return true
}
