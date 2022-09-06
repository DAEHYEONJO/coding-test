package math

import java.io.*
import kotlin.math.floor
import kotlin.math.sqrt

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val m = readLine().toInt()
    val n = readLine().toInt()
    //m 이상 n 이하의 소수 구하기
    val isPrime = BooleanArray(10001){true}
    isPrime[1] = false

    for (i in 2 until sqrt(10000.0).toInt()+1){
        if (isPrime[i]){
            for (j in i+i until 10001 step i){
                isPrime[j] = false
            }
        }
    }

    var sum = 0
    var min = 10001
    for (i in m .. n){
        if (isPrime[i]) {
            min = min.coerceAtMost(i)
            sum+=i
        }
    }
    if (sum == 0){
        println(-1)
    }else{
        println(sum)
        println(min)
    }
}