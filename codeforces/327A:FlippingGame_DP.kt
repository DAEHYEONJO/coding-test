package codeforces

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val st = StringTokenizer(readLine())
    var sum = 0
    val b = IntArray(n){0} // 원본 배열 1 -> -1, 0 -> 1
    repeat(n){
        val num = st.nextToken().toInt()
        sum+=num
        b[it] = if (num==1) -1 else 1
    }
    // maximum sub sum 구하기
    val d = IntArray(n){0}
    //d[i] 는 i 번째 원소가 마지막 으로 포함되는 최대 합
    d[0] = b[0]
    var maxSum = d[0]
    for(i in 1 until n){
        if (d[i-1]+b[i] > b[i]){
            d[i] = d[i-1] + b[i]
        }else{
            d[i] = b[i]
        }
        maxSum = maxSum.coerceAtLeast(d[i])
    }
    println(sum + maxSum)
}